package com.example.examenaplicaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examenaplicaciones.databinding.ActivityMovieDisplayBinding
import com.google.gson.Gson

class MovieDisplayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDisplayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shared = getSharedPreferences("peliculas", MODE_PRIVATE)
        val gson = Gson()
        val editor = shared.edit()
        val json = shared.getString("pelicula", "")

        val peliculas = gson.fromJson(json, Array<Pelicula>::class.java).toCollection(ArrayList())

        binding.listaPelis.text=peliculas.toString()

        binding.regresar2.setOnClickListener {
            val intent = Intent(this, MovieDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.inicio.setOnClickListener {
            val intent = Intent(this, MovieTitleActivity::class.java)
            val json = gson.toJson(peliculas)
            editor.putString("pelicula2", json)
            editor.apply()
            startActivity(intent)
        }
















    }
}