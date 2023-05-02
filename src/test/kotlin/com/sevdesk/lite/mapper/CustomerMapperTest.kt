package com.sevdesk.lite.mapper

import com.sevdesk.lite.configuration.MapStructConfiguration
import com.sevdesk.lite.model.mapper.CustomerMapper
import io.kotest.core.spec.style.ShouldSpec
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [CustomerMapper::class, MapStructConfiguration::class])
class CustomerMapperTest(private val customerMapper: CustomerMapper) : ShouldSpec({

})