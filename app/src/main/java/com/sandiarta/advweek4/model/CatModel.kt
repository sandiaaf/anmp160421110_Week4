package com.sandiarta.advweek4.model

import java.util.Objects

data class Cat(
    val id:String?,
    val name:String?,
    val breed:String?,
    val age:String?,
    val images: List<String>,
    val foods: List<Food>,
    val colors: List<String>
)
data class Food(
    val name:String?,
    val capacity:String
)