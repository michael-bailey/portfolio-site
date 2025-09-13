@file:Project("blog")

package io.github.michael_bailey.spring_blog.datafetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import io.github.michael_bailey.spring_blog.graphql.types.BlogPost
import io.github.michael_bailey.spring_blog.service.BlogService
import net.michael_bailey.metadata.Project

@DgsComponent
class BlogDataFetcher(
	private val blogService: BlogService,
) {

	@DgsQuery
	fun getBlogPosts(): List<BlogPost> {
		return blogService.getAllBlogPosts().map {
			BlogPost(
				id = it.id.toString(),
				name = it.name,
				title = it.title,
				date = it.date.toString(),
				content = it.content,
				url = it.url.toString()
			)
		}
	}

	@DgsMutation
	fun createBlogPost(
		@InputArgument name: String,
		@InputArgument title: String,
		@InputArgument content: String,
	): BlogPost {
		return blogService.createPost(
			name,
			title,
			content,
		)
	}
}