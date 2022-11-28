package com.fastclicker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.radefffactory.fastclicker.R
import com.radefffactory.fastclicker.R.id.editText
import java.io.File


class Notepad : AppCompatActivity() {

    private val FILENAME = "sample.txt" // Имя файла
    lateinit var button: Button
    private lateinit var editText: EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open -> {
                openFile(FILENAME)
                true
            }
            R.id.action_save -> {
                saveFile(FILENAME)
                true
            }
            else -> true
        }
    }

    // Метод для открытия файла
    private fun openFile(fileName: String) {

        val textFromFile =
            File(applicationContext.filesDir, fileName)
                .bufferedReader()
                .use { it.readText(); }
        editText.setText(textFromFile)
    }

    // Метод для сохранения файла
    private fun saveFile(fileName: String) {
        applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(editText.text.toString().toByteArray())
        }
        Toast.makeText(applicationContext, "Файл сохранён", Toast.LENGTH_SHORT).show()


        }

}


