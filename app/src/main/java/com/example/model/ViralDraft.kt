package com.example.model

data class ViralDraft(
    val id: Long = 0,
    val title: String,
    val topic: String,
    val platform: SocialPlatform,
    val tone: ContentTone,
    val format: ContentFormat,
    val hook: String,
    val body: String,
    val cta: String = "",
    val hashtags: List<String> = emptyList(),
    val fullContent: String,
    val createdAt: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = false
)

data class ViralScore(
    val score: Int, // 0 - 100
    val ratingLabel: String,
    val triggerWordsFound: List<String>,
    val tips: List<String>,
    val charCount: Int,
    val wordCount: Int,
    val estReadingSeconds: Int
)
