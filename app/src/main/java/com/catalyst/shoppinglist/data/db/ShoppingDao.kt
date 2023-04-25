package com.catalyst.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.catalyst.shoppinglist.data.db.entities.ShoppingItem


@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem) //a mixture of insert and update methods..

    @Delete
    suspend fun delete(item: ShoppingItem) //in sql it doesnt allow to write to the database in the main thread so we can use another thread or coroutines

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems():LiveData<List<ShoppingItem>>

}