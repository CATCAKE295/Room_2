package edu.uca.ni.movie.repository

import androidx.lifecycle.LiveData
import edu.uca.ni.movie.dao.IdiomaDao
import edu.uca.ni.movie.model.Idioma

class IdiomaRepository(private val idiomaDao: IdiomaDao) {

    val readAllData: LiveData<List<Idioma>> = idiomaDao.readAllData()

    suspend fun addIdioma(idioma: Idioma){
        idiomaDao.addIdioma(idioma)
    }

    suspend fun updateIdioma(idioma: Idioma){
        idiomaDao.updateIdioma(idioma)

    }

    suspend fun deleteIdioma(idioma: Idioma){
        idiomaDao.deleteIdioma(idioma)
    }

    suspend fun deleteAllIdioma(){
        idiomaDao.deleteAllIdioma()
    }


}