package xyz.teamgravity.coresdkdemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import xyz.teamgravity.coresdkandroid.update.UpdateManager
import xyz.teamgravity.coresdkcompose.update.DialogUpdateAvailable
import xyz.teamgravity.coresdkdemoapp.ui.theme.CoreSDKComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoreSDKComposeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    Box(
                        modifier = Modifier.padding(padding)
                    ) {
                        var type by remember { mutableStateOf(UpdateManager.Type.Optional) }
                        DialogUpdateAvailable(
                            type = type,
                            onDismiss = {
                                type = UpdateManager.Type.None
                            },
                            onConfirm = {
                            }
                        )
                    }
                }
            }
        }
    }
}