package com.dddsample.demo.domain.website.member

import com.dddsample.demo.domain.userAccount.UserAccountId

/**
 * オブジェクト種別
 * Value Object
 *
 * 名称
 * メンバー
 */
data class Member(
        val userAccountId: UserAccountId,
        val role: Role
) {
    companion object {
        fun createNewMember(userAccountId: UserAccountId) = Member(userAccountId = userAccountId, role = Role.EDITOR)
    }

    fun justifyWithRole(newRole: Role) = Member(userAccountId = this.userAccountId, role = newRole)
}
