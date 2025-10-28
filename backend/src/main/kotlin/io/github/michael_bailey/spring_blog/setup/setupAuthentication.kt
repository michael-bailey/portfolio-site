@file:OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)

package io.github.michael_bailey.spring_blog.setup

import io.github.michael_bailey.spring_blog.authentication.AnonymousViewerContext
import io.github.michael_bailey.spring_blog.authentication.SessionCookie
import io.github.michael_bailey.spring_blog.authentication.UserViewerContext
import io.github.michael_bailey.spring_blog.entity.SessionEntityTable
import io.github.michael_bailey.spring_blog.entity.UserEntityTable
import io.github.michael_bailey.spring_blog.plugin.requestScopeKey
import io.github.michael_bailey.spring_blog.scope.AuthenticationScope
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.koin.core.component.inject
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlin.uuid.toJavaUuid
import kotlin.uuid.toKotlinUuid

/**
 * Sets up the application's authentication mechanisms.
 *
 * Please ensure that
 */
fun Application.setupAuthentication() {

	install(Sessions) {
		cookie<SessionCookie>("SESSION") {
			cookie.path = "/"
		}
	}

	install(Authentication) {
		session<SessionCookie> {
			this.validate { session ->
				val requestScope = this.requestScope()

				val authScope: AuthenticationScope by requestScope.inject()

				authScope.scope.linkTo(requestScope.scope)

				val userId = transaction {
					SessionEntityTable.select(SessionEntityTable.user)
						.where(SessionEntityTable.id eq session.id.toJavaUuid())
						.firstOrNull()?.get(SessionEntityTable.user)?.value?.toKotlinUuid()
				}

				if (userId == null) return@validate AnonymousViewerContext()

				val username = transaction {
					UserEntityTable.select(UserEntityTable.username)
						.where(UserEntityTable.id eq userId.toJavaUuid()).firstOrNull()
						?.get(UserEntityTable.username)
				}
				return@validate username?.let {
					UserViewerContext(
						userId, name = it,
					)
				} ?: AnonymousViewerContext()

			}

			challenge {

				if (call.request.cookies["SESSION"] == null) call.sessions.set(
					SessionCookie(
						id = Uuid.random()
					)
				)

				return@challenge call.respondRedirect(this.call.request.path())
			}
		}
	}
}

private fun ApplicationCall.requestScope() = attributes[requestScopeKey]

