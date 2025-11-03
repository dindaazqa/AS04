package da.mobile.as04.ui

import androidx.lifecycle.ViewModel
import da.mobile.as04.ui.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun onFirstNameChange(newValue: String) {
        _uiState.update { it.copy(firstName = newValue, errorMessage = "") }
    }

    fun onLastNameChange(newValue: String) {
        _uiState.update { it.copy(lastName = newValue, errorMessage = "") }
    }

    fun onEmailChange(newValue: String) {
        _uiState.update { it.copy(email = newValue, errorMessage = "") }
    }

    fun onPasswordChange(newValue: String) {
        _uiState.update { it.copy(password = newValue, errorMessage = "") }
    }

    fun onSubmit() {
        val current = _uiState.value

        if (current.firstName.isBlank() || current.lastName.isBlank() ||
            current.email.isBlank() || current.password.isBlank()
        ) {
            _uiState.update {
                it.copy(
                    errorMessage = "Semua field harus diisi!",
                    isSubmitted = false
                )
            }
        } else if (!current.email.contains("@")) {
            _uiState.update {
                it.copy(
                    errorMessage = "Format email tidak valid!",
                    isSubmitted = false
                )
            }
        } else if (current.password.length < 6) {
            _uiState.update {
                it.copy(
                    errorMessage = "Password minimal 6 karakter!",
                    isSubmitted = false
                )
            }
        } else {
            val full = "${current.firstName} ${current.lastName}"
            _uiState.update {
                it.copy(
                    fullName = full,
                    isSubmitted = true,
                    errorMessage = ""
                )
            }
        }
    }

    fun reset() {
        _uiState.value = UiState()
    }
}
