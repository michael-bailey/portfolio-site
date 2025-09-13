@file:Project("privacy")

package io.github.michael_bailey.spring_blog.privacy.rules

import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import net.michael_bailey.metadata.Project

class AllowAllRule<TModel>: PrivacyRule<TModel> {
	override fun execute(
		vc: IViewerContext,
		model: TModel,
	): PrivacyResult {
		return PrivacyResult.Deny
	}
}