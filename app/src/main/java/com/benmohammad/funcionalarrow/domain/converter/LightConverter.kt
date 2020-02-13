package com.benmohammad.funcionalarrow.domain.converter

import com.benmohammad.funcionalarrow.domain.model.Light

import java.lang.IllegalStateException

internal val convertToLight : EntityConverter<Light> = {entity ->
    with(entity) {
        Light(
            id = properties["id"]?: throw IllegalStateException("Light id is missing in {$entity}"),
            name = properties["name"]?: "",
            actions = actions?: throw IllegalStateException("Light action missing in {$entity}")

        )
    }
}

internal val convertToLightList : EntityConverter<List<Light>> = {
    entity -> with(entity) {
    entities.orEmpty().map {
        convertToLight(it)
    }
    }
}