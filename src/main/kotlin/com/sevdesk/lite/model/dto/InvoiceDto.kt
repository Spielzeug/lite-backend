package com.sevdesk.lite.model.dto

import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime

class InvoiceDto {
    var id: Long? = null
    var status: String? = null
    var creationDate: OffsetDateTime? = null
    var dueDate: LocalDate? = null
    var invoiceNumber: String? = null
    var quantity: BigDecimal? = null
    var priceNet: BigDecimal? = null
    var priceGross: BigDecimal? = null
    var customer: CustomerDto? = null
}