package com.dddsample.demo.application

import com.dddsample.demo.domain.content.Content
import com.dddsample.demo.domain.content.ContentId
import com.dddsample.demo.domain.content.ContentRepository
import com.dddsample.demo.domain.content.Status
import com.dddsample.demo.domain.content.field.FieldList
import com.dddsample.demo.domain.contentFormat.ContentFormatId
import com.dddsample.demo.domain.contentFormat.ContentFormatRepository
import com.dddsample.demo.domain.website.WebsiteId
import java.lang.IllegalStateException

class ContentApplicationService(
    private val contentRepository: ContentRepository,
    private val contentFormatRepository: ContentFormatRepository
) {
    // 作る
    fun create(
        websiteId: WebsiteId,
        contentFormatId: ContentFormatId,
        status: Status,
        fieldList: FieldList
    ): ContentId {
        val newContent = Content(
            id = contentRepository.nextIdentifier(),
            websiteId = websiteId,
            contentFormatId = contentFormatId,
            status = status,
            fieldList = fieldList
        )

        contentRepository.store(newContent)

        return newContent.id
    }

    // 内容を編集する
    fun editContent(contentId: ContentId, fieldList: FieldList) {
        val targetContent = contentRepository.retrieveById(contentId) ?: throw IllegalStateException()
        val targetContentFormatList = contentFormatRepository.retrieveById(targetContent.contentFormatId)
            ?: throw IllegalStateException()
        val targetFieldFormatList = targetContentFormatList.fieldFormatList

        if (!targetContent.checkFieldList(fieldFormatList = targetFieldFormatList)) {
            throw IllegalStateException()
        }

        targetContent.changeFieldList(fieldList)
        contentRepository.store(targetContent)
    }

    // ステータスを変更する
    fun changeStatus(contentId: ContentId, status: Status) {
        val targetContent = contentRepository.retrieveById(contentId) ?: throw IllegalStateException()
        targetContent.changeStatus(status)

        contentRepository.store(targetContent)
    }

    // 削除する
    fun delete(contentId: ContentId) {
        val targetContent = contentRepository.retrieveById(contentId) ?: throw IllegalStateException()
        contentRepository.remove(targetContent)
    }
}
