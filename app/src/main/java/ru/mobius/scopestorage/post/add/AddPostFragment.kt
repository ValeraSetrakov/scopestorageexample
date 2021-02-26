package ru.mobius.scopestorage.post.add

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ru.mobius.scopestorage.R

class AddPostFragment: Fragment(R.layout.fragment_add_post) {

    private lateinit var titleEditText: TextInputLayout
    private lateinit var descriptionEditText: TextInputLayout
    private lateinit var selectImageView: FrameLayout
    private lateinit var createPostButton: Button
    private lateinit var toolbar: Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        titleEditText = view.findViewById(R.id.title_edit_text)
        descriptionEditText = view.findViewById(R.id.description_edit_text)
        selectImageView = view.findViewById(R.id.select_image_container)
        createPostButton = view.findViewById(R.id.add_post_button)

        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }
}