@file:Project("analytics")

package io.github.michael_bailey.spring_blog.service

import io.github.michael_bailey.spring_blog.model.DomainNameAnalyticsModel
import io.github.michael_bailey.spring_blog.model.RequestAnalyticsModel
import io.github.michael_bailey.spring_blog.model.factories.DomainNameAnalyticsModelFactory
import io.github.michael_bailey.spring_blog.model.factories.RequestAnalyticsModelFactory
import io.github.michael_bailey.spring_blog.privacy.policy.DomainNameAnalyticsPolicy
import io.github.michael_bailey.spring_blog.privacy.policy.RequestAnalyticsPolicy
import io.github.michael_bailey.spring_blog.repository.DomainNameAnalyticsRepository
import io.github.michael_bailey.spring_blog.repository.RequestAnalyticsRepository
import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import net.michael_bailey.metadata.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Service
class AnalyticsService(
	private val clock: Clock,
	private val domainNameAnalyticsRepository: DomainNameAnalyticsRepository,
	private val requestAnalyticsRepository: RequestAnalyticsRepository,
	private val domainNameAnalyticsModelFactory: DomainNameAnalyticsModelFactory,
	private val requestAnalyticsModelFactory: RequestAnalyticsModelFactory,
) {

	val logger: Logger = LoggerFactory.getLogger(this::class.java)

	fun logRequestDomain(requestId: UUID, remoteAddr: String): DomainNameAnalyticsModel? {

		val requestInstant = clock.now()

		logger.info("Creating domain name analytics model for request $requestId and remote address $remoteAddr at $requestInstant")
		val entity = domainNameAnalyticsModelFactory.create(
			requestId = requestId,
			remoteAddr = remoteAddr,
			instant = requestInstant
		)

		logger.info("saving domain name")
		val data = domainNameAnalyticsRepository.save(
			entity
		)

		return data
	}

	fun logRequest(requestId: UUID, method: String, requestURI: String): RequestAnalyticsModel {
		logger.info("Logging request $requestId with method $method and URI $requestURI")

		logger.info("Creating request analytics model for request $requestId and method $method and URI $requestURI")
		val reqAnalytics = requestAnalyticsModelFactory.create(
			requestId = requestId,
			method = method,
			requestURI = requestURI
		)

		logger.info("Saving request analytics model")
		val data = requestAnalyticsRepository.save(
			reqAnalytics
		)

		logger.info("Logged request")

		return data
	}

	fun getViewersAnalytics(vc: IViewerContext): Map<String, String> {
		// TODO: Move policy construction and execution into the repository layer.
		// The repository should return already-sanitised data.
		val domainNamePolicy = DomainNameAnalyticsPolicy(vc)
		val requestPolicy = RequestAnalyticsPolicy(vc)

		val domainAnalytics = domainNameAnalyticsRepository.findByRequestId(vc.requestId)?.let {
			domainNamePolicy.execute(it)
		}

		val requestAnalytics = requestAnalyticsRepository.findByRequestId(vc.requestId)?.let {
			requestPolicy.execute(it)
		}

		return mapOf<String, String>(
			"domain" to (domainAnalytics?.let{ "${it.id}:${it.domainName}" } ?: "null"),
			"request" to (requestAnalytics?.let { "${it.id}:${it.method}:${it.requestURI}" } ?: "null")
		)
	}
}