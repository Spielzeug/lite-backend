package com.sevdesk.lite.model.dto

import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime

data class InvoiceDto(
    val id: Long? = null,
    val status: String = "OPEN",
    val creationDate: OffsetDateTime,
    val dueDate: LocalDate,
    val invoiceNumber: String,
    val quantity: BigDecimal = 0.toBigDecimal(),
    val priceNet: BigDecimal? = 0.toBigDecimal(),
    val priceGross: BigDecimal? = 0.toBigDecimal(),
    var customerDto: CustomerDto,
    var precedingInvoice: InvoiceDto? = null
)