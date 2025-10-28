@file:OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)

package io.github.michael_bailey.spring_blog.authentication

import bitlib.authentication.IViewerContext
import bitlib.authentication.PermissionToken
import java.util.*
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class AnonymousViewerContext(
	override val subjectId: Uuid? = null,
	override val locale: Locale = Locale.getDefault(),
	override val permissionTokens: List<PermissionToken> = emptyList(),
	override val requestInstant: Instant = Clock.System.now(),
): IViewerContext {
	override val name: String = "anonymous"
}
