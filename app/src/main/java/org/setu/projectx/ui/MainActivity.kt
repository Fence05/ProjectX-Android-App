package org.setu.projectx.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.setu.projectx.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) {
            v, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val storesButton =
            findViewById<Button>(R.id.storesButton)

        val favoritesButton =
            findViewById<Button>(R.id.favoritesButton)

        storesButton.setOnClickListener {
            startActivity(
                Intent(this, StoreListActivity::class.java)
            )
        }

        favoritesButton.setOnClickListener {
            startActivity(
                Intent(this, FavoritesActivity::class.java)
            )
        }
    }
}