package da.mobile.as04.ui

data class UiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val fullName: String = "",
    val errorMessage: String = "",
    val isSubmitted: Boolean = false
)
