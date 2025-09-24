package net.michael_bailey.metadata

/**
 * Annotation for marking a function as a controller route.
 * Handles POST requests.
 *
 * @property[path] The relative path of the route.
 *
 * @see[Controller]
 */
annotation class PostRoute(val path: String = "/")
