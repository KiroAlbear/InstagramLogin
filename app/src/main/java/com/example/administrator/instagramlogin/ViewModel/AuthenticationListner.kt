package com.example.administrator.instagramlogin.ViewModel

interface AuthenticationListner {
    fun OnCodeRecieved(auth_token:String)
    fun DataRecieved(data:MutableMap<String,String>)
}