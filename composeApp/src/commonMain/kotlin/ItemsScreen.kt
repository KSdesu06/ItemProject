/*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import viewmodel.ItemViewModel

@Composable
fun ItemsScreen(
    viewModel: ItemViewModel,
    onAddItemClicked: () -> Unit,
) {
    val items by viewModel.items.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Items") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddItemClicked) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
            }
        }
    ) {
        LazyColumn {
            items(items) { item ->
                ItemRow(
                    item = item,
                    onEdit = { viewModel.updateItem(it) },
                    onDelete = { viewModel.deleteItem(it.id) }
                )
            }
        }
    }
}
*/

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Item
import repository.ItemRepository
import viewmodel.ItemViewModel

@Composable
fun ItemsScreen(viewModel: ItemViewModel) {
    val items by viewModel.items.collectAsState()
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Items") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog.value = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Item")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items) { item ->
                ItemRow(item)
            }
        }
    }

    if (showDialog.value) {
        ItemInputDialog(onDismiss = { showDialog.value = false },
            onAddItem = { name, description ->
                viewModel.addItem(Item(name = name, description = description))
                showDialog.value = false
            })
    }
}

@Composable
fun ItemRow(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = item.name, modifier = Modifier.weight(1f))
            Text(text = item.description ?: "", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun ItemInputDialog(onDismiss: () -> Unit, onAddItem: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Item") },
        text = {
            Column {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
            }
        },
        confirmButton = {
            Button(onClick = { onAddItem(name, description) }) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


