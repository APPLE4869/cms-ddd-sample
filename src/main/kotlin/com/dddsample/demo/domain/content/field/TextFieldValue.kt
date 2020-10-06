package com.dddsample.demo.domain.content.field

import com.dddsample.demo.domain.contentFormat.fieldFormat.FieldFormatType

class TextFieldValue(private val value: String) : FieldValue {
    override val type: FieldFormatType = FieldFormatType.TEXT
}