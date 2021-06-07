package com.example.rostik.domain.contracts

class ServiceEntity(
    val name: String,
    val cost: Int,
    val type: String,
    var post: Boolean = false
)