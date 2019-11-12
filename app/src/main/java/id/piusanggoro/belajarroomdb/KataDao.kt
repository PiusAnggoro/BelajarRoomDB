package id.piusanggoro.belajarroomdb

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
interface KataDao {
    @Query("SELECT * from tabel_kata ORDER BY kata ASC")
    fun getAlphabetizedWords(): LiveData<List<Kata>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Kata)

    @Query("DELETE FROM tabel_kata")
    suspend fun deleteAll()
}