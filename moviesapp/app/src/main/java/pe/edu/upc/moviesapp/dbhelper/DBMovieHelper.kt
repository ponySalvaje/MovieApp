package pe.edu.upc.moviesapp.dbhelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import pe.edu.upc.moviesapp.models.MovieDTO

class DBMovieHelper (
    context: Context?
) : SQLiteOpenHelper (
    context,
    DATABASE_NAME, null,
    DATABASE_VER
) {
    companion object {
        private const val DATABASE_NAME = "myApp.db"
        private const val DATABASE_VER = 1

        // Table definition
        private const val TABLE_NAME = "Movie"
        private const val COL_ID = "Id"
        private const val COL_TITLE = "Title"
        private const val COL_IMAGE_URL = "ImageUrl"
        private const val COL_RELEASE_DATE = "ReleaseDate"
        private const val COL_GENRE = "Genre"
        private const val COL_RUNTIME = "Runtime"
        private const val COL_RATING = "Rating"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            ("CREATE TABLE $TABLE_NAME " +
                    "($COL_ID INTEGER IDENTITY PRIMARY KEY," +
                    "$COL_TITLE TEXT," +
                    "$COL_IMAGE_URL TEXT," +
                    "$COL_RELEASE_DATE TEXT," +
                    "$COL_GENRE TEXT," +
                    "$COL_RUNTIME TEXT," +
                    "$COL_RATING INTEGER)")

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    //INSERT AND SELECT
    val allMovies: List<MovieDTO>
        get() {
            val lstMovies = ArrayList<MovieDTO>()
            val findAllQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(findAllQuery, null)

            if (cursor.moveToFirst()) {
                do {
                    val movie = MovieDTO()
                    movie.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    movie.title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                    movie.imageUrl = cursor.getString(cursor.getColumnIndex(COL_IMAGE_URL))
                    movie.releaseDate = cursor.getString(cursor.getColumnIndex(COL_RELEASE_DATE))
                    movie.genre = cursor.getString(cursor.getColumnIndex(COL_GENRE))
                    movie.runtime = cursor.getString(cursor.getColumnIndex(COL_RUNTIME))
                    movie.rating = cursor.getInt(cursor.getColumnIndex(COL_RATING))
                } while (cursor.moveToNext())
            }

            return lstMovies
        }

    fun addMovie(movie: MovieDTO) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_TITLE, movie.title)
        values.put(COL_IMAGE_URL, movie.imageUrl)
        values.put(COL_RELEASE_DATE, movie.releaseDate)
        values.put(COL_GENRE, movie.genre)
        values.put(COL_RUNTIME, movie.runtime)
        values.put(COL_RATING, movie.rating)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}