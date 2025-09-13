@file:Project("privacy")

package io.github.michael_bailey.spring_blog.privacy.policy

import io.github.michael_bailey.spring_blog.model.RequestAnalyticsModel
import io.github.michael_bailey.spring_blog.privacy.rules.AllowIfRequestCreatedRule
import io.github.michael_bailey.spring_blog.privacy.rules.DenyAllRule
import io.github.michael_bailey.spring_blog.privacy.rules.PrivacyRule
import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import net.michael_bailey.metadata.Project

class RequestAnalyticsPolicy(vc: IViewerContext,
): PrivacyPolicy<RequestAnalyticsModel>(vc = vc) {

	override val queryPolicy: List<PrivacyRule<RequestAnalyticsModel>> = listOf(
		AllowIfRequestCreatedRule(),
		DenyAllRule()
	)

}
