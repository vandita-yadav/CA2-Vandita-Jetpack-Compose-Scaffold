package com.example.unit5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState


class CA2Execution : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
            MainScreen()}
        }
    }
}

@Composable
fun MainScreen()
{
    val nav = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val route = nav.currentBackStackEntryAsState().value?.destination?.route

                NavigationBarItem(
                    selected = route == "home",
                    onClick = { nav.navigate("home") },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = route == "search",
                    onClick = { nav.navigate("search") },
                    icon = { Icon(Icons.Default.Search, null) },
                    label = { Text("Search") }
                )

                NavigationBarItem(
                    selected = route == "notify",
                    onClick = { nav.navigate("notify") },
                    icon = { Icon(Icons.Default.Notifications, null) },
                    label = { Text("Notify") }
                )

                NavigationBarItem(
                    selected = route == "settings",
                    onClick = { nav.navigate("settings") },
                    icon = { Icon(Icons.Default.Settings, null) },
                    label = { Text("Settings") }
                )
            }
        }
    ) { padding ->NavHost(
        navController = nav,
        startDestination = "home",
        modifier = Modifier.padding(padding)
    ) {
        composable("home") { HomeScreen() }
        composable("search") { SearchScreen() }
        composable("notify") { NotificationScreen() }
        composable("settings") { SettingsScreen() }
    }

    }
}


@Composable
fun HomeScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize().padding(50.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text("Welcome to Home Screen")

            Spacer(modifier = Modifier.height(30.dp))
            Button(onClick = {
                Toast.makeText(context, "Welcome to the Demo App", Toast.LENGTH_SHORT).show()
            }) {
                Text("View Toast")
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Name: Vandita Yadav")
            Text("Registration Number: 12321015")
            Text("Roll number: 13")
            Text("Section: KO006")
        }
    }
}

@Composable
fun SearchScreen() {
    var input by remember { mutableStateOf("") }
    var check by remember { mutableStateOf(false) }
    var range by remember { mutableStateOf(20f) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            TextField(value = input, onValueChange = { input = it }, label = { Text("Search") })
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = check, onCheckedChange = { check = it })
                Text("Advanced Search")
            }
            Slider(value = range, onValueChange = { range = it }, valueRange = 0f..100f)
            Text("Search Result:  $input")
            Text("Advanced:  $check")
            Text("Current Slider Value: ${range.toInt()}")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Name: Vandita Yadav")
            Text("Registration Number: 12321015")
            Text("Roll number: 13")
            Text("Section: KO006")
        }
    }
}

@Composable
fun NotificationScreen() {
    var selected by remember { mutableStateOf("Low") }
    var enabled by remember { mutableStateOf(false) }
    var optionlist  = listOf("Low", "Medium", "High")

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Select Notification Priority")
            optionlist.forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selected == it,
                        onClick = { selected = it }
                    )
                    Text(it)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(checked = enabled, onCheckedChange = { enabled = it })
                Text("  Enable Notifications")
            }
            Text("Selected:  $selected")
            Text("Enabled:  $enabled")
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Name: Vandita Yadav")
            Text("Registration Number: 12321015")
            Text("Roll number: 13")
            Text("Section: KO006")
        }
    }
}

@Composable
fun SettingsScreen() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var darkMode by remember { mutableStateOf(false) }
    var terms by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Column{
            TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })

            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Switch(checked = darkMode, onCheckedChange = { darkMode = it })
                Text("   Dark Mode")
            }

            Row(verticalAlignment = Alignment.CenterVertically)
            {
                Checkbox(checked = terms, onCheckedChange = { terms = it })
                Text("Accept terms and conditions")
            }

            Text("Username: $username")
            Text("Email: $email")
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text("Name: Vandita Yadav")
            Text("Registration Number: 12321015")
            Text("Roll number: 13")
            Text("Section: KO006")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    MaterialTheme{
    MainScreen()}
}