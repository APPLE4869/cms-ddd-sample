package com.dddsample.demo.domain.contentFormat

import com.dddsample.demo.domain.content.field.Field
import com.dddsample.demo.domain.content.field.FieldList
import com.dddsample.demo.domain.contentFormat.fieldFormat.FieldFormatList
import com.dddsample.demo.domain.website.WebsiteId

/**
 * オブジェクト種別
 * Aggregate Root (Entity)
 *
 * 名称
 * フィールド形式
 */
data class ContentFormat(
        val id: ContentFormatId,
        val websiteId: WebsiteId,
        val formatType: FormatType,
        var contentFormatName: ContentFormatName,
        var fieldFormatList: FieldFormatList
) {
    fun changeFieldFormatList(newFieldFormatList: FieldFormatList) {
        this.fieldFormatList = newFieldFormatList
    }

    fun changeContentFormatName(newContentFormatName: ContentFormatName) {
        this.contentFormatName = newContentFormatName
    }

    fun checkFieldList(
            fieldList: FieldList
    ): Boolean {
        fieldList.list.all {
            val format = fieldFormatList.findFormat(it.fieldIdentifier)
            requireNotNull(format)

            format.sameType(it.type)
        }

    }
}
