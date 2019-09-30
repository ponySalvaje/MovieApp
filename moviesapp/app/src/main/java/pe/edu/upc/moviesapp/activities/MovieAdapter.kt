package pe.edu.upc.moviesapp.activities

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pe.edu.upc.moviesapp.R
import pe.edu.upc.moviesapp.models.MovieDTO

class MovieAdapter (var list: List<MovieDTO>): RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item_row, parent, false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.titleTextView.text = list[position].title

        Picasso.get().load(list[position].imageUrl).into(holder.posterImageView)

        holder.posterImageView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("movie", list[position])
            context.startActivity(intent)
        }

    }

}