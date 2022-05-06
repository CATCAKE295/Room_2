package edu.uca.ni.movie.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.uca.ni.movie.model.Idioma

@Dao
interface IdiomaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addIdioma(idioma: Idioma)

    @Query("SELECT * FROM tbl_idioma")
    fun  readAllData(): LiveData<List<Idioma>>

    @Update
    fun updateIdioma(idioma: Idioma)

    @Delete
    fun deleteIdioma(idioma: Idioma)

    @Query("DELETE FROM tbl_idioma")
    fun deleteAllIdioma()

}