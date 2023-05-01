package com.sevdesk.lite.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DatasourceConfiguration {
    @Bean
    fun h2DataSource(
        @Value("\${spring.datasource.url}") url: String,
        @Value("\${spring.datasource.username}") username: String,
        @Value("\${spring.datasource.password}") password: String,
        @Value("\${spring.datasource.driver-class-name}") driverClassName: String
    ): DataSource = DataSourceBuilder.create()
        .url(url)
        .username(username)
        .password(password)
        .driverClassName(driverClassName)
        .build()
}