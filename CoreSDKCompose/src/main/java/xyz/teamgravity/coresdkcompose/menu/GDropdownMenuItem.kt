package xyz.teamgravity.coresdkcompose.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import xyz.teamgravity.coresdkcompose.image.IconPlain
import xyz.teamgravity.coresdkcompose.text.TextPlain

@Composable
fun GDropdownMenuItem(
    onDismiss: () -> Unit,
    onClick: () -> Unit,
    @DrawableRes icon: Int? = null,
    @StringRes label: Int,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true
) {
    DropdownMenuItem(
        text = {
            TextPlain(
                id = label
            )
        },
        onClick = {
            onDismiss()
            onClick()
        },
        leadingIcon = icon?.let {
            {
                IconPlain(
                    icon = it,
                    contentDescription = label
                )
            }
        },
        trailingIcon = trailingIcon,
        enabled = enabled
    )
}