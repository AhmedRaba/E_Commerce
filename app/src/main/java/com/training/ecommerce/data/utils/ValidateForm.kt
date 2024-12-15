package com.training.ecommerce.data.utils

fun validateForm(
    userName: String = "",
    email: String,
    password: String,
    passwordConfirm: String="",
    userNameError: (Boolean) -> Unit = {},
    userNameErrorMessage: (String) -> Unit = {},
    emailError: (Boolean) -> Unit,
    emailErrorMessage: (String) -> Unit,
    passwordError: (Boolean) -> Unit,
    passwordErrorMessage: (String) -> Unit,
    passwordConfirmError: (Boolean) -> Unit = {},
    passwordConfirmErrorMessage: (String) -> Unit = {},
): Boolean {

    var isValid = true

    if (userName.isEmpty()) {
        userNameError(true)
        userNameErrorMessage("Name cannot be empty")
        isValid = false
    } else {
        userNameError(false)
    }

    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        emailError(true)
        emailErrorMessage("Please enter a valid email")
        isValid = false
    } else {
        emailError(false)
    }

    if (password.isEmpty() || password.length < 6) {
        passwordError(true)
        passwordErrorMessage("Password should be at least 6 characters")
        isValid = false
    } else {
        passwordError(false)
    }

    if (password != passwordConfirm) {
        passwordConfirmError(true)
        passwordConfirmErrorMessage("Passwords do not match")
        isValid = false
    } else {
        passwordConfirmError(false)
    }

    return isValid
}
