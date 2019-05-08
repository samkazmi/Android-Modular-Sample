package samkazmi.example.home.callback

import samkazmi.example.home.data.Menu

interface MenuItemCallback {
    fun onMenuItemClicked(menuItem : Menu)
}
