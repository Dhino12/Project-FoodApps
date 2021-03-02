package com.example.foodapplication.ui.category

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.foodapplication.MyApplication
import com.example.foodapplication.R
import com.example.core.data.Resource
import com.example.core.ui.ListCategoryAdapter
import com.example.foodapplication.databinding.FragmentCategoryBinding
import com.example.foodapplication.ui.ViewModelFactory
import com.example.foodapplication.ui.category.contentCategory.ContentCategoryFragment
import javax.inject.Inject

class ListCategoryFragment : Fragment() {

    @Inject
    lateinit var factory:ViewModelFactory

    private val listCategoryViewModel:ListCategoryViewModel by viewModels { factory }

    private var _binding:FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val listCategoryAdapter = ListCategoryAdapter()
            requireActivity().window.statusBarColor = Color.parseColor(getString(R.string.young_red))

            listCategoryAdapter.onClick = {
                selectedData,direction ->
                val mBundle = Bundle()
                mBundle.putString(ContentCategoryFragment.CONTENT_CATEGORY_TAG, selectedData.key)
                direction.findNavController().navigate(R.id.action_navigation_category_to_contentCategoryFragment, mBundle)
            }

            listCategoryViewModel.listCategory.observe(viewLifecycleOwner) { foodCategory ->
                if(foodCategory != null){
                    when(foodCategory){
                        is Resource.Success -> {
                            listCategoryAdapter.setData(foodCategory.data)
                        }
                    }
                }
            }

            with(binding.rvListCategory){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
                adapter = listCategoryAdapter
            }

            Glide.with(this)
                    .asGif()
                    .load(R.drawable.chef_cooking)
                    .into(binding.coverCategory)
        }
    }
}