package ru.mobius.scopestorage

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import ru.mobius.scopestorage.post.add.AddPostFragment
import ru.mobius.scopestorage.post.list.ListOfPostFragment

class MainActivity : AppCompatActivity(), ListOfPostFragment.AddPostButtonListener {

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
        openAddPostFragment()
    }

    private fun openPostsFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            this.replace<ListOfPostFragment>()
        }
    }

    private fun openAddPostFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            this.replace<AddPostFragment>()
            addToBackStack(null)
        }
    }

    private inline fun <reified F : Fragment> FragmentTransaction.replace(
        @IdRes containerViewId: Int = fragmentContainer.id,
        tag: String? = null,
        args: Bundle? = null
    ): FragmentTransaction = replace(containerViewId, F::class.java, args, tag)
}