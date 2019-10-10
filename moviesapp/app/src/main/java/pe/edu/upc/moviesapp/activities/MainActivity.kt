package pe.edu.upc.moviesapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pe.edu.upc.moviesapp.R
import pe.edu.upc.moviesapp.dbhelper.DBMovieHelper
import pe.edu.upc.moviesapp.models.MovieDTO

class MainActivity : AppCompatActivity() {

    private lateinit var addNewMovieButton: ImageView

    private lateinit var db: DBMovieHelper
    private var lstMovies: List<MovieDTO> = ArrayList()

    private lateinit var recyclerView: RecyclerView

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var movieAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNewMovieButton = findViewById(R.id.new_movie_icon)

        pressButton()
        db = DBMovieHelper(this)

        lstMovies = db.allMovies

        recyclerView = findViewById(R.id.movies_recycler_view)

        linearLayoutManager = LinearLayoutManager(this)

        movieAdapter = MovieAdapter(lstMovies)

        recyclerView.layoutManager = linearLayoutManager

        recyclerView.adapter = movieAdapter

    }

    private fun pressButton() {
        addNewMovieButton.setOnClickListener {
            val intent = Intent(this@MainActivity, MoviesActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
