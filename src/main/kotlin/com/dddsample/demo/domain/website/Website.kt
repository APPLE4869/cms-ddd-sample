package com.dddsample.demo.domain.website

import com.dddsample.demo.domain.userAccount.UserAccountId
import com.dddsample.demo.domain.website.member.Member
import com.dddsample.demo.domain.website.member.Role

/**
 * オブジェクト種別
 * Aggregate Root (Entity)
 *
 * 名称
 * ウェブサイト
 */
class Website(
        val id: WebsiteId,
        val slug: Slug,
        var websiteName: WebsiteName,
        var iconImage: IconImage,
        var memberList: Set<Member>
) {
    companion object {
        fun create(
                id: WebsiteId,
                slug: Slug,
                websiteName: WebsiteName,
                iconImage: IconImage,
                userAccountIdOfCreator: UserAccountId
        ) = Website(
                id = id,
                slug = slug,
                websiteName = websiteName,
                iconImage = iconImage,
                memberList = setOf(Member.createNewMember(userAccountIdOfCreator))
        )
    }

    fun changeWebsiteNameAndIconImage(newWebsiteName: WebsiteName, newIconImage: IconImage) {
        this.websiteName = newWebsiteName
        this.iconImage = newIconImage
    }

    fun addMember(userAccountId: UserAccountId) {
        val newMember = Member.createNewMember(userAccountId = userAccountId)

        if (this.isMember(userAccountId)) { // すでにユーザーがメンバーとして登録されていれば。
            return
        }

        this.memberList = memberList.plus(newMember)
    }

    fun removeMember(userAccountId: UserAccountId) {
        this.memberList = memberList.filter { it.userAccountId == userAccountId }.toSet()
    }

    fun changeMemberRole(userAccountId: UserAccountId, newRole: Role) {
        require(!this.isMember(userAccountId)) // <- 通常引っかかってはいけないエラー。発生したらバグで500が発生する。

        this.memberList = memberList.map {
            if (it.userAccountId == userAccountId)
                it.justifyWithRole(newRole = newRole)
            else
                it
        }.toSet()
    }

    private fun isMember(userAccountId: UserAccountId): Boolean = memberList.any { it.userAccountId == userAccountId }
}
