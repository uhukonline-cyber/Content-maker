package com.example.model

enum class ContentTone(
    val id: String,
    val displayName: String,
    val emoji: String,
    val description: String,
    val sampleHookPrefix: String
) {
    KONTROVERSIAL(
        id = "kontroversial",
        displayName = "Kontroversial & Penasaran",
        emoji = "🔥",
        description = "Membongkar mitos umum, opini berani, bikin orang berhenti scroll dan berdebat.",
        sampleHookPrefix = "Gue tahu ini bakal bikin banyak orang kesel, tapi..."
    ),
    STORYTELLING(
        id = "storytelling",
        displayName = "Storytelling Emosional",
        emoji = "📖",
        description = "Cerita perjalanan nyata, dari titik terendah hingga solusi yang menginspirasi.",
        sampleHookPrefix = "6 bulan lalu tabungan gue nol dan hampir menyerah, sampai..."
    ),
    SARKASTIK(
        id = "sarkastik",
        displayName = "Sarkastik & Humor",
        emoji = "😂",
        description = "Gaya sindiran halus, relatable meme vibe, jenaka dan menggelitik realita.",
        sampleHookPrefix = "Selamat buat kamu yang masih percaya kalau cara ini bekerja..."
    ),
    EDUKATIF(
        id = "edukatif",
        displayName = "Edukatif & Fakta",
        emoji = "💡",
        description = "Berbasis data, otoritatif, to-the-point, membongkar langkah praktis.",
        sampleHookPrefix = "93% orang melakukan kesalahan fatal ini tanpa sadar..."
    ),
    FOMO(
        id = "fomo",
        displayName = "FOMO & Urgensi",
        emoji = "⚡",
        description = "Menciptakan rasa takut ketinggalan rahasia, desakan waktu, eksklusif.",
        sampleHookPrefix = "STOP SCROLL! Simpan video ini sebelum dihapus atau terlambat..."
    ),
    GEN_Z(
        id = "gen_z",
        displayName = "Santai & Gen Z",
        emoji = "😎",
        description = "Bahasa gaul akrab (cuy, spill, no cap, relate banget), fresh tanpa basa-basi.",
        sampleHookPrefix = "Spill dikit rahasia yang gak pernah dibahas di timeline..."
    ),
    PROFESIONAL(
        id = "profesional",
        displayName = "Profesional & Karir",
        emoji = "👔",
        description = "Struktur rapi, berbobot, elegan, cocok untuk B2B dan personal branding.",
        sampleHookPrefix = "Pelajaran penting yang saya pelajari setelah mengelola..."
    ),
    MOTIVASI(
        id = "motivasi",
        displayName = "Motivasi & Mindset",
        emoji = "🚀",
        description = "Kalimat bertenaga tinggi, menampar zona nyaman, membangun semangat.",
        sampleHookPrefix = "Kamu gak kekurangan waktu, kamu cuma kebanyakan alasan..."
    );

    companion object {
        fun fromId(id: String): ContentTone =
            entries.find { it.id.equals(id, ignoreCase = true) } ?: KONTROVERSIAL
    }
}
