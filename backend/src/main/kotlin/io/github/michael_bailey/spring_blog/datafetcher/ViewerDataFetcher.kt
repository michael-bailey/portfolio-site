@file:Project("privacy")

package io.github.michael_bailey.spring_blog.datafetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import io.github.michael_bailey.spring_blog.privacy.PrivacyPreferencesCookie
import io.github.michael_bailey.spring_blog.security.viewer.ViewerContext
import io.github.michael_bailey.spring_blog.security.viewer.ViewerContextHolder
import net.michael_bailey.metadata.Project

private const val CURRENT_VIEWER_GQL_ID = "current_viewer"

@DgsComponent
class ViewerDataFetcher(
	private val viewerContextHolder: ViewerContextHolder
) {

	@DgsQuery
	fun viewer(): ViewerContext {
		return viewerContextHolder.context as ViewerContext
	}

	@DgsData(parentType = "ViewerContext")
	fun locale(dfe: DgsDataFetchingEnvironment): String {
		val parent = dfe.getSource<ViewerContext>()
		return parent.locale.toString()
	}

	@DgsData(parentType = "ViewerContext")
	fun privacyPreferences(dfe: DgsDataFetchingEnvironment): PrivacyPreferencesCookie {
		val parent = dfe.getSource<ViewerContext>()
		return parent.privacyPreferences as PrivacyPreferencesCookie
	}

	@DgsData(parentType = "ViewerContext")
	fun id(dfe: DgsDataFetchingEnvironment): String {
		return CURRENT_VIEWER_GQL_ID
	}

}