package com.example.core.util

import android.util.Log
import com.example.core.data.source.local.entity.*
import com.example.core.data.source.remote.response.*
import com.example.core.domain.model.Article
import com.example.core.domain.model.Category
import com.example.core.domain.model.Cooking
import com.example.core.domain.model.Search
import io.reactivex.Flowable

object DataMapper {

    // ==================== Response to Entity ====================
    fun mapResponseToEntityCooking(input:List<ResultsItemCooking>?):List<CookingEntity>{
        val cookingList = ArrayList<CookingEntity>()
        input?.map {
            val cooking = CookingEntity(
                    cookingID = it.key!!,
                    title = it.title,
                    thumb = it.thumb,
                    times = it.times,
                    portion = it.portion,
                    dificulty = it.dificulty
            )
            cookingList.add(cooking)
        }
        return cookingList
    }

    fun mapResponseToEntityArticle(input:List<ResultItemArticle>?):List<ArticleEntity>{
        val articleList = ArrayList<ArticleEntity>()
        input?.map {
            val cooking = ArticleEntity(
                    key = it.key!!,
                    title = it.title,
                    thumb = it.thumb,
                    tags = it.tags,
                    isFavorite = false
            )
            articleList.add(cooking)
        }
        return articleList
    }

    fun mapResponseToEntityCookingDetail(input:ResultsDetailCooking?):CookingDetailEntity{
        lateinit var cookDetail:CookingDetailEntity

        val i = input?.ingredient?.toMutableList()
        Log.e("errorMapEntity",input?.title.toString())
        if(input != null){
            cookDetail =  CookingDetailEntity(
                    servings = input.servings,
                    times = input.times,
                    ingredient = i!!.map { it.plus(",") }.toString(),
                    thumb = input.thumb,
                    author = input.author.toString(),
                    step = input.step.toString(),
                    title = input.title,
                    dificulty = input.dificulty,
                    desc = input.desc
            )
        }
        return cookDetail
    }

    fun mapResponseToEntityArticleDetail(input:ResultDetailArticle?):ArticleDetailEntity{
        lateinit var articleDetail:ArticleDetailEntity
        if(input != null){
            articleDetail = ArticleDetailEntity(
                    thumb = input.thumb,
                    datePublished = input.datePublished,
                    author = input.author,
                    description = input.description,
                    title = input.title
            )
        }
        return articleDetail
    }

    fun mapResponseToEntityListCategory(input:List<ResultsItemCategory>?):List<ListCategoryEntity>{
        val listCategory= ArrayList<ListCategoryEntity>()
        input?.map {
            val category = ListCategoryEntity(
                    category = it.category,
                    key = it.key!!
            )
            listCategory.add(category)
        }
        return listCategory
    }

    fun mapResponseContentToEntityCooking(input:List<ResultsItemCooking>?, tag:String):List<CookingEntity>{
        val cookingList = ArrayList<CookingEntity>()
        input?.map {
            val cooking = CookingEntity(
                    cookingID = it.key!!,
                    title = it.title,
                    thumb = it.thumb,
                    times = it.times,
                    portion = it.portion,
                    dificulty = it.dificulty,
                    tags = tag
            )
            cookingList.add(cooking)
        }
        return cookingList
    }

    fun mapResponseSearchToEntity(query:List<ResultsItemSearch>):List<Search>{
        val cookingList = ArrayList<Search>()
        query.map {
            val cooking = Search(
                    cookingID = it.key!!,
                    title = it.title,
                    thumb = it.thumb,
                    times = it.times,
                    portion = it.portion,
                    dificulty = it.dificulty
            )
            cookingList.add(cooking)
        }
        return cookingList
    }


    // ==================== Entity to Domain ====================
    fun mapEntitiesCookingToDomain(input:List<CookingEntity>):List<Cooking> =
            input.map {
                Cooking(
                        cookingID = it.cookingID,
                        title = it.title,
                        thumb = it.thumb,
                        times = it.times,
                        servings = it.portion,
                        dificulty = it.dificulty,
                        tags = it.tags
                )
            }

    fun mapEntitiesCookingDetailToDomain(input: List<CookingDetailEntity>): ArrayList<Cooking> {
        Log.e("errorMapToDomain","masuk MapEnDetToDo")
        val i = ArrayList<Cooking>()
        if(input.isNotEmpty()){
            input.map {
                val b = Cooking(
                        servings = it.servings,
                        times = it.times,
                        ingredient = it.ingredient,
                        thumb = it.thumb,
                        author = it.author,
                        step = it.step,
                        title = it.title,
                        dificulty = it.dificulty,
                        desc = it.desc,
                        isFavorite = it.isFavorite
                )
                i.add(b)
            }
        }
        return i
    }


    fun mapEntitiesCookingDetailTESTToDomain(input: Flowable<CookingDetailEntity>):Flowable<Cooking> {
        Log.e("errorMapToDomain","masuk MapEnDetToDo")
        return input.map {
            Log.e("error MAP",it.title.toString())
            Cooking(
                    servings = it.servings,
                    times = it.times,
                    ingredient = it.ingredient,
                    thumb = it.thumb,
                    author = it.author,
                    step = it.step,
                    title = it.title,
                    dificulty = it.dificulty,
                    desc = it.desc,
                    isFavorite = it.isFavorite
            ) }
    }


    fun mapEntitiesCookingFavoriteDetailToDomain(input: List<CookingDetailEntity>):List<Cooking> =
            input.map {
                Cooking(
                        servings = it.servings,
                        times = it.times,
                        ingredient = it.ingredient,
                        thumb = it.thumb,
                        author = it.author,
                        step = it.step,
                        title = it.title,
                        dificulty = it.dificulty,
                        desc = it.desc,
                        isFavorite = it.isFavorite
                )
            }

    fun mapEntitiesArticleToDomain(input: List<ArticleEntity>):List<Article> =
            input.map {
                Article(
                        key = it.key,
                        title = it.title,
                        thumb = it.thumb,
                        tags = it.tags,
                        isFavorite = it.isFavorite
                )
            }

    fun mapEntityArticleDetailToDomain(input: ArticleDetailEntity?):Article =
            Article(
                    thumb = input?.thumb,
                    datePublished = input?.datePublished,
                    author = input?.author,
                    description = input?.description,
                    title = input?.title
            )

    fun mapEntityListCategoryToDomain(input: List<ListCategoryEntity>) : List<Category> =
            input.map {
                Category(
                        category = it.category,
                        key = it.key
                )
            }

    fun mapEntitiesContentCategoryToDomain(input:List<CookingEntity>):List<Cooking> =
            input.map {
                Cooking(
                        cookingID = it.cookingID,
                        title = it.title,
                        thumb = it.thumb,
                        times = it.times,
                        servings = it.portion,
                        dificulty = it.dificulty,
                        tags = it.tags
                )
            }

    // ==================== Domain To Entity ====================
    fun mapDomainToEntityCooking(input:Cooking) = CookingDetailEntity(
            servings = input.servings,
            times = input.times,
            ingredient = input.ingredient,
            thumb = input.thumb,
            author = input.author,
            step = input.step,
            title = input.title!!,
            dificulty = input.dificulty,
            desc = input.desc,
            isFavorite = input.isFavorite!!
    )
}