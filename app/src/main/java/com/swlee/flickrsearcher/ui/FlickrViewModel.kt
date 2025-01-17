package com.swlee.flickrsearcher.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swlee.flickrsearcher.model.SearchImageMetadata
import com.swlee.flickrsearcher.repo.ExecuteSearch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlickrViewModel(private val executeSearch: ExecuteSearch) : ViewModel() {

    private val _images = MutableStateFlow<List<SearchImageMetadata>>(emptyList())
    val images: StateFlow<List<SearchImageMetadata>> = _images

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun searchImages(query: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val results = executeSearch.execute(query)
                if (results.isEmpty()) {
                    _error.value = "No images found for the search term \"$query\""
                } else {
                    _images.value = results
                }
            } catch (e: Exception) {
                _error.value = "Error on fetching images: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
}
