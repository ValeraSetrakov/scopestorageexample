package ru.mobius.scopestorage.post.list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OffsetPostItemDecoration(
    private val left: Int = 0,
    private val top: Int = 0,
    private val right: Int = 0,
    private val bottom: Int = 0,
) : RecyclerView.ItemDecoration() {

    constructor(horizontal: Int = 0, vertical: Int = 0) : this(
        left = horizontal,
        top = vertical,
        right = horizontal,
        bottom = vertical
    )

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(left, top, right, bottom)
    }
}