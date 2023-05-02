-- liquibase formatted sql

-- changeset dmitrykochikiyan:1683041559

alter table INVOICES
    add column PREVIOUS_INVOICE_ID BIGINT;