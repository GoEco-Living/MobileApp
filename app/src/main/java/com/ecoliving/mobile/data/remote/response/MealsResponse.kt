package com.ecoliving.mobile.data.remote.response

data class Meals (
    val type: String? = null,
    val userId: Int? = null,
    val predictedEmission: Boolean? = null
)

data class MealsResponse (
    val meals: Meals? = null,
    val message: String? = null
)