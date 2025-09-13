@file:Project("blog")

package io.github.michael_bailey.spring_blog.repository

import io.github.michael_bailey.spring_blog.model.BlogPostModel
import net.michael_bailey.metadata.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BlogRepository : JpaRepository<BlogPostModel, String> {
	fun findByName(name: String): Optional<BlogPostModel>
}
