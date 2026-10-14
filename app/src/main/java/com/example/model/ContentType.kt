package com.example.model

enum class ContentFormat(
    val id: String,
    val title: String,
    val subtitle: String,
    val emoji: String
) {
    HOOK_PACK(
        id = "hook_pack",
        title = "3-Detik Killer Hook",
        subtitle = "Variasi kalimat pembuka penyetop jempol scrolling",
        emoji = "🎯"
    ),
    FULL_SCRIPT(
        id = "full_script",
        title = "Script Video Lengkap",
        subtitle = "Hook + Masalah + Solusi Daging + CTA Menusuk",
        emoji = "🎬"
    ),
    CAROUSEL_THREAD(
        id = "carousel_thread",
        title = "Carousel & Thread Slides",
        subtitle = "Slide 1 cover viral + breakdown poin 1-5 + kesimpulan",
        emoji = "📑"
    ),
    CAPTION_HASHTAGS(
        id = "caption_hashtags",
        title = "Caption & Hashtag Viral",
        subtitle = "Format rapi siap tempel dengan hashtag tertarget",
        emoji = "📝"
    );

    companion object {
        fun fromId(id: String): ContentFormat =
            entries.find { it.id.equals(id, ignoreCase = true) } ?: FULL_SCRIPT
    }
}
