@file:Project("infra")

package io.github.michael_bailey.spring_blog.model.interfaces

import net.michael_bailey.metadata.Project
import java.util.*

interface IRequestOwnedModel {
	val requestId: UUID
}
