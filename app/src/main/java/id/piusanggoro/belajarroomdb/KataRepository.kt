package id.piusanggoro.belajarroomdb

import androidx.lifecycle.LiveData

class KataRepository(private val wordDao: KataDao) {
    val allWords: LiveData<List<Kata>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Kata) {
        wordDao.insert(word)
    }
}