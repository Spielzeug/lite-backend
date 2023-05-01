package com.sevdesk.lite.controller

import com.sevdesk.lite.model.dto.InvoiceDto
import com.sevdesk.lite.service.InvoiceService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/invoices")
@Validated
class InvoiceController(
    private val invoiceService: InvoiceService
) {

    @GetMapping
    fun getAllInvoices(
        page: Pageable = Pageable.unpaged()
    ): List<InvoiceDto> = invoiceService.getAllInvoices()


    @GetMapping("/{id}")
    fun getInvoice(
        @PathVariable("id") id: Long
    ): InvoiceDto = invoiceService.getInvoice(id)


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addInvoice(
        @RequestBody invoice: InvoiceDto
    ): InvoiceDto = invoiceService.saveInvoice(invoice)


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun deleteInvoice(
        @PathVariable("id") id: Long
    ) = invoiceService.deleteById(id)

}
