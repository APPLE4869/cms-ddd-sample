package com.dddsample.demo.domain.content.field

import com.dddsample.demo.domain.contentFormat.fieldFormat.FieldIdentifier

/**
 * オブジェクト種別
 * Value Object
 *
 * 名称
 * フィールド
 */
data class Field(
        val filedIdentifier: FieldIdentifier,
        val value: FieldValue
) {

}
