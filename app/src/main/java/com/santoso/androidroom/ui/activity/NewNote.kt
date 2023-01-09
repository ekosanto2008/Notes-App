package com.santoso.androidroom.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.santoso.androidroom.R
import com.santoso.androidroom.databinding.ActivityNewNoteBinding

class NewNote : AppCompatActivity() {

    private lateinit var binding: ActivityNewNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.add)

        binding.btnSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.edtTitle.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else if (TextUtils.isEmpty(binding.edtDesc.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = binding.edtTitle.text.toString()
                val desc = binding.edtDesc.text.toString()
                replyIntent.putExtra(EXTRA_TITLE, title)
                replyIntent.putExtra(EXTRA_DESC, desc)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESC = "extra_desc"
    }
}