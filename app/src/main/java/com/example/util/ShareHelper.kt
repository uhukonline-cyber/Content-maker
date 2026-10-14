package com.example.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.model.SocialPlatform

object ShareHelper {

    fun copyToClipboard(context: Context, text: String, label: String = "Viral Content"): Boolean {
        return try {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(label, text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "✅ Teks berhasil disalin ke clipboard!", Toast.LENGTH_SHORT).show()
            true
        } catch (e: Exception) {
            Toast.makeText(context, "Gagal menyalin: ${e.message}", Toast.LENGTH_SHORT).show()
            false
        }
    }

    fun shareContent(context: Context, text: String, platform: SocialPlatform? = null) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        // Try direct target app if specified and available
        val targetPackage = platform?.packageName
        if (targetPackage != null) {
            try {
                val packageManager = context.packageManager
                val intentForPackage = Intent(sendIntent).apply {
                    setPackage(targetPackage)
                }
                val resolveInfos = packageManager.queryIntentActivities(intentForPackage, 0)
                if (resolveInfos.isNotEmpty()) {
                    context.startActivity(Intent.createChooser(intentForPackage, "Buka di ${platform.displayName}"))
                    return
                }
            } catch (e: Exception) {
                // Fallback to generic chooser
            }
        }

        // Generic chooser
        val chooserTitle = if (platform != null) "Bagikan ke ${platform.displayName}" else "Bagikan Konten Viral"
        val chooserIntent = Intent.createChooser(sendIntent, chooserTitle).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            context.startActivity(chooserIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "Gagal membagikan: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun shareToWhatsApp(context: Context, text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
            setPackage("com.whatsapp")
        }
        try {
            context.startActivity(sendIntent)
        } catch (e: Exception) {
            // Fallback generic
            val fallback = Intent.createChooser(
                Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, text)
                    type = "text/plain"
                },
                "Bagikan via WhatsApp"
            )
            context.startActivity(fallback)
        }
    }
}
