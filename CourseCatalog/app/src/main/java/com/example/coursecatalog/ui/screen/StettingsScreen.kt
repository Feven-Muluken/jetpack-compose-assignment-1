//package com.example.coursecatalog.ui.screen
//
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.unit.dp
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.Alignment
//
//
//
//@Composable
//fun SettingsScreen() {
//    var darkModeEnabled by rememberSaveable { mutableStateOf(false) }
//    var notificationsEnabled by rememberSaveable { mutableStateOf(true) }
//    var compactViewEnabled by rememberSaveable { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "Settings",
//            style = MaterialTheme.typography.headlineMedium,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        SettingItem(
//            title = "Dark Mode",
//            checked = darkModeEnabled,
//            onCheckedChange = { darkModeEnabled = it }
//        )
//
//        SettingItem(
//            title = "Enable Notifications",
//            checked = notificationsEnabled,
//            onCheckedChange = { notificationsEnabled = it }
//        )
//
//        SettingItem(
//            title = "Compact View",
//            checked = compactViewEnabled,
//            onCheckedChange = { compactViewEnabled = it }
//        )
//    }
//}
//
//@Composable
//fun SettingItem(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text(text = title, style = MaterialTheme.typography.bodyLarge)
//        Switch(
//            checked = checked,
//            onCheckedChange = onCheckedChange,
//            colors = SwitchDefaults.colors(
//                checkedThumbColor = MaterialTheme.colorScheme.primary
//            )
//        )
//    }
//}
