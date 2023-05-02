package com.sevdesk.lite.controller

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
class UserControllerTest(
    private val mockMvc: MockMvc
) : ShouldSpec({

    should("return list of USER for user id != 5") {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/2"))
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString shouldBe "[\"USER\"]"
    }

    should("return list of USER, ADMIN for user id == 5") {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/5"))
            .andExpect(status().isOk)
            .andReturn()
            .response
            .contentAsString shouldBe "[\"USER\",\"ADMIN\"]"
    }

})
