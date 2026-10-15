package org.setu.projectx.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.setu.projectx.R
import org.setu.projectx.data.AppData
import org.setu.projectx.models.Store

class StoreListActivity : AppCompatActivity() {

    private lateinit var storeAdapter: StoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_list)

        storeAdapter = StoreAdapter(
            stores = AppData.stores.findAll(),
            onFavoriteToggle = { store -> toggleFavorite(store) },
            onItemClick = { store -> showStore(store) }
        )

        findViewById<RecyclerView>(R.id.storesRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@StoreListActivity)
            adapter = storeAdapter
        }

        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        storeAdapter.updateStores(AppData.stores.findAll())
    }

    private fun toggleFavorite(store: Store) {
        AppData.stores.update(store.copy(isFavorite = !store.isFavorite))
        storeAdapter.updateStores(AppData.stores.findAll())
    }

    private fun showStore(store: Store) {
        Toast.makeText(this, store.name, Toast.LENGTH_SHORT).show()
    }
}