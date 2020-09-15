package com.dddsample.demo.domain.content

import com.dddsample.demo.domain.content.field.FieldList
import com.dddsample.demo.domain.contentFormat.ContentFormatId
import com.dddsample.demo.domain.website.WebsiteId

/**
 * オブジェクト種別
 * Aggregate Root (Entity)
 *
 * 名称
 * コンテンツ
 */
class Content(
        val id: ContentId,
        val websiteId: WebsiteId,
        val contentFormatId: ContentFormatId,
        var status: Status,
        var fieldList: FieldList
) {

}
