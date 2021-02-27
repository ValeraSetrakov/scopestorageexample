package ru.mobius.scopestorage.post.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mobius.scopestorage.R
import ru.mobius.scopestorage.post.domain.Post

class PostAdapter(
    private val onPostClick: (post: Post) -> Unit
): RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val posts = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(posts: Collection<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)
        notifyDataSetChanged()
    }

    fun addPost(post: Post) {
        val addResult = this.posts.add(post)
        if (addResult) {
            val newIndex = this.posts.indexOf(post)
            notifyItemInserted(newIndex)
        }
    }

    fun removePost(post: Post): Post {
        val indexOfRemovedPost = this.posts.indexOf(post)
        return removePost(indexOfRemovedPost)
    }

    fun removePost(index: Int): Post {
        val removedPost = this.posts.removeAt(index)
        notifyItemRemoved(index)
        return removedPost
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView = itemView.findViewById<ImageView>(R.id.post_image_view)
        private val titleView = itemView.findViewById<TextView>(R.id.post_title_view)
        private val descriptionView = itemView.findViewById<TextView>(R.id.post_description_view)

        fun bind(post: Post) {
            imageView.setImageURI(post.uri)
            titleView.text = post.title
            descriptionView.text = post.description
            itemView.setOnClickListener{ onPostClick(post) }
        }
    }
}