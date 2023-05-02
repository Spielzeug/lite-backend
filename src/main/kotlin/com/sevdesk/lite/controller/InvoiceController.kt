package com.sevdesk.lite.controller

import com.sevdesk.lite.model.dto.InvoiceDto
import com.sevdesk.lite.service.InvoiceService
import io.swagger.v3.oas.annotations.Hidden
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/invoices")
@Validated
class InvoiceController(
    private val invoiceService: InvoiceService
) {

    @Operation(summary = "Get all invoices")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved invoices"),
            ApiResponse(responseCode = "204", description = "No invoices found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    @GetMapping
    fun getAllInvoices(
        page: Pageable = Pageable.unpaged()
    ) = invoiceService.getAllInvoices().fold(
        {
            if (it.isNotEmpty()) {
                ResponseEntity.ok(it)
            } else {
                ResponseEntity.noContent().build()
            }
        }
    ) { ResponseEntity.internalServerError().build() }


    @Operation(summary = "Get invoice by id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved invoice"),
            ApiResponse(responseCode = "404", description = "Invoice not found"),
            ApiResponse(responseCode = "500", description = "Internal server error")
        ]
    )
    @GetMapping("/{id}")
    fun getInvoice(
        @PathVariable("id") id: Long
    ) = invoiceService.getInvoice(id).fold(
        { ResponseEntity.ok(it) }
    ) {
        ResponseEntity.notFound()
    }


    @Operation(summary = "Add invoice")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully added invoice"),
            ApiResponse(responseCode = "400", description = "Invalid invoice")
        ]
    )
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addInvoice(
        @RequestBody invoice: InvoiceDto
    ) = invoiceService.saveInvoice(invoice).fold(
        { ResponseEntity.ok(it) }
    ) { ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it) }

    @Operation(summary = "Update an invoice")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully updated invoice"),
            ApiResponse(responseCode = "404", description = "Invoice not found")
        ]
    )
    @PutMapping("/{id}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateInvoice(
        @PathVariable id: Long,
        @RequestBody invoice: InvoiceDto
    ) = invoiceService.updateInvoice(id, invoice).fold(
        { ResponseEntity.ok(it) }
    ) { ResponseEntity.notFound().build() }

    @Operation(summary = "Delete invoice")
    @ApiResponse(responseCode = "405", description = "Method is not allowed")
    @DeleteMapping("/{id}")
    @Deprecated(
        "Deleting invoices is no longer allowed", ReplaceWith(
            "Will be removed in later versions"
        )
    )
    @Hidden
    fun deleteInvoice(
        @PathVariable("id") id: Long
    ) = ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)

}
