package edu.uca.ni.movie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.uca.ni.movie.dao.ClasificacionDao
import edu.uca.ni.movie.dao.IdiomaDao
import edu.uca.ni.movie.model.Clasificacion
import edu.uca.ni.movie.model.Idioma


@Database(entities = [Clasificacion::class, Idioma::class],version = 2,exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun clasificacionDao(): ClasificacionDao
    abstract fun idiomaDao(): IdiomaDao

    companion object{

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDataBase(context: Context): MovieDatabase{

            val tempInstance = INSTANCE
            if (tempInstance != null){

                return tempInstance

            }

            synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,MovieDatabase::class.java,"Movie_database"
                ).build()
                INSTANCE = instance
                return instance
            }


        }

    }

}