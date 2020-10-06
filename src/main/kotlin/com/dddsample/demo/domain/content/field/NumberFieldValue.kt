package com.dddsample.demo.domain.content.field

import com.dddsample.demo.domain.contentFormat.fieldFormat.FieldFormatType

class NumberFieldValue(private val value: Int) : FieldValue {
    override val type: FieldFormatType = FieldFormatType.NUMERIC
}