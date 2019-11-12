package id.piusanggoro.belajarroomdb 

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class KataViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: KataRepository
    val allWords: LiveData<List<Kata>>

    init {
        val wordsDao = KataRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = KataRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(word: Kata) = viewModelScope.launch {
        repository.insert(word)
    }
}