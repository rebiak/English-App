package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.DriveFileMove
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
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
import com.example.data.model.Flashcard
import com.example.data.model.Folder
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.MasteredGreen
import com.example.ui.theme.PracticeCoral
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.theme.StarAmber
import com.example.ui.util.AppLanguage
import com.example.ui.util.LearningMode

fun getCategoryIcon(cat: String): String {
    val lower = cat.trim().lowercase()
    return when {
        lower == "all" || lower == "todas" -> "✨"
        lower.contains("basics") || lower.contains("básico") || lower.contains("basico") -> "🌱"
        lower.contains("everyday") || lower.contains("social") || lower.contains("diario") || lower.contains("cotidiano") -> "☕"
        lower.contains("travel") || lower.contains("places") || lower.contains("viaje") -> "✈️"
        lower.contains("work") || lower.contains("business") || lower.contains("trabajo") || lower.contains("negocio") -> "💼"
        lower.contains("tech") || lower.contains("science") || lower.contains("ciencia") || lower.contains("tecnolog") -> "💻"
        lower.contains("food") || lower.contains("lifestyle") || lower.contains("comida") || lower.contains("salud") -> "🍕"
        lower.contains("phrasal") || lower.contains("slang") || lower.contains("idiom") || lower.contains("modismo") -> "🎭"
        lower.contains("academic") || lower.contains("exam") || lower.contains("estudio") || lower.contains("grammar") -> "📚"
        lower.contains("music") || lower.contains("musica") -> "🎵"
        lower.contains("sport") || lower.contains("deporte") -> "⚽"
        else -> "🏷️"
    }
}

enum class ManageHubTab {
    FOLDERS,
    LISTS
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ManageCategoriesSheet(
    categories: List<String>,
    categoryCounts: Map<String, Int>,
    folders: List<Folder> = emptyList(),
    allCards: List<Flashcard> = emptyList(),
    appLanguage: AppLanguage = AppLanguage.SPANISH,
    learningMode: LearningMode = LearningMode.ES_TO_EN,
    onDismiss: () -> Unit,
    onAddCategory: (String) -> Unit,
    onDeleteCategory: (String, Boolean) -> Unit, // (categoryName, deleteCards)
    onRenameCategory: (String, String) -> Unit,
    onSelectCategory: (String) -> Unit = {},
    onAddFolder: (name: String, emoji: String, description: String, categories: List<String>) -> Unit = { _, _, _, _ -> },
    onUpdateFolder: (id: String, name: String, emoji: String, description: String) -> Unit = { _, _, _, _ -> },
    onDeleteFolder: (id: String, deleteLists: Boolean) -> Unit = { _, _ -> },
    onAssignCategoryToFolder: (category: String, folderId: String?) -> Unit = { _, _ -> },
    onSetFolderCategories: (folderId: String, categories: List<String>) -> Unit = { _, _ -> },
    onSelectFolder: (folderId: String) -> Unit = {},
    onDeleteCard: (Flashcard) -> Unit = {},
    onMoveCard: (Flashcard, String) -> Unit = { _, _ -> },
    onPlayAudio: (String) -> Unit = {}
) {
    val isSpanish = appLanguage == AppLanguage.SPANISH
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var currentTab by remember { mutableStateOf(ManageHubTab.FOLDERS) }

    // Dialog state for Lists
    var showCreateCategoryDialog by remember { mutableStateOf(false) }
    var categoryToDelete by remember { mutableStateOf<String?>(null) }
    var categoryToRename by remember { mutableStateOf<String?>(null) }
    var categoryToAssignFolder by remember { mutableStateOf<String?>(null) }
    var cardToDelete by remember { mutableStateOf<Flashcard?>(null) }
    var cardToMove by remember { mutableStateOf<Flashcard?>(null) }

    // Dialog state for Folders
    var showCreateFolderDialog by remember { mutableStateOf(false) }
    var folderToEdit by remember { mutableStateOf<Folder?>(null) }
    var folderToDelete by remember { mutableStateOf<Folder?>(null) }
    var folderToManageLists by remember { mutableStateOf<Folder?>(null) }

    var searchQuery by remember { mutableStateOf("") }

    // Track expanded category cards to see their words
    val expandedCategories = remember { mutableStateMapOf<String, Boolean>() }

    val totalAllWords = allCards.size.takeIf { it > 0 } ?: categoryCounts.values.sum()

    // Filter categories by search
    val filteredCategories = remember(categories, searchQuery, allCards) {
        if (searchQuery.isBlank()) {
            categories
        } else {
            categories.filter { cat ->
                cat.contains(searchQuery, ignoreCase = true) ||
                        allCards.any {
                            it.category.equals(cat, ignoreCase = true) &&
                                    (it.english.contains(searchQuery, ignoreCase = true) ||
                                            it.spanish.contains(searchQuery, ignoreCase = true))
                        }
            }
        }
    }

    // Filter folders by search
    val filteredFolders = remember(folders, searchQuery) {
        if (searchQuery.isBlank()) {
            folders
        } else {
            folders.filter { folder ->
                folder.name.contains(searchQuery, ignoreCase = true) ||
                        folder.description.contains(searchQuery, ignoreCase = true) ||
                        folder.categoryNames.any { it.contains(searchQuery, ignoreCase = true) }
            }
        }
    }

    // Identify lists that don't belong to any folder
    val unassignedCategories = remember(categories, folders) {
        val assignedSet = folders.flatMap { it.categoryNames }.map { it.trim().lowercase() }.toSet()
        categories.filter { !assignedSet.contains(it.trim().lowercase()) }
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
                .padding(horizontal = 18.dp)
                .padding(bottom = 32.dp)
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
                        modifier = Modifier.size(42.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = if (currentTab == ManageHubTab.FOLDERS) Icons.Default.FolderOpen else Icons.Default.Layers,
                                contentDescription = null,
                                tint = PrimaryIndigo,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = if (isSpanish) "Organizador de Carpetas y Listas" else "Folders & Lists Hub",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = if (isSpanish) "${folders.size} carpetas • ${categories.size} listas • $totalAllWords palabras" else "${folders.size} folders • ${categories.size} lists • $totalAllWords words",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.clip(CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = if (isSpanish) "Cerrar" else "Close",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Navigation Tabs: Carpetas vs Listas
            TabRow(
                selectedTabIndex = currentTab.ordinal,
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                contentColor = PrimaryIndigo,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[currentTab.ordinal]),
                        color = PrimaryIndigo,
                        height = 3.dp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
            ) {
                Tab(
                    selected = currentTab == ManageHubTab.FOLDERS,
                    onClick = { currentTab = ManageHubTab.FOLDERS },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isSpanish) "📁 Carpetas (${folders.size})" else "📁 Folders (${folders.size})",
                                fontWeight = if (currentTab == ManageHubTab.FOLDERS) FontWeight.ExtraBold else FontWeight.Medium,
                                fontSize = 13.sp,
                                color = if (currentTab == ManageHubTab.FOLDERS) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                )
                Tab(
                    selected = currentTab == ManageHubTab.LISTS,
                    onClick = { currentTab = ManageHubTab.LISTS },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isSpanish) "🏷️ Listas (${categories.size})" else "🏷️ Lists (${categories.size})",
                                fontWeight = if (currentTab == ManageHubTab.LISTS) FontWeight.ExtraBold else FontWeight.Medium,
                                fontSize = 13.sp,
                                color = if (currentTab == ManageHubTab.LISTS) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    Text(
                        if (currentTab == ManageHubTab.FOLDERS) {
                            if (isSpanish) "Buscar carpetas o listas contenidas..." else "Search folders or contained lists..."
                        } else {
                            if (isSpanish) "Buscar listas o palabras..." else "Search lists or words..."
                        },
                        fontSize = 13.sp
                    )
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear", modifier = Modifier.size(16.dp))
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
                    focusedIndicatorColor = PrimaryIndigo,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Main Content depending on active tab
            when (currentTab) {
                ManageHubTab.FOLDERS -> {
                    // Action button: Add new Folder
                    Button(
                        onClick = { showCreateFolderDialog = true },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("create_folder_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.CreateNewFolder,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isSpanish) "Crear Nueva Carpeta" else "Create New Folder",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 480.dp)
                    ) {
                        if (filteredFolders.isEmpty()) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 32.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text(text = "📂", fontSize = 42.sp)
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = if (isSpanish) "No hay carpetas creadas" else "No folders found",
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = if (isSpanish) "Crea tu primera carpeta para agrupar varias listas." else "Create a folder to group multiple vocabulary lists.",
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        } else {
                            items(filteredFolders, key = { it.id }) { folder ->
                                val folderLists = folder.categoryNames
                                val folderCards = allCards.filter { card ->
                                    folderLists.any { it.equals(card.category, ignoreCase = true) }
                                }
                                val folderWordCount = folderCards.size
                                val folderMasteryAvg = if (folderCards.isNotEmpty()) {
                                    folderCards.map { it.mastery }.average().toInt()
                                } else 0

                                Card(
                                    shape = RoundedCornerShape(18.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                                    ),
                                    border = BorderStroke(1.2.dp, PrimaryIndigo.copy(alpha = 0.2f)),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(18.dp))
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp)
                                    ) {
                                        // Top row: Emoji, Name, and Actions
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.weight(1f)
                                            ) {
                                                Surface(
                                                    shape = RoundedCornerShape(12.dp),
                                                    color = PrimaryIndigo.copy(alpha = 0.15f),
                                                    modifier = Modifier.size(42.dp)
                                                ) {
                                                    Box(contentAlignment = Alignment.Center) {
                                                        Text(text = folder.emoji, fontSize = 22.sp)
                                                    }
                                                }
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Column(modifier = Modifier.weight(1f)) {
                                                    Text(
                                                        text = folder.name,
                                                        fontSize = 16.sp,
                                                        fontWeight = FontWeight.ExtraBold,
                                                        color = MaterialTheme.colorScheme.onSurface,
                                                        maxLines = 1,
                                                        overflow = TextOverflow.Ellipsis
                                                    )
                                                    if (folder.description.isNotBlank()) {
                                                        Spacer(modifier = Modifier.height(1.dp))
                                                        Text(
                                                            text = folder.description,
                                                            fontSize = 11.5.sp,
                                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                            maxLines = 1,
                                                            overflow = TextOverflow.Ellipsis
                                                        )
                                                    }
                                                }
                                            }

                                            // Action Buttons: Edit, Delete
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                                            ) {
                                                Surface(
                                                    shape = RoundedCornerShape(10.dp),
                                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                                                    modifier = Modifier
                                                        .size(32.dp)
                                                        .clip(RoundedCornerShape(10.dp))
                                                        .clickable { folderToEdit = folder }
                                                ) {
                                                    Box(contentAlignment = Alignment.Center) {
                                                        Icon(
                                                            imageVector = Icons.Default.Edit,
                                                            contentDescription = if (isSpanish) "Editar carpeta" else "Edit folder",
                                                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                                            modifier = Modifier.size(15.dp)
                                                        )
                                                    }
                                                }

                                                Surface(
                                                    shape = RoundedCornerShape(10.dp),
                                                    color = PracticeCoral.copy(alpha = 0.14f),
                                                    modifier = Modifier
                                                        .size(32.dp)
                                                        .clip(RoundedCornerShape(10.dp))
                                                        .clickable { folderToDelete = folder }
                                                ) {
                                                    Box(contentAlignment = Alignment.Center) {
                                                        Icon(
                                                            imageVector = Icons.Default.Delete,
                                                            contentDescription = if (isSpanish) "Eliminar carpeta" else "Delete folder",
                                                            tint = PracticeCoral,
                                                            modifier = Modifier.size(15.dp)
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(10.dp))

                                        // Stats Badges
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Surface(
                                                shape = RoundedCornerShape(8.dp),
                                                color = PrimaryIndigo.copy(alpha = 0.12f)
                                            ) {
                                                Text(
                                                    text = if (isSpanish) "${folderLists.size} listas" else "${folderLists.size} lists",
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = PrimaryIndigo,
                                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                                )
                                            }
                                            Surface(
                                                shape = RoundedCornerShape(8.dp),
                                                color = MaterialTheme.colorScheme.surface
                                            ) {
                                                Text(
                                                    text = if (isSpanish) "$folderWordCount palabras" else "$folderWordCount words",
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                                )
                                            }
                                            if (folderWordCount > 0) {
                                                Surface(
                                                    shape = RoundedCornerShape(8.dp),
                                                    color = MasteredGreen.copy(alpha = 0.15f)
                                                ) {
                                                    Text(
                                                        text = "⭐ $folderMasteryAvg% dominio",
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = MasteredGreen,
                                                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                                    )
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(10.dp))

                                        // Lists inside this folder
                                        Text(
                                            text = if (isSpanish) "Listas incluidas:" else "Included lists:",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )

                                        Spacer(modifier = Modifier.height(6.dp))

                                        FlowRow(
                                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                                            verticalArrangement = Arrangement.spacedBy(6.dp),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            if (folderLists.isEmpty()) {
                                                Text(
                                                    text = if (isSpanish) "Sin listas asignadas. Toca '+ Asignar' para agregar." else "No lists assigned. Tap '+ Assign' to add.",
                                                    fontSize = 11.5.sp,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            } else {
                                                folderLists.forEach { catName ->
                                                    val count = allCards.count { it.category.equals(catName, ignoreCase = true) }
                                                    Surface(
                                                        shape = RoundedCornerShape(10.dp),
                                                        color = MaterialTheme.colorScheme.surface,
                                                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)),
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(10.dp))
                                                            .clickable {
                                                                onSelectCategory(catName)
                                                                onDismiss()
                                                            }
                                                    ) {
                                                        Row(
                                                            verticalAlignment = Alignment.CenterVertically,
                                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                                        ) {
                                                            Text(text = getCategoryIcon(catName), fontSize = 11.sp)
                                                            Spacer(modifier = Modifier.width(4.dp))
                                                            Text(
                                                                text = catName,
                                                                fontSize = 11.sp,
                                                                fontWeight = FontWeight.Medium,
                                                                color = MaterialTheme.colorScheme.onSurface
                                                            )
                                                            Spacer(modifier = Modifier.width(4.dp))
                                                            Surface(
                                                                shape = RoundedCornerShape(6.dp),
                                                                color = PrimaryIndigo.copy(alpha = 0.12f)
                                                            ) {
                                                                Text(
                                                                    text = "$count",
                                                                    fontSize = 9.5.sp,
                                                                    fontWeight = FontWeight.Bold,
                                                                    color = PrimaryIndigo,
                                                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                            // "+ Asignar listas" Chip
                                            Surface(
                                                shape = RoundedCornerShape(10.dp),
                                                color = PrimaryIndigo.copy(alpha = 0.12f),
                                                border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.3f)),
                                                modifier = Modifier
                                                    .clip(RoundedCornerShape(10.dp))
                                                    .clickable { folderToManageLists = folder }
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Add,
                                                        contentDescription = null,
                                                        tint = PrimaryIndigo,
                                                        modifier = Modifier.size(12.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(3.dp))
                                                    Text(
                                                        text = if (isSpanish) "Asignar Listas" else "Assign Lists",
                                                        fontSize = 11.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = PrimaryIndigo
                                                    )
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(10.dp))

                                        // Folder Action: Study All in this folder
                                        if (folderLists.isNotEmpty() && folderWordCount > 0) {
                                            Surface(
                                                shape = RoundedCornerShape(12.dp),
                                                color = PrimaryIndigo,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .clickable {
                                                        onSelectFolder(folder.id)
                                                        onDismiss()
                                                    }
                                            ) {
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(vertical = 8.dp),
                                                    horizontalArrangement = Arrangement.Center,
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.School,
                                                        contentDescription = null,
                                                        tint = Color.White,
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(6.dp))
                                                    Text(
                                                        text = if (isSpanish) "Estudiar Esta Carpeta ($folderWordCount palabras)" else "Study This Folder ($folderWordCount words)",
                                                        fontSize = 12.5.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.White
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            // Unassigned lists section
                            if (unassignedCategories.isNotEmpty()) {
                                item {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Card(
                                        shape = RoundedCornerShape(16.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f)
                                        ),
                                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(modifier = Modifier.padding(12.dp)) {
                                            Text(
                                                text = if (isSpanish) "📂 Listas sin Carpeta Asignada (${unassignedCategories.size})" else "📂 Unassigned Lists (${unassignedCategories.size})",
                                                fontSize = 12.5.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                            Spacer(modifier = Modifier.height(6.dp))
                                            FlowRow(
                                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                                verticalArrangement = Arrangement.spacedBy(6.dp)
                                            ) {
                                                unassignedCategories.forEach { cat ->
                                                    Surface(
                                                        shape = RoundedCornerShape(10.dp),
                                                        color = MaterialTheme.colorScheme.surface,
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(10.dp))
                                                            .clickable { categoryToAssignFolder = cat }
                                                    ) {
                                                        Row(
                                                            verticalAlignment = Alignment.CenterVertically,
                                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                                        ) {
                                                            Text(text = "${getCategoryIcon(cat)} $cat", fontSize = 11.5.sp)
                                                            Spacer(modifier = Modifier.width(4.dp))
                                                            Text(
                                                                text = if (isSpanish) "+ Asignar" else "+ Assign",
                                                                fontSize = 10.5.sp,
                                                                fontWeight = FontWeight.Bold,
                                                                color = PrimaryIndigo
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
                    }
                }

                ManageHubTab.LISTS -> {
                    // Action button: Add new List
                    Button(
                        onClick = { showCreateCategoryDialog = true },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("create_list_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isSpanish) "Crear Nueva Lista" else "Create New List",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 480.dp)
                    ) {
                        // 1. Top Aggregate Card: "Todas las Palabras"
                        if (searchQuery.isBlank()) {
                            item {
                                Card(
                                    shape = RoundedCornerShape(18.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = PrimaryIndigo.copy(alpha = 0.08f)
                                    ),
                                    border = BorderStroke(1.5.dp, PrimaryIndigo.copy(alpha = 0.35f)),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(18.dp))
                                        .clickable {
                                            onSelectCategory("All")
                                            onDismiss()
                                        }
                                        .testTag("list_item_todas")
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 14.dp, vertical = 12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Surface(
                                                shape = CircleShape,
                                                color = PrimaryIndigo.copy(alpha = 0.2f),
                                                modifier = Modifier.size(42.dp)
                                            ) {
                                                Box(contentAlignment = Alignment.Center) {
                                                    Text(text = "✨", fontSize = 22.sp)
                                                }
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = if (isSpanish) "Todas las Palabras" else "All Vocabulary",
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.ExtraBold,
                                                    color = MaterialTheme.colorScheme.onSurface,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                                Spacer(modifier = Modifier.height(2.dp))
                                                Text(
                                                    text = if (isSpanish) "$totalAllWords palabras • Estudio libre" else "$totalAllWords words • Free study",
                                                    fontSize = 11.5.sp,
                                                    fontWeight = FontWeight.Normal,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Surface(
                                            shape = RoundedCornerShape(12.dp),
                                            color = PrimaryIndigo,
                                            modifier = Modifier
                                                .clip(RoundedCornerShape(12.dp))
                                                .clickable {
                                                    onSelectCategory("All")
                                                    onDismiss()
                                                }
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                            ) {
                                                Text(
                                                    text = if (isSpanish) "Estudiar Todo" else "Study All",
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White,
                                                    maxLines = 1,
                                                    softWrap = false
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Icon(
                                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier.size(13.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // Section Title for individual lists
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp, bottom = 2.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isSpanish) "TUS LISTAS INDIVIDUALES (${filteredCategories.size})" else "YOUR INDIVIDUAL LISTS (${filteredCategories.size})",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    letterSpacing = 1.1.sp
                                )
                            }
                        }

                        // 2. Individual Categories with Folder Indicator and Expandable Word Lists
                        items(filteredCategories, key = { it }) { cat ->
                            val count = allCards.count { it.category.equals(cat, ignoreCase = true) }.takeIf { it > 0 }
                                ?: (categoryCounts[cat] ?: 0)
                            val icon = getCategoryIcon(cat)
                            val catDisplayName = com.example.ui.util.getCategoryDisplayName(cat, appLanguage)
                            val isExpanded = expandedCategories[cat] == true
                            val assignedFolder = folders.find { it.categoryNames.any { c -> c.equals(cat, ignoreCase = true) } }

                            val cardsInCat = remember(allCards, cat, searchQuery) {
                                allCards.filter { it.category.equals(cat, ignoreCase = true) }
                                    .filter { card ->
                                        searchQuery.isBlank() ||
                                                card.english.contains(searchQuery, ignoreCase = true) ||
                                                card.spanish.contains(searchQuery, ignoreCase = true)
                                    }
                            }

                            Card(
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                                ),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp))
                            ) {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    // Category Header Row
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 14.dp, vertical = 10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .weight(1f)
                                                .clickable {
                                                    expandedCategories[cat] = !isExpanded
                                                }
                                        ) {
                                            Surface(
                                                shape = CircleShape,
                                                color = MaterialTheme.colorScheme.surface,
                                                modifier = Modifier.size(38.dp)
                                            ) {
                                                Box(contentAlignment = Alignment.Center) {
                                                    Text(text = icon, fontSize = 20.sp)
                                                }
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = catDisplayName,
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.onSurface,
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis
                                                )
                                                Spacer(modifier = Modifier.height(2.dp))
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Text(
                                                        text = if (isSpanish) {
                                                            if (count == 1) "1 palabra" else "$count palabras"
                                                        } else {
                                                            if (count == 1) "1 word" else "$count words"
                                                        },
                                                        fontSize = 11.5.sp,
                                                        fontWeight = FontWeight.Medium,
                                                        color = if (count > 0) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant
                                                    )

                                                    Spacer(modifier = Modifier.width(6.dp))

                                                    // Assigned Folder Chip
                                                    Surface(
                                                        shape = RoundedCornerShape(6.dp),
                                                        color = if (assignedFolder != null) PrimaryIndigo.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surfaceVariant,
                                                        modifier = Modifier
                                                            .clip(RoundedCornerShape(6.dp))
                                                            .clickable { categoryToAssignFolder = cat }
                                                    ) {
                                                        Text(
                                                            text = if (assignedFolder != null) "${assignedFolder.emoji} ${assignedFolder.name}" else "📂 Sin carpeta",
                                                            fontSize = 10.sp,
                                                            fontWeight = FontWeight.SemiBold,
                                                            color = if (assignedFolder != null) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant,
                                                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp),
                                                            maxLines = 1,
                                                            overflow = TextOverflow.Ellipsis
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            // "Estudiar" action pill
                                            Surface(
                                                shape = RoundedCornerShape(10.dp),
                                                color = PrimaryIndigo.copy(alpha = 0.14f),
                                                border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.35f)),
                                                modifier = Modifier
                                                    .clip(RoundedCornerShape(10.dp))
                                                    .clickable {
                                                        onSelectCategory(cat)
                                                        onDismiss()
                                                    }
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                                                ) {
                                                    Text(
                                                        text = if (isSpanish) "Estudiar" else "Study",
                                                        fontSize = 11.5.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = PrimaryIndigo,
                                                        maxLines = 1,
                                                        softWrap = false
                                                    )
                                                    Spacer(modifier = Modifier.width(3.dp))
                                                    Icon(
                                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                                        contentDescription = null,
                                                        tint = PrimaryIndigo,
                                                        modifier = Modifier.size(12.dp)
                                                    )
                                                }
                                            }

                                            // Assign Folder button (📁)
                                            Surface(
                                                shape = RoundedCornerShape(10.dp),
                                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                                                modifier = Modifier
                                                    .size(30.dp)
                                                    .clip(RoundedCornerShape(10.dp))
                                                    .clickable { categoryToAssignFolder = cat }
                                            ) {
                                                Box(contentAlignment = Alignment.Center) {
                                                    Icon(
                                                        imageVector = Icons.Default.Folder,
                                                        contentDescription = if (isSpanish) "Asignar carpeta" else "Assign folder",
                                                        tint = PrimaryIndigo,
                                                        modifier = Modifier.size(14.dp)
                                                    )
                                                }
                                            }

                                            // Rename button (✏️)
                                            Surface(
                                                shape = RoundedCornerShape(10.dp),
                                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                                                modifier = Modifier
                                                    .size(30.dp)
                                                    .clip(RoundedCornerShape(10.dp))
                                                    .clickable { categoryToRename = cat }
                                            ) {
                                                Box(contentAlignment = Alignment.Center) {
                                                    Icon(
                                                        imageVector = Icons.Default.Edit,
                                                        contentDescription = if (isSpanish) "Renombrar lista" else "Rename list",
                                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                                        modifier = Modifier.size(14.dp)
                                                    )
                                                }
                                            }

                                            // Delete list button (🗑️)
                                            Surface(
                                                shape = RoundedCornerShape(10.dp),
                                                color = PracticeCoral.copy(alpha = 0.14f),
                                                modifier = Modifier
                                                    .size(30.dp)
                                                    .clip(RoundedCornerShape(10.dp))
                                                    .clickable { categoryToDelete = cat }
                                            ) {
                                                Box(contentAlignment = Alignment.Center) {
                                                    Icon(
                                                        imageVector = Icons.Default.Delete,
                                                        contentDescription = if (isSpanish) "Eliminar lista" else "Delete list",
                                                        tint = PracticeCoral,
                                                        modifier = Modifier.size(14.dp)
                                                    )
                                                }
                                            }

                                            // Expand/Collapse arrow
                                            IconButton(
                                                onClick = { expandedCategories[cat] = !isExpanded },
                                                modifier = Modifier.size(26.dp)
                                            ) {
                                                Icon(
                                                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                                    contentDescription = "Expand list",
                                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }
                                    }

                                    // Expandable Word List inside this Category
                                    AnimatedVisibility(
                                        visible = isExpanded,
                                        enter = expandVertically() + fadeIn(),
                                        exit = shrinkVertically() + fadeOut()
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.6f))
                                                .padding(horizontal = 12.dp, vertical = 8.dp)
                                        ) {
                                            HorizontalDivider(
                                                modifier = Modifier.padding(bottom = 8.dp),
                                                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                                            )

                                            if (cardsInCat.isEmpty()) {
                                                Text(
                                                    text = if (isSpanish) "Esta lista no tiene palabras aún. Usa '+ Crear' para agregar palabras." else "No words in this list yet. Use '+ Create' to add words.",
                                                    fontSize = 12.sp,
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
                                                )
                                            } else {
                                                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                                    cardsInCat.forEach { card ->
                                                        WordItemRow(
                                                            card = card,
                                                            learningMode = learningMode,
                                                            isSpanish = isSpanish,
                                                            onPlayAudio = {
                                                                val word = if (learningMode == LearningMode.EN_TO_ES) card.spanish else card.english
                                                                onPlayAudio(word)
                                                            },
                                                            onMove = { cardToMove = card },
                                                            onDelete = { cardToDelete = card }
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
                }
            }
        }
    }

    // --- Dialog: Create New Folder ---
    if (showCreateFolderDialog) {
        var folderName by remember { mutableStateOf("") }
        var folderEmoji by remember { mutableStateOf("📁") }
        var folderDesc by remember { mutableStateOf("") }
        var listFilterQuery by remember { mutableStateOf("") }
        val selectedLists = remember { mutableStateListOf<String>() }

        val emojiPresets = listOf("📁", "☕", "✈️", "💼", "🎓", "🍕", "💻", "💡", "🎬", "🏥", "🔬", "⚽", "📚", "🛍️", "🏠", "🎨", "🌍", "🚀", "❤️")

        val filteredCategoriesForFolder = remember(categories, listFilterQuery) {
            if (listFilterQuery.isBlank()) categories
            else categories.filter { it.contains(listFilterQuery, ignoreCase = true) }
        }

        AlertDialog(
            onDismissRequest = { showCreateFolderDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = folderEmoji, fontSize = 22.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isSpanish) "Nueva Carpeta" else "New Folder",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = if (isSpanish) "Crea una carpeta para agrupar varias listas relacionadas:" else "Create a folder to organize related vocabulary lists:",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Name field
                    OutlinedTextField(
                        value = folderName,
                        onValueChange = { folderName = it },
                        label = { Text(if (isSpanish) "Nombre de la carpeta" else "Folder name") },
                        placeholder = { Text(if (isSpanish) "Ej: Ocio & Viajes, Negocios..." else "e.g., Leisure & Travel, Business...") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Description field
                    OutlinedTextField(
                        value = folderDesc,
                        onValueChange = { folderDesc = it },
                        label = { Text(if (isSpanish) "Descripción (opcional)" else "Description (optional)") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Emoji selector
                    Text(
                        text = if (isSpanish) "Icono / Emoji:" else "Icon / Emoji:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        emojiPresets.forEach { emoji ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (folderEmoji == emoji) PrimaryIndigo.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant,
                                border = if (folderEmoji == emoji) BorderStroke(1.5.dp, PrimaryIndigo) else null,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { folderEmoji = emoji }
                            ) {
                                Text(
                                    text = emoji,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Select initial lists header & controls
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = if (isSpanish) "Listas disponibles (${categories.size}):" else "Available lists (${categories.size}):",
                                fontSize = 12.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (isSpanish) "Desliza para ver todas y seleccionar" else "Scroll to see all and select",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            TextButton(
                                onClick = {
                                    if (selectedLists.size == categories.size) {
                                        selectedLists.clear()
                                    } else {
                                        selectedLists.clear()
                                        selectedLists.addAll(categories)
                                    }
                                }
                            ) {
                                Text(
                                    text = if (selectedLists.size == categories.size) {
                                        if (isSpanish) "Deseleccionar" else "Clear"
                                    } else {
                                        if (isSpanish) "Todas" else "All"
                                    },
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryIndigo
                                )
                            }
                        }
                    }

                    if (categories.size > 4) {
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = listFilterQuery,
                            onValueChange = { listFilterQuery = it },
                            placeholder = { Text(if (isSpanish) "Buscar lista..." else "Search list...", fontSize = 11.5.sp) },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(16.dp)) },
                            trailingIcon = {
                                if (listFilterQuery.isNotBlank()) {
                                    IconButton(onClick = { listFilterQuery = "" }) {
                                        Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(14.dp))
                                    }
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    // Scrollable List Container
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 100.dp, max = 190.dp)
                                .verticalScroll(rememberScrollState())
                                .padding(6.dp)
                        ) {
                            if (filteredCategoriesForFolder.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = if (isSpanish) "No se encontraron listas" else "No lists found",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            } else {
                                filteredCategoriesForFolder.forEach { cat ->
                                    val isChecked = selectedLists.contains(cat)
                                    val count = categoryCounts[cat] ?: allCards.count { it.category.equals(cat, ignoreCase = true) }
                                    val existingFolder = folders.find { it.categoryNames.any { c -> c.equals(cat, ignoreCase = true) } }

                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = if (isChecked) PrimaryIndigo.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                                        border = if (isChecked) BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.4f)) else null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                if (isChecked) selectedLists.remove(cat) else selectedLists.add(cat)
                                            }
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 6.dp, vertical = 5.dp)
                                        ) {
                                            Checkbox(
                                                checked = isChecked,
                                                onCheckedChange = { checked ->
                                                    if (checked) selectedLists.add(cat) else selectedLists.remove(cat)
                                                },
                                                colors = CheckboxDefaults.colors(checkedColor = PrimaryIndigo)
                                            )
                                            Spacer(modifier = Modifier.width(2.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Text(
                                                        text = "${getCategoryIcon(cat)} $cat",
                                                        fontSize = 13.sp,
                                                        fontWeight = if (isChecked) FontWeight.Bold else FontWeight.Medium,
                                                        color = MaterialTheme.colorScheme.onSurface
                                                    )
                                                }
                                                if (existingFolder != null) {
                                                    Text(
                                                        text = if (isSpanish) "📁 Actualmente en: ${existingFolder.name}" else "📁 Currently in: ${existingFolder.name}",
                                                        fontSize = 10.sp,
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                }
                                            }
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Surface(
                                                shape = RoundedCornerShape(6.dp),
                                                color = if (isChecked) PrimaryIndigo.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant
                                            ) {
                                                Text(
                                                    text = if (isSpanish) "$count pal." else "$count w.",
                                                    fontSize = 10.5.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = if (isChecked) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (selectedLists.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isSpanish) "✓ ${selectedLists.size} lista(s) seleccionada(s)" else "✓ ${selectedLists.size} list(s) selected",
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryIndigo
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (folderName.isNotBlank()) {
                            onAddFolder(folderName.trim(), folderEmoji, folderDesc.trim(), selectedLists.toList())
                            showCreateFolderDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Crear Carpeta" else "Create Folder")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateFolderDialog = false }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Edit Folder ---
    if (folderToEdit != null) {
        val targetFolder = folderToEdit!!
        var editName by remember(targetFolder) { mutableStateOf(targetFolder.name) }
        var editEmoji by remember(targetFolder) { mutableStateOf(targetFolder.emoji) }
        var editDesc by remember(targetFolder) { mutableStateOf(targetFolder.description) }

        val emojiPresets = listOf("📁", "☕", "✈️", "💼", "🎓", "🍕", "💻", "💡", "🎬", "🏥", "🔬", "⚽", "📚", "🛍️", "🏠", "🎨", "🌍", "🚀", "❤️")

        AlertDialog(
            onDismissRequest = { folderToEdit = null },
            title = {
                Text(
                    text = if (isSpanish) "Editar Carpeta" else "Edit Folder",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text(if (isSpanish) "Nombre de la carpeta" else "Folder name") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editDesc,
                        onValueChange = { editDesc = it },
                        label = { Text(if (isSpanish) "Descripción" else "Description") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = if (isSpanish) "Icono / Emoji:" else "Icon / Emoji:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        emojiPresets.forEach { emoji ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (editEmoji == emoji) PrimaryIndigo.copy(alpha = 0.2f) else MaterialTheme.colorScheme.surfaceVariant,
                                border = if (editEmoji == emoji) BorderStroke(1.5.dp, PrimaryIndigo) else null,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { editEmoji = emoji }
                            ) {
                                Text(
                                    text = emoji,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (editName.isNotBlank()) {
                            onUpdateFolder(targetFolder.id, editName.trim(), editEmoji, editDesc.trim())
                            folderToEdit = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Guardar Cambios" else "Save Changes")
                }
            },
            dismissButton = {
                TextButton(onClick = { folderToEdit = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Delete Folder ---
    if (folderToDelete != null) {
        val targetFolder = folderToDelete!!
        val listsCount = targetFolder.categoryNames.size

        AlertDialog(
            onDismissRequest = { folderToDelete = null },
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = PracticeCoral,
                    modifier = Modifier.size(32.dp)
                )
            },
            title = {
                Text(
                    text = if (isSpanish) "¿Eliminar carpeta '${targetFolder.name}'?" else "Delete folder '${targetFolder.name}'?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Text(
                    text = if (isSpanish) {
                        "Esta carpeta contiene $listsCount lista(s). Puedes eliminar solo la carpeta (conservando tus listas y palabras) o eliminar todo su contenido."
                    } else {
                        "This folder contains $listsCount list(s). You can delete just the folder (keeping your lists & words) or delete all content inside."
                    },
                    fontSize = 13.5.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            confirmButton = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Button(
                        onClick = {
                            onDeleteFolder(targetFolder.id, false)
                            folderToDelete = null
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (isSpanish) "📂 Eliminar carpeta y conservar listas" else "📂 Delete folder & keep lists")
                    }

                    if (listsCount > 0) {
                        OutlinedButton(
                            onClick = {
                                onDeleteFolder(targetFolder.id, true)
                                folderToDelete = null
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (isSpanish) "🗑️ Eliminar carpeta y todas sus listas" else "🗑️ Delete folder & all its lists",
                                color = PracticeCoral
                            )
                        }
                    }

                    TextButton(
                        onClick = { folderToDelete = null },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(if (isSpanish) "Cancelar" else "Cancel")
                    }
                }
            },
            dismissButton = {}
        )
    }

    // --- Dialog: Manage Lists inside a Folder ---
    if (folderToManageLists != null) {
        val targetFolder = folderToManageLists!!
        var manageListQuery by remember { mutableStateOf("") }
        val currentSelections = remember(targetFolder) {
            mutableStateListOf<String>().apply { addAll(targetFolder.categoryNames) }
        }

        val filteredCategoriesForManage = remember(categories, manageListQuery) {
            if (manageListQuery.isBlank()) categories
            else categories.filter { it.contains(manageListQuery, ignoreCase = true) }
        }

        AlertDialog(
            onDismissRequest = { folderToManageLists = null },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = targetFolder.emoji, fontSize = 22.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isSpanish) "Listas en '${targetFolder.name}'" else "Lists in '${targetFolder.name}'",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = if (isSpanish) "Marca las listas que deben pertenecer a esta carpeta:" else "Check the lists that belong to this folder:",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isSpanish) "Listas (${categories.size}):" else "Lists (${categories.size}):",
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            TextButton(
                                onClick = {
                                    if (currentSelections.size == categories.size) {
                                        currentSelections.clear()
                                    } else {
                                        currentSelections.clear()
                                        currentSelections.addAll(categories)
                                    }
                                }
                            ) {
                                Text(
                                    text = if (currentSelections.size == categories.size) {
                                        if (isSpanish) "Deseleccionar" else "Clear"
                                    } else {
                                        if (isSpanish) "Todas" else "All"
                                    },
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryIndigo
                                )
                            }
                        }
                    }

                    if (categories.size > 4) {
                        Spacer(modifier = Modifier.height(4.dp))
                        OutlinedTextField(
                            value = manageListQuery,
                            onValueChange = { manageListQuery = it },
                            placeholder = { Text(if (isSpanish) "Buscar lista..." else "Search list...", fontSize = 11.5.sp) },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(16.dp)) },
                            trailingIcon = {
                                if (manageListQuery.isNotBlank()) {
                                    IconButton(onClick = { manageListQuery = "" }) {
                                        Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(14.dp))
                                    }
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 120.dp, max = 220.dp)
                                .verticalScroll(rememberScrollState())
                                .padding(6.dp)
                        ) {
                            if (filteredCategoriesForManage.isEmpty()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = if (isSpanish) "No se encontraron listas" else "No lists found",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            } else {
                                filteredCategoriesForManage.forEach { cat ->
                                    val isChecked = currentSelections.any { it.equals(cat, ignoreCase = true) }
                                    val count = categoryCounts[cat] ?: allCards.count { it.category.equals(cat, ignoreCase = true) }
                                    val otherFolder = folders.find { it.id != targetFolder.id && it.categoryNames.any { c -> c.equals(cat, ignoreCase = true) } }

                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = if (isChecked) PrimaryIndigo.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                                        border = if (isChecked) BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.4f)) else null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable {
                                                if (isChecked) {
                                                    currentSelections.removeAll { it.equals(cat, ignoreCase = true) }
                                                } else {
                                                    currentSelections.add(cat)
                                                }
                                            }
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 6.dp, vertical = 5.dp)
                                        ) {
                                            Checkbox(
                                                checked = isChecked,
                                                onCheckedChange = { checked ->
                                                    if (checked) {
                                                        currentSelections.add(cat)
                                                    } else {
                                                        currentSelections.removeAll { it.equals(cat, ignoreCase = true) }
                                                    }
                                                },
                                                colors = CheckboxDefaults.colors(checkedColor = PrimaryIndigo)
                                            )
                                            Spacer(modifier = Modifier.width(2.dp))
                                            Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                    text = "${getCategoryIcon(cat)} $cat",
                                                    fontSize = 13.sp,
                                                    fontWeight = if (isChecked) FontWeight.Bold else FontWeight.Medium,
                                                    color = MaterialTheme.colorScheme.onSurface
                                                )
                                                if (otherFolder != null) {
                                                    Text(
                                                        text = if (isSpanish) "📁 En: ${otherFolder.name} (se moverá aquí)" else "📁 In: ${otherFolder.name} (will move here)",
                                                        fontSize = 10.sp,
                                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                }
                                            }
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Surface(
                                                shape = RoundedCornerShape(6.dp),
                                                color = if (isChecked) PrimaryIndigo.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant
                                            ) {
                                                Text(
                                                    text = if (isSpanish) "$count pal." else "$count w.",
                                                    fontSize = 10.5.sp,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = if (isChecked) PrimaryIndigo else MaterialTheme.colorScheme.onSurfaceVariant,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (currentSelections.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = if (isSpanish) "✓ ${currentSelections.size} lista(s) en esta carpeta" else "✓ ${currentSelections.size} list(s) in this folder",
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryIndigo
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onSetFolderCategories(targetFolder.id, currentSelections.toList())
                        folderToManageLists = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Guardar Asignaciones" else "Save Assignments")
                }
            },
            dismissButton = {
                TextButton(onClick = { folderToManageLists = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Assign Single Category to a Folder ---
    if (categoryToAssignFolder != null) {
        val targetCat = categoryToAssignFolder!!
        val currentFolder = folders.find { it.categoryNames.any { c -> c.equals(targetCat, ignoreCase = true) } }
        var selectedFolderId by remember(targetCat) { mutableStateOf(currentFolder?.id) }

        AlertDialog(
            onDismissRequest = { categoryToAssignFolder = null },
            icon = {
                Icon(
                    imageVector = Icons.Default.Folder,
                    contentDescription = null,
                    tint = PrimaryIndigo,
                    modifier = Modifier.size(28.dp)
                )
            },
            title = {
                Text(
                    text = if (isSpanish) "Mover lista a Carpeta" else "Move list to Folder",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = if (isSpanish) "Lista seleccionada: ${getCategoryIcon(targetCat)} $targetCat" else "Selected list: ${getCategoryIcon(targetCat)} $targetCat",
                        fontSize = 13.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryIndigo
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = if (isSpanish) "Elige la carpeta contenedora:" else "Choose container folder:",
                        fontSize = 12.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Option: Sin Carpeta
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (selectedFolderId == null) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { selectedFolderId = null }
                    ) {
                        Text(
                            text = if (isSpanish) "📂 Ninguna (Sin Carpeta)" else "📂 None (No Folder)",
                            fontSize = 12.5.sp,
                            fontWeight = if (selectedFolderId == null) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedFolderId == null) Color.White else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    folders.forEach { folder ->
                        val isSelected = selectedFolderId == folder.id
                        val countInFolder = folder.categoryNames.size
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { selectedFolderId = folder.id }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            ) {
                                Text(
                                    text = "${folder.emoji} ${folder.name}",
                                    fontSize = 12.5.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = if (isSpanish) "$countInFolder listas" else "$countInFolder lists",
                                    fontSize = 11.sp,
                                    color = if (isSelected) Color.White.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onAssignCategoryToFolder(targetCat, selectedFolderId)
                        categoryToAssignFolder = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Guardar" else "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { categoryToAssignFolder = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Create New Category ---
    if (showCreateCategoryDialog) {
        var newName by remember { mutableStateOf("") }
        var selectedFolderForNewList by remember { mutableStateOf<String?>(null) }

        AlertDialog(
            onDismissRequest = { showCreateCategoryDialog = false },
            title = {
                Text(
                    text = if (isSpanish) "Nueva Lista / Categoría" else "New List / Category",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column {
                    Text(
                        text = if (isSpanish) {
                            "Escribe el nombre de la nueva lista para organizar tus palabras y frases:"
                        } else {
                            "Type the name of the new list to organize your flashcards and words:"
                        },
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = newName,
                        onValueChange = { newName = it },
                        label = { Text(if (isSpanish) "Nombre de la lista" else "List name") },
                        placeholder = { Text(if (isSpanish) "Ej: Phrasal Verbs, Medicina, Series..." else "e.g., Phrasal Verbs, Medical, TV Shows...") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (folders.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = if (isSpanish) "Asignar a Carpeta:" else "Assign to Folder:",
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (selectedFolderForNewList == null) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable { selectedFolderForNewList = null }
                            ) {
                                Text(
                                    text = if (isSpanish) "📂 Sin carpeta" else "📂 No folder",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = if (selectedFolderForNewList == null) Color.White else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                )
                            }

                            folders.forEach { folder ->
                                val isSelected = selectedFolderForNewList == folder.id
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { selectedFolderForNewList = folder.id }
                                ) {
                                    Text(
                                        text = "${folder.emoji} ${folder.name}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (isSpanish) "Sugerencias de temas:" else "Topic suggestions:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val suggestions = if (isSpanish) {
                            listOf("Conversación", "Entrevistas", "Slang Urbano", "Música", "Salud", "Series", "Negocios")
                        } else {
                            listOf("Conversation", "Interviews", "Urban Slang", "Music", "Health", "TV Shows", "Business")
                        }
                        suggestions.forEach { sample ->
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable { newName = sample }
                            ) {
                                Text(
                                    text = sample,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newName.isNotBlank()) {
                            val trimmed = newName.trim()
                            onAddCategory(trimmed)
                            if (selectedFolderForNewList != null) {
                                onAssignCategoryToFolder(trimmed, selectedFolderForNewList)
                            }
                            showCreateCategoryDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Crear Lista" else "Create List")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCreateCategoryDialog = false }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Rename Category ---
    if (categoryToRename != null) {
        val oldName = categoryToRename!!
        var renameText by remember(oldName) { mutableStateOf(oldName) }

        AlertDialog(
            onDismissRequest = { categoryToRename = null },
            title = {
                Text(
                    text = if (isSpanish) "Renombrar Lista" else "Rename List",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column {
                    Text(
                        text = if (isSpanish) {
                            "Cambia el nombre de la lista '$oldName'. Todas las palabras asociadas se actualizarán automáticamente."
                        } else {
                            "Change the name for list '$oldName'. All associated words will be updated automatically."
                        },
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = renameText,
                        onValueChange = { renameText = it },
                        label = { Text(if (isSpanish) "Nuevo nombre" else "New name") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (renameText.isNotBlank()) {
                            onRenameCategory(oldName, renameText.trim())
                            categoryToRename = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Guardar" else "Save")
                }
            },
            dismissButton = {
                TextButton(onClick = { categoryToRename = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Delete Category Confirmation ---
    if (categoryToDelete != null) {
        val targetCat = categoryToDelete!!
        val cardCount = allCards.count { it.category.equals(targetCat, ignoreCase = true) }.takeIf { it > 0 }
            ?: (categoryCounts[targetCat] ?: 0)

        AlertDialog(
            onDismissRequest = { categoryToDelete = null },
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = PracticeCoral,
                    modifier = Modifier.size(32.dp)
                )
            },
            title = {
                Text(
                    text = if (isSpanish) "¿Eliminar lista '$targetCat'?" else "Delete list '$targetCat'?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column {
                    Text(
                        text = if (cardCount > 0) {
                            if (isSpanish) "Esta lista contiene $cardCount palabra(s). ¿Qué deseas hacer con esas palabras?" else "This list contains $cardCount word(s). What would you like to do with them?"
                        } else {
                            if (isSpanish) "Esta lista está vacía y se eliminará de inmediato." else "This list is empty and will be removed immediately."
                        },
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            confirmButton = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (cardCount > 0) {
                        Button(
                            onClick = {
                                onDeleteCategory(targetCat, true)
                                categoryToDelete = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PracticeCoral),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(if (isSpanish) "🗑️ Borrar lista y sus $cardCount palabras" else "🗑️ Delete list and its $cardCount words")
                        }

                        OutlinedButton(
                            onClick = {
                                onDeleteCategory(targetCat, false)
                                categoryToDelete = null
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(if (isSpanish) "📦 Borrar lista y mover palabras a 'Everyday'" else "📦 Delete list and move words to 'Everyday'")
                        }
                    } else {
                        Button(
                            onClick = {
                                onDeleteCategory(targetCat, true)
                                categoryToDelete = null
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PracticeCoral),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(if (isSpanish) "Eliminar Lista" else "Delete List")
                        }
                    }

                    TextButton(
                        onClick = { categoryToDelete = null },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(if (isSpanish) "Cancelar" else "Cancel")
                    }
                }
            },
            dismissButton = {}
        )
    }

    // --- Dialog: Delete Single Card Confirmation ---
    if (cardToDelete != null) {
        val targetCard = cardToDelete!!
        val wordDisplay = if (learningMode == LearningMode.EN_TO_ES) targetCard.spanish else targetCard.english
        AlertDialog(
            onDismissRequest = { cardToDelete = null },
            icon = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = PracticeCoral,
                    modifier = Modifier.size(28.dp)
                )
            },
            title = {
                Text(
                    text = if (isSpanish) "¿Eliminar palabra?" else "Delete word?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            },
            text = {
                Text(
                    text = if (isSpanish) "¿Estás seguro de eliminar '${targetCard.emoji} $wordDisplay' (${targetCard.category}) de tu baraja?" else "Are you sure you want to delete '${targetCard.emoji} $wordDisplay' from list '${targetCard.category}'?",
                    fontSize = 13.5.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDeleteCard(targetCard)
                        cardToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PracticeCoral),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Eliminar" else "Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { cardToDelete = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Move Single Card to Another List ---
    if (cardToMove != null) {
        val targetCard = cardToMove!!
        var selectedDestination by remember { mutableStateOf(categories.firstOrNull { !it.equals(targetCard.category, ignoreCase = true) } ?: targetCard.category) }
        var customNewDest by remember { mutableStateOf("") }
        var isCreatingNewInMove by remember { mutableStateOf(false) }

        AlertDialog(
            onDismissRequest = { cardToMove = null },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.DriveFileMove,
                    contentDescription = null,
                    tint = PrimaryIndigo,
                    modifier = Modifier.size(28.dp)
                )
            },
            title = {
                Text(
                    text = if (isSpanish) "Mover Palabra a otra Lista" else "Move Word to Another List",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            },
            text = {
                Column {
                    Text(
                        text = if (isSpanish) "Palabra: ${targetCard.emoji} ${if (learningMode == LearningMode.EN_TO_ES) targetCard.spanish else targetCard.english}" else "Word: ${targetCard.emoji} ${if (learningMode == LearningMode.EN_TO_ES) targetCard.spanish else targetCard.english}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryIndigo
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = if (isSpanish) "Selecciona la lista de destino:" else "Select target list:",
                        fontSize = 12.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    if (!isCreatingNewInMove) {
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            categories.forEach { cat ->
                                val isSelected = cat.equals(selectedDestination, ignoreCase = true)
                                val isCurrent = cat.equals(targetCard.category, ignoreCase = true)
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                                    border = if (isCurrent) BorderStroke(1.dp, PracticeCoral.copy(alpha = 0.5f)) else null,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable { selectedDestination = cat }
                                ) {
                                    Text(
                                        text = "${getCategoryIcon(cat)} $cat ${if (isCurrent) "(actual)" else ""}",
                                        fontSize = 11.5.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        TextButton(
                            onClick = { isCreatingNewInMove = true },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(if (isSpanish) "+ Crear nueva lista de destino" else "+ Create new target list", fontSize = 12.sp)
                        }
                    } else {
                        OutlinedTextField(
                            value = customNewDest,
                            onValueChange = { customNewDest = it },
                            label = { Text(if (isSpanish) "Nueva lista" else "New list") },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        TextButton(
                            onClick = { isCreatingNewInMove = false },
                            modifier = Modifier.align(Alignment.Start)
                        ) {
                            Text(if (isSpanish) "← Elegir lista existente" else "← Choose existing list", fontSize = 12.sp)
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val destination = if (isCreatingNewInMove) customNewDest.trim() else selectedDestination.trim()
                        if (destination.isNotBlank()) {
                            onMoveCard(targetCard, destination)
                            cardToMove = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isSpanish) "Mover" else "Move")
                }
            },
            dismissButton = {
                TextButton(onClick = { cardToMove = null }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }
}

/**
 * Compact Word Row displayed inside an expanded list.
 */
@Composable
private fun WordItemRow(
    card: Flashcard,
    learningMode: LearningMode,
    isSpanish: Boolean,
    onPlayAudio: () -> Unit,
    onMove: () -> Unit,
    onDelete: () -> Unit
) {
    val primaryText = if (learningMode == LearningMode.EN_TO_ES) card.spanish else card.english
    val secondaryText = if (learningMode == LearningMode.EN_TO_ES) card.english else card.spanish

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.25f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = card.emoji, fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = primaryText,
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        if (card.type.isNotBlank() && card.type != "Word") {
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = PrimaryIndigo.copy(alpha = 0.12f)
                            ) {
                                Text(
                                    text = card.type,
                                    fontSize = 9.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryIndigo,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                )
                            }
                        }
                    }
                    Text(
                        text = secondaryText,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                // Audio button
                IconButton(onClick = onPlayAudio, modifier = Modifier.size(28.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                        contentDescription = "Audio",
                        tint = PrimaryIndigo,
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Move to another list
                IconButton(onClick = onMove, modifier = Modifier.size(28.dp)) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.DriveFileMove,
                        contentDescription = "Mover",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                }

                // Delete card
                IconButton(onClick = onDelete, modifier = Modifier.size(28.dp)) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = PracticeCoral.copy(alpha = 0.8f),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
