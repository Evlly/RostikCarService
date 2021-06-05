package com.example.rostik.domain.contracts

import com.example.rostik.ui.adapter.ServicesAdapter

class ContractEntity(
    val id: Int,
    val date_start: String,
    val date_finish: String,
    val name: String,
    val fio_small: String,
    val services: List<ServiceEntity>
) {
}