package com.example.foodapplication.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapplication.MyApplication
import com.example.foodapplication.R
import com.example.foodapplication.core.data.Resource
import com.example.foodapplication.core.data.source.local.entity.ArticleEntity
import com.example.foodapplication.core.data.source.local.entity.CookingEntity
import com.example.foodapplication.core.domain.model.Article
import com.example.foodapplication.core.domain.model.Cooking
import com.example.foodapplication.databinding.FragmentHomeBinding
import com.example.foodapplication.ui.ViewModelFactory
import com.example.foodapplication.ui.detail.food.DetailFoodActivity
import com.example.foodapplication.ui.home.listItem.ListItemFragment
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val homeViewModel: HomeViewModel by viewModels { factory }

    private var _binding: FragmentHomeBinding? = null
    private var btn_forwardViewArticle:Button? = null
    private var btn_forwardViewCooking:Button? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        btn_forwardViewArticle = _binding!!.tvArticle
        btn_forwardViewCooking = _binding!!.tvCookingMenu
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            requireActivity().window.statusBarColor = Color.parseColor(getString(R.string.orange))
            val cookAdapter = CookingAdapter()
            val articleAdapter = ArticleAdapter()
            val arrayListArticle = ArrayList<Article>()
            val arrayListCooking = ArrayList<Cooking>()

            cookAdapter.onItemClick = {
                selectedData ->
                val intent = Intent(context, DetailFoodActivity::class.java)
                intent.putExtra(DetailFoodActivity.EXTRA_TITLE_COOKING, selectedData.title)
                intent.putExtra(DetailFoodActivity.EXTRA_ID_COOKING, selectedData.cookingID)
                startActivity(intent)
            }

            articleAdapter.onItemClick = {
                selectedData ->
                val intent = Intent(activity, DetailFoodActivity::class.java)
                intent.putExtra(DetailFoodActivity.EXTRA_ARTICLE_TITLE, selectedData.title)
                intent.putExtra(DetailFoodActivity.EXTRA_ARTICLE_TAG, selectedData.tags)
                intent.putExtra(DetailFoodActivity.EXTRA_ARTICLE_ID, selectedData.key)
                startActivity(intent)
            }

            homeViewModel.cook.observe(viewLifecycleOwner) { food ->
                if(food != null){
                    when(food){
                        is Resource.Success -> {
                            cookAdapter.setData(food.data)
                            arrayListCooking.addAll(food.data!!)
                            btn_forwardViewCooking?.setOnClickListener { direction -> val mBundle = Bundle()
                                mBundle.putParcelableArrayList(ListItemFragment.COOKING, arrayListCooking)
                                direction.findNavController().navigate(R.id.action_navigation_home_to_listItemFragment, mBundle)
                            }
                        }
                    }
                }
            }

            homeViewModel.article.observe(viewLifecycleOwner) { article ->
                if (article != null){
                    when(article){
                        is Resource.Success -> {
                            articleAdapter.setData(article.data)
                            arrayListArticle.addAll(article.data!!)
                            btn_forwardViewArticle?.setOnClickListener { direction -> val mBundle = Bundle()
                                mBundle.putParcelableArrayList(ListItemFragment.ARTICLE, arrayListArticle)
                                direction.findNavController().navigate(R.id.action_navigation_home_to_listItemFragment, mBundle)
                            }
                        }
                    }
                }
            }

            binding.searchButton?.setOnClickListener {
                direction ->
                direction.findNavController().navigate(R.id.action_navigation_home_to_searchFragment)
            }
            with(binding.rvRecentFood){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = cookAdapter
            }
            with(binding.rvPopular){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = articleAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}