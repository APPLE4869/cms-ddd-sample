package com.dddsample.demo.application

import com.dddsample.demo.domain.content.ContentRepository
import com.dddsample.demo.domain.contentFormat.*
import com.dddsample.demo.domain.contentFormat.fieldFormat.FieldFormatList
import com.dddsample.demo.domain.userAccount.UserAccountId
import com.dddsample.demo.domain.userAccount.UserAccountRepository
import com.dddsample.demo.domain.website.WebsiteId
import com.dddsample.demo.domain.website.WebsiteRepository
import java.lang.IllegalStateException

class ContentFormatApplicationService(
    private val contentFormatRepository: ContentFormatRepository,
    private val contentsFormatCreationSpecification: ContentsFormatCreationSpecification,
    private val websiteRepository: WebsiteRepository,
    private val userAccountRepository: UserAccountRepository
) {
    // 作る
    fun create(userAccountId: UserAccountId, websiteId: WebsiteId, contentFormatName: ContentFormatName, formatType: FormatType, fieldFormatList: FieldFormatList): ContentFormatId {
        if (!isMember(websiteId, userAccountId)) throw IllegalStateException()

        val newContentFormat = ContentFormat(
            id = contentFormatRepository.nextIdentifier(),
            websiteId = websiteId,
            contentFormatName = contentFormatName,
            formatType = formatType,
            fieldFormatList = fieldFormatList
        )
        contentFormatRepository.store(newContentFormat)
        return newContentFormat.id
    }

    // フィールド形式を編集する
    fun editFieldFormatList(websiteId: WebsiteId, userAccountId: UserAccountId, contentFormatId: ContentFormatId, newFieldFormatList: FieldFormatList) {
        if (!isMember(websiteId, userAccountId)) throw IllegalStateException()

        if (!contentsFormatCreationSpecification.isSaticifiedBy(contentFormatId)) {
            throw IllegalStateException()
        }

        val contentFormat = contentFormatRepository.retrieveById(contentFormatId) ?: throw IllegalStateException()
        contentFormat.changeFieldFormatList(newFieldFormatList)
        contentFormatRepository.store(contentFormat)
    }

    // コンテンツ形式名を編集する
    fun editContentFormatName(websiteId: WebsiteId, userAccountId: UserAccountId, contentFormatId: ContentFormatId, newContentFormatName: ContentFormatName) {
        if (!isMember(websiteId, userAccountId)) throw IllegalStateException()

        val contentFormat = contentFormatRepository.retrieveById(contentFormatId) ?: throw IllegalStateException()
        contentFormat.changeContentFormatName(newContentFormatName)
        contentFormatRepository.store(contentFormat)
    }

    // 削除する
    fun delete(websiteId: WebsiteId, userAccountId: UserAccountId, contentFormatId: ContentFormatId) {
        if (!isMember(websiteId, userAccountId)) throw IllegalStateException()

        val contentFormat = contentFormatRepository.retrieveById(contentFormatId) ?: throw IllegalStateException()
        contentFormatRepository.remove(contentFormat)
    }

    private fun isMember(websiteId: WebsiteId, userAccountId: UserAccountId): Boolean {
        val targetWebsite = websiteRepository.retrieveById(websiteId) ?: throw IllegalStateException()

        val targetUserAccount = userAccountRepository.retrieveById(userAccountId) ?: throw IllegalStateException()
        return targetWebsite.isMember(targetUserAccount.id)
    }
}
