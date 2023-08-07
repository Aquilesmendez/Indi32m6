package com.example.indi32m6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CuentaAdapter(private var cuenta: List<Cuenta>) :
    RecyclerView.Adapter<CuentaAdapter.CuentaViewHolder>() {

    // Crea una nueva vista para cada elemento del RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaViewHolder {
        // Infla el layout del elemento de la cuenta desde el archivo de diseño 'article_item.xml'
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return CuentaViewHolder(view)
    }

    // Vincula los datos del elemento en la posición dada con la vista
    override fun onBindViewHolder(holder: CuentaViewHolder, position: Int) {
        // Obtiene los datos actuales en la posición dada
        val currentCuenta = cuenta[position]
        // Vincula los datos con la vista del titular de los datos
        holder.bind(currentCuenta)
    }

    // Devuelve la cantidad total de elementos en la lista de datos
    override fun getItemCount(): Int = cuenta.size

    // Define la clase ViewHolder que representa cada elemento del Pedido
    class CuentaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Referencias a los elementos de la vista
        private val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        private val precioTextView: TextView = view.findViewById(R.id.precioTextView)
        private val cantidadTextView: TextView = view.findViewById(R.id.cantidadTextView)

        // Vincula la cuenta con los elementos de la vista
        fun bind(cuenta: Cuenta) {
            // Establece los valores de los campos de texto con los datos correspondientes
            nombreTextView.text = cuenta.nombre
            precioTextView.text = cuenta.precio.toString()
            cantidadTextView.text = cuenta.cantidad.toString()
        }
    }
}