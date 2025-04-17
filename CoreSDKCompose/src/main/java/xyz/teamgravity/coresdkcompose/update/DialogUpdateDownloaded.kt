package xyz.teamgravity.coresdkcompose.update

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import xyz.teamgravity.coresdkcompose.R

@Composable
fun DialogUpdateDownloaded(
    visible: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (visible) {
        AlertDialog(
            onDismissRequest = onDismiss,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = null
                )
            },
            title = {
                Text(
                    text = stringResource(R.string.update_downloaded_title)
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.update_downloaded_text)
                )
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text(
                        text = stringResource(R.string.update_downloaded_dismiss_button)
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirm
                ) {
                    Text(
                        text = stringResource(R.string.update_downloaded_confirm_button)
                    )
                }
            },
            properties = DialogProperties(
                dismissOnClickOutside = false
            )
        )
    }
}