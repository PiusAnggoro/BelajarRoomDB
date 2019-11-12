package id.piusanggoro.belajarroomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Kata::class], version = 1, exportSchema = false)
abstract class KataRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): KataDao

    companion object {
        @Volatile
        private var INSTANCE: KataRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): KataRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        KataRoomDatabase::class.java,
                        "word_database"
                )
                        .addCallback(WordDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(wordDao: KataDao) {
            wordDao.deleteAll()

            var word = Kata("Halo")
            wordDao.insert(word)
            word = Kata("Dunia!")
            wordDao.insert(word)
        }
    }
}