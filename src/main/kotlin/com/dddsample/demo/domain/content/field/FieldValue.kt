package com.dddsample.demo.domain.content.field

import com.dddsample.demo.domain.contentFormat.fieldFormat.FieldFormatType

/**
 * オブジェクト種別
 * Value Object
 *
 * 名称
 * フィールドの値
 */
interface FieldValue {
    val type: FieldFormatType

    fun matchType(type: FieldFormatType) = type == this.type
}
