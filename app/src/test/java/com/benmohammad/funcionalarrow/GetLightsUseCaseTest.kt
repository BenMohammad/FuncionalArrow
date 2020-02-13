package com.benmohammad.funcionalarrow

import arrow.core.Either
import arrow.core.Either.Right
import com.benmohammad.funcionalarrow.data.model.Entity
import com.benmohammad.funcionalarrow.data.remote.GatewayApi
import com.benmohammad.funcionalarrow.domain.converter.EntityConverter
import com.benmohammad.funcionalarrow.domain.model.Light
import com.benmohammad.funcionalarrow.domain.usecase.getLightsUseCseFactory
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import retrofit2.Response

class GetLightsUseCaseTest {

    private val testEntity = Entity(
        properties = emptyMap(),
        actions = null,
        entities = null
    )

    private val mockApi = mockk<GatewayApi>()

    private val lights = listOf(
        Light(
            id = "1234",
            name = "",
            actions = emptyList()
        )
    )

    private val mockConverter = mockk<EntityConverter<List<Light>>> converter@{
        every { this@converter.invoke(testEntity) } returns lights
    }

    private val useCase = getLightsUseCseFactory(mockApi, mockConverter)

    @Test
    fun `calling use case with response success should return list oflist of lights`() {
        val roomId = "room 1"
        coEvery{ mockApi.getLights(roomId)} returns Response.success(testEntity)

        val result = runBlocking {  useCase(roomId) }

        coVerify { mockApi.getLights(roomId) }
        verifyAll { mockConverter(testEntity) }


        assertThat(result).isInstanceOf(Right::class.java)
        result as Right
        assertThat(result.b).isEqualTo(lights)
    }

}