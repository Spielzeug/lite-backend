package com.sevdesk.lite.model.mapper

import com.sevdesk.lite.model.dto.InvoiceDto
import com.sevdesk.lite.model.entity.Invoice
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(uses = [CustomerMapper::class])
interface InvoiceMapper {
    @Mappings(
        Mapping(source = "customer.id", target = "customerDto.id"),
        Mapping(source = "customer.givenname", target = "customerDto.givenname"),
        Mapping(source = "customer.surname", target = "customerDto.surname")

    )
    fun toDto(entity: Invoice): InvoiceDto

    @InheritInverseConfiguration
    fun toEntity(dto: InvoiceDto): Invoice

    fun toDtoList(entities: List<Invoice>): List<InvoiceDto>

    fun toEntityList(dtos: List<InvoiceDto>): List<Invoice>
}