package ru.mobius.scopestorage.post.add

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import ru.mobius.scopestorage.R
import ru.mobius.scopestorage.post.domain.Media
import ru.mobius.scopestorage.post.domain.NonMedia
import ru.mobius.scopestorage.post.domain.Post
import java.io.File

class AddPostFragment : Fragment(R.layout.fragment_add_post) {

    private lateinit var titleEditText: TextInputLayout
    private lateinit var descriptionEditText: TextInputLayout
    private lateinit var selectImageView: FrameLayout
    private lateinit var selectedImageView: ImageView
    private lateinit var addPostButton: Button
    private lateinit var toolbar: Toolbar
    private lateinit var storageTypeDestinationSelectorView: RadioGroup
    private lateinit var appSpecificStorageTypeDestinationSelectorView: RadioGroup

    private var onPostAddedListener: OnPostAddedListener? = null

    private val isAppSpecificStorageTypeSelect: Boolean
        get() {
            return storageTypeDestinationSelectorView.checkedRadioButtonId == R.id.app_specific_storage_type_rb
        }

    private val isSharedStorageTypeSelect: Boolean
        get() {
            return storageTypeDestinationSelectorView.checkedRadioButtonId == R.id.shared_storage_type_rb
        }

    private val isInternalStorageTypeSelect: Boolean
        get() {
            return isAppSpecificStorageTypeSelect &&
                    appSpecificStorageTypeDestinationSelectorView.checkedRadioButtonId == R.id.internal_rb
        }

    private val isExternalStorageTypeSelect: Boolean
        get() {
            return isAppSpecificStorageTypeSelect &&
                    appSpecificStorageTypeDestinationSelectorView.checkedRadioButtonId == R.id.external_rb
        }

    private var media: Media = NonMedia
        get() {
            return if (isSharedStorageTypeSelect) {
                field
            } else {
                NonMedia
            }
        }

    private val title: String
        get() = titleEditText.editText?.text.toString()

    private val description: String
        get() = descriptionEditText.editText?.text.toString()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        titleEditText = view.findViewById(R.id.title_edit_text)
        descriptionEditText = view.findViewById(R.id.description_edit_text)
        selectImageView = view.findViewById(R.id.select_image_container)
        addPostButton = view.findViewById(R.id.add_post_button)
        storageTypeDestinationSelectorView = view.findViewById(R.id.storage_type_destination_rg)
        appSpecificStorageTypeDestinationSelectorView =
            view.findViewById(R.id.app_specific_type_destination_rg)
        selectedImageView = view.findViewById(R.id.image_view)

        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        addPostButton.setOnClickListener { addPost() }
        selectImageView.setOnClickListener { selectImage() }
        storageTypeDestinationSelectorView.setOnCheckedChangeListener { _, checkedId ->
            appSpecificStorageTypeDestinationSelectorView.isVisible =
                checkedId == R.id.app_specific_storage_type_rb
            selectImageView.isVisible = checkedId == R.id.shared_storage_type_rb
        }

        onPostAddedListener = requireActivity() as? OnPostAddedListener
    }

    private fun addPost() {
        val createdPost = createPost()
        savePost(createdPost)
        onPostAdded(createdPost)
    }

    private fun createPost(): Post {
        return Post(
            title = title,
            description = description,
            media = media
        )
    }

    private fun savePost(post: Post) {
        when {
            isSharedStorageTypeSelect -> {
                savePostToSharedStorage(post)
            }
            isExternalStorageTypeSelect -> {
                savePostToExternalStorage(post)
            }
            isInternalStorageTypeSelect -> {
                savePostToInternalStorage(post)
            }
        }
    }

    private fun savePostToSharedStorage(post: Post) {
        // todo add saving to shared storage
    }

    private fun savePostToInternalStorage(post: Post) {
        val filesDir = requireContext().filesDir
        val postsDir = File(filesDir, POSTS_DIR_NAME)
        if (!postsDir.exists() && !postsDir.mkdir()) {
            error("Failed creation dir for posts")
        }
        val postDir = File(postsDir, post.id)
        if (!postDir.exists() && !postDir.mkdir()) {
            error("Failed creation dir for post $post")
        }
        val postFile = File(postDir, post.title)
        if (!postFile.exists() && !postFile.createNewFile()) {
            error("Failed creation post file $post")
        }
        postFile.writeBytes(post.description.toByteArray())
    }

    private fun savePostToExternalStorage(post: Post) {
        // todo add saving to external storage
    }

    private fun onPostAdded(post: Post) {
        onPostAddedListener?.onPostAdded(post)
    }

    private fun selectImage() {
        //todo add select image logic
    }

    interface OnPostAddedListener {
        fun onPostAdded(post: Post)
    }

    companion object {
        private const val POSTS_DIR_NAME = "posts"
    }
}