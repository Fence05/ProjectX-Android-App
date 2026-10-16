package org.setu.projectx.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.setu.androidx.models.TechStore
import org.setu.projectx.R

class TechStoreAdapter(
    private var stores: List<TechStore>,
    private val onEdit: (TechStore) -> Unit,
    private val onDelete: (TechStore) -> Unit,
    private val onFavoriteToggle: ((TechStore) -> Unit)? = null
) : RecyclerView.Adapter<TechStoreAdapter.MainHolder>() {

    class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val storeTitle: TextView = itemView.findViewById(R.id.storeTitle)
        val cityText: TextView = itemView.findViewById(R.id.cityText)
        val coordinatesText: TextView = itemView.findViewById(R.id.coordinatesText)
        val descriptionText: TextView = itemView.findViewById(R.id.descriptionText)
        val userOwnNotes: TextView = itemView.findViewById(R.id.userOwnNotes)
        val favoritesButton: Button = itemView.findViewById(R.id.FavoritesButton)
        val removeButton: Button = itemView.findViewById(R.id.RemoveButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.card_store,
                parent,
                false
            )

        return MainHolder(view)
    }

    override fun onBindViewHolder(
        holder: MainHolder,
        position: Int
    ) {


        val store = stores[position]

        holder.storeTitle.text = store.name
        holder.cityText.text = store.city
        holder.coordinatesText.text = "Lat: ${store.latitude}, Long: ${store.longitude}"
        holder.descriptionText.text = store.description

        if (store.note.isNotEmpty()) {
            holder.userOwnNotes.text = store.note
            holder.userOwnNotes.visibility = View.VISIBLE
        } else {
            holder.userOwnNotes.visibility = View.GONE
        }

        holder.favoritesButton.setText(
            if (store.isFavorite) R.string.remove_favorite else R.string.save_favorite
        )

        holder.itemView.setOnClickListener { onEdit(store) }
        holder.removeButton.setOnClickListener { onDelete(store) }
        holder.favoritesButton.setOnClickListener { onFavoriteToggle?.invoke(store) }
    }

    override fun getItemCount(): Int {
        return stores.size
    }

    fun updateStores(newStores: List<TechStore>) {
        stores = newStores
        notifyDataSetChanged()
    }
}
