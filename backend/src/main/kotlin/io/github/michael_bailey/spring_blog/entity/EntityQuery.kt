package io.github.michael_bailey.spring_blog.entity

interface EntityQuery<TEnt> {
	fun execute(): List<TEnt>

	fun first(): TEnt?
	fun tryFirst(): Result<TEnt>
}
