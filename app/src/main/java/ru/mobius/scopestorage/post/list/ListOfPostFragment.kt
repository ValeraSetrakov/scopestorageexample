package ru.mobius.scopestorage.post.list

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import ru.mobius.scopestorage.R
import ru.mobius.scopestorage.getThemeColor
import ru.mobius.scopestorage.post.domain.Post
import ru.mobius.scopestorage.post.domain.createTestPost

class ListOfPostFragment : Fragment(R.layout.fragment_list_of_post) {

    private val postAdapter = PostAdapter {
        openPostListener?.onPostDetailOpen(it)
    }
    private lateinit var postsListView: RecyclerView
    private lateinit var addPostButton: FloatingActionButton
    private lateinit var reloadPostsView: SwipeRefreshLayout
    private var addPostListener: AddPostButtonListener? = null
    private var openPostListener: OpenPostDetailsListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addPostButton = view.findViewById(R.id.add_post_button)
        reloadPostsView = view.findViewById(R.id.swipe_refresh_layout)
        setupRecyclerView(view)
        addPostListener = requireActivity() as AddPostButtonListener
        openPostListener = requireActivity() as OpenPostDetailsListener
        addPostButton.setOnClickListener(addPostListener)
        reloadPostsView.setOnRefreshListener { reloadPosts() }


        loadPosts()
    }

    private fun setupRecyclerView(view: View) {
        postsListView = view.findViewById(R.id.posts_view)
        postsListView.adapter = postAdapter
        ItemTouchHelper(DeleteSwipeCallback()).attachToRecyclerView(postsListView)
        val offset = resources.getDimensionPixelOffset(R.dimen.screen_padding)
        val dividerColor = requireContext().getThemeColor(R.attr.dividerColor)
        postsListView.addItemDecoration(
            OffsetPostItemDecoration(
                horizontal = offset,
                vertical = offset
            )
        )
        postsListView.addItemDecoration(
            DividerPostItemDecoration(
                color = dividerColor,
                leftOffset = offset
            )
        )
    }

    private fun onRemovedPost(post: Post) {
        viewLifecycleOwner.lifecycleScope.launch {
            asycnhRemovePost(post)
        }
    }

    private fun reloadPosts() {
        viewLifecycleOwner.lifecycleScope.launch {
            val posts = asynchLoadPosts()
            postAdapter.setPosts(posts)
            reloadPostsView.isRefreshing = false
        }
    }

    private fun loadPosts() {
        viewLifecycleOwner.lifecycleScope.launch {
            val posts = asynchLoadPosts()
            postAdapter.setPosts(posts)
        }
    }

    private suspend fun asynchLoadPosts(): Collection<Post> {
        //todo add posts loading logic
        return createTestPosts()
    }

    private suspend fun asycnhRemovePost(post: Post) {
        //todo add removing post logic
    }

    interface AddPostButtonListener : View.OnClickListener

    interface OpenPostDetailsListener {
        fun onPostDetailOpen(post: Post)
    }

    private inner class DeleteSwipeCallback : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val adapterPosition = viewHolder.adapterPosition
            val removedPost = postAdapter.removePost(adapterPosition)
            onRemovedPost(removedPost)
        }
    }

    private class OffsetPostItemDecoration(
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

    private class DividerPostItemDecoration(
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
}

private fun createTestPosts(): Collection<Post> {
    val posts = mutableListOf<Post>()
    repeat(times = 3) {
        val post = createTestPost(it)
        posts.add(post)
    }
    return posts
}