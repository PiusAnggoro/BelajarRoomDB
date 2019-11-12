package id.piusanggoro.belajarroomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabel_kata")
data class Kata(@PrimaryKey @ColumnInfo(name = "kata") val word: String)