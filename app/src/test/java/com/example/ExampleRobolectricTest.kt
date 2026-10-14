package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.engine.ViralTemplateEngine
import com.example.model.ContentFormat
import com.example.model.ContentTone
import com.example.model.SocialPlatform
import com.example.util.TextFormatter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("ViralCraft", appName)
  }

  @Test
  fun `test viral template generator generates full content`() {
    val draft = ViralTemplateEngine.generateViralPackage(
      topic = "Trik rahasia FYP TikTok",
      platform = SocialPlatform.TIKTOK,
      tone = ContentTone.KONTROVERSIAL,
      format = ContentFormat.FULL_SCRIPT
    )

    assertNotNull(draft)
    assertTrue(draft.fullContent.isNotBlank())
    assertTrue(draft.hook.isNotBlank())
    assertTrue(draft.body.isNotBlank())
    assertTrue(draft.hashtags.isNotEmpty())
  }

  @Test
  fun `test text formatter bold unicode`() {
    val original = "STOP SCROLL"
    val bold = TextFormatter.toBoldUnicode(original)
    assertTrue(bold.isNotEmpty())
    // Ensure unicode transformed
    assertTrue(bold != original)
  }

  @Test
  fun `test hook swapper replaces first line`() {
    val originalText = "Hook Lama Disini\n\nIsi konten kedua..."
    val newHook = "Hook Baru Super Viral 🔥"
    val swapped = TextFormatter.replaceHook(originalText, newHook)
    assertTrue(swapped.startsWith(newHook))
  }
}
