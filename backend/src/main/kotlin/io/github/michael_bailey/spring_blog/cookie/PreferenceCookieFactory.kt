@file:Project("privacy")

package io.github.michael_bailey.spring_blog.cookie

import io.github.michael_bailey.spring_blog.privacy.PrivacyPreferencesCookie
import jakarta.servlet.http.Cookie
import net.michael_bailey.metadata.Project
import org.springframework.stereotype.Component

@Component
class PreferenceCookieFactory {

	companion object {
		const val PRIVACY_COOKIE_NAME: String = "privacy_preferences"
	}

	fun createNoAnalytics(): PrivacyPreferencesCookie {
		return PrivacyPreferencesCookie(
			allowedDomainLogging = false,
			allowedRequestLogging = false
		)
	}

	fun create(
		privacyPreferencesCookie: PrivacyPreferencesCookie = createNoAnalytics(),
	): Cookie {
		return Cookie(
			PRIVACY_COOKIE_NAME,
			privacyPreferencesCookie.encode()
		).apply {
			path = "/"
			isHttpOnly = false
		}
	}

}