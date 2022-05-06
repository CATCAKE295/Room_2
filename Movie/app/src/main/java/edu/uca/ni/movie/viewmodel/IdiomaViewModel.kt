package edu.uca.ni.movie.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import edu.uca.ni.movie.database.MovieDatabase

import edu.uca.ni.movie.model.Idioma
import edu.uca.ni.movie.repository.ClasificacionRepository
import edu.uca.ni.movie.repository.IdiomaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IdiomaViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Idioma>>
    private val repository: IdiomaRepository

    init {
        val idiomaDao = MovieDatabase.getDataBase(application).idiomaDao()
        repository = IdiomaRepository(idiomaDao)
        readAllData = repository.readAllData
    }

    fun addIdioma(idioma: Idioma){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIdioma(idioma)
        }
    }

    fun updateIdioma(idioma: Idioma){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateIdioma(idioma)
        }
    }

    fun deleteIdioma(idioma: Idioma){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteIdioma(idioma)
        }
    }

    fun deleteAllIdioma(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllIdioma()
        }
    }

}