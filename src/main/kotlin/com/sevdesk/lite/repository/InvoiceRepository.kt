package com.sevdesk.lite.repository

import com.sevdesk.lite.model.entity.Invoice
import org.springframework.data.repository.CrudRepository

interface InvoiceRepository : CrudRepository<Invoice, Long>
