package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.engine.ViralTemplateEngine

@Composable
fun QuickFormatBar(
    onBoldHook: () -> Unit,
    onCycleHook: () -> Unit,
    onCleanSpacing: () -> Unit,
    onAppendCta: (String) -> Unit,
    onAppendEmoji: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showCtaMenu by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ALAT EDIT CEPAT",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Edit sebelum share 🚀",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            // Bold Hook Action
            item {
                QuickToolButton(
                    testTag = "btn_bold_hook",
                    emoji = "🔤",
                    label = "Tebalkan Hook",
                    onClick = onBoldHook
                )
            }

            // Cycle Alternate Hook
            item {
                QuickToolButton(
                    testTag = "btn_swap_hook",
                    emoji = "🔄",
                    label = "Ganti Hook",
                    onClick = onCycleHook
                )
            }

            // Clean Spacing Action
            item {
                QuickToolButton(
                    testTag = "btn_clean_space",
                    emoji = "🧹",
                    label = "Rapikan Spasi",
                    onClick = onCleanSpacing
                )
            }

            // CTA Presets Dropdown
            item {
                Box {
                    QuickToolButton(
                        testTag = "btn_add_cta",
                        emoji = "💬",
                        label = "+ Call To Action",
                        onClick = { showCtaMenu = true }
                    )

                    DropdownMenu(
                        expanded = showCtaMenu,
                        onDismissRequest = { showCtaMenu = false }
                    ) {
                        Text(
                            text = "Pilih Call to Action (CTA):",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                        ViralTemplateEngine.CTA_PRESETS.forEach { cta ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = cta,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                },
                                onClick = {
                                    onAppendCta(cta)
                                    showCtaMenu = false
                                }
                            )
                        }
                    }
                }
            }

            // Hook Emojis
            items(ViralTemplateEngine.HOOK_EMOJIS) { emoji ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                        .clickable { onAppendEmoji(emoji) }
                        .padding(horizontal = 10.dp, vertical = 7.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = emoji, fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun QuickToolButton(
    testTag: String,
    emoji: String,
    label: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .testTag(testTag)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = emoji, fontSize = 15.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
