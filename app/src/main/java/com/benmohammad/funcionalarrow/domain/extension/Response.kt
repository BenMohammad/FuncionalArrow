package com.benmohammad.funcionalarrow.domain.extension

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import retrofit2.Response


fun <T> Response<T>.toEither(): Either<Exception, T> =
    if(isSuccessful) Right(body()!!) else Left(Exception(errorBody()?.string() ?:  "Unknown Error" ))