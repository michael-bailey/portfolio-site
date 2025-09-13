@file:Project("privacy")

package io.github.michael_bailey.spring_blog.security.viewer

import net.michael_bailey.metadata.Project
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
class ViewerContextHolder {
	lateinit var context: IViewerContext
}