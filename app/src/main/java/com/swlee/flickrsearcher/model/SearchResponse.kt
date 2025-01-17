package com.swlee.flickrsearcher.model

import com.google.gson.annotations.SerializedName

data class FlickrResponse(
    val title: String,
    val link: String,
    val description: String?,
    val modified: String,
    val generator: String,
    val items: List<FlickrItem>
)

/*
    {
        "title": "IMG_0999",
        "link": "https:\/\/www.flickr.com\/photos\/macspud\/54255022494\/",
        "media": {"m":"https:\/\/live.staticflickr.com\/65535\/54255022494_0ba648ff48_m.jpg"},
        "date_taken": "2025-01-08T11:44:56-08:00",
        "description": " <p><a href=\"https:\/\/www.flickr.com\/people\/macspud\/\">Mac Spud<\/a> posted a photo:<\/p> <p><a href=\"https:\/\/www.flickr.com\/photos\/macspud\/54255022494\/\" title=\"IMG_0999\"><img src=\"https:\/\/live.staticflickr.com\/65535\/54255022494_0ba648ff48_m.jpg\" width=\"240\" height=\"180\" alt=\"IMG_0999\" \/><\/a><\/p> <p>Porcupine<\/p> ",
        "published": "2025-01-08T19:43:32Z",
        "author": "nobody@flickr.com (\"Mac Spud\")",
        "author_id": "28178879@N06",
        "tags": "london zoo porcupine"
    },
*/
data class FlickrItem(
    val title: String,
    val link: String,
    val media: Media,
    val date_taken: String,
    val description: String,
    val published: String,
    val author: String,
    val author_id: String,
    val tags: String
)

/**
 *  Image information such as URL to the image.
 */
data class Media(
    @SerializedName("m") val imageUrl: String
)
