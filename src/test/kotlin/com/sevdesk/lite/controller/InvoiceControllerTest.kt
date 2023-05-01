package com.sevdesk.lite.controller

import com.sevdesk.lite.model.dto.InvoiceDto
import com.sevdesk.lite.service.InvoiceService
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.*
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.io.ClassPathResource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(InvoiceController::class)
class InvoiceControllerTest(
    @MockBean private val invoiceService: InvoiceService,
    private val mockMvc: MockMvc
) : ShouldSpec() {
    init {
        should("return all invoices") {
            whenever(invoiceService.getAllInvoices()).thenReturn(
                listOf(
                    InvoiceDto().apply { id = 1 },
                    InvoiceDto().apply { id = 2 },
                )
            )

            val result = mockMvc.perform(MockMvcRequestBuilders.get("/invoices"))
                .andExpect(status().isOk)
                .andReturn()
            val expected = ClassPathResource("invoices.json")
                .inputStream
                .readBytes()
                .toString(Charsets.UTF_8)

            verify(invoiceService, times(1)).getAllInvoices()
            result.response.contentAsString shouldBe expected
        }

        should("find invoice by id") {
            whenever(invoiceService.getInvoice(1))
                .thenReturn(InvoiceDto().apply { id = 1 })

            val result = mockMvc.perform(MockMvcRequestBuilders.get("/invoices/1"))
                .andExpect(status().isOk)
                .andReturn()
            val expected = ClassPathResource("invoice.json")
                .inputStream
                .readBytes()
                .toString(Charsets.UTF_8)

            verify(invoiceService, times(1)).getInvoice(1)
            result.response.contentAsString shouldBe expected
        }

        should("save an invoice") {
            whenever(
                invoiceService.saveInvoice(
                    any()
                )
            )
                .thenReturn(
                    InvoiceDto().apply { id = 1 })

            val result = mockMvc.perform(
                MockMvcRequestBuilders.post("/invoices")
                    .contentType("application/json")
                    .content(
                        ClassPathResource("invoice.json")
                            .inputStream
                            .readBytes()
                            .toString(Charsets.UTF_8)
                    )
            )
                .andExpect(status().isOk)
                .andReturn()

            val expected = ClassPathResource("invoice.json")
                .inputStream
                .readBytes()
                .toString(Charsets.UTF_8)

            verify(invoiceService, times(1)).saveInvoice(any())
            result.response.contentAsString shouldBe expected
        }

        should("delete an invoice") {
            doNothing().whenever(invoiceService).deleteById(any())

            val result = mockMvc.perform(MockMvcRequestBuilders.delete("/invoices/1"))
                .andExpect(status().isAccepted)
                .andReturn()

            verify(invoiceService, times(1)).deleteById(any())
            result.response.contentAsString shouldBe ""
        }
    }
}