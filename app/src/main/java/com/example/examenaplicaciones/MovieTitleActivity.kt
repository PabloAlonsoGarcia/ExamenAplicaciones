package com.example.examenaplicaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examenaplicaciones.databinding.ActivityMainBinding
import com.google.gson.Gson

class MovieTitleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shared = getSharedPreferences("peliculas", MODE_PRIVATE)
        val gson = Gson()
        val editor = shared.edit()



        var titulo:String="";
        var duracion:String="";

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //arraylist de peliculas
        val peliculas = ArrayList<Pelicula>()

        val p1 = Pelicula()



        binding.continuar2.setOnClickListener {

            titulo = binding.titulo.text.toString()
            duracion = binding.duracion.text.toString()

            if (titulo.isEmpty() || duracion.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show()
            } else {
                p1.titulo=titulo
                p1.duracion=duracion
                val intent = Intent(this, MovieDetailsActivity::class.java)

                val json = gson.toJson(p1)
                editor.putString("pelicula", json)
                editor.apply()
                startActivity(intent)
            }
        }


    }

}