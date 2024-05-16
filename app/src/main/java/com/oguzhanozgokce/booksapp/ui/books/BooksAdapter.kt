package com.oguzhanozgokce.booksapp.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguzhanozgokce.booksapp.data.model.Result
import com.oguzhanozgokce.booksapp.databinding.BookItemBinding

class BooksAdapter(
    var books: List<Result>,
    private val onClickFavorite: (Result) -> Unit
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    class BooksViewHolder(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = BookItemBinding.inflate(inflate,parent,false)
        return BooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        with(holder.binding){
            val book = books[position]
            title.text = book.title
            yazar.text = book.yazar
            fiyat.text = book.fiyat
            indirim.text = book.indirim
            // Assuming you have a function to load image from URL
            Glide.with(holder.itemView.context)
                .load(book.image)
                .into(image)

            favoriteIcon.setOnClickListener {
                onClickFavorite(book)
            }
        }

    }
    override fun getItemCount(): Int {
        return books.size
    }
}