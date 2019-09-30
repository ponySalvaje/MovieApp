package pe.edu.upc.moviesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import pe.edu.upc.moviesapp.R
import pe.edu.upc.moviesapp.dbhelper.DBMovieHelper

class MainActivity : AppCompatActivity() {

    private lateinit var addNewMovieButton: ImageView

    private lateinit var db: DBMovieHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNewMovieButton = findViewById(R.id.new_movie_icon)

        pressButton()

        db = DBMovieHelper(this)
        refreshData()
    }

    private fun pressButton() {
        addNewMovieButton.setOnClickListener {
            val intent = Intent(this@MainActivity, MoviesActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun refreshData() {

    }
}
