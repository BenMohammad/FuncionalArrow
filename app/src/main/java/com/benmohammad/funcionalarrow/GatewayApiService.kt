package com.benmohammad.funcionalarrow

import arrow.core.Either
import com.benmohammad.funcionalarrow.data.remote.GatewayApi
import com.benmohammad.funcionalarrow.domain.converter.ConvertTag
import com.benmohammad.funcionalarrow.domain.converter.resolveConverter
import com.benmohammad.funcionalarrow.domain.model.Light
import com.benmohammad.funcionalarrow.domain.model.SystemDetail
import com.benmohammad.funcionalarrow.domain.usecase.GetLightsUseCase
import com.benmohammad.funcionalarrow.domain.usecase.GetSystemDetailUseCase
import com.benmohammad.funcionalarrow.domain.usecase.getLightsUseCseFactory
import com.benmohammad.funcionalarrow.domain.usecase.getSystemDetailsUseCaseFactory

interface GatewayApiService {


    suspend fun getLights(roomId: String): Either<Throwable, List<Light>>

    suspend fun getSystemDetails() : Either<Throwable, SystemDetail>

    companion object {
        fun with(baseUrl: String): GatewayApiService {

            val api =GatewayApi.create(baseUrl)

            val lightsConverter = resolveConverter<List<Light>>(ConvertTag.LIGHT_LIST)

            val systemDetailConverter = resolveConverter<SystemDetail>(ConvertTag.SYSTEM_DETAIL)

            return DefaultGAteWayApiService(
                getLightUseCase = getLightsUseCseFactory(api, lightsConverter),
                getSystemDetailsUseCase = getSystemDetailsUseCaseFactory(api, systemDetailConverter)
            )
        }
    }
}

internal class DefaultGAteWayApiService(
    val getLightUseCase : GetLightsUseCase,
    val getSystemDetailsUseCase : GetSystemDetailUseCase
) : GatewayApiService {
    override suspend fun getLights(roomId: String): Either<Throwable, List<Light>> = getLightUseCase(roomId)

    override suspend fun getSystemDetails(): Either<Throwable, SystemDetail> = getSystemDetailsUseCase()
}
