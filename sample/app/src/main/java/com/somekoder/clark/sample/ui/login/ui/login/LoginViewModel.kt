package com.somekoder.clark.sample.ui.login.ui.login

import androidx.lifecycle.viewModelScope
import com.somekoder.clark.domain.DataState
import com.somekoder.clark.sample.R
import com.somekoder.clark.sample.use_cases.LoginUseCase
import com.somekoder.clark.ui.viewmodel.ClarkViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val loginUseCase: LoginUseCase
) : ClarkViewModel<LoginIntent, LoginState, LoginEffect>() {

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
        setState { copy(isLoading = true) }
        loginUseCase.execute(intent.username, intent.password).onEach {
            when (it) {
                is DataState.Data<*> -> {
                    setState { copy(isLoading = false) }
                    setEffect { LoginEffect.LoginSuccessful }
                }
                is DataState.Error<*> -> {
                    setState { copy(isLoading = false) }
                    setEffect { LoginEffect.ShowToast(R.string.login_error) }
                }
                is DataState.Exception -> {
                    setState { copy(isLoading = false) }
                    setEffect { LoginEffect.ShowToast(R.string.unexpected_error) }
                }
            }
        }.launchIn(viewModelScope)
    }

}