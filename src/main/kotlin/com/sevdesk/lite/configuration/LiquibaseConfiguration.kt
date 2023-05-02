package com.sevdesk.lite.configuration

import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class LiquibaseConfiguration(
    private val dataSourceBean: DataSource
) {

    @Bean
    fun liquibase(
        @Value("\${spring.liquibase.change-log}") changeLog: String,
    ) = SpringLiquibase().apply {
        this.dataSource = dataSourceBean
        this.changeLog = changeLog
    }
}