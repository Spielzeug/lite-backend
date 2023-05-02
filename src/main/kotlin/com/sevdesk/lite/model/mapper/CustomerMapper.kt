package com.sevdesk.lite.model.mapper

import com.sevdesk.lite.model.dto.CustomerDto
import com.sevdesk.lite.model.entity.Customer
import org.mapstruct.Mapper

@Mapper
interface CustomerMapper {
    fun toDto(customer: Customer): CustomerDto
    fun toEntity(customerDto: CustomerDto): Customer
}