package com.dddsample.demo.domain.content.field

import com.dddsample.demo.domain.contentFormat.fieldFormat.FieldFormatList

/**
 * オブジェクト種別
 * Value Object
 *
 * 名称
 * フィールドリスト
 */
data class FieldList(private val list: List<Field>) {
    fun checkFieldFormatList(fieldFormatList: FieldFormatList) = fieldFormatList.checkList(this.list)
}
