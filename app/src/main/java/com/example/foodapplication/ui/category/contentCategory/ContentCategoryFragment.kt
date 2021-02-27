package com.example.foodapplication.ui.category.contentCategory

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapplication.R
import com.example.foodapplication.core.data.Resource
import com.example.foodapplication.databinding.FragmentListItemBinding
import com.example.foodapplication.ui.ViewModelFactory
import com.example.foodapplication.ui.detail.food.DetailFoodActivity

class ContentCategoryFragment: Fragment() {

    private var _binding:FragmentListItemBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbars:Toolbar

    companion object{
        const val CONTENT_CATEGORY_TAG = "content_tag"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentListItemBinding.inflate(inflater, container, false)
        toolbars = _binding!!.toolbarBack
        Toast.makeText(activity, arguments?.getString(CONTENT_CATEGORY_TAG).toString(),Toast.LENGTH_SHORT).show()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            requireActivity().window.statusBarColor = Color.parseColor(getString(R.string.orange))
            
            val getTag = arguments?.getString(CONTENT_CATEGORY_TAG)
            Log.e("error_contentCateg",getTag.toString())

            val factory = ViewModelFactory.getInstance(requireActivity())
            val contentViewModel = ViewModelProvider(this,factory)[ContentCategoryViewModel::class.java]

            toolbars.title = getTag?.replace("-"," ")

            val contentAdapter = ContentCategoryAdapter()
            contentAdapter.onItemClickContent = {
                selectedData ->
                val intent = Intent(context, DetailFoodActivity::class.java)
                intent.putExtra(DetailFoodActivity.EXTRA_TITLE_COOKING, selectedData.title)
                intent.putExtra(DetailFoodActivity.EXTRA_ID_COOKING, selectedData.cookingID)
                startActivity(intent)
            }

            contentViewModel.setSelectedCategory(getTag)
            contentViewModel.contentCategory.observe(viewLifecycleOwner) { contentData ->
                if(contentData != null){
                    when(contentData){
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            contentAdapter.setData(contentData.data)
                            Log.e("error_contentCateg", contentData.data?.size.toString())
                        }
                        is Resource.Error -> {
                            Toast.makeText(activity, contentData.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            with(binding.rvListItem){
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(true)
                adapter = contentAdapter
            }

            toolbars.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        Log.e("error_content",(activity == null).toString()  )
    }
}