package nz.ac.uclive.scl113.supysorter.model

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nz.ac.uclive.scl113.supysorter.model.relations.MealIngredientCrossReference

@Database(
    entities = [
        Meal::class,
        Ingredient::class,
        MealIngredientCrossReference::class
    ],
    version = 2
)
abstract class MealDatabase: RoomDatabase() {

    abstract val dao: MealDao

    companion object {
        private const val DATABASE_NAME = "meal_db"

        /**
         * Builds the database given an application context
         */
        fun build(application: Application, databaseAsset: String? = null): MealDatabase {
            val builder: Builder<MealDatabase> = Room.databaseBuilder(
                application,
                MealDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration()

            if (databaseAsset != null) {
                builder.createFromAsset(databaseAsset)
            }

            return builder.build()
        }
    }
}