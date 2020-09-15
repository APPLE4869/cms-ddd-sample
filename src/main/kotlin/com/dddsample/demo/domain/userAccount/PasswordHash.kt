package com.dddsample.demo.domain.userAccount

/**
 * オブジェクト種別
 * Value Object
 *
 * 名称
 * パスワードハッシュ
 */
data class PasswordHash(val value: String) {
    init {
        validate()
    }

    fun validate() {
        TODO()
    }
}
