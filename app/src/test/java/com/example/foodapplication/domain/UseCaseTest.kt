package com.example.foodapplication.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.domain.repository.IFoodRepository
import com.example.core.domain.usecase.FoodInteractor
import com.example.core.domain.usecase.FoodUseCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UseCaseTest {

    private lateinit var foodUseCase: FoodUseCase

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    private lateinit var foodRepository: IFoodRepository

    @Before
    fun setup(){
        foodUseCase = FoodInteractor(foodRepository)
    }

    @Test
    fun `get data from repository`(){
        foodUseCase.getAllCooking()
        Mockito.verify(foodRepository).getAllCooking()
        Assert.assertNotNull(foodUseCase)
    }

}