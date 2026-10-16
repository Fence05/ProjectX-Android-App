package org.setu.projectx.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.setu.projectx.AppData
import org.setu.projectx.R

class EditNoteActivity : AppCompatActivity() {

    private lateinit var noteInput: EditText
    private lateinit var storeNameHeader: TextView

    private var editingId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        noteInput = findViewById(R.id.noteEditText)
        storeNameHeader = findViewById(R.id.storeNameHeader)

        val saveButton = findViewById<Button>(R.id.saveNoteButton)
        val cancelButton = findViewById<Button>(R.id.cancelNoteButton)

        editingId = intent.getLongExtra("id", -1L)
            .takeIf { it != -1L } ?: intent.getLongExtra("store_id", -1L)
            .takeIf { it != -1L }

        if (editingId != null) {
            loadExistingNote(editingId!!)
        }

        saveButton.setOnClickListener {
            saveNote()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    private fun loadExistingNote(id: Long) {
        val store = AppData.TechStores.findOne(id)

        if (store == null) {
            Toast.makeText(
                this,
                "Store not found",
                Toast.LENGTH_SHORT
            ).show()

            finish()
            return
        }

        storeNameHeader.text = "Store: ${store.name}"
        noteInput.setText(store.note)
    }

    private fun saveNote() {
        if (editingId == null) {
            Toast.makeText(
                this,
                "Invalid Store ID",
                Toast.LENGTH_SHORT
            ).show()
            finish()
            return
        }

        val store = AppData.TechStores.findOne(editingId!!)

        if (store == null) {
            Toast.makeText(
                this,
                "Store not found",
                Toast.LENGTH_SHORT
            ).show()
            finish()
            return
        }

        val noteText = noteInput.text.toString().trim()
        store.note = noteText

        AppData.TechStores.update(store)

        Toast.makeText(
            this,
            "Note updated",
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }
}
