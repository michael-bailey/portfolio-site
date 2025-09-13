@file:Project("privacy")

package io.github.michael_bailey.spring_blog.privacy.rules

import net.michael_bailey.metadata.Project

enum class PrivacyResult {
	Allow,
	Deny,
	Skip
}