package com.catalyst.shoppinglist.ui.shoppingList

import com.catalyst.shoppinglist.data.db.entities.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)
}