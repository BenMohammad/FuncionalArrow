package com.benmohammad.funcionalarrow.domain.usecase

import arrow.core.Either
import com.benmohammad.funcionalarrow.data.remote.GatewayApi
import com.benmohammad.funcionalarrow.domain.converter.EntityConverter
import com.benmohammad.funcionalarrow.domain.extension.toEither
import com.benmohammad.funcionalarrow.domain.model.SystemDetail

internal typealias GetSystemDetailUseCase = suspend () -> Either<Exception, SystemDetail>

internal fun getSystemDetailsUseCaseFactory(
    api : GatewayApi,
    converter : EntityConverter<SystemDetail>
) : GetSystemDetailUseCase = {
    api.getSystemDetails()
        .toEither()
        .map { converter(it)}
}