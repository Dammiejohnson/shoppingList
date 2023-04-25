package com.catalyst.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.catalyst.shoppinglist.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase (){

    abstract fun getShoppingDao(): ShoppingDao //that we can access the database operations from inside the database class

    companion object {
        //this is like the static class in java which allows to create reach a method without creating an object for the class and we are creating a singleton
        //to create just one instance, volatile annotation to only one thread at a time write to this instance

        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        //the operator function is executed whenever we create an instance of the shoppingdb class, it will return the instance
        //but if it is null it will call the synchronised block so no other thread can access it and if its null again, it creates the db
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            ShoppingDatabase::class.java, "ShoppingDB.db").build()

    }
}

//the @Database annotation was used to also describe which model uses this database, it is kept in an array, when there is an update on the database
// theversion should change and this inherits from the room database class