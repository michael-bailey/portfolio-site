@file:Project("privacy")

package io.github.michael_bailey.spring_blog.datafetcher

import com.netflix.graphql.dgs.*
import io.github.michael_bailey.spring_blog.cookie.PreferenceCookieFactory.Companion.PRIVACY_COOKIE_NAME
import io.github.michael_bailey.spring_blog.graphql.types.SelectCookieResult
import io.github.michael_bailey.spring_blog.privacy.PrivacyPreferencesCookie
import io.github.michael_bailey.spring_blog.security.viewer.ViewerContextHolder
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.michael_bailey.metadata.Project

private const val CURRENT_VIEWER_PRIVACY_COOKIE_GQL_ID =
	"current_cookie_preferences"


@DgsComponent
class PrivacyPreferenceDataFetcher(
	private val viewerContextHolder: ViewerContextHolder,
	private val request: HttpServletRequest,
	private val response: HttpServletResponse
) {

	@DgsMutation
	fun selectCookiePreferences(
		@InputArgument result: SelectCookieResult,
	): PrivacyPreferencesCookie {
		val vc = viewerContextHolder.context

		val privacyCookie = vc.privacyPreferences as? PrivacyPreferencesCookie

		if (privacyCookie?.cookiePromptDismissed == true) return privacyCookie

		val cookieData = when (result) {
			SelectCookieResult.Accept -> {
				PrivacyPreferencesCookie(
					cookiePromptDismissed = true,
					allowedDomainLogging = true,
					allowedRequestLogging = true
				)
			}
			SelectCookieResult.Deny -> privacyCookie?.apply {
				PrivacyPreferencesCookie(
					cookiePromptDismissed = true,
					allowedDomainLogging = false,
					allowedRequestLogging = false
				)
			}
		}

		response.addCookie(
			Cookie(PRIVACY_COOKIE_NAME, cookieData?.encode())
		)

		return vc.privacyPreferences as PrivacyPreferencesCookie
	}

	@DgsData(parentType = "PrivacyPreferences")
	fun id(): String {
		return CURRENT_VIEWER_PRIVACY_COOKIE_GQL_ID
	}

	@DgsData(parentType = "PrivacyPreferences")
	fun cookiePromptDismissed(
		dfe: DgsDataFetchingEnvironment,
	): Boolean {
		return dfe.getSource<PrivacyPreferencesCookie>().cookiePromptDismissed
	}

	@DgsData(parentType = "PrivacyPreferences")
	fun allowedDomainLogging(
		dfe: DgsDataFetchingEnvironment,
	): Boolean {
		return dfe.getSource<PrivacyPreferencesCookie>().allowedDomainLogging
	}

	@DgsData(parentType = "PrivacyPreferences")
	fun allowedRequestLogging(
		dfe: DgsDataFetchingEnvironment,
	): Boolean {
		return dfe.getSource<PrivacyPreferencesCookie>().allowedRequestLogging
	}

}