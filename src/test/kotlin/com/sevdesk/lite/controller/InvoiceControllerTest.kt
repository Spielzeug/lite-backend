package com.sevdesk.lite.controller

import com.sevdesk.lite.model.dto.CustomerDto
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.time.OffsetDateTime

@WebMvcTest(InvoiceController::class)
class InvoiceControllerTest(
    @MockBean private val invoiceService: InvoiceService,
    private val mockMvc: MockMvc
) : ShouldSpec({
    val timestamp = OffsetDateTime.of(LocalDateTime.of(2023, 1, 1, 0, 0), OffsetDateTime.now().offset)
    should("return all invoices") {
        whenever(invoiceService.getAllInvoices()).thenReturn(
            Result.success(
                listOf(
                    InvoiceDto(
                        creationDate = timestamp,
                        dueDate = timestamp.toLocalDate(),
                        invoiceNumber = "1",
                        customerDto = CustomerDto()
                    ),
                    InvoiceDto(
                        creationDate = timestamp,
                        dueDate = timestamp.toLocalDate(),
                        invoiceNumber = "1",
                        customerDto = CustomerDto()
                    ),
                )
            )
        )

        val result = mockMvc.perform(get("/invoices"))
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
            .thenReturn(
                Result.success(
                    InvoiceDto(
                        creationDate = timestamp,
                        dueDate = timestamp.toLocalDate(),
                        invoiceNumber = "1",
                        customerDto = CustomerDto()
                    )
                )
            )

        val result = mockMvc.perform(get("/invoices/1"))
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
                Result.success(
                    InvoiceDto(
                        creationDate = timestamp,
                        dueDate = timestamp.toLocalDate(),
                        invoiceNumber = "1",
                        customerDto = CustomerDto()
                    )
                )
            )

        val result = mockMvc.perform(
            post("/invoices")
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
})
