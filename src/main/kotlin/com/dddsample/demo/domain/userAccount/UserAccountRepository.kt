package com.dddsample.demo.domain.userAccount

/**
 * オブジェクト種別
 * Repository
 *
 * 名称
 * ユーザーアカウント用リポジトリ
 */
interface UserAccountRepository {
    fun nextIdentifier(): UserAccountId

    fun retrieveById(id: UserAccountId): UserAccount?

    fun store(userAccount: UserAccount)

    fun remove(userAccount: UserAccount)
}
