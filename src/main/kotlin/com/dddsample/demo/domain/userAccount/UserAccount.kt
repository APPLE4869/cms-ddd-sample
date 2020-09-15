package com.dddsample.demo.domain.userAccount

/**
 * オブジェクト種別
 * Aggregate Root (Entity)
 *
 * 名称
 * ユーザーアカウント
 */
class UserAccount(
        val id: UserAccountId,
        var mailAddress: MailAddress,
        var passwordHash: PasswordHash
) {
    companion object {
        fun create(
                id: UserAccountId,
                mailAddress: MailAddress,
                password: Password
        ) = UserAccount(
                id,
                mailAddress,
                password.toHash()
        )
    }

    fun changeMailAddress(mailAddress: MailAddress) {
        this.mailAddress = mailAddress
    }

    fun changePassword(password: Password) {
        this.passwordHash = password.toHash()
    }
}
