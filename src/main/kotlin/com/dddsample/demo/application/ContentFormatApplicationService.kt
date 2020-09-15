package com.dddsample.demo.application

import com.dddsample.demo.domain.content.ContentRepository
import com.dddsample.demo.domain.contentFormat.*
import com.dddsample.demo.domain.contentFormat.fieldFormat.FieldFormatList
import com.dddsample.demo.domain.website.WebsiteId
import java.lang.IllegalStateException

class ContentFormatApplicationService(
    private val contentFormatRepository: ContentFormatRepository,
    private val contentsFormatCreationSpecification: ContentsFormatCreationSpecification
) {
    // 作る
    fun create(websiteId: WebsiteId, contentFormatName: ContentFormatName, formatType: FormatType, fieldFormatList: FieldFormatList): ContentFormatId {
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
    fun editFieldFormatList(contentFormatId: ContentFormatId, newFieldFormatList: FieldFormatList) {
        if (!contentsFormatCreationSpecification.isSaticifiedBy(contentFormatId)) {
            throw IllegalStateException()
        }

        val contentFormat = contentFormatRepository.retrieveById(contentFormatId) ?: throw IllegalStateException()
        contentFormat.changeFieldFormatList(newFieldFormatList)
        contentFormatRepository.store(contentFormat)
    }

    // コンテンツ形式名を編集する
    fun editContentFormatName(contentFormatId: ContentFormatId, newContentFormatName: ContentFormatName) {
        val contentFormat = contentFormatRepository.retrieveById(contentFormatId) ?: throw IllegalStateException()
        contentFormat.changeContentFormatName(newContentFormatName)
        contentFormatRepository.store(contentFormat)
    }

    // 削除する
    fun delete(contentFormatId: ContentFormatId) {
        val contentFormat = contentFormatRepository.retrieveById(contentFormatId) ?: throw IllegalStateException()
        contentFormatRepository.remove(contentFormat)
    }
}
