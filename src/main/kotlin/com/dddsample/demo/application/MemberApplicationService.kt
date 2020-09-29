package com.dddsample.demo.application

import com.dddsample.demo.domain.userAccount.UserAccountId
import com.dddsample.demo.domain.website.WebsiteId
import com.dddsample.demo.domain.website.WebsiteRepository
import com.dddsample.demo.domain.website.member.Role


class MemberApplicationService(
    private val websiteRepository: WebsiteRepository
) {
    // メンバーを追加する
    fun addMember(
        userAccountId: UserAccountId,
        websiteId: WebsiteId
    ) {
        val targetWebsite = websiteRepository.retrieveById(websiteId) ?:throw IllegalStateException()
        if (targetWebsite.isMember(userAccountId) || !targetWebsite.isWithinMemberLimit()) {
            throw IllegalStateException()
        }

        targetWebsite.addMember(userAccountId)
        websiteRepository.store(targetWebsite)
    }

    // ロールを変更する
    fun changeRole(
        userAccountId: UserAccountId,
        role: Role,
        websiteId: WebsiteId
    ) {
        val targetWebsite = websiteRepository.retrieveById(websiteId) ?:throw IllegalStateException()
        if (!targetWebsite.isMember(userAccountId)) {
            throw IllegalStateException()
        }
        targetWebsite.changeMemberRole(
            userAccountId = userAccountId,
            newRole = role
        )
        websiteRepository.store(targetWebsite)
    }

    // メンバーを除名する
    fun removeMember(
        userAccountId: UserAccountId,
        websiteId: WebsiteId
    ) {
        val targetWebsite = websiteRepository.retrieveById(websiteId) ?:throw IllegalStateException()
        if (!targetWebsite.isMember(userAccountId) || !targetWebsite.canRemoveMember()) {
            throw IllegalStateException()
        }
        targetWebsite.removeMember(
            userAccountId = userAccountId
        )
        websiteRepository.store(targetWebsite)
    }
}
