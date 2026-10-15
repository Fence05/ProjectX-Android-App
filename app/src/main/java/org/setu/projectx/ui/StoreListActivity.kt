package org.setu.projectx.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.setu.androidx.models.TechStore
import org.setu.projectx.AppData
import org.setu.projectx.R

class StoreListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TechStoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_list)

        recyclerView = findViewById(R.id.storesRecyclerView)

        adapter = TechStoreAdapter(
            stores = AppData.TechStores.findAll(),
            onEdit = { store ->
                editStore(store)
            },
            onDelete = { store ->
                deleteStore(store)
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val returnButton = findViewById<Button>(R.id.returnButton)

        returnButton.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }

    override fun onResume() {
        super.onResume()

        if (::adapter.isInitialized) {
            adapter.updateStores(
                AppData.TechStores.findAll()
            )
        }
    }

    private fun editStore(store: TechStore) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id", store.id)
        startActivity(intent)
    }

    private fun deleteStore(store: TechStore) {
        AppData.TechStores.delete(store.id)
        adapter.updateStores(
            AppData.TechStores.findAll()
        )
    }
}
