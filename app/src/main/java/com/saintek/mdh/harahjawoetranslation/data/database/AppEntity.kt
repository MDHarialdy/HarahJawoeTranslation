package com.saintek.mdh.harahjawoetranslation.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val age: Int,
    val city: String
)

@Entity
data class HistoryEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val historyId: Int,
    val imageByteArray: ByteArray,
    val result: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HistoryEntity

        if (id != other.id) return false
        if (!imageByteArray.contentEquals(other.imageByteArray)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + imageByteArray.contentHashCode()
        return result
    }
}