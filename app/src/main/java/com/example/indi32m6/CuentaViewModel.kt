package com.example.indi32m6

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CuentaViewModel(private val repository: CuentaRepository) : ViewModel() {

    // devuelve todos los datos de la bd
    val allCuenta: LiveData<List<Cuenta>> = repository.allCuenta

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insert(dato: Cuenta) = viewModelScope.launch {
        repository.insert(dato)
    }
    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

class CuentaViewModelFactory(private val repository: CuentaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CuentaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CuentaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
