package xyz.teamgravity.coresdkdemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import timber.log.Timber
import xyz.teamgravity.coresdkandroid.crypto.CryptoManager
import xyz.teamgravity.coresdkandroid.preferences.Preferences
import xyz.teamgravity.coresdkandroid.review.ReviewManager
import xyz.teamgravity.coresdkcompose.review.DialogReview
import xyz.teamgravity.coresdkdemoapp.ui.theme.CoreSDKComposeTheme

class MainActivity : ComponentActivity() {

    private val review by lazy {
        ReviewManager(
            preferences = Preferences(
                crypto = CryptoManager(),
                context = this
            ),
            context = this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        enableEdgeToEdge()
        setContent {
            CoreSDKComposeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    Column(
                        modifier = Modifier.padding(padding)
                    ) {
                        var visible by remember { mutableStateOf(false) }

                        LaunchedEffect(Unit) {
                            launch {
                                review.event.collect {
                                    when (it) {
                                        ReviewManager.ReviewEvent.Eligible -> visible = true
                                    }
                                }
                            }
                            review.monitor()
                        }

                        DialogReview(
                            visible = visible,
                            onDismiss = {
                                visible = false
                            },
                            onDeny = {
                                review.deny()
                            },
                            onRemindLater = {
                                review.remindLater()
                            },
                            onReview = {
                                review.review(this@MainActivity)
                            }
                        )
                    }
                }
            }
        }
    }
}