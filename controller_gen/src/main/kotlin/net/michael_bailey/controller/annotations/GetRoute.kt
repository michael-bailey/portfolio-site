package net.michael_bailey.controller.annotations

/**
 * Annotation for marking a function as a controller route.
 * Handles GET requests.
 *
 * @property[path] The relative path of the route.
 *
 * @see[Controller]
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class GetRoute(val path: String = "/")
