package com.swlee.flickrsearcher

import com.swlee.flickrsearcher.model.FlickrItem
import com.swlee.flickrsearcher.model.FlickrResponse
import com.swlee.flickrsearcher.model.Media
import com.swlee.flickrsearcher.model.SearchImageMetadata
import com.swlee.flickrsearcher.network.ApiService
import com.swlee.flickrsearcher.repo.FlickrSearchRepoImpl
import com.swlee.flickrsearcher.repo.FlickrSearchRepo
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FlickrSearchRepoImplTest {

    private val apiService: ApiService = mock()
    private val flickrRepository: FlickrSearchRepo = FlickrSearchRepoImpl(apiService)

    @Test
    fun `test searchImages maps API response to flickr data model`() = runTest {
        val mockFlickrResponse = FlickrResponse(
            title = "Flickr Feed Test",
            link = "Link",
            description = null,
            modified = "2025-01-01",
            generator = "Flickr",
            items = listOf(
                FlickrItem(
                    title = "Image Title",
                    link = "Image Link",
                    media = Media("imageUrl"),
                    date_taken = "2025-01-01",
                    description = "width=100, height=200",
                    published = "2025-01-02",
                    author = "Author",
                    author_id = "123456789",
                    tags = "mountain, landscape"
                )
            )
        )

        whenever(apiService.getImagesByTag("mountain")).thenReturn(mockFlickrResponse)

        // When
        val searchResult = flickrRepository.searchImages("mountain")

        // Then
        val expected = listOf(
            SearchImageMetadata(
                title = "Image Title",
                link = "Image Link",
                imageUrl = "imageUrl",
                dateTaken = "2025-01-01",
                description = "width=100, height=200",
                published = "2025-01-02",
                author = "Author",
                tags = "mountain, landscape"
            )
        )
        assertEquals(expected, searchResult)
    }
}
