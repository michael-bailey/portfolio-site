@file:OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)

package io.github.michael_bailey.spring_blog.authentication

import bitlib.authentication.IViewerContext
import bitlib.authentication.PermissionToken
import java.util.*
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class UserViewerContext(
	override val subjectId: Uuid,
	override val name: String,
	override val locale: Locale = Locale.getDefault(),
	override val requestInstant: Instant = Clock.System.now(),
	override val permissionTokens: List<PermissionToken> = emptyList(),
): IViewerContext
