package com.dddsample.demo.domain.content.field

import com.dddsample.demo.domain.contentFormat.fieldFormat.FieldFormatType

class FileFieldValue(private val path: String) : FieldValue {
    override val type: FieldFormatType = FieldFormatType.FILE
}