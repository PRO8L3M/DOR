package com.mobile.droidsOnRoids.ext

import retrofit2.Response
import java.lang.Exception

fun <A: Any> Response<A>.bodyOrException(): A {
    val body = body()
    return if (isSuccessful && body != null) {
        body
    } else throw Exception(message())
}