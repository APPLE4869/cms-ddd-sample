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
        const val REGEX = "何らかの正規表現"
        const val MAX_LENGTH = 255
    }

    init {
        validate()
    }

    fun validate() {
        require(REGEX.toRegex().matches(value))
        require(value.length <= MAX_LENGTH)
    }
}
