package com.example.indi32m6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class ComandaFragment : Fragment() {

    // Interfaz para manejar los eventos de los botones del fragmento
    interface CarroButtonClickListener {
        fun onCarroButtonClick()
        fun insertar()
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el diseño del fragmento desde el archivo de diseño 'fragment_comanda.xml'
        val view = inflater.inflate(R.layout.fragment_comanda, container, false)

        // Configura la acción del botón "Carro"
        view.findViewById<Button>(R.id.btnCarro).setOnClickListener {
            // Verifica si la actividad contenedora implementa la interfaz CarroButtonClickListener
            val listener = activity as? CarroButtonClickListener

            // Llama al método onCarroButtonClick de la interfaz si está implementado en la actividad
            listener?.onCarroButtonClick()
        }

        // Configura la acción del botón "Agregar"
        view.findViewById<Button>(R.id.btnAgregar).setOnClickListener {
            // Verifica si la actividad contenedora implementa la interfaz CarroButtonClickListener
            val listener = activity as? CarroButtonClickListener

            // Llama al método insertar de la interfaz si está implementado en la actividad
            listener?.insertar()
        }
        return view
    }
}
