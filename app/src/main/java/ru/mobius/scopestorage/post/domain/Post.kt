package ru.mobius.scopestorage.post.domain

import android.net.Uri
import java.io.Serializable
import java.util.*

data class Post(
    val id: String = UUID.randomUUID().toString(),
    val media: Media = NonMedia,
    val title: String,
    val description: String
) : Serializable {
    val uri: Uri
        get() = media.uri
}

fun createTestPost(
    index: Int
): Post {
    return Post(
        id = UUID.randomUUID().toString(),
        media = Image(uri = Uri.EMPTY),
        title = "Заголовок $index",
        description = "Описание $index"
    )
}