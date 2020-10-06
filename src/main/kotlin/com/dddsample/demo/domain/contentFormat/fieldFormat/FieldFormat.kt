package com.dddsample.demo.domain.contentFormat.fieldFormat

/**
 * オブジェクト種別
 * Value Object
 *
 * 名称
 * フィールド形式
 */
data class FieldFormat(
        private val fieldIdentifier: FieldIdentifier,
        private val name: FieldName,
        private val type: FieldFormatType
) {
    fun matchIdentifier(fieldIdentifier: FieldIdentifier): Boolean {
        TODO()
    }
}
