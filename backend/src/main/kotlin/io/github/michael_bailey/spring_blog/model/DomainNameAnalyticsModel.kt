@file:Project("analytics")

package io.github.michael_bailey.spring_blog.model

import io.github.michael_bailey.spring_blog.extension.nullUUID
import io.github.michael_bailey.spring_blog.model.converter.StringToUUIDConverter
import io.github.michael_bailey.spring_blog.model.interfaces.IRequestOwnedModel
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import net.michael_bailey.metadata.Project
import java.time.Instant
import java.util.*

@Entity
data class DomainNameAnalyticsModel(
	@Id val id: UUID = UUID.randomUUID(),

	@Convert(StringToUUIDConverter::class)
	override val requestId: UUID = nullUUID(),
	val domainName: String? = null,
	val instant: Instant = Instant.EPOCH,
): IRequestOwnedModel