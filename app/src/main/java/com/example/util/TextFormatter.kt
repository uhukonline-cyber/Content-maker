package com.example.util

object TextFormatter {

    // Converts ASCII letters to Mathematical Sans-Serif Bold Unicode (universally supported across TikTok, IG, X, LinkedIn)
    fun toBoldUnicode(input: String): String {
        val sb = StringBuilder()
        for (ch in input) {
            when (ch) {
                in 'A'..'Z' -> {
                    val codePoint = 0x1D5D4 + (ch - 'A')
                    sb.append(String(Character.toChars(codePoint)))
                }
                in 'a'..'z' -> {
                    val codePoint = 0x1D5EE + (ch - 'a')
                    sb.append(String(Character.toChars(codePoint)))
                }
                in '0'..'9' -> {
                    val codePoint = 0x1D7EC + (ch - '0')
                    sb.append(String(Character.toChars(codePoint)))
                }
                else -> sb.append(ch)
            }
        }
        return sb.toString()
    }

    // Bold the first line (the Hook) of any content
    fun makeHookBold(fullText: String): String {
        val lines = fullText.lines()
        if (lines.isEmpty()) return fullText
        val firstLine = lines.first()
        val rest = lines.drop(1).joinToString("\n")
        val boldFirstLine = toBoldUnicode(firstLine)
        return if (rest.isBlank()) boldFirstLine else "$boldFirstLine\n$rest"
    }

    // Swap the first line / hook with a new hook
    fun replaceHook(fullText: String, newHook: String): String {
        val lines = fullText.lines()
        if (lines.isEmpty()) return newHook
        // find where the hook ends (usually first non-blank line)
        val firstNonBlankIndex = lines.indexOfFirst { it.isNotBlank() }
        if (firstNonBlankIndex == -1) return newHook

        val mutableLines = lines.toMutableList()
        mutableLines[firstNonBlankIndex] = newHook
        return mutableLines.joinToString("\n")
    }

    // Append CTA
    fun appendCta(fullText: String, ctaText: String): String {
        val clean = fullText.trimEnd()
        return "$clean\n\n$ctaText"
    }

    // Append Hashtags
    fun appendHashtag(fullText: String, tag: String): String {
        val formattedTag = if (tag.startsWith("#")) tag else "#$tag"
        return if (fullText.endsWith(" ")) "$fullText$formattedTag" else "$fullText $formattedTag"
    }

    // Clean extra empty blank lines
    fun cleanSpacing(fullText: String): String {
        return fullText.replace(Regex("\n{3,}"), "\n\n").trim()
    }
}
