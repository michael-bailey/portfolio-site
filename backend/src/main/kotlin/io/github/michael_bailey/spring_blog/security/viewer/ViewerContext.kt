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
data class ViewerContext(
	override val applicationContext: ApplicationContext,

	override val viewer: IPrincipal,
	override val locale: Locale,
	override val requestId: UUID,
	override val requestTime: Instant,
	override val privacyPreferences: IPrivacyPreferences
): IViewerContext
