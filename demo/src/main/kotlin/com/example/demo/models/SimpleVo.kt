package com.example.demo.models

data class SimpleVo(val name:String, val age:Int)
data class User(var id:String, var password: String, var nickname: String)
data class ViewToken(val token:String)
data class UserVo(var id:String, var password:String) {
    fun bind():User {
        var user = User(id, password, "aaa");
        return user
    }
}