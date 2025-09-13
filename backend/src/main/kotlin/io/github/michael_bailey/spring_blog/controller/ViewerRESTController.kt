@file:Project("privacy")

package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import io.github.michael_bailey.spring_blog.service.AnalyticsService
import net.michael_bailey.metadata.Project
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/viewer")
class ViewerRESTController(
	private val vc: IViewerContext,
	private val analyticsService: AnalyticsService
) {

	@GetMapping("/username")
	fun getViewer(): String = vc.viewer.username

	@GetMapping("/analytics")
	fun getRequestAnalytics(): Map<String, String> {

		return analyticsService.getViewersAnalytics(vc)

	}




}