package ru.mobius.scopestorage.post.domain

import android.net.Uri

sealed class Media {
    abstract val uri: Uri

    val isEmpty: Boolean
        get() = uri == Uri.EMPTY
}

data class Image(
    override val uri: Uri
) : Media()

object NonMedia: Media() {
    override val uri: Uri = Uri.EMPTY
}