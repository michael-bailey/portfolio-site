@file:Project("analytics")

package io.github.michael_bailey.spring_blog.repository

import io.github.michael_bailey.spring_blog.model.RequestAnalyticsModel
import net.michael_bailey.metadata.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RequestAnalyticsRepository: JpaRepository<RequestAnalyticsModel, String> {
	fun findByRequestId(requestId: UUID): RequestAnalyticsModel?
}
