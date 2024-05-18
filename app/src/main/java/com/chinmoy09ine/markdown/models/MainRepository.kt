package com.chinmoy09ine.markdown.models

import android.app.Application
import androidx.lifecycle.LiveData
import com.chinmoy09ine.markdown.database.NotesDao
import com.chinmoy09ine.markdown.database.NotesRoomDatabase
import com.chinmoy09ine.markdown.database.NotesTable

class MainRepository(application: Application) {

    private val notesDao: NotesDao = NotesRoomDatabase.getDatabase(application).notesDao()

    suspend fun insertNote(note: NotesTable) {
        try {
            notesDao.insertNote(note)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun updateNote(
        noteId: String,
        title: String,
        description: String,
        updatedAt: Long,
        isLocked: Int,
        isPinned: Long,
        bgColor: String
    ) {

        try {

            notesDao.updateNote(noteId, title, description, updatedAt, isLocked, isPinned, bgColor)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    suspend fun deleteNote(noteId: String) {
        notesDao.deleteNote(noteId)
    }

    suspend fun findNote(noteId: String): List<NotesTable> {
        return notesDao.findNote(noteId)
    }

    val getAllNotes: LiveData<List<NotesTable>> = notesDao.getAllNotes()

}