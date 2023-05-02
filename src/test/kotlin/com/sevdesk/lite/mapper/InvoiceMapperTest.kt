package com.sevdesk.lite.mapper

import com.sevdesk.lite.configuration.MapStructConfiguration
import com.sevdesk.lite.model.dto.CustomerDto
import com.sevdesk.lite.model.dto.InvoiceDto
import com.sevdesk.lite.model.entity.Customer
import com.sevdesk.lite.model.entity.Invoice
import com.sevdesk.lite.model.mapper.InvoiceMapper
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import java.time.OffsetDateTime

@SpringBootTest(classes = [InvoiceMapper::class, MapStructConfiguration::class])
class InvoiceMapperTest(private val invoiceMapper: InvoiceMapper) : ShouldSpec({
    should("map invoice to dto") {
        val invoice = Invoice(
            id = 1,
            creationDate = OffsetDateTime.now(),
            dueDate = OffsetDateTime.now().toLocalDate(),
            invoiceNumber = "1",
            customer = Customer(
                id = 1,
                givenname = "John",
                surname = "Doe"
            )
        )

        val invoiceDto = invoiceMapper.toDto(invoice)

        invoiceDto shouldNotBe null
        invoiceDto.shouldMatch(invoice)
    }
    should("map dto to invoice") {
        val invoiceDto = InvoiceDto(
            id = 1,
            creationDate = OffsetDateTime.now(),
            dueDate = OffsetDateTime.now().toLocalDate(),
            invoiceNumber = "1",
            customerDto = CustomerDto(
                id = 1,
                givenname = "John",
                surname = "Doe"
            )
        )

        val invoice = invoiceMapper.toEntity(invoiceDto)

        invoice shouldNotBe null
        invoice.shouldMatch(invoiceDto)
    }
})

fun Invoice.shouldMatch(invoiceDto: InvoiceDto) {
    invoiceDto.id shouldBe id
    invoiceDto.creationDate shouldBe creationDate
    invoiceDto.dueDate shouldBe dueDate
    invoiceDto.invoiceNumber shouldBe invoiceNumber
    invoiceDto.customerDto.shouldMatch(customer)
}

fun CustomerDto.shouldMatch(other: Customer) {
    other.id shouldBe id
    other.givenname shouldBe givenname
    other.surname shouldBe surname
}

fun InvoiceDto.shouldMatch(invoice: Invoice) {
    invoice.id shouldBe id
    invoice.creationDate shouldBe creationDate
    invoice.dueDate shouldBe dueDate
    invoice.invoiceNumber shouldBe invoiceNumber
    invoice.customer.shouldMatch(customerDto)
}

fun Customer.shouldMatch(other: CustomerDto) {
    other.id shouldBe id
    other.givenname shouldBe givenname
    other.surname shouldBe surname
}