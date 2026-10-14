package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.ContentFormat
import com.example.model.ContentTone
import com.example.model.SocialPlatform
import com.example.model.ViralDraft

@Entity(tableName = "saved_drafts")
data class SavedDraftEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val topic: String,
    val platformId: String,
    val toneId: String,
    val formatId: String,
    val hook: String,
    val body: String,
    val fullContent: String,
    val hashtagsCsv: String,
    val createdAt: Long,
    val isFavorite: Boolean
) {
    fun toViralDraft(): ViralDraft {
        val hashtags = if (hashtagsCsv.isBlank()) emptyList() else hashtagsCsv.split(",").map { it.trim() }
        return ViralDraft(
            id = id,
            title = title,
            topic = topic,
            platform = SocialPlatform.fromId(platformId),
            tone = ContentTone.fromId(toneId),
            format = ContentFormat.fromId(formatId),
            hook = hook,
            body = body,
            hashtags = hashtags,
            fullContent = fullContent,
            createdAt = createdAt,
            isFavorite = isFavorite
        )
    }

    companion object {
        fun fromViralDraft(draft: ViralDraft): SavedDraftEntity {
            return SavedDraftEntity(
                id = draft.id,
                title = draft.title,
                topic = draft.topic,
                platformId = draft.platform.id,
                toneId = draft.tone.id,
                formatId = draft.format.id,
                hook = draft.hook,
                body = draft.body,
                fullContent = draft.fullContent,
                hashtagsCsv = draft.hashtags.joinToString(","),
                createdAt = draft.createdAt,
                isFavorite = draft.isFavorite
            )
        }
    }
}
