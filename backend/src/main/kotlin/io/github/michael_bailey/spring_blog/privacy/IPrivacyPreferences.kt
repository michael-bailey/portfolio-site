@file:Project("privacy")

package io.github.michael_bailey.spring_blog.privacy

import net.michael_bailey.metadata.Project

interface IPrivacyPreferences {
	val cookiePromptDismissed: Boolean
	val allowedDomainLogging: Boolean
	val allowedRequestLogging: Boolean
}
