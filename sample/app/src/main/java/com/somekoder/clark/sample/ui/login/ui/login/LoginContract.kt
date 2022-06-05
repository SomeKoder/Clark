package com.somekoder.clark.sample.ui.login.ui.login

sealed class LoginIntent {
    data class Login(
        val username: String,
        val password: String
    ) : LoginIntent()
}

data class LoginState(
    val isLoading: Boolean
)

sealed class LoginEffect {
    object LoginSuccessful : LoginEffect()
    data class ShowToast(val stringResource: Int) : LoginEffect()
}