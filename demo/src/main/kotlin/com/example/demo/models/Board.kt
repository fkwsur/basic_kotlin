
package com.example.demo.models

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "root_board")
@EntityListeners(AuditingEntityListener::class)
data class Board(
    @Id
    @Column(name = "id", length = 11, nullable = false)
    var id: String = "",

    @Column(name = "user_id", length = 11, nullable = false)
    var userId: String = "",

    @Column(name = "title", length = 100, nullable = false)
    var title: String = "",

    @Column(name = "desc", length = 500, nullable = false)
    var desc: String = "",

   
    @CreatedDate
    @Column(name = "createdAt", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "updatedAt", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)

data class MyBoard(
    val id: String,
    val user_id: String,
    val nickname: String,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)