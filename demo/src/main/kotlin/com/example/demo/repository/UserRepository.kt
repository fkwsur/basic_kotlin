package com.example.demo.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.transaction.annotation.Transactional
import com.example.demo.models.*;

@Repository
@EntityScan("com.example.demo.models")
interface UserRepository: JpaRepository<User,String> {

    @Transactional
    @Modifying
    @Query("update User set refreshToken= :refreshToken where id = :userId")
    fun updateUserRefreshToken(userId:String, refreshToken: String):Int

}