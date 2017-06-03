package de.sandritter.fifalicious.web.client.domain.config

import de.sandritter.fifalicious.web.client.domain.repository.PlayerRepository
import de.sandritter.fifalicious.web.client.domain.repository.fifaliciousserver.PlayerRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {

    @Bean
    PlayerRepository playerRepository() {
        new PlayerRepositoryImpl()
    }
}
