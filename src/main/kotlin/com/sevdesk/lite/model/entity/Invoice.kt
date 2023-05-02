package com.sevdesk.lite.model.entity

import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "INVOICES")
data class Invoice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(name = "status", length = 50)
    val status: String = "OPEN",
    @Column(name = "creation_date")
    val creationDate: OffsetDateTime,
    @Column(name = "due_date")
    val dueDate: LocalDate,
    @Column(name = "invoice_number")
    val invoiceNumber: String,
    @Column(name = "quantity")
    val quantity: BigDecimal = 0.toBigDecimal(),
    @Column(name = "price_net")
    val priceNet: BigDecimal = 0.toBigDecimal(),
    @Column(name = "price_gross")
    val priceGross: BigDecimal = 0.toBigDecimal(),
    @ManyToOne(cascade = [CascadeType.ALL])
    var customer: Customer,
    @OneToOne
    @JoinColumn(name = "previous_invoice_id")
    var precedingInvoice: Invoice? = null

)
