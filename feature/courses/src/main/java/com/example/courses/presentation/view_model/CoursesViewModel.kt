import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courses.domain.repository.CoursesRepository
import com.example.courses.presentation.model.CoursesUiState
import com.example.domain.Course
import com.example.domain.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CoursesViewModel(
    private val repository: CoursesRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CoursesUiState())
    val uiState: StateFlow<CoursesUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            favoritesRepository.getFavorites().collect { favorites ->
                val favoriteIds = favorites.map { it.id }.toSet()
                _uiState.update { state ->
                    state.copy(
                        courses = state.courses.map { course ->
                            course.copy(isFavorite = course.id in favoriteIds)
                        },
                        filteredCourses = state.filteredCourses.map { course ->
                            course.copy(isFavorite = course.id in favoriteIds)
                        }
                    )
                }
            }
        }
    }

    fun loadCourses() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            repository.getCourses()
                .onSuccess { courses ->
                    _uiState.update {
                        it.copy(
                            courses = courses,
                            filteredCourses = courses,
                            isLoading = false,
                            isSortedByDate = false
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Ошибка загрузки"
                        )
                    }
                }
        }
    }

    fun onSearchChange(query: String) {
        _uiState.update { state ->
            val filtered = if (query.isBlank()) {
                state.courses
            } else {
                state.courses.filter { course ->
                    course.title.contains(query, ignoreCase = true) ||
                            course.description.contains(query, ignoreCase = true)
                }
            }

            state.copy(
                searchQuery = query,
                filteredCourses = filtered
            )
        }
    }

    fun toggleSort() {
        val currentCourses = _uiState.value.filteredCourses
        val sorted = if (_uiState.value.isSortedByDate) {
            // Вернуть к исходному порядку
            currentCourses.sortedBy { it.id }
        } else {
            // Сортировка по publishDate (убывание)
            currentCourses.sortedByDescending { course ->
                try {
                    LocalDateTime.parse(course.publishDate, DateTimeFormatter.ISO_DATE_TIME)
                } catch (_: Exception) {
                    LocalDateTime.MIN
                }
            }
        }

        _uiState.update {
            it.copy(
                filteredCourses = sorted,
                isSortedByDate = !it.isSortedByDate
            )
        }
    }

    fun toggleFavorite(course: Course) {
        viewModelScope.launch {
            favoritesRepository.toggleFavorite(course)
        }
    }
}