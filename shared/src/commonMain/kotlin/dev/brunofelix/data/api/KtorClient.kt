package dev.brunofelix.data.api

import dev.brunofelix.BuildKonfig
import dev.brunofelix.data.util.NetworkException
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.io.IOException
import kotlinx.serialization.json.Json

object KtorClient {

    val client = HttpClient {
        expectSuccess = true

        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, _ ->
                when (exception) {
                    is ClientRequestException -> {
                        when (exception.response.status.value) {
                            401 -> throw NetworkException.Unauthorized()
                            404 -> throw NetworkException.NotFound()
                            else -> throw NetworkException.Unknown()
                        }
                    }
                    is ServerResponseException -> throw NetworkException.ServerError()
                    is IOException -> throw NetworkException.NoInternet()
                    else -> throw NetworkException.Unknown()
                }
            }
        }
        defaultRequest {
            url(BuildKonfig.BASE_URL)
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = BuildKonfig.ACCESS_TOKEN,
                        refreshToken = ""
                    )
                }
            }
        }
    }
}