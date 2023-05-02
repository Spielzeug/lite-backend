package com.sevdesk.lite.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
@Validated
class UserController {

    @Operation(summary = "Get all invoices for user id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved invoices for a user"),
            ApiResponse(responseCode = "204", description = "No invoices found for user"),
            ApiResponse(responseCode = "404", description = "No user with id found")
        ]
    )
    @GetMapping("/{id}")
    fun getAllInvoices(
        @PathVariable("id") id: Int
    ) = when (id) {
        5 -> ResponseEntity.ok(listOf("USER", "ADMIN"))
        else -> ResponseEntity.ok(listOf("USER"))
    }
}
