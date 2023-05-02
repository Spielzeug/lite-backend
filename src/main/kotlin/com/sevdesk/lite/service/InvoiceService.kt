package com.sevdesk.lite.service

import com.sevdesk.lite.model.dto.InvoiceDto
import com.sevdesk.lite.model.mapper.InvoiceMapper
import com.sevdesk.lite.repository.InvoiceRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class InvoiceService(
    private val invoiceRepository: InvoiceRepository,
    private val invoiceMapper: InvoiceMapper
) {

    fun getAllInvoices(): Result<List<InvoiceDto>> = Result.success(invoiceRepository
        .findAll()
        .map { invoiceMapper.toDto(it) }
    )

    fun getInvoice(id: Long): Result<InvoiceDto> = Result.runCatching {
            invoiceMapper.toDto(invoiceRepository
                .findById(id)
                .orElseThrow { EntityNotFoundException(id.toString()) },
        )
    }

    fun saveInvoice(
        invoice: InvoiceDto
    ): Result<InvoiceDto> {
        val invoiceEntity = invoiceMapper.toEntity(invoice)

        return Result.runCatching {
            invoiceMapper.toDto(
                invoiceRepository.save(invoiceEntity)
            )
        }
    }

    fun updateInvoice(id: Long, invoice: InvoiceDto) =
        Result.runCatching {
            getInvoice(id).fold(
                {
                    val newInvoiceEntity = invoiceMapper.toEntity(invoice)
                    newInvoiceEntity.precedingInvoice = invoiceMapper.toEntity(it)
                    invoiceMapper.toDto(invoiceRepository.save(newInvoiceEntity))
                }
            ) {
                throw it
            }
        }
}
