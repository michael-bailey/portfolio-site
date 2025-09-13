@file:Project("privacy")

package io.github.michael_bailey.spring_blog.privacy.rules

import io.github.michael_bailey.spring_blog.model.interfaces.IRequestOwnedModel
import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import net.michael_bailey.metadata.Project

class AllowIfRequestCreatedRule<TModel> :
	PrivacyRule<TModel> where TModel : IRequestOwnedModel {

	override fun execute(
		vc: IViewerContext,
		model: TModel,
	): PrivacyResult =
		if (vc.requestId == model.requestId) PrivacyResult.Allow else PrivacyResult.Skip

}
