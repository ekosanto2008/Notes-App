package com.santoso.androidroom.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.santoso.androidroom.*
import com.santoso.androidroom.database.local.Note
import com.santoso.androidroom.databinding.ActivityMainBinding
import com.santoso.androidroom.ui.viewmodel.NoteViewModel
import com.santoso.androidroom.ui.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val newNoteActivityRequestCode = 1
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = NoteAdapter()
        binding.rvNote.adapter = adapter
        binding.rvNote.layoutManager = LinearLayoutManager(this)
        binding.rvNote.setHasFixedSize(true)

        noteViewModel.allNotes.observe(this) { notes ->
            notes.let { adapter.submitList(it) }
        }

        binding.fbView.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNote::class.java)
            startActivityForResult(intent, newNoteActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {
                val note = intentData?.getStringExtra(NewNote.EXTRA_TITLE)?.let {
                    Note(
                        it,
                        intentData.getStringExtra(NewNote.EXTRA_DESC)!!
                    )
                }
            if (note != null) {
                noteViewModel.insert(note)
            }

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.refresh_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.refresh -> {
                noteViewModel.delete()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}