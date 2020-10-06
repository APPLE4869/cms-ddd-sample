package com.dddsample.demo.domain.contentFormat.fieldFormat

import com.dddsample.demo.domain.content.field.Field
import com.dddsample.demo.domain.content.field.FieldList

/**
 * オブジェクト種別
 * Value Object
 *
 * 名称
 * フィールド形式リスト
 */
data class FieldFormatList(private val list: List<FieldFormat>) {
    fun findFormat(fieldIdentifier: FieldIdentifier) = list.find {
        it.matchIdentifier(fieldIdentifier)
    }
}
