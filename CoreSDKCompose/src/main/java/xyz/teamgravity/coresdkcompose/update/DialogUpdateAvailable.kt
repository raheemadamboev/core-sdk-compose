package xyz.teamgravity.coresdkcompose.update

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import xyz.teamgravity.coresdkandroid.update.UpdateManager
import xyz.teamgravity.coresdkcompose.R

@Composable
fun DialogUpdateAvailable(
    visible: Boolean,
    type: UpdateManager.Type,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (visible && type != UpdateManager.Type.None) {
        val forced by remember { derivedStateOf { type == UpdateManager.Type.Forced } }
        AlertDialog(
            onDismissRequest = onDismiss,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = null
                )
            },
            title = {
                Text(
                    text = stringResource(if (forced) R.string.update_available_title_forced else R.string.update_available_title_optional)
                )
            },
            text = {
                Text(
                    text = stringResource(if (forced) R.string.update_available_text_forced else R.string.update_available_text_optional)
                )
            },
            dismissButton = {
                if (!forced) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text(
                            text = stringResource(R.string.update_available_dismiss_button)
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirm
                ) {
                    Text(
                        text = stringResource(R.string.update_available_confirm_button)
                    )
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = !forced,
                dismissOnClickOutside = !forced
            )
        )
    }
}