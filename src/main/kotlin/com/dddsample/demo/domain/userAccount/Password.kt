package com.dddsample.demo.domain.userAccount

/**
 * オブジェクト種別
 * Value Object
 *
 * 名称
 * パスワード
 */
data class Password(val value: String) {
    fun toHash(): PasswordHash {
        val hash = value // TODO : hash化する処理
        return PasswordHash(hash)
    }
}
