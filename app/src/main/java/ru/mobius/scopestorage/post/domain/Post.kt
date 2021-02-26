package ru.mobius.scopestorage.post.domain

import android.net.Uri
import java.io.Serializable

data class Post(
    val image: Uri,
    val title: String,
    val description: String
): Serializable