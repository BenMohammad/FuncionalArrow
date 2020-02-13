package com.benmohammad.funcionalarrow.domain.model

import com.benmohammad.funcionalarrow.data.model.Action

data class Light(
  val id : String,
  val name : String,
  val actions : List<Action>
)