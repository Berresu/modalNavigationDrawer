package com.example.modalnavigationdrawer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.modalnavigationdrawer.ui.theme.ModalNavigationDrawerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModalNavigationDrawerTheme {
                DetayliMenuOrnegi()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetayliMenuOrnegi(){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                Text("Menü Başlığı", modifier = Modifier.padding(16.dp))

                NavigationDrawerItem(
                    label = {Text("Section 1")},
                    selected = false,
                    onClick = {
                        Toast.makeText(context, "Bölüm 1 tıklandı", Toast.LENGTH_SHORT).show()
                    }
                )

                NavigationDrawerItem(
                    label = {Text("Section 2")},
                    selected = false,
                    onClick = {
                        Toast.makeText(context, "Bölüm 2 tıklandı", Toast.LENGTH_SHORT).show()
                    }
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                NavigationDrawerItem(
                    label = {Text("Settings")},
                    selected = false,
                    icon = {Icon(Icons.Default.Settings, contentDescription = null)},
                    onClick = {Toast.makeText(context, "Ayarlara tıklandı", Toast.LENGTH_SHORT).show()}
                )

                NavigationDrawerItem(
                    label = {Text("Help and Feedback")},
                    selected = false,
                    icon = {Icon(Icons.Default.Info, contentDescription = null)},
                    onClick = { Toast.makeText(context, "Yardım ve Geri bildirime tıklandı", Toast.LENGTH_SHORT).show()}
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = { Text("Benim Uygulamam") },
                    navigationIcon = {
                        IconButton(onClick = {
                            // SCOPE.LAUNCH: Animasyonlu açılış işlemini başlatır.
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menüyü Aç")
                        }
                    }
                )
            }
        ) {
            innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize(), contentAlignment = Alignment.Center){
                Text("Menüyü açmak için sol üstteki ikona basın!")
            }
        }
    }
}
