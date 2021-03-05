package com.example.foodapplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.core.data.Resource
import com.example.core.domain.model.Cooking
import com.example.core.domain.repository.IFoodRepository
import com.example.core.domain.usecase.FoodInteractor
import com.example.core.domain.usecase.FoodUseCase
import com.example.foodapplication.ui.home.HomeViewModel
import com.example.foodapplication.util.DataDummy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var foodUseCase: FoodUseCase

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var foodRepository:IFoodRepository

    @Before
    fun setup(){
        foodUseCase = FoodInteractor(foodRepository)
        homeViewModel = HomeViewModel(foodUseCase)
    }

    @Test
    fun getFood(){
        foodUseCase.getAllCooking()
        val dataRecipes = DataDummy.dummyAllCooking()

        `when`(foodRepository.getAllCooking()).thenReturn(dataRecipes)
        `when`(foodUseCase.getAllCooking()).thenReturn(dataRecipes)
        verify(foodRepository, atLeast(2)).getAllCooking()

        Assert.assertEquals(foodUseCase.getAllCooking(), dataRecipes)
        Assert.assertNotNull(foodUseCase.getAllCooking())

        println(foodUseCase.getAllCooking())
        println(dataRecipes)

    }
}