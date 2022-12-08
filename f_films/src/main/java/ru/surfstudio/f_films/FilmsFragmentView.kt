package ru.surfstudio.f_films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.surfstudio.android.filmssurf.f_films.databinding.FragmentFilmsBinding

@AndroidEntryPoint
class FilmsFragmentView : Fragment() {

    private var _binding: FragmentFilmsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FilmsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        //todo
    }
}