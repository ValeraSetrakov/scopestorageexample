package ru.mobius.scopestorage

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import ru.mobius.scopestorage.post.add.AddPostFragment
import ru.mobius.scopestorage.post.change.ChangePostFragment
import ru.mobius.scopestorage.post.detail.DetailPostFragment
import ru.mobius.scopestorage.post.domain.Post
import ru.mobius.scopestorage.post.list.ListOfPostFragment

class MainActivity : AppCompatActivity(), ListOfPostFragment.AddPostButtonListener,
    ListOfPostFragment.OpenPostDetailsListener, DetailPostFragment.ChangePostButtonListener, AddPostFragment.OnPostAddedListener {

    private lateinit var fragmentContainer: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentContainer = findViewById(R.id.fragment_container_view)
        if (savedInstanceState == null) {
            openPostsFragment()
        }
    }

    override fun onClick(v: View?) {
        openAddPostScreen()
    }

    override fun onPostDetailOpen(post: Post) {
        openPostDetailScreen(post)
    }

    override fun onChangePostOpen(post: Post) {
        openChangePostScreen(post)
    }

    override fun onPostAdded(post: Post) {

    }

    private fun openPostsFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            this.replace<ListOfPostFragment>()
        }
    }

    private fun openAddPostScreen() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            this.replace<AddPostFragment>()
            addToBackStack(null)
        }
    }

    private fun openPostDetailScreen(post: Post) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val arguments = DetailPostFragment.createArgs(post)
            this.replace<DetailPostFragment>(args = arguments)
            addToBackStack(null)
        }
    }

    private fun openChangePostScreen(post: Post) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            val arguments = ChangePostFragment.createArguments(post)
            this.replace<ChangePostFragment>(args = arguments)
            addToBackStack(null)
        }
    }

    private inline fun <reified F : Fragment> FragmentTransaction.replace(
        @IdRes containerViewId: Int = fragmentContainer.id,
        tag: String? = null,
        args: Bundle? = null
    ): FragmentTransaction = replace(containerViewId, F::class.java, args, tag)
}