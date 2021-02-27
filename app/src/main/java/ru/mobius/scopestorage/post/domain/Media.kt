package ru.mobius.scopestorage.post.domain

import android.net.Uri

sealed class Media {
    abstract val uri: Uri
}

data class Image(
    override val uri: Uri
) : Media()