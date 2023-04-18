package com.example.demo.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import all.inner.one.models.*;

@Repository
interface UserRepository: JpaRepository<User,String> {

}