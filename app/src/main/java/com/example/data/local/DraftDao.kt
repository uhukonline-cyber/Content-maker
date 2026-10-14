package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DraftDao {
    @Query("SELECT * FROM saved_drafts ORDER BY createdAt DESC")
    fun getAllDrafts(): Flow<List<SavedDraftEntity>>

    @Query("SELECT * FROM saved_drafts WHERE isFavorite = 1 ORDER BY createdAt DESC")
    fun getFavoriteDrafts(): Flow<List<SavedDraftEntity>>

    @Query("SELECT * FROM saved_drafts WHERE platformId = :platformId ORDER BY createdAt DESC")
    fun getDraftsByPlatform(platformId: String): Flow<List<SavedDraftEntity>>

    @Query("SELECT * FROM saved_drafts WHERE id = :id LIMIT 1")
    suspend fun getDraftById(id: Long): SavedDraftEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDraft(draft: SavedDraftEntity): Long

    @Update
    suspend fun updateDraft(draft: SavedDraftEntity)

    @Delete
    suspend fun deleteDraft(draft: SavedDraftEntity)

    @Query("DELETE FROM saved_drafts WHERE id = :id")
    suspend fun deleteDraftById(id: Long)

    @Query("UPDATE saved_drafts SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(id: Long, isFavorite: Boolean)
}
