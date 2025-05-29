package xyz.teamgravity.coresdkcompose.button

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun IconButtonPlain(
    onClick: () -> Unit,
    icon: ImageVector,
    @StringRes contentDescription: Int,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = contentDescription),
            tint = tint
        )
    }
}

@Composable
fun IconButtonPlain(
    onClick: () -> Unit,
    @DrawableRes icon: Int,
    @StringRes contentDescription: Int,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = contentDescription),
            tint = tint
        )
    }
}