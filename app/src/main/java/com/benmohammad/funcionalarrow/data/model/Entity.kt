package com.benmohammad.funcionalarrow.data.model

internal data class Entity(
    val properties: Map<String, String>,
    val actions : List<Action>?,
    val entities : List<Entity>
)

data class Action (
    val name : String,
    val href : String,
    val method : String,
    val fields : List<String>
    )