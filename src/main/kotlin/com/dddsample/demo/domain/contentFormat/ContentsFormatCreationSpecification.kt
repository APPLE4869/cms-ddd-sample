package com.dddsample.demo.domain.contentFormat

import com.dddsample.demo.domain.content.ContentRepository

class ContentsFormatCreationSpecification(
    private val contentRepository: ContentRepository
) {
    fun isSaticifiedBy(contentFormatId: ContentFormatId): Boolean {
        return contentRepository.retrieveAllByContentFormatId(contentFormatId).isNotEmpty()
    }
}
