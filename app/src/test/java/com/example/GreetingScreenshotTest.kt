package com.example

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import com.example.data.model.Flashcard
import com.example.ui.components.FlipFlashcard
import com.example.ui.theme.EnglishSwipeTheme
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel8, sdk = [36])
class GreetingScreenshotTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun card_screenshot() {
    val sampleCard = Flashcard(
        english = "stubborn",
        spanish = "terco / testarudo",
        phonetic = "/ˈstʌb.ərn/",
        definition = "Refusing to change one's mind or posture.",
        example = "He is too stubborn to admit he was wrong.",
        exampleTranslation = "Él es demasiado terco para admitir que estaba equivocado.",
        type = "Word",
        category = "Everyday",
        cefrLevel = "B1",
        emoji = "🫏"
    )

    composeTestRule.setContent {
      EnglishSwipeTheme {
        FlipFlashcard(
            card = sampleCard,
            isFlipped = false,
            onFlip = {},
            onPlayAudio = {},
            onToggleFavorite = {},
            modifier = Modifier.fillMaxSize().padding(16.dp)
        )
      }
    }

    composeTestRule.waitForIdle()

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/greeting.png")
  }
}

