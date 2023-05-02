package com.sevdesk.lite.configuration

import com.sevdesk.lite.model.mapper.CustomerMapper
import com.sevdesk.lite.model.mapper.InvoiceMapper
import org.mapstruct.factory.Mappers
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MapStructConfiguration {

    @Bean
    fun invoiceMapper() = Mappers.getMapper(InvoiceMapper::class.java)

    @Bean
    fun customerMapper() = Mappers.getMapper(CustomerMapper::class.java)
}