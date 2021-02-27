package ru.mobius.scopestorage.post.detail

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.mobius.scopestorage.R
import ru.mobius.scopestorage.post.domain.Post

class DetailPostFragment: Fragment(R.layout.fragment_detail_post) {

    private lateinit var toolbar: Toolbar
    private lateinit var descriptionView: TextView
    private lateinit var imageView: ImageView
    private lateinit var changeButton: Button

    private val post: Post by lazy {
        arguments?.getSerializable(POST_KEY) as Post
    }

    private lateinit var openChangePostScreenListener: ChangePostButtonListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        descriptionView = view.findViewById(R.id.description_view)
        imageView = view.findViewById(R.id.image_view)
        changeButton = view.findViewById(R.id.change_post_button)
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        openChangePostScreenListener = requireActivity() as ChangePostButtonListener
        changeButton.setOnClickListener {
            openChangePostScreenListener.onChangePostOpen(post)
        }

        with(post) {
            toolbar.title = title
            descriptionView.text = description
            imageView.setImageURI(image)
        }
    }

    interface ChangePostButtonListener {
        fun onChangePostOpen(post: Post)
    }

    companion object {
        private const val POST_KEY = "POST_KEY"
        fun createArgs(post: Post): Bundle {
            return bundleOf(POST_KEY to post)
        }
    }
}