package com.swlee.flickrsearcher.repo

import com.swlee.flickrsearcher.network.ApiService
import com.swlee.flickrsearcher.model.SearchImageMetadata
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickrSearchRepoImpl(
    private val apiService: ApiService
) : FlickrSearchRepo {

    override suspend fun searchImages(searchTerm: String): List<SearchImageMetadata> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getImagesByTag(searchTerm)
                if (response.items.isNotEmpty()) {
                    response.items.map { item ->
                        SearchImageMetadata(
                            title = item.title,
                            link = item.link,
                            imageUrl = item.media.imageUrl,
                            dateTaken = item.date_taken,
                            description = item.description,
                            published = item.published,
                            author = item.author,
                            tags = item.tags
                        )
                    }
                } else {
                    emptyList()  // no images found.
                }
            } catch (e: Exception) {
                emptyList()  // an error occurs.
            }
        }
    }
}
