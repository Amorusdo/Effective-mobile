package com.example.ui.components.bottom_navigation

import SurfaceDark
import White
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.core_navigation.navigation.Routes
import com.example.ui.R
import com.example.ui.models.BottomNavItemData

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        BottomNavItemData(Routes.COURSES , R.drawable.ic_state_layer, R.string.general) ,
        BottomNavItemData(Routes.FAVORITES, R.drawable.ic_bookmarker, R.string.favourites),
        BottomNavItemData(Routes.ACCOUNT, R.drawable.ic_accout, R.string.account)
    )

    NavigationBar(
        containerColor = SurfaceDark,
        contentColor = White
    ) {
        items.forEach { item ->
            BottomNavItem(
                route = item.route,
                currentRoute = currentRoute,
                icon = item.icon,
                label = stringResource(item.labelRes),
                onNavigate = onNavigate
            )
        }
    }
}