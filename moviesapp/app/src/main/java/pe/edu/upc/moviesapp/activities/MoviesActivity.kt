package pe.edu.upc.moviesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import pe.edu.upc.moviesapp.R
import pe.edu.upc.moviesapp.dbhelper.DBMovieHelper
import pe.edu.upc.moviesapp.models.Movie
import pe.edu.upc.moviesapp.models.MovieDTO
import pe.edu.upc.moviesapp.repository.GetMovie
import pe.edu.upc.moviesapp.repository.RetrofitClientInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    private lateinit var getMovieButton: Button

    private lateinit var movieTitle: EditText
    private lateinit var imageMovie: ImageView

    private lateinit var db: DBMovieHelper

    private lateinit var rateButton: Button
    private lateinit var ratingEditText: EditText

    private var title: String = ""
    private var releaseDate: String = ""
    private var genre: String = ""
    private var imageUrl: String = ""
    private var runtime: String = ""

    private val movieRepo =
        RetrofitClientInstance().getRetrofitInstance().create(GetMovie::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        getMovieButton = findViewById(R.id.get_movie_button)
        movieTitle = findViewById(R.id.movie_title_edit_text)
        imageMovie = findViewById(R.id.movie_image)
        pressGetMovieButton()

        db = DBMovieHelper(this)

        rateButton = findViewById(R.id.rate_button)
        pressRateButton()
    }

    private fun pressGetMovieButton() {
        getMovieButton.setOnClickListener {
            movieRepo.getMovie(apiKey = "bb7fd48c", t = movieTitle.text.toString()).enqueue(object : Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Toast.makeText(this@MoviesActivity, "Can not get data", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    Toast.makeText(this@MoviesActivity, "Data", Toast.LENGTH_SHORT).show()
                    val photoUrl = response.body()?.poster
                    Picasso.get().load(photoUrl).into(imageMovie)

                    title = response.body()?.title!!
                    releaseDate = response.body()?.released!!
                    genre = response.body()?.genre!!
                    imageUrl = response.body()?.poster!!
                    runtime = response.body()?.runtime!!
                }
            })
        }
    }

    private fun pressRateButton() {
        rateButton.setOnClickListener {
            ratingEditText = findViewById(R.id.rate_edit_text)

            val rating = Integer.parseInt(ratingEditText.text.toString())

            val movie = MovieDTO(
                title,
                imageUrl,
                releaseDate,
                genre,
                runtime,
                rating
            )
            db.addMovie(movie)

            val intent = Intent(this@MoviesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
