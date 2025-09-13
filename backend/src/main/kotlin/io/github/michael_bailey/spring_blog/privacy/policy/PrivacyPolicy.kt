@file:Project("privacy")

package io.github.michael_bailey.spring_blog.privacy.policy

import io.github.michael_bailey.spring_blog.privacy.rules.PrivacyResult
import io.github.michael_bailey.spring_blog.privacy.rules.PrivacyRule
import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import net.michael_bailey.metadata.Project

abstract class PrivacyPolicy<TModel>(
	private val vc: IViewerContext
) {

	abstract val queryPolicy: List<PrivacyRule<TModel>>

	fun execute(models: List<TModel>): List<TModel> {
		return emptyList()
	}

	fun execute(model: TModel): TModel? {
		for (p in queryPolicy) {
			return when (p.execute(vc, model)) {
				PrivacyResult.Allow -> model
				PrivacyResult.Skip -> continue
				PrivacyResult.Deny -> null
			}
		}

		return null
	}
}
