package com.dddsample.demo.domain.content

import com.dddsample.demo.domain.contentFormat.ContentFormatId

/**
 * オブジェクト種別
 * Repository
 *
 * 名称
 * コンテンツ用リポジトリ
 */
interface ContentRepository {
    fun retrieveAllByContentFormatId(id: ContentFormatId): List<Content>

    fun retrieveById(id: ContentId): Content?

    fun store(content: Content)

    fun remove(content: Content)
}
