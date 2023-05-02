package com.sevdesk.lite.service

import com.sevdesk.lite.mapper.shouldMatch
import com.sevdesk.lite.model.dto.CustomerDto
import com.sevdesk.lite.model.dto.InvoiceDto
import com.sevdesk.lite.model.entity.Customer
import com.sevdesk.lite.model.entity.Invoice
import com.sevdesk.lite.repository.InvoiceRepository
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.OffsetDateTime
import java.util.*

@SpringBootTest
class InvoiceServiceTest(
    @MockBean private val invoiceRepository: InvoiceRepository,
    private val invoiceService: InvoiceService
) : ShouldSpec({
    val timestamp = OffsetDateTime.now()
    should("return all invoices") {
        val repositoryAnswer = listOf(
            Invoice(
                id = 1,
                creationDate = timestamp,
                dueDate = timestamp.toLocalDate(),
                invoiceNumber = "1",
                customer = Customer()
            ),
            Invoice(
                id = 2,
                creationDate = timestamp,
                dueDate = timestamp.toLocalDate(),
                invoiceNumber = "2",
                customer = Customer()
            )
        )

        whenever(invoiceRepository.findAll()).thenReturn(repositoryAnswer)

        val result = invoiceService.getAllInvoices()

        result.isSuccess shouldBe true
        result.getOrNull() shouldNotBe null
        result.getOrNull() shouldContainExactly listOf(
            InvoiceDto(
                id = 1,
                creationDate = timestamp,
                dueDate = timestamp.toLocalDate(),
                invoiceNumber = "1",
                customerDto = CustomerDto()
            ),
            InvoiceDto(
                id = 2,
                creationDate = timestamp,
                dueDate = timestamp.toLocalDate(),
                invoiceNumber = "2",
                customerDto = CustomerDto()
            )
        )
    }

    should("update an invoice") {
        val repositoryAnswer = Invoice(
            id = 1,
            creationDate = timestamp,
            dueDate = timestamp.toLocalDate(),
            invoiceNumber = "1",
            customer = Customer()
        )

        val savedEntity = Invoice(
            id = 2,
            creationDate = timestamp,
            dueDate = timestamp.toLocalDate(),
            invoiceNumber = "2",
            customer = Customer()
        )

        whenever(invoiceRepository.findById(1)).thenReturn(Optional.of(repositoryAnswer))
        //Some weird interaction with Mockito, the mock does not return an entity needed and leads to failing test
        `when`(invoiceRepository.save(any())).thenReturn(savedEntity)

        val result = invoiceService.updateInvoice(1, InvoiceDto(
            creationDate = timestamp,
            dueDate = timestamp.toLocalDate(),
            invoiceNumber = "2",
            customerDto = CustomerDto()
        ))

        result.isSuccess shouldBe true
        result.getOrNull() shouldNotBe null
        result.getOrNull()!!.shouldMatch(savedEntity)
    }
})