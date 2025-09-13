@file:Project("analytics")

package io.github.michael_bailey.spring_blog.model.factories

import io.github.michael_bailey.spring_blog.model.DomainNameAnalyticsModel
import net.michael_bailey.metadata.Project
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.net.InetAddress
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.time.toJavaInstant

@Component
@OptIn(ExperimentalTime::class)
class DomainNameAnalyticsModelFactory {

	private val logger = LoggerFactory.getLogger(this::class.java)

	fun create(
		requestId: UUID,
		remoteAddr: String,
		instant: Instant
	): DomainNameAnalyticsModel = DomainNameAnalyticsModel(
		requestId = requestId,
		domainName = getDomainNameFromAddress(remoteAddr),
		instant = instant.toJavaInstant()
	)

	private fun getDomainNameFromAddress(remoteAddr: String): String? {

		logger.info("Attempting to fetching domain name")
		val domainName =  InetAddress.getByName(remoteAddr).hostName

		if (domainName == null) {
			logger.info("No domain name found")
			return null
		}

		logger.info("Domain name found: $domainName")
		return if (domainName != remoteAddr) { domainName } else { null }

	}
}