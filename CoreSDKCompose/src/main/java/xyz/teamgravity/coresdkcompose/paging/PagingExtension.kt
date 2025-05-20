package xyz.teamgravity.coresdkcompose.paging

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

/**
 * Should show empty state?
 *
 * This won't return true while data is loading initially.
 */
@Composable
fun LazyPagingItems<*>.shouldShowEmptyState(): State<Boolean> {
    return remember { derivedStateOf { loadState.refresh !is LoadState.Loading && itemSnapshotList.isEmpty() } }
}