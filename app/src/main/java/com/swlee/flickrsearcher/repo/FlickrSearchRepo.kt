package com.swlee.flickrsearcher.repo

import com.swlee.flickrsearcher.model.SearchImageMetadata

interface FlickrSearchRepo {
    suspend fun searchImages(searchTerm: String): List<SearchImageMetadata>
}
