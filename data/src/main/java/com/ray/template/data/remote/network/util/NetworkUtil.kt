package com.ray.template.data.remote.network.util

import com.ray.template.data.mapper.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

fun <T : DataMapper<R>, R : Any> Flow<Response<T>>.convertResponseToDomain(): Flow<R> = map { response ->
    if (response.isSuccessful) {
        return@map response.body()?.toDomain() ?: throw Exception("Response Empty Body")
    }
    // TODO : Error 형식이 주어져 있을 경우, 해당 규격에 맞게 에러 모델 변환
    throw Exception("Internal Server Error (Unexcepted Response)")
}
