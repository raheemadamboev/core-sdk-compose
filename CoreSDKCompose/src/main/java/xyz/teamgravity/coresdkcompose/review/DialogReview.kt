package xyz.teamgravity.coresdkcompose.review

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import xyz.teamgravity.coresdkcompose.R

@Composable
fun DialogReview(
    visible: Boolean,
    onDismiss: () -> Unit,
    onDeny: () -> Unit,
    onRemindLater: () -> Unit,
    onReview: () -> Unit
) {
    if (visible) {
        AlertDialog(
            onDismissRequest = onDismiss,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = null
                )
            },
            title = {
                Text(
                    text = stringResource(R.string.review_title)
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.review_text)
                )
            },
            confirmButton = {
                Column {
                    TextButton(
                        onClick = {
                            onDismiss()
                            onDeny()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.review_dismiss_button)
                        )
                    }
                    TextButton(
                        onClick = {
                            onDismiss()
                            onRemindLater()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.review_neutral_button)
                        )
                    }
                    TextButton(
                        onClick = {
                            onDismiss()
                            onReview()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.review_confirm_button)
                        )
                    }
                }
            }
        )
    }
}