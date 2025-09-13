@file:Project("privacy")

package io.github.michael_bailey.spring_blog.security.viewer

import io.github.michael_bailey.spring_blog.privacy.IPrivacyPreferences
import io.github.michael_bailey.spring_blog.security.principal.IPrincipal
import net.michael_bailey.metadata.Project
import org.springframework.context.ApplicationContext
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
interface IViewerContext {
	val applicationContext: ApplicationContext

	val viewer: IPrincipal
	val locale: Locale
	val requestId: UUID
	val requestTime: Instant
	val privacyPreferences: IPrivacyPreferences
}