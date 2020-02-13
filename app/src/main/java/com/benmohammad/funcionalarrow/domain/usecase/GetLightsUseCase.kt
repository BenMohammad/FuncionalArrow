package com.benmohammad.funcionalarrow.domain.usecase

import arrow.core.Either
import com.benmohammad.funcionalarrow.data.remote.GatewayApi
import com.benmohammad.funcionalarrow.domain.converter.EntityConverter
import com.benmohammad.funcionalarrow.domain.extension.toEither
import com.benmohammad.funcionalarrow.domain.model.Light

internal typealias GetLightsUseCase = suspend (String) -> Either<Exception, List<Light>>

internal fun getLightsUseCseFactory(
    api: GatewayApi,
    converter : EntityConverter<List<Light>>
) : GetLightsUseCase = {
    roomId: String ->
    api.getLights(roomId)
        .toEither()
        .map { converter(it)}
}