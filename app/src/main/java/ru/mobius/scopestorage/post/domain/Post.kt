package ru.mobius.scopestorage.post.domain

import android.net.Uri
import java.io.Serializable

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