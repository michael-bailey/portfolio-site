@file:Project("github")

package io.github.michael_bailey.spring_blog.repository

import com.github.client.UserAvatarUrlQuery
import io.github.michael_bailey.spring_blog.config.GraphQLConfig
import net.michael_bailey.metadata.Project
import org.springframework.stereotype.Repository
import org.springframework.web.context.annotation.RequestScope

/**
 * Repository for fetching personal GitHub data using GraphQL.
 * This repository is scoped to handle requests for GitHub user data,
 */
@Repository
@RequestScope
class PersonalGithubDataRepository(
	private val graphQLConfig: GraphQLConfig,
) {

	/**
	 * Fetches my user's avatar URL from GitHub.
	 *
	 * @return The URL string of my GitHub avatar
	 * @throws ClassCastException if the avatar URL cannot be cast to String
	 */
	suspend fun getUserAvatar(): String = graphQLConfig.graphQLClient.query(
		UserAvatarUrlQuery()
	).execute().data?.viewer?.avatarUrl as String


}