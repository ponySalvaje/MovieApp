package pe.edu.upc.moviesapp.activities

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_item_row.view.*
import pe.edu.upc.moviesapp.R

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var titleTextView: TextView = itemView.findViewById(R.id.item_row_title)
    var posterImageView: ImageView = itemView.findViewById(R.id.item_row_image)

}