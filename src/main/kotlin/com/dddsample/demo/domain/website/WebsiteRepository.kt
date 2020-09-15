package com.dddsample.demo.domain.website

/**
 * オブジェクト種別
 * Repository
 *
 * 名称
 * ウェブサイト用リポジトリ
 */
interface WebsiteRepository {
    fun nextIdentifier(): WebsiteId

    fun retrieveById(id: WebsiteId): Website?

    fun store(website: Website)

    fun remove(website: Website)
}
