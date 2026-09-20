package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Folder
import com.example.data.model.sortedNaturally
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.ElectricCyanDark
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.util.AppLanguage
import com.example.ui.util.getCategoryDisplayName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListeningListPickerSheet(
    folders: List<Folder>,
    allCategories: List<String>,
    categoryCounts: Map<String, Int>,
    totalCardsCount: Int,
    initialIsAllSelected: Boolean,
    initialSelectedCategories: Set<String>,
    appLanguage: AppLanguage,
    onApplySelection: (isAll: Boolean, selectedCats: Set<String>) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val isSpanish = appLanguage == AppLanguage.SPANISH

    // Local mutable state
    var isAllSelected by remember { mutableStateOf(initialIsAllSelected) }
    var selectedCategories by remember {
        mutableStateOf(
            if (initialIsAllSelected) allCategories.toSet() else initialSelectedCategories
        )
    }
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf("ALL") }
    var selectedFolderId by remember { mutableStateOf<String?>(null) }

    // Active folders and categorizations
    val activeFolders = remember(folders) {
        folders.filterNot { it.isPurgedLegacyFolder }.sortedNaturally()
    }
    val basicsFolders = remember(activeFolders) { activeFolders.filter { it.isBasics } }
    val vocabFolders = remember(activeFolders) { activeFolders.filter { it.isVocabularyBooklet } }
    val transFolders = remember(activeFolders) { activeFolders.filter { it.isTranslations } }
    val userFolders = remember(activeFolders) { activeFolders.filter { it.isUserCreated } }

    val assignedCats = remember(activeFolders) {
        activeFolders.flatMap { it.categoryNames }.map { it.trim().lowercase() }.toSet()
    }
    val standaloneCategories = remember(allCategories, assignedCats, categoryCounts) {
        val dummyCats = setOf("everyday & social", "travel & places", "work & business")
        allCategories.filter { cat ->
            val norm = cat.trim().lowercase()
            !assignedCats.contains(norm) && (!dummyCats.contains(norm) || (categoryCounts[cat] ?: 0) > 0)
        }.sortedNaturally()
    }

    // Toggle individual category
    fun toggleCategory(category: String) {
        val next = selectedCategories.toMutableSet()
        if (next.contains(category)) {
            next.remove(category)
            isAllSelected = false
        } else {
            next.add(category)
            if (next.size >= allCategories.size) {
                isAllSelected = true
            }
        }
        selectedCategories = next
    }

    // Toggle all categories in a folder
    fun toggleFolder(folder: Folder) {
        val folderCats = folder.categoryNames
        val next = selectedCategories.toMutableSet()
        val allFolderCatsSelected = folderCats.all { next.contains(it) }
        if (allFolderCatsSelected) {
            next.removeAll(folderCats.toSet())
            isAllSelected = false
        } else {
            next.addAll(folderCats)
            if (next.size >= allCategories.size) {
                isAllSelected = true
            }
        }
        selectedCategories = next
    }

    // Select all lists in app
    fun selectAll() {
        isAllSelected = true
        selectedCategories = allCategories.toSet()
    }

    // Clear selection
    fun clearAll() {
        isAllSelected = false
        selectedCategories = emptySet()
    }

    // Calculate current selected words count
    val selectedWordsCount = remember(isAllSelected, selectedCategories, allCategories, categoryCounts, totalCardsCount) {
        if (isAllSelected) {
            totalCardsCount
        } else {
            selectedCategories.sumOf { categoryCounts[it] ?: 0 }
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .padding(horizontal = 18.dp)
                .padding(bottom = 16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = CircleShape,
                        color = PrimaryIndigo.copy(alpha = 0.15f),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = "🎧", fontSize = 20.sp)
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = if (isSpanish) "Listas para Escuchar" else "Listening Lists",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = if (isAllSelected) {
                                if (isSpanish) "Todas las listas • $totalCardsCount palabras" else "All lists • $totalCardsCount words"
                            } else {
                                if (isSpanish) "${selectedCategories.size} listas • $selectedWordsCount palabras" else "${selectedCategories.size} lists • $selectedWordsCount words"
                            },
                            fontSize = 12.sp,
                            color = PrimaryIndigo,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Cerrar",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("listening_sheet_search"),
                placeholder = {
                    Text(
                        text = if (isSpanish) "Buscar por nombre de lista o carpeta..." else "Search lists or folders...",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = PrimaryIndigo,
                        modifier = Modifier.size(18.dp)
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotBlank()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "Limpiar búsqueda",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(14.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f),
                    focusedBorderColor = PrimaryIndigo
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Quick Selection Toolbar (Select All / Clear)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isAllSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            if (isAllSelected) clearAll() else selectAll()
                        }
                        .testTag("listening_select_all_btn")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.SelectAll,
                            contentDescription = null,
                            tint = if (isAllSelected) Color.White else PrimaryIndigo,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isAllSelected) {
                                if (isSpanish) "Deseleccionar todo" else "Deselect all"
                            } else {
                                if (isSpanish) "Seleccionar todo" else "Select all"
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isAllSelected) Color.White else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                if (!isAllSelected && selectedCategories.isNotEmpty()) {
                    OutlinedButton(
                        onClick = { clearAll() },
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(
                            text = if (isSpanish) "Limpiar (${selectedCategories.size})" else "Clear (${selectedCategories.size})",
                            fontSize = 11.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Collection Tabs (Only when not searching)
            if (searchQuery.isBlank()) {
                val collectionTabs = remember(basicsFolders, vocabFolders, transFolders, userFolders, standaloneCategories) {
                    buildList {
                        add(Triple("ALL", if (isSpanish) "🌟 Todas" else "🌟 All", ""))
                        if (basicsFolders.isNotEmpty()) add(Triple("BASICS", "🌱 Basics", "(${basicsFolders.size})"))
                        if (vocabFolders.isNotEmpty()) add(Triple("VOCAB", if (isSpanish) "📚 Vocabulario" else "📚 Vocabulary", "(${vocabFolders.size})"))
                        if (transFolders.isNotEmpty()) add(Triple("TRANS", "💬 Translations", "(${transFolders.size})"))
                        if (userFolders.isNotEmpty()) add(Triple("CUSTOM", if (isSpanish) "⭐ Mis Colecciones" else "⭐ Custom", "(${userFolders.size})"))
                        if (standaloneCategories.isNotEmpty()) add(Triple("STANDALONE", if (isSpanish) "📌 Otras" else "📌 Other", "(${standaloneCategories.size})"))
                    }
                }

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(collectionTabs) { (type, label, countTag) ->
                        val isSelected = selectedTab == type
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)),
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    selectedTab = type
                                    selectedFolderId = null
                                }
                                .testTag("listening_sheet_tab_$type")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 12.5.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                                if (countTag.isNotBlank()) {
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = countTag,
                                        fontSize = 11.sp,
                                        color = if (isSelected) Color.White.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }
                }

                // Sub-folder pills if the selected collection has multiple folders
                val tabFolders = when (selectedTab) {
                    "BASICS" -> basicsFolders
                    "VOCAB" -> vocabFolders
                    "TRANS" -> transFolders
                    "CUSTOM" -> userFolders
                    else -> emptyList()
                }

                if (tabFolders.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        item {
                            val isAllSubSelected = selectedFolderId == null
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isAllSubSelected) ElectricCyanDark else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { selectedFolderId = null }
                            ) {
                                Text(
                                    text = if (isSpanish) "Todas las carpetas" else "All folders",
                                    fontSize = 11.5.sp,
                                    fontWeight = if (isAllSubSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isAllSubSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp)
                                )
                            }
                        }

                        items(tabFolders) { folder ->
                            val isFolderActive = selectedFolderId == folder.id
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isFolderActive) ElectricCyanDark else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                                border = if (isFolderActive) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { selectedFolderId = folder.id }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = folder.emoji, fontSize = 11.sp)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = folder.name,
                                        fontSize = 11.5.sp,
                                        fontWeight = if (isFolderActive) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isFolderActive) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Main List Content
            Box(modifier = Modifier.weight(1f)) {
                if (searchQuery.isNotBlank()) {
                    // Search results across all categories
                    val query = searchQuery.trim().lowercase()
                    val matchingCategories = remember(query, allCategories) {
                        allCategories.filter { it.lowercase().contains(query) }.sortedNaturally()
                    }

                    if (matchingCategories.isEmpty()) {
                        Box(modifier = Modifier.fillMaxWidth().padding(30.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = if (isSpanish) "No se encontraron listas con '$searchQuery'" else "No lists found for '$searchQuery'",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 13.sp
                            )
                        }
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(matchingCategories) { cat ->
                                val isChecked = isAllSelected || selectedCategories.contains(cat)
                                val count = categoryCounts[cat] ?: 0
                                CategorySelectableItem(
                                    category = cat,
                                    isChecked = isChecked,
                                    count = count,
                                    appLanguage = appLanguage,
                                    onToggle = { toggleCategory(cat) }
                                )
                            }
                        }
                    }
                } else {
                    // Display folders according to selected tab and selectedFolderId
                    val foldersToDisplay = remember(selectedTab, selectedFolderId, activeFolders, basicsFolders, vocabFolders, transFolders, userFolders) {
                        when (selectedTab) {
                            "ALL" -> activeFolders
                            "BASICS" -> if (selectedFolderId != null) basicsFolders.filter { it.id == selectedFolderId } else basicsFolders
                            "VOCAB" -> if (selectedFolderId != null) vocabFolders.filter { it.id == selectedFolderId } else vocabFolders
                            "TRANS" -> if (selectedFolderId != null) transFolders.filter { it.id == selectedFolderId } else transFolders
                            "CUSTOM" -> if (selectedFolderId != null) userFolders.filter { it.id == selectedFolderId } else userFolders
                            "STANDALONE" -> emptyList()
                            else -> activeFolders
                        }
                    }

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (selectedTab != "STANDALONE") {
                            items(foldersToDisplay, key = { it.id }) { folder ->
                                val folderCats = folder.categoryNames
                                val selectedCountInFolder = folderCats.count { isAllSelected || selectedCategories.contains(it) }
                                val isFolderFullySelected = folderCats.isNotEmpty() && selectedCountInFolder == folderCats.size
                                val folderWordsCount = remember(folder, categoryCounts) {
                                    folderCats.sumOf { categoryCounts[it] ?: 0 }
                                }

                                Card(
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)),
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        // Folder Header Row
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.weight(1f)
                                            ) {
                                                Text(text = folder.emoji, fontSize = 16.sp)
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Column {
                                                    Text(
                                                        text = folder.name,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 14.sp,
                                                        color = MaterialTheme.colorScheme.onSurface
                                                    )
                                                    Text(
                                                        text = "${folderCats.size} ${if (isSpanish) "listas" else "lists"} • $folderWordsCount ${if (isSpanish) "palabras" else "words"}",
                                                        fontSize = 11.sp,
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                }
                                            }

                                            // Quick Folder Check / Toggle Button
                                            Surface(
                                                shape = RoundedCornerShape(8.dp),
                                                color = if (isFolderFullySelected) PrimaryIndigo else MaterialTheme.colorScheme.surface,
                                                border = if (isFolderFullySelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                                                modifier = Modifier
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .clickable { toggleFolder(folder) }
                                            ) {
                                                Row(
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    if (isFolderFullySelected) {
                                                        Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(13.dp))
                                                        Spacer(modifier = Modifier.width(4.dp))
                                                    }
                                                    Text(
                                                        text = if (isFolderFullySelected) {
                                                            if (isSpanish) "Toda lista ✓" else "All lists ✓"
                                                        } else {
                                                            if (isSpanish) "Toda carpeta" else "Whole folder"
                                                        },
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = if (isFolderFullySelected) Color.White else PrimaryIndigo
                                                    )
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(10.dp))

                                        // Folder Lists
                                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                            folderCats.forEach { cat ->
                                                val isChecked = isAllSelected || selectedCategories.contains(cat)
                                                val count = categoryCounts[cat] ?: 0
                                                CategorySelectableItem(
                                                    category = cat,
                                                    isChecked = isChecked,
                                                    count = count,
                                                    appLanguage = appLanguage,
                                                    onToggle = { toggleCategory(cat) }
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // Standalone Lists (if tab is ALL or STANDALONE)
                        if ((selectedTab == "ALL" || selectedTab == "STANDALONE") && standaloneCategories.isNotEmpty()) {
                            item {
                                Card(
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)),
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(text = "📌", fontSize = 16.sp)
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Column {
                                                    Text(
                                                        text = if (isSpanish) "Otras Listas" else "Other Lists",
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 14.sp
                                                    )
                                                    Text(
                                                        text = "${standaloneCategories.size} ${if (isSpanish) "listas" else "lists"}",
                                                        fontSize = 11.sp,
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                            standaloneCategories.forEach { cat ->
                                                val isChecked = isAllSelected || selectedCategories.contains(cat)
                                                val count = categoryCounts[cat] ?: 0
                                                CategorySelectableItem(
                                                    category = cat,
                                                    isChecked = isChecked,
                                                    count = count,
                                                    appLanguage = appLanguage,
                                                    onToggle = { toggleCategory(cat) }
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Bottom Confirmation / Apply Bar
            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.5.dp, PrimaryIndigo.copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (isAllSelected) {
                                if (isSpanish) "🌟 Todas las listas" else "🌟 All lists"
                            } else {
                                if (isSpanish) "${selectedCategories.size} seleccionadas" else "${selectedCategories.size} selected"
                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.5.sp,
                            color = PrimaryIndigo,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "$selectedWordsCount ${if (isSpanish) "palabras para escuchar" else "words to listen"}",
                            fontSize = 11.5.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            val finalIsAll = isAllSelected || selectedCategories.size >= allCategories.size || selectedCategories.isEmpty()
                            val finalCats = if (finalIsAll) allCategories.toSet() else selectedCategories
                            onApplySelection(finalIsAll, finalCats)
                            onDismiss()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.testTag("listening_apply_selection_btn")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.DoneAll, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isSpanish) "Aplicar Selección" else "Apply Selection",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.5.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CategorySelectableItem(
    category: String,
    isChecked: Boolean,
    count: Int,
    appLanguage: AppLanguage,
    onToggle: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (isChecked) PrimaryIndigo.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface,
        border = if (isChecked) BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)) else BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f)),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable { onToggle() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(
                    checkedColor = PrimaryIndigo,
                    checkmarkColor = Color.White
                ),
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = getCategoryIcon(category),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = getCategoryDisplayName(category, appLanguage),
                fontSize = 13.sp,
                fontWeight = if (isChecked) FontWeight.SemiBold else FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.width(6.dp))

            Surface(
                shape = CircleShape,
                color = if (isChecked) PrimaryIndigo.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.padding(start = 4.dp)
            ) {
                Text(
                    text = "$count",
                    fontSize = 10.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isChecked) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}
