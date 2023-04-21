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
interface BoardRepository: JpaRepository<Board,String> {

    @Query(nativeQuery=true,value=
    "select b.id as id, b.user_id as user_id, u.nickname as nickname, b.title as title, b.description as description, b.createdAt as createdAt, b.updatedAt as updatedAt " + 
    "from root_board b inner join root_user u on b.user_id = u.id " +
    "where u.id = :userId")
    fun myBoard(userId: String): List<Map<String,Any>>

}