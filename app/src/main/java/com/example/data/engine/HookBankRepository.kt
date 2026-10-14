package com.example.data.engine

data class HookTemplate(
    val id: String,
    val category: String,
    val template: String,
    val example: String,
    val psychology: String
)

object HookBankRepository {
    val categories = listOf(
        "Semua",
        "Pattern Interrupt",
        "Curiosity Gap",
        "Mitos & Kontrarian",
        "Fakta & Angka",
        "Story & Vulnerability",
        "Trik Cepat"
    )

    val hooks = listOf(
        HookTemplate(
            id = "pi_1",
            category = "Pattern Interrupt",
            template = "STOP SCROLL! Sebelum kamu [aktivitas umum], kamu harus tahu ini...",
            example = "STOP SCROLL! Sebelum kamu beli kopi kekinian lagi, kamu harus tahu ini...",
            psychology = "Menghentikan refleks scrolling ibu jari dengan perintah mendadak."
        ),
        HookTemplate(
            id = "pi_2",
            category = "Pattern Interrupt",
            template = "Tunggu dulu, jangan skip video ini kalau kamu masih sering [masalah]!",
            example = "Tunggu dulu, jangan skip video ini kalau kamu masih sering ngerasa gaji numpang lewat!",
            psychology = "Langsung memanggil target audiens dengan rasa sakit utama mereka."
        ),
        HookTemplate(
            id = "pi_3",
            category = "Pattern Interrupt",
            template = "Ini alasan kenapa 99% orang gagal di [bidang] dan cuma buang-buang waktu.",
            example = "Ini alasan kenapa 99% orang gagal diet di minggu kedua dan cuma buang-buang waktu.",
            psychology = "Menciptakan kekhawatiran bahwa mereka ada di kelompok yang gagal."
        ),
        HookTemplate(
            id = "cg_1",
            category = "Curiosity Gap",
            template = "Jujur gue kaget banget pas baru tahu trik [topik] ini kemarin...",
            example = "Jujur gue kaget banget pas baru tahu trik prompt AI ini kemarin...",
            psychology = "Reaksi emosional pembicara membuat penonton ingin tahu apa yang bikin kaget."
        ),
        HookTemplate(
            id = "cg_2",
            category = "Curiosity Gap",
            template = "Kenapa gak ada mentor yang ngasih tahu rahasia [topik] ini dari dulu ya?",
            example = "Kenapa gak ada yang ngasih tahu rahasia negosiasi gaji ini dari dulu ya?",
            psychology = "Menyiratkan ada informasi eksklusif yang sengaja ditutupi."
        ),
        HookTemplate(
            id = "cg_3",
            category = "Curiosity Gap",
            template = "Ada satu tombol tersembunyi di [tools/aplikasi] yang bisa ngubah segalanya.",
            example = "Ada satu tombol tersembunyi di Excel yang bisa ngurangin lembur kamu 2 jam.",
            psychology = "Spesifik, misterius, dan menjanjikan solusi instan."
        ),
        HookTemplate(
            id = "mk_1",
            category = "Mitos & Kontrarian",
            template = "Stop percaya mitos kalau kamu butuh [hal umum] buat mulai [tujuan]!",
            example = "Stop percaya mitos kalau kamu butuh kamera mahal buat jadi konten kreator!",
            psychology = "Menghancurkan hambatan mental terbesar audiens."
        ),
        HookTemplate(
            id = "mk_2",
            category = "Mitos & Kontrarian",
            template = "Semua orang bilang [A], padahal yang sebenarnya bekerja adalah [B].",
            example = "Semua orang bilang harus bangun jam 4 pagi, padahal kuncinya ada di manajemen energi.",
            psychology = "Menantang konsensus umum membuat orang penasaran ingin membantah atau setuju."
        ),
        HookTemplate(
            id = "mk_3",
            category = "Mitos & Kontrarian",
            template = "Gue tahu ini bakal bikin banyak orang kesel, tapi ini kenyataan brutalnya.",
            example = "Gue tahu ini bakal bikin banyak orang kesel, tapi sertifikat kursus kamu gak jamin dapat kerja.",
            psychology = "Keberanian memicu perdebatan panas di kolom komentar (engagement tinggi)."
        ),
        HookTemplate(
            id = "fa_1",
            category = "Fakta & Angka",
            template = "93% orang melakukan 1 kesalahan fatal ini waktu [kegiatan].",
            example = "93% orang melakukan 1 kesalahan fatal ini waktu bikin CV lamaran.",
            psychology = "Angka spesifik meningkatkan kredibilitas dan memicu rasa takut bersalah."
        ),
        HookTemplate(
            id = "fa_2",
            category = "Fakta & Angka",
            template = "Cuma butuh [waktu singkat] buat kamu melipatgandakan [hasil].",
            example = "Cuma butuh 5 menit sehari buat kamu melipatgandakan vocabulary bahasa Inggris.",
            psychology = "Effort kecil dengan reward besar adalah magnet perhatian manusia."
        ),
        HookTemplate(
            id = "fa_3",
            category = "Fakta & Angka",
            template = "Gimana cara gue ngubah [modal kecil] jadi [pencapaian besar] dalam [waktu].",
            example = "Gimana cara gue ngubah modal 50 ribu jadi omset 15 juta dalam 3 bulan.",
            psychology = "Formula kontras angka ekstrem yang sangat terbukti menarik atensi."
        ),
        HookTemplate(
            id = "sv_1",
            category = "Story & Vulnerability",
            template = "Tahun lalu gue berada di titik terendah sampai momen ini terjadi...",
            example = "Tahun lalu gue berada di titik terendah, tabungan sisa 200 ribu...",
            psychology = "Empati manusia langsung terhubung dengan perjuangan orang lain."
        ),
        HookTemplate(
            id = "sv_2",
            category = "Story & Vulnerability",
            template = "Jangan pernah ulangi kebodohan terbesar gue waktu pertama kali [tindakan].",
            example = "Jangan pernah ulangi kebodohan terbesar gue waktu pertama kali nyoba jualan online.",
            psychology = "Orang suka belajar dari kesalahan orang lain agar tidak rugi sendiri."
        ),
        HookTemplate(
            id = "tc_1",
            category = "Trik Cepat",
            template = "Cara sat-set [menyelesaikan masalah] tanpa perlu [hal ribet].",
            example = "Cara sat-set bersihin memori HP tanpa perlu hapus foto dan video kenangan.",
            psychology = "Pendekatan solusi praktis tanpa friksi."
        ),
        HookTemplate(
            id = "tc_2",
            category = "Trik Cepat",
            template = "Simpan cara ini: 3 langkah anti-ribet buat kamu yang mau [hasil].",
            example = "Simpan cara ini: 3 langkah anti-ribet buat kamu yang mau mulai investasi reksadana.",
            psychology = "Framework 3 langkah mudah diproses otak manusia."
        )
    )
}
