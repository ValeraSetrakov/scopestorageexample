package ru.mobius.scopestorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import ru.mobius.scopestorage.post.PostAdapter
import ru.mobius.scopestorage.post.PostsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentContainer: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentContainer = findViewById(R.id.fragment_container_view)
        if (savedInstanceState == null) {
            openPostsFragment()
        }
    }

    private fun openPostsFragment() {
        supportFragmentManager.commit {
            this.add<PostsFragment>(fragmentContainer.id)
        }
    }
}