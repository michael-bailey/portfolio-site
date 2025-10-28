@file:OptIn(ExperimentalUuidApi::class)

package bitlib.authentication

import kotlin.uuid.ExperimentalUuidApi

sealed class SubjectType {

	object Anonymous: SubjectType()
	object User: SubjectType()
	object Application: SubjectType()

}
