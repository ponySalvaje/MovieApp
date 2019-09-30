package pe.edu.upc.moviesapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import pe.edu.upc.moviesapp.R
import pe.edu.upc.moviesapp.models.MovieDTO

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var movieItemImage: ImageView
    private lateinit var ratingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieItemImage = findViewById(R.id.movie_image)
        ratingTextView = findViewById(R.id.rating_text_view)

        var myMovie = intent.getSerializableExtra("movie") as MovieDTO

        Picasso.get().load(myMovie.imageUrl).into(movieItemImage)
        ratingTextView.text = myMovie.rating.toString()

    }


}
