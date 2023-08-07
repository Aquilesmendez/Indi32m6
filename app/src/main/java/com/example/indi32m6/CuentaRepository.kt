package com.example.indi32m6

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class CuentaRepository(private val cuentaDao: CuentaDao) {

    val allCuenta: LiveData<List<Cuenta>> = cuentaDao.getAllCuenta()

    val precioTotal: LiveData<List<Cuenta>> = cuentaDao.getAllCuenta()

    @WorkerThread
    suspend fun insert(cuenta: Cuenta) {
        cuentaDao.insert(cuenta)
    }
    @WorkerThread
    suspend fun deleteAll() {
        cuentaDao.deleteAll()
    }
}
