package net.michael_bailey.metadata

@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
annotation class Project(
	val name: String
)