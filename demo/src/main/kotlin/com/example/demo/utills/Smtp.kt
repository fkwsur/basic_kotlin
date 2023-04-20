package com.example.demo.utills;

import org.springframework.stereotype.Service;
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Service
class Smtp {

    fun sendEmail(to: String, subject:String, content: String) {
        val props = Properties().apply {
            put("mail.smtp.host", "smtp.gmail.com")
            put("mail.smtp.port", "587")
            put("mail.smtp.auth", "true")
            put("mail.smtp.starttls.enable", "true")
        }

        val session = Session.getInstance(props, object : javax.mail.Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication("본인 email", "본인 password")
            }
        })
    
        try {
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress("email"))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
                setSubject(subject)
                setText(content)
            }
    
            Transport.send(message)
    
        } catch (e: MessagingException) {
            throw RuntimeException(e)
        }
    }
}