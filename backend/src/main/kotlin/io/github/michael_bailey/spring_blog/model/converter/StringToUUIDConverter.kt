@file:Project("infra")

package io.github.michael_bailey.spring_blog.model.converter

import jakarta.persistence.AttributeConverter
import net.michael_bailey.metadata.Project
import java.util.*

class StringToUUIDConverter(): AttributeConverter<UUID, String> {
	override fun convertToDatabaseColumn(attribute: UUID?): String? {
		return attribute?.toString()
	}

	override fun convertToEntityAttribute(dbData: String?): UUID? {
		return dbData?.let { UUID.fromString(it) }
	}

}
