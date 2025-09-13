@file:Project("authentication")

package io.github.michael_bailey.spring_blog.model

import net.michael_bailey.metadata.Project
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserModel : UserDetails {

	private val _username: String
	private val _password: String
	private val _roles: List<String>
	private val _authorities: List<String>

	constructor(
		username: String,
		password: String,
		roles: List<String> = emptyList(),
		authorities: List<String> = emptyList(),
	) {
		this._username = username
		this._password = password
		this._roles = roles
		this._authorities = authorities
	}

	override fun getUsername(): String = this._username

	override fun getPassword(): String? = this._password

	override fun getAuthorities(): Collection<GrantedAuthority> =
		_authorities.map {
			SimpleGrantedAuthority(it)
		}

	fun getRoles(): List<String> = _roles
}
