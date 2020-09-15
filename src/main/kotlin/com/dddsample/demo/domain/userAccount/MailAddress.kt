package com.dddsample.demo.domain.userAccount

/**
 * オブジェクト種別
 * Value Object
 *
 * 名称
 * メールアドレス
 */
data class MailAddress(var value: String) {
    companion object {
        fun change(newValue: String) = MailAddress(newValue)
    }
    init {
        validate()
    }

    fun validate() {
        TODO()
    }
}
