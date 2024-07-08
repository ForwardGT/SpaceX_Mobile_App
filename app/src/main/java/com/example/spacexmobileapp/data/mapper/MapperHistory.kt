package com.example.spacexmobileapp.data.mapper

import com.example.spacexmobileapp.data.model.BlockHistoryDto
import com.example.spacexmobileapp.domain.entity.BlockHistory
import com.example.spacexmobileapp.extensions.mapTimestampToDate

fun mapperHistory(
    responseHistory: List<BlockHistoryDto>
): List<BlockHistory> {

    val result = mutableListOf<BlockHistory>()

    responseHistory.forEach { item ->
        val history = BlockHistory(
            title = item.title,
            eventDate = item.eventDate.mapTimestampToDate(),
            details = item.details
        )
        result.add(history)
    }
    return result
}
