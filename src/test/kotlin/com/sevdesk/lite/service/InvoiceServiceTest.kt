package com.sevdesk.lite.service

import com.sevdesk.lite.repository.InvoiceRepository
import io.kotest.core.spec.style.ShouldSpec
import org.modelmapper.ModelMapper
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class InvoiceServiceTest(
    @MockBean private val invoiceRepository: InvoiceRepository,
    @MockBean private val modelMapper: ModelMapper
) : ShouldSpec() {
    init {
        should("return all invoices") {

        }
    }
}