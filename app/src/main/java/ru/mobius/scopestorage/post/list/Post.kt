package ru.mobius.scopestorage.post.list

import android.net.Uri

data class Post(
    val image: Uri,
    val title: String,
    val description: String
)