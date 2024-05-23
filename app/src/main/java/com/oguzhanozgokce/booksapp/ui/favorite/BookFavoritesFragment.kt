package com.oguzhanozgokce.booksapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.oguzhanozgokce.booksapp.databinding.FragmentBookFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFavoritesFragment : Fragment() {
    private var _binding: FragmentBookFavoritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var adapter: BookFavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adapter oluşturulur
        adapter = BookFavoritesAdapter { book ->
            // Favoriden kaldırma işlemi burada yapılacak
            viewModel.removeFavoriteBook(book)
        }

        // RecyclerView ve adapter ayarları yapılır
        binding.recyclerView.apply {
            adapter = this@BookFavoritesFragment.adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }

        // Favori kitapları gözlemleme işlemi
        viewModel.favoriteBooks.observe(viewLifecycleOwner) { favoriteBooks ->
            adapter.submitList(favoriteBooks)
        }

        // Yükleme durumu gözlemleme işlemi
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBarId.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
