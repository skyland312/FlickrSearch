package com.swlee.flickrsearcher.repo

class ExecuteSearch(private val repository: FlickrSearchRepo) {
    suspend fun execute(searchTerm: String) = repository.searchImages(searchTerm)
}
