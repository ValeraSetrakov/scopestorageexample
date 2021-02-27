package ru.mobius.scopestorage.post.domain

import android.net.Uri
import java.io.Serializable
import java.util.*

sealed class Post : Serializable {
    abstract val id: String
    abstract val media: Media
    abstract val title: String
    abstract val description: String

    val uri: Uri
        get() = media.uri
}

data class InternalPost(
    override val id: String,
    override val media: Media,
    override val title: String,
    override val description: String
) : Post()

data class ExternalPost(
    override val id: String,
    override val media: Media,
    override val title: String,
    override val description: String
) : Post()

fun createTestPost(
    index: Int
): Post {
    return InternalPost(
        id = UUID.randomUUID().toString(),
        media = Image(uri = Uri.EMPTY),
        title = "Заголовок $index",
        description = "Описание $index"
    )
}