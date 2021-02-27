package ru.mobius.scopestorage.post.change

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import ru.mobius.scopestorage.R
import ru.mobius.scopestorage.post.domain.Post

class ChangePostFragment: Fragment(R.layout.fragment_change_post) {

    private lateinit var titleEditText: TextInputLayout
    private lateinit var descriptionEditText: TextInputLayout
    private lateinit var selectImageView: FrameLayout
    private lateinit var selectedImageView: ImageView
    private lateinit var changePostButton: Button
    private lateinit var toolbar: Toolbar

    private val post: Post by lazy { arguments?.getSerializable(POST_KEY) as Post }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        titleEditText = view.findViewById(R.id.title_edit_text)
        descriptionEditText = view.findViewById(R.id.description_edit_text)
        selectImageView = view.findViewById(R.id.select_image_container)
        selectedImageView = view.findViewById(R.id.image_view)
        changePostButton = view.findViewById(R.id.change_post_button)
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        changePostButton.setOnClickListener { changePost(post) }

        with(post) {
            titleEditText.editText?.setText(this.title)
            descriptionEditText.editText?.setText(this.description)
            selectedImageView.setImageURI(this.image)
        }
    }

    private fun changePost(post: Post) {
        viewLifecycleOwner.lifecycleScope.launch {
            //todo add change post logic
        }
    }

    companion object {
        private const val POST_KEY = "POST_KEY"
        fun createArguments(post: Post): Bundle {
            return bundleOf(POST_KEY to post)
        }
    }
}