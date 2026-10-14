package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.engine.ViralTemplateEngine
import com.example.data.local.AppDatabase
import com.example.data.local.SavedDraftEntity
import com.example.data.service.GeminiContentService
import com.example.model.ContentFormat
import com.example.model.ContentTone
import com.example.model.SocialPlatform
import com.example.model.ViralDraft
import com.example.model.ViralScore
import com.example.util.TextFormatter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppScreen(val label: String, val iconEmoji: String) {
    GENERATOR("Generator", "⚡"),
    EDITOR("Editor & Share", "✍️"),
    SAVED("Tersimpan", "💾"),
    HOOK_BANK("Bank Hook", "🎯")
}

data class GeneratorUiState(
    val topic: String = "",
    val selectedPlatform: SocialPlatform = SocialPlatform.TIKTOK,
    val selectedTone: ContentTone = ContentTone.KONTROVERSIAL,
    val selectedFormat: ContentFormat = ContentFormat.FULL_SCRIPT,
    val isGenerating: Boolean = false,
    val hookText: String = "",
    val bodyText: String = "",
    val ctaText: String = "",
    val hashtagsText: String = "",
    val editedContent: String = "",
    val currentDraft: ViralDraft? = null,
    val alternateHooks: List<String> = emptyList(),
    val currentHookIndex: Int = 0,
    val viralScore: ViralScore = ViralScore(0, "Siap Dibuat", emptyList(), emptyList(), 0, 0, 0),
    val currentScreen: AppScreen = AppScreen.GENERATOR,
    val isAiAvailable: Boolean = false,
    val statusMessage: String? = null
)

class ViralGeneratorViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val draftDao = db.draftDao()
    private val geminiService = GeminiContentService()

    private val _uiState = MutableStateFlow(
        GeneratorUiState(isAiAvailable = geminiService.isAiConfigured())
    )
    val uiState: StateFlow<GeneratorUiState> = _uiState.asStateFlow()

    val savedDrafts: StateFlow<List<ViralDraft>> = draftDao.getAllDrafts()
        .map { list -> list.map { it.toViralDraft() } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        val defaultTopic = "Kesalahan fatal pemula saat mulai jualan online"
        val starterDraft = ViralTemplateEngine.generateViralPackage(
            topic = defaultTopic,
            platform = SocialPlatform.TIKTOK,
            tone = ContentTone.KONTROVERSIAL,
            format = ContentFormat.FULL_SCRIPT
        )
        val initialScore = ViralTemplateEngine.analyzeViralScore(starterDraft.fullContent, SocialPlatform.TIKTOK)
        val initialHooks = ViralTemplateEngine.generateHookOptions(defaultTopic, ContentTone.KONTROVERSIAL, SocialPlatform.TIKTOK)

        _uiState.value = _uiState.value.copy(
            topic = defaultTopic,
            hookText = starterDraft.hook,
            bodyText = starterDraft.body,
            ctaText = starterDraft.cta,
            hashtagsText = starterDraft.hashtags.joinToString(" "),
            editedContent = starterDraft.fullContent,
            currentDraft = starterDraft,
            alternateHooks = initialHooks,
            viralScore = initialScore
        )
    }

    fun setTopic(topic: String) {
        _uiState.value = _uiState.value.copy(topic = topic)
    }

    fun selectPlatform(platform: SocialPlatform) {
        val currentContent = _uiState.value.editedContent
        val updatedScore = ViralTemplateEngine.analyzeViralScore(currentContent, platform)
        _uiState.value = _uiState.value.copy(
            selectedPlatform = platform,
            viralScore = updatedScore
        )
    }

    fun selectTone(tone: ContentTone) {
        _uiState.value = _uiState.value.copy(selectedTone = tone)
    }

    fun selectFormat(format: ContentFormat) {
        _uiState.value = _uiState.value.copy(selectedFormat = format)
    }

    fun navigateTo(screen: AppScreen) {
        _uiState.value = _uiState.value.copy(currentScreen = screen)
    }

    fun pickSurpriseTopic() {
        val randomTopic = ViralTemplateEngine.SURPRISE_TOPICS.random()
        _uiState.value = _uiState.value.copy(topic = randomTopic)
        generateContent(useAi = false)
    }

    fun generateContent(useAi: Boolean = false) {
        val currentState = _uiState.value
        val currentTopic = if (currentState.topic.isBlank()) "Tips Viral Hari Ini" else currentState.topic

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isGenerating = true, statusMessage = "Membuat hook dan isi konten viral...")

            val draft = if (useAi) {
                geminiService.generateContent(
                    topic = currentTopic,
                    platform = currentState.selectedPlatform,
                    tone = currentState.selectedTone,
                    format = currentState.selectedFormat
                )
            } else {
                ViralTemplateEngine.generateViralPackage(
                    topic = currentTopic,
                    platform = currentState.selectedPlatform,
                    tone = currentState.selectedTone,
                    format = currentState.selectedFormat
                )
            }

            val hooks = ViralTemplateEngine.generateHookOptions(
                topic = currentTopic,
                tone = currentState.selectedTone,
                platform = currentState.selectedPlatform
            )
            val score = ViralTemplateEngine.analyzeViralScore(draft.fullContent, currentState.selectedPlatform)

            _uiState.value = _uiState.value.copy(
                isGenerating = false,
                currentDraft = draft,
                hookText = draft.hook,
                bodyText = draft.body,
                ctaText = draft.cta,
                hashtagsText = draft.hashtags.joinToString(" "),
                editedContent = draft.fullContent,
                alternateHooks = hooks,
                currentHookIndex = 0,
                viralScore = score,
                statusMessage = null,
                currentScreen = AppScreen.EDITOR
            )
        }
    }

    fun updateHookText(newHook: String) {
        val full = reassembleContent(newHook, _uiState.value.bodyText, _uiState.value.ctaText, _uiState.value.hashtagsText)
        val score = ViralTemplateEngine.analyzeViralScore(full, _uiState.value.selectedPlatform)
        _uiState.value = _uiState.value.copy(
            hookText = newHook,
            editedContent = full,
            viralScore = score
        )
    }

    fun updateBodyText(newBody: String) {
        val full = reassembleContent(_uiState.value.hookText, newBody, _uiState.value.ctaText, _uiState.value.hashtagsText)
        val score = ViralTemplateEngine.analyzeViralScore(full, _uiState.value.selectedPlatform)
        _uiState.value = _uiState.value.copy(
            bodyText = newBody,
            editedContent = full,
            viralScore = score
        )
    }

    fun updateCtaText(newCta: String) {
        val full = reassembleContent(_uiState.value.hookText, _uiState.value.bodyText, newCta, _uiState.value.hashtagsText)
        val score = ViralTemplateEngine.analyzeViralScore(full, _uiState.value.selectedPlatform)
        _uiState.value = _uiState.value.copy(
            ctaText = newCta,
            editedContent = full,
            viralScore = score
        )
    }

    fun updateFullEditedContent(newText: String) {
        val score = ViralTemplateEngine.analyzeViralScore(newText, _uiState.value.selectedPlatform)
        _uiState.value = _uiState.value.copy(
            editedContent = newText,
            viralScore = score
        )
    }

    private fun reassembleContent(hook: String, body: String, cta: String, hashtags: String): String {
        val parts = mutableListOf<String>()
        if (hook.isNotBlank()) parts.add(hook.trim())
        if (body.isNotBlank()) parts.add(body.trim())
        if (cta.isNotBlank()) parts.add(cta.trim())
        if (hashtags.isNotBlank()) parts.add(hashtags.trim())
        return parts.joinToString("\n\n")
    }

    fun cycleNextHook() {
        val hooks = _uiState.value.alternateHooks
        if (hooks.isEmpty()) return

        val nextIndex = (_uiState.value.currentHookIndex + 1) % hooks.size
        val newHook = hooks[nextIndex]
        updateHookText(newHook)
        _uiState.value = _uiState.value.copy(currentHookIndex = nextIndex)
    }

    fun applyHook(hook: String) {
        updateHookText(hook)
        _uiState.value = _uiState.value.copy(currentScreen = AppScreen.EDITOR)
    }

    fun applyBoldHook() {
        val currentHook = _uiState.value.hookText
        val boldHook = TextFormatter.toBoldUnicode(currentHook)
        updateHookText(boldHook)
    }

    fun cleanSpacing() {
        val cleanBody = TextFormatter.cleanSpacing(_uiState.value.bodyText)
        updateBodyText(cleanBody)
    }

    fun saveCurrentDraft() {
        viewModelScope.launch {
            val state = _uiState.value
            val currentDraft = state.currentDraft ?: return@launch
            val draftToSave = currentDraft.copy(
                hook = state.hookText,
                body = state.bodyText,
                cta = state.ctaText,
                fullContent = state.editedContent,
                createdAt = System.currentTimeMillis()
            )
            val entity = SavedDraftEntity.fromViralDraft(draftToSave)
            draftDao.insertDraft(entity)
        }
    }

    fun loadDraftIntoEditor(draft: ViralDraft) {
        val score = ViralTemplateEngine.analyzeViralScore(draft.fullContent, draft.platform)
        val hooks = ViralTemplateEngine.generateHookOptions(draft.topic, draft.tone, draft.platform)
        _uiState.value = _uiState.value.copy(
            topic = draft.topic,
            selectedPlatform = draft.platform,
            selectedTone = draft.tone,
            selectedFormat = draft.format,
            currentDraft = draft,
            hookText = draft.hook,
            bodyText = draft.body,
            ctaText = draft.cta,
            hashtagsText = draft.hashtags.joinToString(" "),
            editedContent = draft.fullContent,
            alternateHooks = hooks,
            viralScore = score,
            currentScreen = AppScreen.EDITOR
        )
    }

    fun deleteDraft(draftId: Long) {
        viewModelScope.launch {
            draftDao.deleteDraftById(draftId)
        }
    }

    fun toggleFavorite(draftId: Long, currentFav: Boolean) {
        viewModelScope.launch {
            draftDao.updateFavorite(draftId, !currentFav)
        }
    }
}
