package xyz.teamgravity.coresdkcompose.text

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun TextPlain(
    @StringRes id: Int
) {
    Text(
        text = stringResource(id = id)
    )
}