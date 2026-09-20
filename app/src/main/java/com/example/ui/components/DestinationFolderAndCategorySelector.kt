package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.FolderOpen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.Folder
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.PrimaryIndigo
import com.example.ui.util.AppLanguage
import com.example.ui.viewmodel.MainViewModel

/**
 * Universal destination selector that allows users to pick both the Folder and the Category/List inside it,
 * or create a new Folder / List on the fly with automatic association.
 */
@Composable
fun DestinationFolderAndCategorySelector(
    viewModel: MainViewModel,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    initialFolderId: String? = null,
    showContainerCard: Boolean = true
) {
    val folders by viewModel.folders.collectAsStateWithLifecycle()
    val allCategories by viewModel.categories.collectAsStateWithLifecycle()
    val appLanguage by viewModel.appLanguage.collectAsStateWithLifecycle()
    val isSpanish = appLanguage == AppLanguage.SPANISH

    // Determine initial folder from selectedCategory
    var selectedFolderId by rememberSaveable(initialFolderId) {
        val matchingFolder = if (initialFolderId != null) {
            folders.find { it.id == initialFolderId }
        } else {
            folders.find { f -> f.categoryNames.any { it.equals(selectedCategory, ignoreCase = true) } }
        }
        mutableStateOf(matchingFolder?.id)
    }

    var showNewFolderDialog by remember { mutableStateOf(false) }
    var showNewListDialog by remember { mutableStateOf(false) }

    val activeFolder = folders.find { it.id == selectedFolderId }

    // Visible categories filtered by folder
    val visibleCategories = remember(selectedFolderId, activeFolder, allCategories) {
        if (activeFolder == null) {
            allCategories
        } else {
            val listInFolder = allCategories.filter { cat ->
                activeFolder.categoryNames.any { it.equals(cat, ignoreCase = true) }
            }
            if (listInFolder.isEmpty()) emptyList() else listInFolder
        }
    }

    val content = @Composable {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Header Row: Title and Quick Create actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title ?: if (isSpanish) "📁 Carpeta & Lista de Destino:" else "📁 Target Folder & List:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    // + Carpeta Button
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = PrimaryIndigo.copy(alpha = 0.12f),
                        border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { showNewFolderDialog = true }
                            .testTag("btn_quick_new_folder")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.CreateNewFolder, contentDescription = null, tint = PrimaryIndigo, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = if (isSpanish) "+ Carpeta" else "+ Folder",
                                color = PrimaryIndigo,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.5.sp
                            )
                        }
                    }

                    // + Lista Button
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = ElectricCyan.copy(alpha = 0.14f),
                        border = BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.4f)),
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { showNewListDialog = true }
                            .testTag("btn_quick_new_list")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, tint = ElectricCyan, modifier = Modifier.size(13.dp))
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = if (isSpanish) "+ Lista" else "+ List",
                                color = ElectricCyan,
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.5.sp
                            )
                        }
                    }
                }
            }

            // --- 1. Folder Selector Row ---
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = if (isSpanish) "1. Selecciona la Carpeta:" else "1. Select Folder:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // "Todas las Carpetas" (no folder filter)
                    item {
                        val isAllSelected = selectedFolderId == null
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isAllSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                            border = if (isAllSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { selectedFolderId = null }
                                .testTag("destination_folder_all")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = if (isAllSelected) Icons.Default.FolderOpen else Icons.Default.Folder,
                                    contentDescription = null,
                                    tint = if (isAllSelected) Color.White else PrimaryIndigo,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = if (isSpanish) "Todas" else "All",
                                    fontSize = 11.5.sp,
                                    fontWeight = if (isAllSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isAllSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    items(folders, key = { it.id }) { folder ->
                        val isSelected = selectedFolderId == folder.id
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                            border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                    selectedFolderId = folder.id
                                    // If current category is not in this folder and folder has categories, auto-pick first one
                                    if (folder.categoryNames.isNotEmpty() && !folder.categoryNames.any { it.equals(selectedCategory, ignoreCase = true) }) {
                                        onCategorySelected(folder.categoryNames.first())
                                    }
                                }
                                .testTag("destination_folder_${folder.id}")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = folder.emoji, fontSize = 13.sp)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = folder.name,
                                    fontSize = 11.5.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (isSelected) Color.White.copy(alpha = 0.25f) else PrimaryIndigo.copy(alpha = 0.12f)
                                ) {
                                    Text(
                                        text = "${folder.categoryNames.size}",
                                        fontSize = 9.5.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.White else PrimaryIndigo,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // --- 2. Category / List Selector Row ---
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (activeFolder == null) {
                            if (isSpanish) "2. Selecciona la Lista:" else "2. Select List:"
                        } else {
                            if (isSpanish) "2. Selecciona la Lista en '${activeFolder.name}':" else "2. Select List in '${activeFolder.name}':"
                        },
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (visibleCategories.isEmpty()) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = PrimaryIndigo.copy(alpha = 0.08f),
                        border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.25f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { showNewListDialog = true }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = PrimaryIndigo.copy(alpha = 0.15f),
                                modifier = Modifier.size(26.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.Add, contentDescription = null, tint = PrimaryIndigo, modifier = Modifier.size(16.dp))
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (isSpanish) "Esta carpeta aún no tiene listas" else "No lists in this folder yet",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryIndigo
                                )
                                Text(
                                    text = if (isSpanish) "Toca aquí para crear una lista nueva" else "Tap here to create a new list",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                } else {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        items(visibleCategories) { cat ->
                            val isSelected = cat.equals(selectedCategory, ignoreCase = true)
                            val catFolder = folders.find { f -> f.categoryNames.any { it.equals(cat, ignoreCase = true) } }

                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                                border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable {
                                        onCategorySelected(cat)
                                        // If clicked list is in a specific folder, sync folder selection
                                        if (catFolder != null && selectedFolderId == null) {
                                            selectedFolderId = catFolder.id
                                        }
                                    }
                                    .testTag("destination_category_$cat")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(text = getCategoryIcon(cat), fontSize = 12.sp)
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Text(
                                        text = cat,
                                        fontSize = 11.5.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                    if (isSelected) {
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Icon(
                                            imageVector = Icons.Default.Check,
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
            }

            // --- 3. Destination Assigned Confirmation Badge ---
            val assignedFolder = folders.find { f -> f.categoryNames.any { it.equals(selectedCategory, ignoreCase = true) } } ?: activeFolder
            val folderBadgeName = assignedFolder?.let { "${it.emoji} ${it.name}" } ?: if (isSpanish) "📁 General" else "📁 General"
            val categoryBadgeName = "${getCategoryIcon(selectedCategory)} $selectedCategory"

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = PrimaryIndigo.copy(alpha = 0.08f),
                border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.25f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "🎯", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isSpanish) "Destino:" else "Target:",
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(6.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.65f),
                        border = BorderStroke(1.dp, PrimaryIndigo.copy(alpha = 0.2f)),
                        modifier = Modifier.weight(1f, fill = false)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = folderBadgeName,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryIndigo,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = " › ",
                                fontSize = 11.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryIndigo.copy(alpha = 0.7f)
                            )
                            Text(
                                text = categoryBadgeName,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onSurface,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }

    if (showContainerCard) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
            modifier = modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.padding(12.dp)) {
                content()
            }
        }
    } else {
        Box(modifier = modifier) {
            content()
        }
    }

    // --- Dialog: Create New Folder ---
    if (showNewFolderDialog) {
        var folderName by remember { mutableStateOf("") }
        var folderDesc by remember { mutableStateOf("") }
        var folderEmoji by remember { mutableStateOf("📁") }
        val emojiPresets = listOf("📁", "☕", "✈️", "💼", "🎓", "🍕", "💻", "💡", "🎬", "🏥", "🔬", "⚽", "📚", "🛍️", "🏠", "🎨", "🌍", "🚀", "❤️")

        AlertDialog(
            onDismissRequest = { showNewFolderDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = folderEmoji, fontSize = 22.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isSpanish) "Nueva Carpeta" else "New Folder",
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
                    OutlinedTextField(
                        value = folderName,
                        onValueChange = { folderName = it },
                        label = { Text(if (isSpanish) "Nombre de Carpeta *" else "Folder Name *") },
                        placeholder = { Text(if (isSpanish) "ej. Trabajo, Viajes..." else "e.g. Work, Travel...") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = folderDesc,
                        onValueChange = { folderDesc = it },
                        label = { Text(if (isSpanish) "Descripción (Opcional)" else "Description (Optional)") },
                        placeholder = { Text(if (isSpanish) "ej. Para estudio profesional" else "e.g. Professional study") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = if (isSpanish) "Icono / Emoji:" else "Icon / Emoji:",
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(emojiPresets) { emoji ->
                            val isSelected = folderEmoji == emoji
                            Surface(
                                shape = CircleShape,
                                color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .clickable { folderEmoji = emoji }
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(text = emoji, fontSize = 16.sp)
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (folderName.isNotBlank()) {
                            viewModel.createFolder(folderName.trim(), folderEmoji, folderDesc.trim())
                            val createdFolder = viewModel.folders.value.lastOrNull()
                            if (createdFolder != null) {
                                selectedFolderId = createdFolder.id
                            }
                            showNewFolderDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(if (isSpanish) "Crear Carpeta" else "Create Folder")
                }
            },
            dismissButton = {
                TextButton(onClick = { showNewFolderDialog = false }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }

    // --- Dialog: Create New List / Category with Folder Choice ---
    if (showNewListDialog) {
        var newListName by remember { mutableStateOf("") }
        var targetFolderForNewList by remember { mutableStateOf<String?>(selectedFolderId) }

        AlertDialog(
            onDismissRequest = { showNewListDialog = false },
            title = {
                Text(
                    text = if (isSpanish) "Nueva Lista de Vocabulario" else "New Vocabulary List",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = newListName,
                        onValueChange = { newListName = it },
                        label = { Text(if (isSpanish) "Nombre de la Lista *" else "List Name *") },
                        placeholder = { Text(if (isSpanish) "ej. Aeropuerto, Reuniones..." else "e.g. Airport, Meetings...") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (folders.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = if (isSpanish) "Guardar dentro de la Carpeta:" else "Save inside Folder:",
                            fontSize = 11.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            item {
                                val isNone = targetFolderForNewList == null
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isNone) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { targetFolderForNewList = null }
                                ) {
                                    Text(
                                        text = if (isSpanish) "Sin Carpeta" else "No Folder",
                                        fontSize = 11.sp,
                                        fontWeight = if (isNone) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isNone) Color.White else MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                    )
                                }
                            }

                            items(folders) { folder ->
                                val isSelected = targetFolderForNewList == folder.id
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isSelected) PrimaryIndigo else MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { targetFolderForNewList = folder.id }
                                ) {
                                    Text(
                                        text = "${folder.emoji} ${folder.name}",
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val trimmed = newListName.trim()
                        if (trimmed.isNotBlank()) {
                            viewModel.addCategory(trimmed, targetFolderForNewList)
                            onCategorySelected(trimmed)
                            if (targetFolderForNewList != null) {
                                selectedFolderId = targetFolderForNewList
                            }
                            showNewListDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryIndigo),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(if (isSpanish) "Crear Lista" else "Create List")
                }
            },
            dismissButton = {
                TextButton(onClick = { showNewListDialog = false }) {
                    Text(if (isSpanish) "Cancelar" else "Cancel")
                }
            }
        )
    }
}
