package com.somekoder.clark.sample.login.ui.login

import androidx.lifecycle.viewModelScope
import com.somekoder.clark.sample.R
import com.somekoder.clark.viewmodel.ClarkViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ClarkViewModel<LoginIntent, LoginState, LoginEffect>() {

    override fun createInitialState(): LoginState {
        return LoginState(isLoading = false)
    }

    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.Login -> {
                login(intent)
            }
        }
    }

    private fun login(intent: LoginIntent.Login) {
        viewModelScope.launch(Dispatchers.IO) {
            setState { copy(isLoading = true) }
            delay(1000)
            if (intent.user == "Bob" && intent.password == "1234") {
                setEffect { LoginEffect.LoginSuccessful }
                setState { copy(isLoading = false) }
            }
            else {
                setEffect { LoginEffect.ShowToast(R.string.login_error) }
                setState { copy(isLoading = false) }
            }
        }
    }

}