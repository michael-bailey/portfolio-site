@file:Project("analytics")

package io.github.michael_bailey.spring_blog.model.factories

import io.github.michael_bailey.spring_blog.model.RequestAnalyticsModel
import net.michael_bailey.metadata.Project
import org.springframework.stereotype.Component
import java.util.*
import kotlin.time.ExperimentalTime

@Component
@OptIn(ExperimentalTime::class)
class RequestAnalyticsModelFactory {

	fun create(
		requestId: UUID,
		method: String,
		requestURI: String
	): RequestAnalyticsModel {
		
		return RequestAnalyticsModel(

			requestId = requestId,
			method = method,
			requestURI = requestURI
		)
	}

}
