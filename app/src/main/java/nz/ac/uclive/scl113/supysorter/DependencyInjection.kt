package nz.ac.uclive.scl113.supysorter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nz.ac.uclive.scl113.supysorter.model.MealDatabase
import nz.ac.uclive.scl113.supysorter.model.MealRepository
import nz.ac.uclive.scl113.supysorter.model.MealRepositoryImpl
import nz.ac.uclive.scl113.supysorter.service.MealService
import javax.inject.Singleton
import android.app.Application

@Module
@InstallIn(SingletonComponent::class)
object DependencyInjection {

    @Provides
    @Singleton
    fun provideMealDatabase(application: Application): MealDatabase {
        return MealDatabase.build(application)
    }

    @Provides
    @Singleton
    fun provideMealRepository(mealDatabase: MealDatabase): MealRepository {
        return MealRepositoryImpl(mealDatabase.dao)
    }

    @Provides
    @Singleton
    fun provideMealService(mealRepository: MealRepository): MealService {
        return MealService(mealRepository)
    }
}