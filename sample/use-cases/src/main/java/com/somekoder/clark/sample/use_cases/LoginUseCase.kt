package com.somekoder.clark.sample.use_cases

class LoginUseCase(
    private val repository: Repository
) {
    fun execute(username: String, password: String) = repository.login(username, password)
}