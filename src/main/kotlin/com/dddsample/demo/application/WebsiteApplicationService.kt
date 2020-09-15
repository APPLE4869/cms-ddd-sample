package com.dddsample.demo.application

import com.dddsample.demo.domain.userAccount.UserAccountId
import com.dddsample.demo.domain.website.IconImage
import com.dddsample.demo.domain.website.Slug
import com.dddsample.demo.domain.website.Website
import com.dddsample.demo.domain.website.WebsiteId
import com.dddsample.demo.domain.website.WebsiteName
import com.dddsample.demo.domain.website.WebsiteRepository
import java.lang.IllegalStateException

class WebsiteApplicationService(
        private val websiteRepository: WebsiteRepository
) {
    // 作る
    fun create(
            slug: Slug,
            websiteName: WebsiteName,
            iconImage: IconImage,
            userAccountIdOfCreator: UserAccountId
    ): WebsiteId {
        val newWebsite = Website.create(
                id = websiteRepository.nextIdentifier(),
                slug = slug,
                websiteName = websiteName,
                iconImage = iconImage,
                userAccountIdOfCreator = userAccountIdOfCreator
        )
        websiteRepository.store(newWebsite)

        return newWebsite.id
    }

    // ウェブサイトの情報を編集する
    fun editWebsiteInformation(
            websiteId: WebsiteId,
            websiteName: WebsiteName,
            iconImage: IconImage
    ) {
        val website = websiteRepository.retrieveById(websiteId) ?: throw IllegalStateException()
        website.changeWebsiteNameAndIconImage(
                newWebsiteName = websiteName,
                newIconImage = iconImage
        )
        websiteRepository.store(website)
    }

    // 削除する
    fun delete(websiteId: WebsiteId) {
        val website = websiteRepository.retrieveById(websiteId) ?: throw IllegalStateException()
        websiteRepository.remove(website)
    }
}
