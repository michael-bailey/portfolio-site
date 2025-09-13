@file:Project("blog")

package io.github.michael_bailey.spring_blog.service

import io.github.michael_bailey.spring_blog.graphql.types.BlogPost
import io.github.michael_bailey.spring_blog.model.BlogPostModel
import io.github.michael_bailey.spring_blog.repository.BlogRepository
import net.michael_bailey.metadata.Project
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

/**
 * Service for managing blog posts, including retrieval and creation.
 */
@Service
class BlogService(private val repo: BlogRepository) {

    /**
     * Retrieves a blog post by its unique name.
     *
     * @param name the name identifier of the blog post.
     * @return the BlogPostModel if found, or null.
     */
    fun getByName(name: String): BlogPostModel? =
        repo.findByName(name).getOrNull()

    /**
     * Retrieves all blog posts stored in the repository.
     *
     * @return a list of all BlogPostModel instances.
     */
    fun getAllBlogPosts(): List<BlogPostModel> = repo.findAll()

    /**
     * Creates and saves a new blog post with the provided details.
     *
     * @param name the unique name for the blog post.
     * @param title the title of the blog post.
     * @param content the markdown content of the blog post.
     * @return a BlogPost DTO representing the newly created post.
     */
    fun createPost(name: String, title: String, content: String): BlogPost {
        val post = BlogPostModel(
            name = name,
            title = title,
            content = content
        )
        repo.save(post)

        return BlogPost(
            id = post.id.toString(),
            name = name,
            title = title,
            date = post.date.toString(),
            content = content,
            url = post.url.toString()
        )
    }
}
