package com.dddsample.demo.domain.contentFormat.fieldFormat

/**
 * オブジェクト種別
 * Value Object
 *
 * 名称
 * フィールド形式
 */
data class FieldFormat(
        val fieldIdentifier: FieldIdentifier,
        val name: FieldName,
        val type: FieldFormatType
) {

}
