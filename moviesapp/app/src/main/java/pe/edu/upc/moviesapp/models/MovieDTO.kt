package pe.edu.upc.moviesapp.models

import java.io.Serializable

class MovieDTO : Serializable {
    var id: Int?= 0
    var title: String? = null
    var imageUrl: String? = null
    var releaseDate: String? = null
    var genre: String? = null
    var runtime: String? = null
    var rating: Int?= 0

    constructor()

    constructor(title: String, imageUrl: String,
                releaseDate: String, genre: String,
                runtime: String, rating: Int) {
        this.title = title
        this.imageUrl = imageUrl
        this.releaseDate = releaseDate
        this.genre = genre
        this.runtime = runtime
        this.rating = rating
    }

}