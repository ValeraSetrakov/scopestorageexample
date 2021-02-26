package ru.mobius.scopestorage.post.list

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.mobius.scopestorage.R
import ru.mobius.scopestorage.post.domain.Post

class ListOfPostFragment: Fragment(R.layout.fragment_list_of_post) {

    private val postAdapter = PostAdapter()
    private lateinit var postsListView: RecyclerView
    private lateinit var addPostButton: FloatingActionButton
    private var addPostListener: AddPostButtonListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addPostButton = view.findViewById(R.id.add_post_button)
        postsListView = view.findViewById(R.id.posts_view)
        postsListView.adapter = postAdapter
        this.postAdapter.setPosts(createTestPosts())
        addPostListener = requireActivity() as AddPostButtonListener
        addPostButton.setOnClickListener(addPostListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        addPostListener = null
    }

    interface AddPostButtonListener: View.OnClickListener
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