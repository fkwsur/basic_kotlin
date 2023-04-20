
package com.example.demo.controller

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*
import java.io.ByteArrayInputStream

@Controller
class S3UploadController(
    @Value("\${aws.access_key}") private val accessKey: String,
    @Value("\${aws.secret_key}") private val secretKey: String,
    @Value("\${aws.bucket_name}") private val bucketName: String
) {

    private val s3client: AmazonS3

    init {
        val awsCreds = BasicAWSCredentials(accessKey, secretKey)
        s3client = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.AP_NORTHEAST_2)
            .withCredentials(AWSStaticCredentialsProvider(awsCreds))
            .build()
    }

    @PostMapping("/upload/form")
    @ResponseBody
    fun uploadImage(@RequestParam("image") image: MultipartFile): ResponseEntity<Any> {
        try {
            val filename = image.originalFilename
            val contentType = image.contentType
            val inputStream = image.inputStream

            val metadata = ObjectMetadata()
            metadata.contentType = contentType
            metadata.contentLength = image.size

            val request = PutObjectRequest(bucketName, filename, inputStream, metadata)

            s3client.putObject(request)

            val map = mapOf(
            "result" to true)
            return ResponseEntity(map, HttpStatus.OK)
        } catch (e: IOException) {
            return ResponseEntity("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/upload/base64")
    @ResponseBody
    fun uploadImageBase64(@RequestBody request: Map<String, Any>): ResponseEntity<Any> {    
        try {
            val image = request["image"] as String
            val decodedImage = Base64.getDecoder().decode(image)
            val inputStream = ByteArrayInputStream(decodedImage)
            val s3Object = s3client.putObject(bucketName, UUID.randomUUID().toString(), inputStream, null)
    
            val map = mapOf(
            "result" to true)
            return ResponseEntity(map, HttpStatus.OK)
        } catch (e: IOException) {
            return ResponseEntity("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    
}