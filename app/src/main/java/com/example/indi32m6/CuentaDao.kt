package com.example.indi32m6

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CuentaDao {

    @Query("SELECT * FROM TABLE_CUENTA ORDER BY id ASC")
    fun getAllCuenta(): LiveData<List<Cuenta>>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cuenta: Cuenta)
    @Query("SELECT SUM(precio) FROM TABLE_CUENTA")
    fun getPrecio(): LiveData<Double>

    @Query("DELETE FROM TABLE_CUENTA")
    suspend fun deleteAll()
}