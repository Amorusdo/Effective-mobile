package com.example.common.utils

// app/src/main/java/com/example/app/utils/ErrorExtensions.kt

import android.content.Context
import com.example.common.R
import com.example.domain.error.DomainError


fun DomainError.toMessage(context: Context): String {
    return when (this) {
        is DomainError.InvalidCredentials ->
            context.getString(R.string.error_invalid_credentials)
        is DomainError.EmailAlreadyInUse ->
            context.getString(R.string.error_email_already_in_use)
        is DomainError.WeakPassword ->
            context.getString(R.string.error_weak_password)
        is DomainError.InvalidEmail ->
            context.getString(R.string.error_invalid_email)
        is DomainError.LogoutFailed ->
            context.getString(R.string.error_logout_failed)
        is DomainError.UserDataFetchFailed ->
            context.getString(R.string.error_user_data_fetch_failed)
        is DomainError.EmailEmpty ->
            context.getString(R.string.error_email_empty)
        is DomainError.PasswordEmpty ->
            context.getString(R.string.error_password_empty)
        is DomainError.PasswordTooShort ->
            context.getString(R.string.error_password_too_short)
        is DomainError.PasswordsDoNotMatch ->
            context.getString(R.string.error_passwords_do_not_match)
        is DomainError.NameEmpty ->
            context.getString(R.string.error_name_empty)
        is DomainError.CourseNotFound ->
            context.getString(R.string.error_course_not_found)
        is DomainError.NetworkError ->
            context.getString(R.string.error_network)
        is DomainError.ServerError ->
            context.getString(R.string.error_server)
        is DomainError.Unknown ->
            message ?: context.getString(R.string.error_unknown)
    }
}
fun Throwable.toUserMessage(context: Context): String {
    return if (this is DomainError) {
        this.toMessage(context)
    } else {
        this.message ?: context.getString(R.string.error_unknown)
    }
}