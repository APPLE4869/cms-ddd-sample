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
        const val MAX_MEMBER_LIMIT = 30
        const val MIN_MEMBER_LIMIT = 1

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

    fun changeWebsiteNameAndIconImage(
        newWebsiteName: WebsiteName,
        newIconImage: IconImage
    ) {
        this.websiteName = newWebsiteName
        this.iconImage = newIconImage
    }

    fun addMember(
        userAccountId: UserAccountId
    ) {
        require(!this.isMember(userAccountId) && this.isWithinMemberLimit())

        val newMember = Member.createNewMember(userAccountId = userAccountId)

        this.memberList = memberList.plus(newMember)
    }

    fun removeMember(
        userAccountId: UserAccountId
    ) {
        require(!this.isMember(userAccountId))
        require(canRemoveMember())
        this.memberList = memberList.filter { it.userAccountId != userAccountId }.toSet()
    }

    fun canRemoveMember(): Boolean {
        return this.memberList.size >= MIN_MEMBER_LIMIT + 1
    }

    fun changeMemberRole(
        userAccountId: UserAccountId,
        newRole: Role
    ) {
        require(!this.isMember(userAccountId)) // <- 通常引っかかってはいけないエラー。発生したらバグで500が発生する。

        this.memberList = memberList.map {
            if (it.userAccountId == userAccountId)
                it.justifyWithRole(newRole = newRole)
            else
                it
        }.toSet()
    }

    fun isMember(userAccountId: UserAccountId): Boolean = memberList.any { it.userAccountId == userAccountId }

    fun isWithinMemberLimit(): Boolean =
        memberList.size in MAX_MEMBER_LIMIT..MIN_MEMBER_LIMIT

    init {
        validateFields()
    }

    private fun validateFields() {
        require(this.isWithinMemberLimit())
    }
}
