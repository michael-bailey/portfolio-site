@file:OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)

package bitlib.authentication

import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface IViewerContext {

	val subjectId: Uuid?

	val name: String

	val permissionTokens: List<PermissionToken>

	val locale: Locale
	val requestInstant: Instant

}