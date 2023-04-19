package com.example.demo.models

data class SimpleVo(val name:String, val age:Int)
data class PreUser(var id:String, var password: String, var nickname: String)
data class ViewToken(val token:String)
data class UserVo(var id:String, var password:String) {
    fun bind():PreUser {
        var user = PreUser(id, password, "aaa");
        return user
    }
}