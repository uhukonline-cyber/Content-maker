package com.example.data.service

import android.util.Log
import com.example.BuildConfig
import com.example.data.engine.ViralTemplateEngine
import com.example.model.ContentFormat
import com.example.model.ContentTone
import com.example.model.SocialPlatform
import com.example.model.ViralDraft
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class GeminiContentService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(25, TimeUnit.SECONDS)
        .build()

    fun isAiConfigured(): Boolean {
        val key = getApiKey()
        return key.isNotBlank() && key != "MY_GEMINI_API_KEY"
    }

    private fun getApiKey(): String {
        return try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }
    }

    suspend fun generateContent(
        topic: String,
        platform: SocialPlatform,
        tone: ContentTone,
        format: ContentFormat,
        customInstructions: String = ""
    ): ViralDraft = withContext(Dispatchers.IO) {
        val apiKey = getApiKey()

        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext ViralTemplateEngine.generateViralPackage(
                topic = topic,
                platform = platform,
                tone = tone,
                format = format
            )
        }

        try {
            val prompt = buildPrompt(topic, platform, tone, format, customInstructions)
            val jsonPayload = JSONObject().apply {
                val contents = JSONArray().apply {
                    put(JSONObject().apply {
                        val parts = JSONArray().apply {
                            put(JSONObject().put("text", prompt))
                        }
                        put("parts", parts)
                    })
                }
                put("contents", contents)
                put("generationConfig", JSONObject().apply {
                    put("temperature", 0.75)
                    put("maxOutputTokens", 1200)
                })
            }

            val requestBody = jsonPayload.toString().toRequestBody("application/json".toMediaType())
            val url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=$apiKey"

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    Log.w("GeminiService", "API call unsuccessful: ${response.code}, falling back to template")
                    return@withContext ViralTemplateEngine.generateViralPackage(topic, platform, tone, format)
                }

                val responseBody = response.body?.string() ?: ""
                val responseJson = JSONObject(responseBody)
                val candidates = responseJson.optJSONArray("candidates")
                if (candidates != null && candidates.length() > 0) {
                    val candidate = candidates.getJSONObject(0)
                    val contentObj = candidate.optJSONObject("content")
                    val parts = contentObj?.optJSONArray("parts")
                    val text = parts?.optJSONObject(0)?.optString("text") ?: ""

                    if (text.isNotBlank()) {
                        val lines = text.trim().lines().filter { it.isNotBlank() }
                        val hook = lines.firstOrNull() ?: topic
                        val body = lines.drop(1).joinToString("\n\n")
                        val hashtags = ViralTemplateEngine.generateHashtags(topic, platform)
                        val cta = ViralTemplateEngine.CTA_PRESETS.random()

                        return@withContext ViralDraft(
                            title = "AI: $topic",
                            topic = topic,
                            platform = platform,
                            tone = tone,
                            format = format,
                            hook = hook,
                            body = body,
                            cta = cta,
                            hashtags = hashtags,
                            fullContent = text
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("GeminiService", "Error generating with AI", e)
        }

        ViralTemplateEngine.generateViralPackage(topic, platform, tone, format)
    }

    private fun buildPrompt(
        topic: String,
        platform: SocialPlatform,
        tone: ContentTone,
        format: ContentFormat,
        customInstructions: String
    ): String {
        return """
            Kamu adalah Pakar Konten Kreator & Copywriter Viral nomor 1 di Indonesia.
            Tugas: Buat konten viral yang memicu FYP, engagement tinggi, share, dan simpan.

            Detail Brief:
            - Platform Tujuan: ${platform.displayName}
            - Topik Utama: $topic
            - Gaya Bahasa / Tone: ${tone.displayName} (${tone.description})
            - Format Konten: ${format.title} (${format.subtitle})
            ${if (customInstructions.isNotBlank()) "- Instruksi Tambahan: $customInstructions" else ""}

            Pedoman Penulisan:
            1. Baris pertama WAJIB berupa HOOK MAUT 3-detik yang menghentikan scroll jempol (Pattern interrupt atau curiosity gap).
            2. Berikan BAGIAN ISI KONTEN & PEMBAHASAN yang padat, berbobot, dan menarik (poin-poin daging, penjelasan praktis, bukan hanya basa-basi).
            3. Berikan spasi baris yang lega agar nyaman dibaca.
            4. Sertakan Call To Action (CTA) yang kuat di akhir.
            5. Tambahkan hashtag relevan di paling bawah.
            Output teks langsung tanpa prolog.
        """.trimIndent()
    }
}
