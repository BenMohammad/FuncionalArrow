package com.benmohammad.funcionalarrow.domain.converter

import com.benmohammad.funcionalarrow.data.model.Entity
import java.lang.IllegalArgumentException


internal typealias EntityConverter<T> = (Entity) -> T

internal enum class ConvertTag {
    LIGHT_LIST,
    SYSTEM_DETAIL
}

internal fun <T> resolveConverter(tag : ConvertTag) : EntityConverter<T> =
    converters[tag] as? EntityConverter<T> ?: throw IllegalArgumentException("No converters for tag")

private val converters: Map<ConvertTag, EntityConverter<*>> = mapOf(
    ConvertTag.LIGHT_LIST to convertToLightList)

