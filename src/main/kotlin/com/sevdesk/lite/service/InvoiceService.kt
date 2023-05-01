package com.sevdesk.lite.service

import com.sevdesk.lite.model.dto.InvoiceDto
import com.sevdesk.lite.model.entity.Invoice
import org.springframework.stereotype.Service
import com.sevdesk.lite.repository.InvoiceRepository
import org.modelmapper.ModelMapper
import java.time.OffsetDateTime

@Service
class InvoiceService(
    private val invoiceRepository: InvoiceRepository,
    private val modelMapper: ModelMapper
) {

    fun getAllInvoices(): List<InvoiceDto> = invoiceRepository
        .findAll()
        .map { modelMapper.map(it, InvoiceDto::class.java) }

    fun getInvoice(id: Long): InvoiceDto = modelMapper.map(
        invoiceRepository
            .findById(id)
            .orElseGet { Invoice() },
        InvoiceDto::class.java
    )

    fun saveInvoice(
        invoice: InvoiceDto
    ): InvoiceDto {
        val invoiceEntity = modelMapper.map(invoice, Invoice::class.java)
        invoiceEntity.creationDate = OffsetDateTime.now()

        return modelMapper.map(
            invoiceRepository.save(invoiceEntity),
            InvoiceDto::class.java
        )
    }

    fun deleteById(id: Long) = invoiceRepository.deleteById(id)
}
