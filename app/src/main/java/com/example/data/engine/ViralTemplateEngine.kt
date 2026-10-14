package com.example.data.engine

import com.example.model.ContentFormat
import com.example.model.ContentTone
import com.example.model.SocialPlatform
import com.example.model.ViralDraft
import com.example.model.ViralScore

object ViralTemplateEngine {

    val SURPRISE_TOPICS = listOf(
        "Cara menghasilkan uang dari HP modal 0 rupiah",
        "Kenapa kopi kekinian rasanya mirip semua",
        "Kesalahan fatal fresh graduate pas interview kerja",
        "Trik AI biar kerjaan 8 jam selesai dalam 1 jam",
        "Rahasia algoritma TikTok FYP yang dirahasiakan agensi",
        "Cara nabung 10 juta pertama tanpa menyiksa diri",
        "Alasan kenapa orang pintar sering kalah sukses sama orang konsisten",
        "Barang murah di e-commerce yang bikin hidup berubah 180 derajat",
        "Trik psikologi biar orang langsung percaya sama kamu",
        "Cara membuat konten edukasi yang ditonton jutaan orang",
        "Kesalahan fatal saat mulai jualan online yang bikin bangkrut"
    )

    val CTA_PRESETS = listOf(
        "📌 Simpan postingan ini biar gak hilang pas kamu butuh nanti!",
        "💬 Komen 'MAU' di bawah, nanti gue kirim rangkuman lengkapnya!",
        "🚀 Share ke temen kamu yang wajib tahu info penting ini!",
        "🔥 Follow buat update konten daging & rahasia viral lainnya!",
        "🤔 Setuju atau nggak? Yuk diskusi santai di kolom komentar!",
        "👇 Tap link di bio kalau kamu mau belajar langkah praktisnya!"
    )

    val HOOK_EMOJIS = listOf("🔥", "🚨", "🤫", "🤯", "💡", "⚠️", "⚡", "👀", "🎯", "🛑")

    fun generateHookOptions(topic: String, tone: ContentTone, platform: SocialPlatform): List<String> {
        val cleanTopic = if (topic.isBlank()) "topik ini" else topic.trim()
        val hooks = mutableListOf<String>()

        when (tone) {
            ContentTone.KONTROVERSIAL -> {
                hooks.add("🚨 Gue tahu ini bakal bikin banyak orang panas, tapi stop percaya kalau $cleanTopic itu gampang!")
                hooks.add("🤯 Opini kontroversial: 90% orang yang ngajarin $cleanTopic itu sebenarnya gak paham praktiknya.")
                hooks.add("⚠️ Jangan pernah sentuh $cleanTopic sebelum kamu tahu rahasia pahit yang disembunyiin ini.")
                hooks.add("🤫 Kebohongan terbesar yang sering kamu dengar soal $cleanTopic akhirnya terbongkar!")
                hooks.add("🔥 Mau jujur atau manis? Ini kenyataan brutal tentang $cleanTopic yang gak ada yang berani bahas.")
            }
            ContentTone.STORYTELLING -> {
                hooks.add("📖 6 bulan lalu gue bener-bener mentok soal $cleanTopic, sampai satu momen kecil ini ngubah segalanya...")
                hooks.add("🥺 Gue sempat ngerasa gagal banget pas nyobain $cleanTopic. Ini pelajaran berharga yang gue dapat.")
                hooks.add("✨ Dari minus sampai akhirnya paham formula $cleanTopic, ini cerita nyata tanpa ditutup-tutupi.")
                hooks.add("☕ Duduk santai dulu, gue mau cerita gimana $cleanTopic nyelametin waktu dan finansial gue.")
                hooks.add("🌱 Jangan ulangi kebodohan gue 2 tahun lalu pas pertama kali kenal $cleanTopic.")
            }
            ContentTone.SARKASTIK -> {
                hooks.add("😂 Selamat buat kamu yang masih mikir kalau $cleanTopic bisa sukses cuma modal rebahan!")
                hooks.add("🤦‍♂️ Tutorial cara membuang waktu dan energi lewat $cleanTopic ala orang kebanyakan...")
                hooks.add("🤡 Katanya $cleanTopic bikin cepet kaya? Sini gue spill realitanya biar gak kaget.")
                hooks.add("💀 Ekspektasi vs Realita pas belajar $cleanTopic: siap-siap mental ya!")
                hooks.add("🍿 Duduk manis dan tonton gimana kesalahan konyol ini terjadi di $cleanTopic.")
            }
            ContentTone.EDUKATIF -> {
                hooks.add("💡 93% orang salah langkah di $cleanTopic karena gak tahu prinsip dasar nomor 1 ini.")
                hooks.add("📊 Berdasarkan riset dan data: Ini cara paling efektif menguasai $cleanTopic tanpa ribet.")
                hooks.add("🎯 3 aturan emas $cleanTopic yang bikin hasil kamu 5x lipat lebih cepat.")
                hooks.add("🧠 Bedah tuntas $cleanTopic dalam 60 detik: Panduan anti pusing buat pemula.")
                hooks.add("⚡ Jangan lakuin $cleanTopic sebelum cek checklist penting ini!")
            }
            ContentTone.FOMO -> {
                hooks.add("⚡ STOP SCROLL! Simpan postingan $cleanTopic ini sebelum terlambat dan nyesel!")
                hooks.add("⏳ Cuma berlaku sampai minggu ini: Peluang emas di $cleanTopic yang lagi viral!")
                hooks.add("👀 Kompetitor kamu udah pakai trik $cleanTopic ini, kamu masih santai-santai aja?")
                hooks.add("🚨 Rahasia tersembunyi $cleanTopic yang belum banyak orang tahu di Indonesia!")
                hooks.add("🔥 Kenapa semua kreator lagi heboh bahas $cleanTopic hari ini? Ini alasannya!")
            }
            ContentTone.GEN_Z -> {
                hooks.add("😎 Real cuy, spill jujur soal $cleanTopic yang selama ini cuma jadi rahasia inner circle.")
                hooks.add("😭 Demi apa baru tahu trik $cleanTopic ini sekarang?! Kemana aja gue selama ini woy!")
                hooks.add("💯 Gak usah overthinking, gini cara paling sat-set eksekusi $cleanTopic.")
                hooks.add("💅 Mau spill dikit hack $cleanTopic yang bikin hidup auto upgrade.")
                hooks.add("👀 No cap, $cleanTopic ini beneran game-changer banget sumpah!")
            }
            ContentTone.PROFESIONAL -> {
                hooks.add("👔 5 strategi krusial dalam $cleanTopic yang wajib dipahami para leader dan profesional.")
                hooks.add("📈 Bagaimana mengoptimalkan $cleanTopic untuk meningkatkan ROI dan efisiensi hingga 300%.")
                hooks.add("🎯 Framework teruji dalam mengeksekusi $cleanTopic secara sistematis.")
                hooks.add("💼 Pelajaran manajemen berharga: Apa yang sering luput saat menerapkan $cleanTopic.")
                hooks.add("🔍 Analisis mendalam: Masa depan $cleanTopic dan dampaknya pada industri.")
            }
            ContentTone.MOTIVASI -> {
                hooks.add("🚀 Kamu bukan gak mampu di $cleanTopic, kamu cuma belum mulai dengan konsisten!")
                hooks.add("🔥 Rasa takut gagal di $cleanTopic itu normal, yang gak normal adalah berhenti mencoba.")
                hooks.add("⚡ Mulai hari ini, buang semua alasan kamu soal $cleanTopic. Saatnya buktikan!")
                hooks.add("💪 Orang lain udah melangkah jauh di $cleanTopic, kapan giliran kamu bangkit?")
                hooks.add("🌟 Satu keputusan kecil kamu soal $cleanTopic hari ini bisa mengubah 1 tahun ke depan.")
            }
        }

        if (platform == SocialPlatform.TWITTER_X) {
            hooks[0] = "🧵 " + hooks[0].replace("🚨 ", "").replace("🔥 ", "")
        } else if (platform == SocialPlatform.TIKTOK) {
            hooks[0] = "🎵 [0:00-0:03] " + hooks[0]
        }

        return hooks
    }

    fun generateViralPackage(
        topic: String,
        platform: SocialPlatform,
        tone: ContentTone,
        format: ContentFormat
    ): ViralDraft {
        val cleanTopic = if (topic.isBlank()) "Ide Konten Viral" else topic.trim()
        val hooks = generateHookOptions(cleanTopic, tone, platform)
        val selectedHook = hooks.first()

        val hashtags = generateHashtags(cleanTopic, platform)
        val cta = CTA_PRESETS.random()

        val body = buildBodyContent(cleanTopic, platform, tone, format)
        val fullContent = assembleFullContent(selectedHook, body, cta, hashtags, platform, format)

        return ViralDraft(
            title = "Konten: $cleanTopic",
            topic = cleanTopic,
            platform = platform,
            tone = tone,
            format = format,
            hook = selectedHook,
            body = body,
            cta = cta,
            hashtags = hashtags,
            fullContent = fullContent
        )
    }

    // Generates rich, structured Isi Konten & Pembahasan
    fun buildBodyContent(
        topic: String,
        platform: SocialPlatform,
        tone: ContentTone,
        format: ContentFormat
    ): String {
        val sb = StringBuilder()

        when (format) {
            ContentFormat.FULL_SCRIPT -> {
                when (platform) {
                    SocialPlatform.TIKTOK, SocialPlatform.YOUTUBE_SHORTS -> {
                        sb.append("⚠️ MASALAH (Detik 03-12):\n")
                        sb.append("Banyak banget yang ngerasa udah nyoba $topic mati-matian tapi hasilnya nihil. Masalahnya bukan di modal atau bakat, tapi cara eksekusinya yang keliru dari awal.\n\n")

                        sb.append("💡 ISI PEMBAHASAN & VALUE DAGING (Detik 12-45):\n")
                        sb.append("Ada 3 aturan penting yang wajib kamu terapkan:\n")
                        sb.append("1. Pangkas 80% hal yang gak penting — fokus cuma di 1 tindakan yang berdampak paling besar.\n")
                        sb.append("2. Bangun sistem 15 menit per hari. Konsistensi harian jauh lebih dahsyat daripada kerja marathon tapi langsung burnout.\n")
                        sb.append("3. Dokumentasikan data progres kamu setiap minggu agar tahu apa yang bekerja dan apa yang perlu diubah.\n\n")

                        sb.append("🤯 PLOT TWIST / AHA MOMENT (Detik 45-55):\n")
                        sb.append("Begitu kamu ganti pola pikir dari 'menunggu sempurna' ke 'mulai dengan versi minimalis', perkembangannya bakal kerasa dalam hitungan hari!")
                    }
                    SocialPlatform.TWITTER_X -> {
                        sb.append("Berikut intisari penting soal $topic yang dirangkum tanpa basa-basi: 👇\n\n")
                        sb.append("1/ Hambatan Terbesar:\nBukan kurang ilmu, tapi overthinking dan takut gagal sebelum mulai.\n\n")
                        sb.append("2/ Solusi Praktis:\nMulailah dari versi paling sederhana. Iterasi lebih penting daripada rencana di atas kertas.\n\n")
                        sb.append("3/ Formula Pengali:\nManfaatkan alat bantu dan template agar pekerjaan berulang selesai 3x lebih cepat.")
                    }
                    SocialPlatform.LINKEDIN -> {
                        sb.append("Dalam dunia profesional dan bisnis, banyak asumsi keliru mengenai $topic.\n\n")
                        sb.append("Berdasarkan pengamatan di lapangan, ada 3 fundamental utama yang membedakan mereka yang berhasil dan yang stagnan:\n\n")
                        sb.append("• Kejelasan Eksekusi: Menghilangkan distraksi yang hanya terlihat produktif namun tidak menghasilkan dampak riil.\n")
                        sb.append("• Fleksibilitas Terhadap Data: Cepat beradaptasi terhadap respons pasar daripada terpaku pada asumsi lama.\n")
                        sb.append("• Keberlanjutan Sistem: Membangun alur kerja yang dapat diulang secara konsisten tanpa mengorbankan kualitas.")
                    }
                    else -> {
                        sb.append("Seringkali kita merasa bahwa $topic itu rumit dan butuh waktu lama untuk dikuasai.\n\n")
                        sb.append("Padahal, jika diurai menjadi langkah konkret, kuncinya hanya ada 3 hal:\n")
                        sb.append("✔️ Langkah 1: Pahami akar masalah sebelum mencari solusi cepat.\n")
                        sb.append("✔️ Langkah 2: Fokus pada fondasi utama daripada tergiur tren sesaat.\n")
                        sb.append("✔️ Langkah 3: Evaluasi secara berkala dan perbaiki celah kecil setiap hari.")
                    }
                }
            }
            ContentFormat.CAROUSEL_THREAD -> {
                sb.append("👉 SLIDE 1 (COVER):\n")
                sb.append("[Visual / Teks Besar]: Geser ke kiri untuk bedah tuntas solusinya! ➡️\n\n")

                sb.append("👉 SLIDE 2 (MASALAH):\n")
                sb.append("Kenapa kebanyakan orang mentok di $topic?\n")
                sb.append("Karena mereka terjebak pada mitos lama yang sudah tidak efektif di era sekarang.\n\n")

                sb.append("👉 SLIDE 3 (POIN KUNCI #1):\n")
                sb.append("1. Ganti Metode Rumit Menjadi Langkah Mikro.\n")
                sb.append("Selesaikan 1 aksi terukur dalam 10 menit pertama setiap hari.\n\n")

                sb.append("👉 SLIDE 4 (POIN KUNCI #2):\n")
                sb.append("2. Gunakan Tools Pendukung.\n")
                sb.append("Jangan ulangi semua hal dari nol. Gunakan automasi dan template teruji.\n\n")

                sb.append("👉 SLIDE 5 (POIN KUNCI #3):\n")
                sb.append("3. Review Berkala.\n")
                sb.append("Ketahui metrik yang paling berpengaruh terhadap pertumbuhanmu.")
            }
            ContentFormat.CAPTION_HASHTAGS -> {
                sb.append("Sering banget denger keluhan seputar $topic akhir-akhir ini.\n\n")
                sb.append("Kenyataannya, kunci suksesnya jauh lebih sederhana dari yang kita kira:\n\n")
                sb.append("1. Jangan menunggu waktu yang tepat, mulailah dengan apa yang ada di tanganmu sekarang.\n")
                sb.append("2. Kurangi distraksi dan prioritaskan kualitas di atas kuantitas tanpa arah.\n")
                sb.append("3. Evaluasi hasil setiap minggu agar kamu terus melangkah ke arah yang benar.")
            }
            ContentFormat.HOOK_PACK -> {
                sb.append("💡 CARA PENYAMPAIAN HOOK:\n")
                sb.append("- Tampilkan teks hook di detik 0-2 dengan ukuran besar dan kontras tinggi.\n")
                sb.append("- Gunakan intonasi tegas dan eye contact langsung ke lensa kamera.\n")
                sb.append("- Beri jeda 0.5 detik setelah hook sebelum masuk ke inti pembahasan.")
            }
        }

        return sb.toString()
    }

    fun assembleFullContent(
        hook: String,
        body: String,
        cta: String,
        hashtags: List<String>,
        platform: SocialPlatform,
        format: ContentFormat
    ): String {
        val sb = StringBuilder()
        sb.append(hook.trim())
        sb.append("\n\n")
        sb.append(body.trim())

        if (cta.isNotBlank()) {
            sb.append("\n\n")
            sb.append(cta.trim())
        }

        if (hashtags.isNotEmpty()) {
            sb.append("\n\n")
            sb.append(hashtags.joinToString(" "))
        }

        return sb.toString()
    }

    fun generateHashtags(topic: String, platform: SocialPlatform): List<String> {
        val tags = mutableListOf<String>()

        when (platform) {
            SocialPlatform.TIKTOK -> tags.addAll(listOf("#fyp", "#fypシ", "#viralindonesia", "#serunyabelajar"))
            SocialPlatform.INSTAGRAM -> tags.addAll(listOf("#reelsindonesia", "#explorepage", "#tipsindonesia", "#kontenkreator"))
            SocialPlatform.TWITTER_X -> tags.addAll(listOf("#TrendingTopic", "#IndoTwitter", "#ThreadViral"))
            SocialPlatform.LINKEDIN -> tags.addAll(listOf("#Leadership", "#CareerGrowth", "#Productivity", "#Indonesia"))
            SocialPlatform.YOUTUBE_SHORTS -> tags.addAll(listOf("#shorts", "#youtubeshorts", "#viralshorts", "#trending"))
            SocialPlatform.THREADS_FB -> tags.addAll(listOf("#threadsindo", "#sharingiscaring", "#relatable"))
        }

        val topicCleanTag = topic.filter { it.isLetterOrDigit() }.take(18)
        if (topicCleanTag.length >= 3) {
            tags.add(1, "#$topicCleanTag")
        }

        return tags.distinct().take(8)
    }

    fun analyzeViralScore(text: String, platform: SocialPlatform): ViralScore {
        if (text.isBlank()) {
            return ViralScore(
                score = 0,
                ratingLabel = "Belum Ada Teks",
                triggerWordsFound = emptyList(),
                tips = listOf("Tulis atau generate konten terlebih dahulu."),
                charCount = 0,
                wordCount = 0,
                estReadingSeconds = 0
            )
        }

        val chars = text.length
        val words = text.trim().split("\\s+".toRegex()).filter { it.isNotBlank() }
        val wordCount = words.size
        val estSeconds = ((wordCount / 180.0) * 60).toInt().coerceAtLeast(1)

        val powerWords = listOf(
            "rahasia", "stop", "kenapa", "ternyata", "kesalahan", "trik", "tips",
            "bocoran", "rugi", "gratis", "mudah", "nyesel", "jangan", "fatal",
            "fakta", "terbukti", "cepat", "cuan", "gagal", "berhasil", "kontroversial",
            "spill", "real", "overthinking", "kebohongan", "alasan", "viral"
        )

        val lowerText = text.lowercase()
        val foundTriggers = powerWords.filter { lowerText.contains(it) }

        var score = 40
        score += (foundTriggers.size * 7).coerceAtMost(30)

        val lineBreaks = text.count { it == '\n' }
        if (lineBreaks >= 4) score += 10 else if (lineBreaks >= 2) score += 5
        val hasEmoji = text.any { Character.getType(it) == Character.SURROGATE.toInt() || it.code > 1000 }
        if (hasEmoji) score += 5

        val hasCta = listOf("komen", "share", "simpan", "save", "follow", "klik", "bio", "diskusi", "tulis").any {
            lowerText.contains(it)
        }
        if (hasCta) score += 10

        val tips = mutableListOf<String>()
        platform.charLimit?.let { limit ->
            if (chars > limit) {
                score -= 20
                tips.add("⚠️ Teks melebihi batas karakter ${platform.displayName} ($chars / $limit karakter). Kurangi agar muat!")
            }
        }

        if (foundTriggers.isEmpty()) {
            tips.add("🔥 Tambahkan 'power words' (rahasia, stop, jangan, ternyata, fatal) di baris pertama.")
        }
        if (!hasCta) {
            tips.add("🚀 Belum ada Call To Action (ajakan simpan/komen/share). Gunakan tombol CTA cepat.")
        }
        if (lineBreaks < 3) {
            tips.add("👁️ Beri spasi baris antar paragraf agar pembaca tidak malas membaca dinding teks.")
        }

        val clampedScore = score.coerceIn(15, 99)
        val ratingLabel = when {
            clampedScore >= 85 -> "🚀 Potensi FYP Meledak!"
            clampedScore >= 70 -> "🔥 Sangat Menarik & Viral"
            clampedScore >= 50 -> "👍 Konten Cukup Bagus"
            else -> "⚠️ Perlu Ditajamkan Lagi"
        }

        return ViralScore(
            score = clampedScore,
            ratingLabel = ratingLabel,
            triggerWordsFound = foundTriggers,
            tips = tips,
            charCount = chars,
            wordCount = wordCount,
            estReadingSeconds = estSeconds
        )
    }
}
