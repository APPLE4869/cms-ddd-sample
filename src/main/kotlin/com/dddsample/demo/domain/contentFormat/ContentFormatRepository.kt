package com.dddsample.demo.domain.contentFormat

import com.dddsample.demo.domain.userAccount.UserAccountId

/**
 * オブジェクト種別
 * Repository
 *
 * 名称
 * フィールド形式用リポジトリ
 */
interface ContentFormatRepository {
    fun nextIdentifier(): ContentFormatId

    fun retrieveById(id: ContentFormatId): ContentFormat?

    fun store(contentFormat: ContentFormat)

    fun remove(contentFormat: ContentFormat)
}
