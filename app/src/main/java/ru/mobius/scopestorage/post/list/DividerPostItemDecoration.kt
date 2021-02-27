package ru.mobius.scopestorage.post.list

import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView

class DividerPostItemDecoration(
    @ColorInt private val color: Int,
    private val leftOffset: Int
) : RecyclerView.ItemDecoration() {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.color = this@DividerPostItemDecoration.color
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        parent.forEach {
            val startX = parent.x + leftOffset
            val startY = it.y + it.height
            val stopX = parent.width.toFloat()
            val stopY = startY
            c.drawLine(startX, startY, stopX, stopY, paint)
        }
    }
}