package api.kudago

import api.kudago.data.KudaGoShowingsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

class KudaGoManager(kudaGoClient: KudaGoClient) {
    val kudaGoClient = kudaGoClient
    fun printFilms() {
        kudaGoClient.getFilms().subscribe({ r -> for (film in r.results) println(film.movie.title) })
    }
}

class KudaGoClient(httpClient: HttpClient) {
    val client = httpClient.get("https://kudago.com/public-api/v1.3/").create(KudaGoApi::class.java)

    fun getFilms(): Observable<KudaGoShowingsResponse> {
        val since = Date().time / 1000
        val until = since + 3600 * 24
        return client.getTop("msk", "movie,place", 100, since, until)
    }
}

interface KudaGoApi {
    @GET("movie-showings/")
    fun getTop(
            @Query("location") location: String,
            @Query("expand") expand: String,
            @Query("page_size") pageSize: Int,
            @Query("actual_since") actualSince: Long,
            @Query("actual_until") actualUntil: Long)
            : Observable<KudaGoShowingsResponse>
}

