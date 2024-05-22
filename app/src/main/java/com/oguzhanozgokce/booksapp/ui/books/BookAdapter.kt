package com.oguzhanozgokce.booksapp.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguzhanozgokce.booksapp.R
import com.oguzhanozgokce.booksapp.data.room.BookEntity
import com.oguzhanozgokce.booksapp.databinding.BookItemBinding

class BookAdapter(
    private val onFavoriteClickListener: (BookEntity) -> Unit) :
    ListAdapter<BookEntity, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentBook = getItem(position)
        holder.bind(currentBook, onFavoriteClickListener)
    }

    inner class BookViewHolder(private val binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookEntity, onFavoriteClickListener: (BookEntity) -> Unit) {
            binding.apply {
                title.text = book.title
                yazar.text = book.yazar
                fiyat.text = book.fiyat

                Glide.with(itemView.context)
                    .load(book.image) // BookEntity içinde image için bir alan olduğunu varsayıyorum
                    .placeholder(R.drawable.books_defoult) // Yükleme sırasında gösterilecek yer tutucu resim
                    .into(image) // Resmi yüklemek için ImageView

                // Favori ikonu
                favoriteIcon.setImageResource(
                    if (book.isFavorite) {
                        R.drawable.baseline_favorite_24
                    } else {
                        R.drawable.favorite_border_24
                    }
                )

                // Favoriye ekleme veya çıkarma işlemi
                favoriteIcon.setOnClickListener {
                    onFavoriteClickListener(book)
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



