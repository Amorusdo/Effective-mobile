package com.example.domain.error

// core/core-domain/src/main/java/com/example/domain/error/DomainError.kt

sealed class DomainError : Exception() {
    // Auth errors
    object InvalidCredentials : DomainError() {
        private fun readResolve(): Any = InvalidCredentials
    }

    object EmailAlreadyInUse : DomainError() {
        private fun readResolve(): Any = EmailAlreadyInUse
    }

    object WeakPassword : DomainError() {
        private fun readResolve(): Any = WeakPassword
    }

    object InvalidEmail : DomainError() {
        private fun readResolve(): Any = InvalidEmail
    }

    object LogoutFailed : DomainError() {
        private fun readResolve(): Any = LogoutFailed
    }

    object UserDataFetchFailed : DomainError() {
        private fun readResolve(): Any = UserDataFetchFailed
    }

    // Course errors
    object CourseNotFound : DomainError() {
        private fun readResolve(): Any = CourseNotFound
    }

    // Network errors
    object NetworkError : DomainError() {
        private fun readResolve(): Any = NetworkError
    }

    object ServerError : DomainError() {
        private fun readResolve(): Any = ServerError
    }

    // General
    data class Unknown(override val message: String?) : DomainError()


    object EmailEmpty : DomainError()
    {
        private fun readResolve(): Any = EmailEmpty
    }

    object PasswordEmpty : DomainError()
    {
        private fun readResolve(): Any = PasswordEmpty
    }

    object PasswordTooShort : DomainError()
    {
        private fun readResolve(): Any = PasswordTooShort
    }
    object PasswordsDoNotMatch : DomainError()
    {
        private fun readResolve(): Any = PasswordsDoNotMatch
    }

    object NameEmpty : DomainError()
    {
        private fun readResolve(): Any = NameEmpty
    }
}
