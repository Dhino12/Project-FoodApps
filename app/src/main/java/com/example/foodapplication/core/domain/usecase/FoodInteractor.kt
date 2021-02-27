package com.example.foodapplication.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.foodapplication.core.data.Resource
import com.example.foodapplication.core.domain.model.Article
import com.example.foodapplication.core.domain.model.Category
import com.example.foodapplication.core.domain.model.Cooking
import com.example.foodapplication.core.domain.model.Search
import com.example.foodapplication.core.domain.repository.IFoodRepository
import io.reactivex.Flowable

class FoodInteractor(private val foodRepository: IFoodRepository):FoodUseCase {

    override fun getAllCooking(): Flowable<Resource<List<Cooking>>> = foodRepository.getAllCooking()

    override fun getDetailCooking(key:List<String>):Flowable<Resource<List<Cooking>>> = foodRepository.getDetailCooking(key)

    override fun getAllArticle(): Flowable<Resource<List<Article>>> = foodRepository.getAllArticle()

    override fun getArticleDetail(key: List<String>): Flowable<Resource<Article>> = foodRepository.getDetailArticle(key)

    override fun getListCategory(): Flowable<Resource<List<Category>>> = foodRepository.getListCategory()

    override fun getAllContentCategory(tag: String): Flowable<Resource<List<Cooking>>> = foodRepository.getAllContentCategory(tag)

    override fun getFavoriteFood(): Flowable<List<Cooking>> = foodRepository.getFavoriteFood()

    override fun setFavoriteFood(food: Cooking, state: Boolean) = foodRepository.setFavoriteFood(food, state)

    override fun getSearchFood(query: String?): Flowable<Resource<List<Search>>> = foodRepository.getSearchFood(query)

}