package dev.brunofelix.data.util

sealed interface NetworkException {
    class Unauthorized : Exception(), NetworkException
    class NotFound : Exception(), NetworkException
    class ServerError : Exception(), NetworkException
    class NoInternet : Exception(), NetworkException
    class Unknown : Exception(), NetworkException
}