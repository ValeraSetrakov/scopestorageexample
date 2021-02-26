package ru.mobius.scopestorage.post

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ru.mobius.scopestorage.R

class PostsFragment: Fragment(R.layout.fragment_posts) {

    private val postAdapter = PostAdapter()
    private lateinit var postsListView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postsListView = view.findViewById(R.id.posts_view)
        postsListView.adapter = this.postAdapter
        this.postAdapter.setPosts(createTestPosts())
    }
}

private fun createTestPosts(): Collection<Post> {
    val posts = mutableListOf<Post>()
    repeat(times = 3) {
        val post = Post(
            image = Uri.EMPTY,
            title = "Заголовок $it",
            description = "Описание к посту под номером $it"
        )
        posts.add(post)
    }
    return posts
}