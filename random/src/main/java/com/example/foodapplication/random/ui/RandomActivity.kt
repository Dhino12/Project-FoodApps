package com.example.foodapplication.random.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.example.core.data.Resource
import com.example.core.di.DaggerCoreComponent
import com.example.core.domain.model.Cooking
import com.example.foodapplication.random.databinding.ActivityRandomBinding
import com.example.foodapplication.random.di.DaggerRandomComponent
import com.example.foodapplication.random.util.DataToViewRandom
import com.example.foodapplication.ui.ViewModelFactory
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks
import com.github.ksoichiro.android.observablescrollview.ScrollState
import com.github.ksoichiro.android.observablescrollview.ScrollUtils
import javax.inject.Inject

@Suppress("DEPRECATION")
class RandomActivity : AppCompatActivity(), ObservableScrollViewCallbacks {

    @Inject
    lateinit var factory:ViewModelFactory

    private val viewModelRandom: RandomViewModel by viewModels { factory }

    private lateinit var binding: ActivityRandomBinding

    private lateinit var dataToViewRandom: DataToViewRandom

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerRandomComponent.builder()
            .coreComponent(DaggerCoreComponent.factory().create(this))
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarOverlay)
        val toolbars = supportActionBar
        if (toolbars != null) supportActionBar?.setDisplayHomeAsUpEnabled(true)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        //make fully Android Transparent Status bar
        window.statusBarColor = Color.TRANSPARENT

        dataToViewRandom = DataToViewRandom(binding, this, viewModelRandom)

        val dataRandomCooking = intent.getParcelableArrayListExtra<Cooking>("keyRandom")
        val keyCollection = mutableListOf<String>()
        Log.e("error","Random ${dataRandomCooking?.isEmpty()}")
        Log.e("error","Random ${dataRandomCooking?.size}")

        if (dataRandomCooking != null && dataRandomCooking.isNotEmpty()) {
            if (dataRandomCooking.isNotEmpty()) {
                val dataRandom = dataRandomCooking.random()
                if (keyCollection.isNullOrEmpty()) {
                    keyCollection.add(dataRandom.title ?: "")
                    keyCollection.add(dataRandom.cookingID ?: "")
                } else {
                    keyCollection[0] = dataRandom.title ?: ""
                    keyCollection[1] = dataRandom.cookingID ?: ""
                }

                viewModelRandom.setRandomData(keyCollection)
                viewModelRandom.cookingData.observe(this) { response ->
                    if (response != null) {
                        when (response) {
                            is Resource.Loading -> {
                                dataToViewRandom.showLoading(true)
                            }
                            is Resource.Success -> {
                                dataToViewRandom.showLoading(false)
                                dataToViewRandom.implementToView(response.data?.get(0))
                            }
                            is Resource.Error -> {
                                Toast.makeText(this, "Error ${response.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                binding.scroll.setScrollViewCallbacks(this)

                ScrollUtils.addOnGlobalLayoutListener(binding.scroll) {
                    onScrollChanged(0, firstScroll = false, dragging = false)
                }
            }
        }
    }

    override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {
        dataToViewRandom.updateFlexibleView(scrollY)
    }

    override fun onDownMotionEvent() {

    }

    override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {

    }
}