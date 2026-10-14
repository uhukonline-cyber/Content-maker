package com.example.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.InstagramColor
import com.example.ui.theme.LinkedInColor
import com.example.ui.theme.ThreadsColor
import com.example.ui.theme.TikTokColor
import com.example.ui.theme.TwitterColor
import com.example.ui.theme.YouTubeColor

enum class SocialPlatform(
    val id: String,
    val displayName: String,
    val iconEmoji: String,
    val charLimit: Int?,
    val brandColor: Color,
    val bestPractices: String,
    val packageName: String?
) {
    TIKTOK(
        id = "tiktok",
        displayName = "TikTok",
        iconEmoji = "🎵",
        charLimit = 2200,
        brandColor = TikTokColor,
        bestPractices = "Hook visual & kata-kata dalam 2 detik pertama! Gunakan sound trending dan CTA ajak stitch/komen.",
        packageName = "com.zhiliaoapp.musically"
    ),
    INSTAGRAM(
        id = "instagram",
        displayName = "Instagram",
        iconEmoji = "📸",
        charLimit = 2200,
        brandColor = InstagramColor,
        bestPractices = "Slide 1 cover carousel harus bikin penasaran. Gunakan baris kosong & bullet point agar enak dibaca.",
        packageName = "com.instagram.android"
    ),
    TWITTER_X(
        id = "twitter",
        displayName = "X (Twitter)",
        iconEmoji = "𝕏",
        charLimit = 280,
        brandColor = TwitterColor,
        bestPractices = "Tweet pembuka harus kuat & kontroversial. Taruh value di tweet thread berikutnya.",
        packageName = "com.twitter.android"
    ),
    YOUTUBE_SHORTS(
        id = "youtube",
        displayName = "YT Shorts",
        iconEmoji = "▶️",
        charLimit = 1000,
        brandColor = YouTubeColor,
        bestPractices = "Judul mengandung rasa penasaran (High CTR). Loop ending video ke kalimat hook pembuka.",
        packageName = "com.google.android.youtube"
    ),
    LINKEDIN(
        id = "linkedin",
        displayName = "LinkedIn",
        iconEmoji = "💼",
        charLimit = 3000,
        brandColor = LinkedInColor,
        bestPractices = "Gunakan spasi baris pendek (1-2 baris per paragraf). Ceritakan kegagalan menuju pelajaran profesional.",
        packageName = "com.linkedin.android"
    ),
    THREADS_FB(
        id = "threads",
        displayName = "Threads",
        iconEmoji = "🧵",
        charLimit = 500,
        brandColor = ThreadsColor,
        bestPractices = "Tulis seperti ngobrol santai dengan teman dekat. Tanyakan opini pembaca di akhir.",
        packageName = "com.instagram.barcelona"
    );

    companion object {
        fun fromId(id: String): SocialPlatform =
            entries.find { it.id.equals(id, ignoreCase = true) } ?: TIKTOK
    }
}
