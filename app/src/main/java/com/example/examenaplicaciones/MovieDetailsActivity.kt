package com.example.examenaplicaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.examenaplicaciones.databinding.ActivityMovieDetailsBinding
import com.google.gson.Gson

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val shared = getSharedPreferences("peliculas", MODE_PRIVATE)
        val gson = Gson()
        val editor = shared.edit()
        val json = shared.getString("pelicula", "")
        val json2 = shared.getString("pelicula2", "")

        val p1 = gson.fromJson(json, Pelicula::class.java)
        var director:String=""
        var anio:String=""

        //crear un array list de peliculas
        val peliculas = ArrayList<Pelicula>()
        //crear un arraylist e igualarlo a lo que recibimos de shared preferences


        binding.regresar.setOnClickListener {
            val intent = Intent(this, MovieTitleActivity::class.java)
            startActivity(intent)
        }
        //si la casilla favorito se marcar activar el booleano de pelicula
        binding.fav.setOnClickListener {
            p1.isFav=true
        }

        binding.continuar2.setOnClickListener {
            director = binding.director.text.toString()
            anio = binding.anio.text.toString()


            if (director.isEmpty() || anio.isEmpty()){
                Toast.makeText(this, "Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MovieDisplayActivity::class.java)
                p1.director=director
                p1.año=anio
                peliculas.add(p1)
                val json = gson.toJson(peliculas)
                editor.putString("pelicula", json)
                editor.apply()
                    startActivity(intent)
                    }
            }
        }
    }
