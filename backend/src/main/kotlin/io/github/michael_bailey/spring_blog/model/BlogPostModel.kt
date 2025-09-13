@file:Project("blog")

package io.github.michael_bailey.spring_blog.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import net.michael_bailey.metadata.Project
import org.apache.catalina.util.URLEncoder
import java.net.URI
import java.nio.charset.Charset
import java.time.LocalDate
import java.util.*

@Entity
data class BlogPostModel(
	@Id val id: UUID = UUID.randomUUID(),

	@Deprecated(message = "Use 'title' instead.")
	val name: String = "",

	val title: String = "",
	val date: LocalDate = LocalDate.now(),

	@Column(columnDefinition = "TEXT")
	val content: String = "",
) {

	val url: URI
		get() = URI.create(
			"/blog/${
				URLEncoder().encode(
					name,
					Charset.defaultCharset()
				)
			}"
		)

}
