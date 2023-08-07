package com.example.indi32m6

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.indi32m6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ComandaFragment.CarroButtonClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cuentaViewModel: CuentaViewModel
    internal lateinit var data: List<Cuenta>
    private lateinit var totalTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Asignar el TextView del layout al miembro totalTextView
        totalTextView = binding.textTotal

        //instanciar la bd
        val database = CuentaRoomDatabase.getDatabase(applicationContext)
        val cuentaDao = database.cuentaDao()
        val cuentaRepository = CuentaRepository(cuentaDao)
        val cuentaViewModelFactory = CuentaViewModelFactory(cuentaRepository)
        cuentaViewModel =
            ViewModelProvider(this, cuentaViewModelFactory).get(CuentaViewModel::class.java)

        cuentaViewModel.allCuenta.observe(this, Observer { cuentaList ->
            this.data = cuentaList // Aquí cargamos la variable global data con lta lista de allDatos
            var total = 0.0
            // Puedes iterar sobre el pedido y acceder a cada elemento individualmente
            for (data in cuentaList) {
                val ttotal = data.cantidad * data.precio
                total += ttotal
            }
            val totalImprimir = total.toString()
            // Asignar el valor del total al TextView
            totalTextView.text = totalImprimir
        })
        //Cargamos el fragmento para agregar apenas cargue la app
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<ComandaFragment>(R.id.fragmentContainer)
        }
    }


    // Función para el botón carro
    override fun onCarroButtonClick() {
        try {
            // Verificar si la propiedad ids está inicializada
            if (!data.isEmpty()) {
                // val total = this.data.sumOf { it.precio }
                // Acceder a la propiedad ids
                val pedidoFragment = PedidoFragment()
                val bundle = Bundle()
                //   bundle.putString("total", total.toString())
                pedidoFragment.arguments = bundle
                supportFragmentManager.commit {
                    replace(R.id.fragmentContainer, pedidoFragment)
                    addToBackStack(null)
                }
            } else {
                // La propiedad ids no ha sido inicializada
                // Realiza la lógica correspondiente en este caso
                Toast.makeText(this, "la lista está vacía", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            // Manejar la excepción en caso de que ocurra algún error
            Toast.makeText(this, "Error al acceder a los datos", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }


    // Función para el botón insertar
    override fun insertar() {
        val nombreEditText = findViewById<EditText>(R.id.nombre)
        val cantidadEditText = findViewById<EditText>(R.id.cantidad)
        val precioEditText = findViewById<EditText>(R.id.precio)

        val nombre = nombreEditText.text.toString()
        val cantidad = cantidadEditText.text.toString().toIntOrNull() ?: 0
        val precio = precioEditText.text.toString().toDoubleOrNull() ?: 0.0
        val datos = Cuenta(null, nombre, precio, cantidad)
        cuentaViewModel.insert(datos)
        Toast.makeText(this, "agregado correctamente", Toast.LENGTH_SHORT).show()
    }

    fun eliminar()
    {
        cuentaViewModel.deleteAll()
        data = emptyList()
        totalTextView.text = "0"
        val listaFragment = PedidoFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, listaFragment)
            .commit()
    }
}
