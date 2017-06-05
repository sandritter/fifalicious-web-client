package de.sandritter.fifalicious.web.client.domain.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import de.sandritter.fifalicious.web.client.domain.repository.PlayerRepository
import de.sandritter.fifalicious.web.client.domain.repository.fifaliciousserver.PlayerRepositoryImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.hateoas.hal.Jackson2HalModule
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

import java.time.Clock

import static java.time.Clock.systemDefaultZone
import static org.springframework.hateoas.client.Traverson.getDefaultMessageConverters
import static org.springframework.http.MediaType.APPLICATION_JSON

@Configuration
class BeanConfiguration {

    private static final List<MediaType> MEDIA_TYPES = [MediaType.valueOf('application/hal+json'),
                                                        APPLICATION_JSON]
    private static final String WILDCARD = '*'

    @Bean
    PlayerRepository playerRepository() {
        new PlayerRepositoryImpl()
    }

    @Bean
    RestTemplate restTemplate(@Value('${rest.client.baseUrl}') String baseUrl,
                              @Value('${timeout.connection}') int connectionTimeout,
                              @Value('${timeout.read}') int readTimeout) {
        List<HttpMessageConverter<?>> messageConverters = [] + getDefaultMessageConverters(MEDIA_TYPES)
        messageConverters << halConverterForJson

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(baseUrl)
                .setConnectTimeout(connectionTimeout)
                .setReadTimeout(readTimeout)
                .build()
        restTemplate.messageConverters = messageConverters
        restTemplate
    }

    @Bean
    Clock clock() {
        systemDefaultZone()
    }

    @Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource()
        CorsConfiguration config = new CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin WILDCARD
        config.addAllowedHeader WILDCARD
        config.addAllowedMethod WILDCARD
        source.registerCorsConfiguration '/**', config
        new CorsFilter(source)
    }

    private static HttpMessageConverter<?> getHalConverterForJson() {
        ObjectMapper mapper = new ObjectMapper()
        mapper.registerModule new Jackson2HalModule()
        mapper.configure DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter()

        converter.objectMapper = mapper
        converter.supportedMediaTypes = MEDIA_TYPES
        converter
    }
}
