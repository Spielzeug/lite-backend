package com.sevdesk.lite.model.dto

import com.sevdesk.lite.configuration.ModelMapperConfiguration
import com.sevdesk.lite.model.entity.Customer
import com.sevdesk.lite.model.entity.Invoice
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.modelmapper.ModelMapper
import org.springframework.test.context.ContextConfiguration
import java.time.OffsetDateTime

@ContextConfiguration(classes = [ModelMapperConfiguration::class])
class ModelMapperTest(
    private val modelMapper: ModelMapper
) : ShouldSpec() {
    init {
        should("map customer entity to customer dto") {
            val customer = Customer().apply {
                id = 1
                givenname = "John"
                surname = "Doe"
            }
            val expectedCustomerDto = CustomerDto().apply {
                id = 1
                givenname = "John"
                surname = "Doe"
            }

            val mappedCustomerDto = modelMapper.map(customer, CustomerDto::class.java)

            mappedCustomerDto.shouldMatch(expectedCustomerDto)
        }
        should("map customer dto to customer entity") {
            val customerDto = CustomerDto().apply {
                id = 1
                givenname = "John"
                surname = "Doe"
            }
            val expectedEntity = Customer().apply {
                id = 1
                givenname = "John"
                surname = "Doe"
            }

            val mappedCustomerEntity = modelMapper.map(customerDto, Customer::class.java)

            mappedCustomerEntity.shouldMatch(expectedEntity)
        }
        should("map invoice entity to invoice dto") {
            val timestamp =  OffsetDateTime.now()
            val invoice = Invoice().apply {
                id = 1
                invoiceNumber = "1"
                status = "OPEN"
                creationDate = timestamp
                dueDate = timestamp.toLocalDate()
                quantity = 1.toBigDecimal()
                priceNet = 1.toBigDecimal()
                priceGross = 1.toBigDecimal()
                customer = Customer().apply {
                    id = 1
                    givenname = "John"
                    surname = "Doe"
                }
            }
            val expectedInvoiceDto = InvoiceDto().apply {
                id = 1
                invoiceNumber = "1"
                status = "OPEN"
                creationDate = timestamp
                dueDate = timestamp.toLocalDate()
                quantity = 1.toBigDecimal()
                priceNet = 1.toBigDecimal()
                priceGross = 1.toBigDecimal()
                customer = CustomerDto().apply {
                    id = 1
                    givenname = "John"
                    surname = "Doe"
                }
            }

            val mappedInvoiceDto = modelMapper.map(invoice, InvoiceDto::class.java)
            mappedInvoiceDto.shouldMatch(expectedInvoiceDto)
        }
        should("map invoice dto to invoice entity") {
            val timestamp =  OffsetDateTime.now()
            val invoiceDto = InvoiceDto().apply {
                id = 1
                invoiceNumber = "1"
                status = "OPEN"
                creationDate = timestamp
                dueDate = timestamp.toLocalDate()
                quantity = 1.toBigDecimal()
                priceNet = 1.toBigDecimal()
                priceGross = 1.toBigDecimal()
                customer = CustomerDto().apply {
                    id = 1
                    givenname = "John"
                    surname = "Doe"
                }
            }
            val expectedInvoiceEntity = Invoice().apply {
                id = 1
                invoiceNumber = "1"
                status = "OPEN"
                creationDate = timestamp
                dueDate = timestamp.toLocalDate()
                quantity = 1.toBigDecimal()
                priceNet = 1.toBigDecimal()
                priceGross = 1.toBigDecimal()
                customer = Customer().apply {
                    id = 1
                    givenname = "John"
                    surname = "Doe"
                }
            }

            val mappedInvoiceEntity = modelMapper.map(invoiceDto, Invoice::class.java)
            mappedInvoiceEntity.shouldMatch(expectedInvoiceEntity)
        }
    }
}

fun Customer.shouldMatch(other: Customer) {
    id shouldBe other.id
    givenname shouldBe other.givenname
    surname shouldBe other.surname
}

fun CustomerDto.shouldMatch(other: CustomerDto) {
    id shouldBe other.id
    givenname shouldBe other.givenname
    surname shouldBe other.surname
}

fun Invoice.shouldMatch(other: Invoice) {
    id shouldBe other.id
    invoiceNumber shouldBe other.invoiceNumber
    status shouldBe other.status
    creationDate shouldBe other.creationDate
    dueDate shouldBe other.dueDate
    quantity shouldBe other.quantity
    priceNet shouldBe other.priceNet
    priceGross shouldBe other.priceGross
    customer?.shouldMatch(other.customer?: Customer())
}

fun InvoiceDto.shouldMatch(other: InvoiceDto) {
    id shouldBe other.id
    invoiceNumber shouldBe other.invoiceNumber
    status shouldBe other.status
    creationDate shouldBe other.creationDate
    dueDate shouldBe other.dueDate
    quantity shouldBe other.quantity
    priceNet shouldBe other.priceNet
    priceGross shouldBe other.priceGross
    customer?.shouldMatch(other.customer?: CustomerDto())
}
