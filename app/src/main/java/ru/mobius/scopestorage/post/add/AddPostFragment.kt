package ru.mobius.scopestorage.post.add

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import ru.mobius.scopestorage.R
import ru.mobius.scopestorage.post.domain.Post
import ru.mobius.scopestorage.post.domain.createTestPost

class AddPostFragment: Fragment(R.layout.fragment_add_post) {

    private lateinit var titleEditText: TextInputLayout
    private lateinit var descriptionEditText: TextInputLayout
    private lateinit var selectImageView: FrameLayout
    private lateinit var addPostButton: Button
    private lateinit var toolbar: Toolbar

    private var onPostAddedListener: OnPostAddedListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        titleEditText = view.findViewById(R.id.title_edit_text)
        descriptionEditText = view.findViewById(R.id.description_edit_text)
        selectImageView = view.findViewById(R.id.select_image_container)
        addPostButton = view.findViewById(R.id.add_post_button)

        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        addPostButton.setOnClickListener { addPost() }

        onPostAddedListener = requireActivity() as? OnPostAddedListener
    }

    private fun addPost() {
        viewLifecycleOwner.lifecycleScope.launch {
            val createdPost = asynchAddPost()
            onPostAdded(createdPost)
        }
    }

    private suspend fun asynchAddPost(): Post {
        //todo add create post logic
        return createTestPost(0)
    }


    private fun onPostAdded(post: Post) {
        onPostAddedListener?.onPostAdded(post)
    }

    interface OnPostAddedListener {
        fun onPostAdded(post: Post)
    }
}