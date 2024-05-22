package com.oguzhanozgokce.booksapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguzhanozgokce.booksapp.R
import com.oguzhanozgokce.booksapp.data.room.BookEntity
import com.oguzhanozgokce.booksapp.databinding.BookItemBinding

class BookFavoritesAdapter(
    private val onRemoveFavoriteClickListener: (BookEntity) -> Unit
) : ListAdapter<BookEntity, BookFavoritesAdapter.BookFavoritesViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookFavoritesViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookFavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookFavoritesViewHolder, position: Int) {
        val currentBook = getItem(position)
        holder.bind(currentBook, onRemoveFavoriteClickListener)
    }

    inner class BookFavoritesViewHolder(private val binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookEntity, onRemoveFavoriteClickListener: (BookEntity) -> Unit) {
            binding.apply {
                // Kitap özelliklerini görüntüleme işlemleri
                title.text = book.title
                yazar.text = book.yazar
                fiyat.text = book.fiyat

                // Glide ile resim yükleme işlemi
                Glide.with(itemView.context)
                    .load(book.image)
                    .placeholder(R.drawable.books_defoult)
                    .into(image)

                // Favori ikonu
                favoriteIcon.setImageResource(
                    if (book.isFavorite) {
                        R.drawable.baseline_favorite_24
                    } else {
                        R.drawable.favorite_border_24
                    }
                )

                // Favori kitapları kaldırma işlemi
                favoriteIcon.setOnClickListener {
                    onRemoveFavoriteClickListener(book)
                }
            }
        }
    }

    class BookDiffCallback : DiffUtil.ItemCallback<BookEntity>() {
        override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
            return oldItem == newItem
        }
    }
}
