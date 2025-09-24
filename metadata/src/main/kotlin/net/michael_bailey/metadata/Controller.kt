package net.michael_bailey.metadata



/**
 * Annotation for registering a controller.
 *
 * This annotation should be applied to a class which implements the [IController] interface, or subclasses of it.
 *
 * @property[basePath] The base path for the controller.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class Controller(
	val basePath: String
)
