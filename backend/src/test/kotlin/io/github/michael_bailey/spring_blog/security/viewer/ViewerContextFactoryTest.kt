package io.github.michael_bailey.spring_blog.security.viewer

import io.github.michael_bailey.spring_blog.extension.nullUUID
import io.github.michael_bailey.spring_blog.http.CustomHttpRequest
import io.github.michael_bailey.spring_blog.privacy.PrivacyPreferencesCookie
import io.github.michael_bailey.spring_blog.security.principal.UserPrincipal
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.context.ApplicationContext
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.test.context.ActiveProfiles
import java.util.*
import kotlin.test.assertEquals
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant


@OptIn(ExperimentalTime::class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension::class)
class ViewerContextFactoryTest {

	private val username = "test"
	private val password = "password"

	@Mock
	private lateinit var usernameAuthentication: UsernamePasswordAuthenticationToken

	@Mock
	private lateinit var anonAuthentication: AnonymousAuthenticationToken

	@Mock
	private lateinit var request: CustomHttpRequest

	@Mock
	private lateinit var clock: Clock

	@Mock
	private lateinit var applicationContext: ApplicationContext

	@Test
	fun `Factory creates viewer context from login principal`() {

		`when`(usernameAuthentication.principal).thenReturn(UserPrincipal(
			username = username,
			roles = listOf("ADMIN")
		))

		`when`(request.locale).thenReturn(Locale.ENGLISH)
		`when`(request.requestId).thenReturn(nullUUID().toString())

		`when`(clock.now()).thenReturn(Instant.DISTANT_PAST)

		val factory = ViewerContextFactory()

		val vc = factory.fromAuthentication(
			authentication = usernameAuthentication,
			request = request,
			clock = clock,
			applicationContext = applicationContext,
			privacyPreferences = PrivacyPreferencesCookie()
		)

		assertEquals(Locale.ENGLISH, vc.locale)
		assertEquals(username, vc.viewer.username)

	}

	@Test
	fun `Factory creates viewer context from anonymous principal`() {

		`when`(request.locale).thenReturn(Locale.ENGLISH)
		`when`(request.requestId).thenReturn(nullUUID().toString())

		`when`(clock.now()).thenReturn(Instant.DISTANT_PAST)

		val factory = ViewerContextFactory()

		val vc = factory.fromAuthentication(
			authentication = anonAuthentication,
			request = request,
			clock = clock,
			applicationContext = applicationContext,
			privacyPreferences = PrivacyPreferencesCookie()
		)

		assertEquals(Locale.ENGLISH, vc.locale)
		assertEquals("anonymousUser", vc.viewer.username)
	}

}