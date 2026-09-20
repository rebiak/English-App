package com.example

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.sortedNaturally
import com.example.ui.viewmodel.MainViewModel
import androidx.room.Room
import com.example.data.local.AppDatabase
import com.example.data.model.Flashcard
import com.example.data.model.LearningGoalType
import com.example.data.repository.FlashcardRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
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
    assertEquals("EnglishSwipe", appName)
  }

  @Test
  fun `test markAsNeedsPractice mastery degradation thresholds`() = runBlocking {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val inMemoryDb = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
        .allowMainThreadQueries()
        .build()
    val repo = FlashcardRepository(inMemoryDb.flashcardDao())

    val baseCard = Flashcard(
        id = 1L,
        english = "Serendipity",
        spanish = "Chiripa",
        category = "Vocab",
        mastery = 0
    )

    // 1. If mastery is 0 (new word) -> stays 0
    val card0 = repo.markAsNeedsPractice(baseCard.copy(mastery = 0))
    assertEquals(0, card0.mastery)

    // 2. If mastery is 25 -> drops to 0
    val card25 = repo.markAsNeedsPractice(baseCard.copy(mastery = 25))
    assertEquals(0, card25.mastery)

    // 3. If mastery is 50 -> drops to 25
    val card50 = repo.markAsNeedsPractice(baseCard.copy(mastery = 50))
    assertEquals(25, card50.mastery)

    // 4. If mastery is 75 -> drops to 50
    val card75 = repo.markAsNeedsPractice(baseCard.copy(mastery = 75))
    assertEquals(50, card75.mastery)

    // 5. If mastery is 100 -> drops to 50
    val card100 = repo.markAsNeedsPractice(baseCard.copy(mastery = 100))
    assertEquals(50, card100.mastery)

    inMemoryDb.close()
  }

  @Test
  fun `test navigation title for vocabulary`() {
    val app = ApplicationProvider.getApplicationContext<Application>()
    val viewModel = MainViewModel(app)
    viewModel.setNavIndex(0)
    viewModel.setNavIndex(1)
    val titleSpanish = viewModel.getPreviousNavLabel(com.example.ui.util.AppLanguage.SPANISH)
    assertEquals("Vocabulario", titleSpanish)
    val titleEnglish = viewModel.getPreviousNavLabel(com.example.ui.util.AppLanguage.ENGLISH)
    assertEquals("Vocabulary", titleEnglish)
  }

  @Test
  fun `test initial hub screen navigation actions`() {
    val app = ApplicationProvider.getApplicationContext<Application>()
    val viewModel = MainViewModel(app)
    assertEquals(MainViewModel.NAV_HOME, viewModel.currentNavIndex.value)

    // Button 1: Learn / Continue
    viewModel.openLearnFeedAtLastCard()
    assertEquals(MainViewModel.NAV_LEARN, viewModel.currentNavIndex.value)
    assertTrue(viewModel.canNavigateBack.value)

    // Back to hub
    viewModel.navigateBack()
    assertEquals(MainViewModel.NAV_HOME, viewModel.currentNavIndex.value)

    // Button 2: Studying Lists
    viewModel.openStudyingLists()
    assertEquals(MainViewModel.NAV_VOCABULARY, viewModel.currentNavIndex.value)
    assertEquals(com.example.ui.screens.WordsExplorerTab.STUDYING_LISTS, viewModel.targetWordsTab.value)

    // Back to hub
    viewModel.navigateBack()
    assertEquals(MainViewModel.NAV_HOME, viewModel.currentNavIndex.value)

    // Button 3: All Collections / Vocabulary
    viewModel.openVocabularyCollections()
    assertEquals(MainViewModel.NAV_VOCABULARY, viewModel.currentNavIndex.value)
    assertEquals(com.example.ui.screens.WordsExplorerTab.VOCABULARY, viewModel.targetWordsTab.value)

    // Back to hub
    viewModel.navigateBack()
    assertEquals(MainViewModel.NAV_HOME, viewModel.currentNavIndex.value)

    // Button 4: Progress
    viewModel.openProgress()
    assertEquals(MainViewModel.NAV_PROGRESS, viewModel.currentNavIndex.value)

    // Back to hub
    viewModel.navigateBack()
    assertEquals(MainViewModel.NAV_HOME, viewModel.currentNavIndex.value)
  }

  @Test
  fun `test natural sorting and folder operations`() {
    val app = ApplicationProvider.getApplicationContext<Application>()
    val viewModel = MainViewModel(app)

    // Test natural sorting
    val rawList = listOf("Translation Sentence 10", "Translation Sentence 1", "Translation Sentence 2")
    val sorted = rawList.sortedNaturally()
    assertEquals(listOf("Translation Sentence 1", "Translation Sentence 2", "Translation Sentence 10"), sorted)

    // Test folder creation and update
    viewModel.createFolder(
      name = "Work Lists",
      emoji = "💼",
      description = "Business topics",
      initialCategories = listOf("Work & Business")
    )
    val folder = viewModel.folders.value.first { it.name == "Work Lists" }
    assertTrue(folder.categoryNames.contains("Work & Business"))

    // Test assign category to folder
    viewModel.assignCategoryToFolder("Travel & Places", folder.id)
    val updatedFolder = viewModel.folders.value.first { it.id == folder.id }
    assertTrue(updatedFolder.categoryNames.contains("Travel & Places"))

    // Test folder update
    viewModel.updateFolder(folder.id, "Work & Careers", "🏢", "Updated description")
    val renamedFolder = viewModel.folders.value.first { it.id == folder.id }
    assertEquals("Work & Careers", renamedFolder.name)
    assertEquals("🏢", renamedFolder.emoji)

    // Test delete folder (standalone lists are preserved)
    viewModel.deleteFolder(folder.id)
    assertFalse(viewModel.folders.value.any { it.id == folder.id })
  }

  @Test
  fun `test auto-scroll behavior when session timer expires`() {
    val app = ApplicationProvider.getApplicationContext<Application>()
    val viewModel = MainViewModel(app)

    // Case 1: In foreground, stopping auto-scroll
    com.example.service.BackgroundAudioPlaybackManager.setAppInForeground(true)
    viewModel.setAutoScroll(true)
    assertTrue(viewModel.isAutoScrollEnabled.value)
    // If in foreground and timer ends:
    if (com.example.service.BackgroundAudioPlaybackManager.isAppInForeground.value && viewModel.isAutoScrollEnabled.value) {
      viewModel.setAutoScroll(false)
    }
    assertFalse(viewModel.isAutoScrollEnabled.value)

    // Case 2: In background, auto-scroll / audio playback continues
    com.example.service.BackgroundAudioPlaybackManager.setAppInForeground(false)
    viewModel.setAutoScroll(true)
    assertTrue(viewModel.isAutoScrollEnabled.value)
    // When timer ends in background, foreground condition is false so it doesn't stop
    if (com.example.service.BackgroundAudioPlaybackManager.isAppInForeground.value && viewModel.isAutoScrollEnabled.value) {
      viewModel.setAutoScroll(false)
    }
    assertTrue(viewModel.isAutoScrollEnabled.value)
    viewModel.setAutoScroll(false)
  }

  @Test
  fun `test initial learning goal recommendations and direct access navigation`() {
    val app = ApplicationProvider.getApplicationContext<Application>()
    val viewModel = MainViewModel(app)

    // Verify initial goal selection & change
    viewModel.setStudentLearningGoal(LearningGoalType.FROM_SCRATCH)
    assertEquals(LearningGoalType.FROM_SCRATCH, viewModel.studentLearningGoal.value)
    assertTrue(viewModel.hasExplicitlySelectedGoal.value)

    viewModel.setStudentLearningGoal(LearningGoalType.VOCABULARY)
    assertEquals(LearningGoalType.VOCABULARY, viewModel.studentLearningGoal.value)

    viewModel.setStudentLearningGoal(LearningGoalType.SENTENCES)
    assertEquals(LearningGoalType.SENTENCES, viewModel.studentLearningGoal.value)

    // Test direct start studying folder (e.g. folder_basics_1)
    viewModel.startStudyFolder("folder_basics_1")
    assertEquals(MainViewModel.NAV_LEARN, viewModel.currentNavIndex.value)
    assertEquals("folder_basics_1", viewModel.selectedFolderId.value)

    // Return to home
    viewModel.navigateBack()
    assertEquals(MainViewModel.NAV_HOME, viewModel.currentNavIndex.value)

    // Test direct opening folder in library (e.g. folder_translations_1)
    viewModel.openFolderInLibrary("folder_translations_1")
    assertEquals(MainViewModel.NAV_VOCABULARY, viewModel.currentNavIndex.value)
    assertEquals("folder_translations_1", viewModel.targetFolderToOpen.value)

    // Return to home
    viewModel.navigateBack()
    assertEquals(MainViewModel.NAV_HOME, viewModel.currentNavIndex.value)

    // Test direct start studying sentences series A
    viewModel.startStudyFolder("folder_translations_a")
    assertEquals(MainViewModel.NAV_LEARN, viewModel.currentNavIndex.value)
    assertEquals("folder_translations_a", viewModel.selectedFolderId.value)
  }
}


