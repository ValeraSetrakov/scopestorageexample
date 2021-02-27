package ru.mobius.scopestorage

import android.content.Context
import android.content.res.Resources.Theme
import android.net.Uri
import android.util.TypedValue
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.view.isVisible


val Uri.isEmpty: Boolean
    get() = this == Uri.EMPTY

fun ImageView.setImageUriOrGone(uri: Uri) {
    if (uri.isEmpty) {
        isVisible = false
    } else {
        setImageURI(uri)
    }
}

@ColorInt fun Context.getThemeColor(
    @AttrRes attrColor: Int
): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attrColor, typedValue, true)
    return typedValue.data
}