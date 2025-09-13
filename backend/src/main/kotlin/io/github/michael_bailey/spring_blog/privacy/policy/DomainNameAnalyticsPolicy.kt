@file:Project("privacy")

package io.github.michael_bailey.spring_blog.privacy.policy

import io.github.michael_bailey.spring_blog.model.DomainNameAnalyticsModel
import io.github.michael_bailey.spring_blog.privacy.rules.AllowIfRequestCreatedRule
import io.github.michael_bailey.spring_blog.privacy.rules.DenyAllRule
import io.github.michael_bailey.spring_blog.privacy.rules.PrivacyRule
import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import net.michael_bailey.metadata.Project

class DomainNameAnalyticsPolicy(
	vc: IViewerContext
): PrivacyPolicy<DomainNameAnalyticsModel>(vc = vc) {

	override val queryPolicy: List<PrivacyRule<DomainNameAnalyticsModel>> = listOf(
		AllowIfRequestCreatedRule(),
		DenyAllRule(), // denys all last
	)

}
