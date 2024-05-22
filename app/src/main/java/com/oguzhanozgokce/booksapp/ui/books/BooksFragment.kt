package com.oguzhanozgokce.booksapp.ui.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.oguzhanozgokce.booksapp.databinding.FragmentBooksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksFragment : Fragment() {
    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BooksViewModel by viewModels()
    private lateinit var booksAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Kitapları listelemek için RecyclerView oluşturun
        booksAdapter = BookAdapter { book ->
            // Favoriye ekleme veya çıkarma işlemi burada yapılacak
            viewModel.toggleFavorite(book)
        }
        binding.recyclerView.apply {
            adapter = booksAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        // Kitap verilerini gözlemleyin
        viewModel.books.observe(viewLifecycleOwner) { books ->
            booksAdapter.submitList(books)
        }

        // Yüklenme durumunu gözlemleyin
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }


        viewModel.fetchBooks()
    }

}
