package com.example.foodapplication.favorite.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.DaggerCoreComponent
import com.example.core.ui.FavoriteAdapter
import com.example.foodapplication.R
import com.example.foodapplication.databinding.FragmentListItemBinding
import com.example.foodapplication.favorite.di.DaggerFavoriteComponent
import com.example.foodapplication.ui.ViewModelFactory
import com.example.foodapplication.ui.detail.food.DetailFoodActivity
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var factory:ViewModelFactory

    private val viewModelFavorite: FavoriteFoodViewModel by viewModels { factory }

    private var _binding:FragmentListItemBinding? = null
    private lateinit var toolbar: Toolbar
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListItemBinding.inflate(inflater, container, false)
        Toast.makeText(activity,"ada di Favorite", Toast.LENGTH_SHORT).show()
        toolbar = _binding!!.toolbarBack
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("error","FavoriteFragment masuk onAttach")

        DaggerFavoriteComponent
                .builder()
                .coreComponent(DaggerCoreComponent.factory().create(requireContext()))
                .build()
                .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            requireActivity().window.statusBarColor = Color.parseColor(getString(R.string.orange))

            val favoriteAdapter = FavoriteAdapter()
            toolbar.title = "Favorite"
            favoriteAdapter.onItemClick = {
                selectedData ->
                val intent = Intent(activity, DetailFoodActivity::class.java)
                intent.putExtra(DetailFoodActivity.EXTRA_TITLE_COOKING, selectedData.title)
                startActivity(intent)
            }

            Log.e("error","FavoriteFragment masuk onViewCreated")
            viewModelFavorite.favoriteData.observe(viewLifecycleOwner) { dataFavorite ->
                if(dataFavorite.isNullOrEmpty()){
                    binding.imgNoItem.visibility = View.VISIBLE
                    binding.btnBackToMain.visibility = View.VISIBLE
                }else {
                    binding.btnBackToMain.visibility = View.GONE
                    binding.imgNoItem.visibility = View.GONE
                }
                favoriteAdapter.setData(dataFavorite)
                favoriteAdapter.notifyDataSetChanged()
            }

            with(binding.rvListItem){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteAdapter
            }
            binding.btnBackToMain.setOnClickListener {
                requireActivity().onBackPressed()
            }
            toolbar.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}