package com.bigbang.googlebookschallenge.network;

import com.bigbang.googlebookschallenge.model.BookResultSet;
import com.bigbang.googlebookschallenge.model.Item;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.bigbang.googlebookschallenge.util.Constants.*;

public interface GoogleBooksService {

/*
    @GET("/3/search/movie?language=en-US&page=1&include_adult=false")
    public Call<MovieResultSet> getMovieData(@Query("api_key") String api_key, @Query("query") String search_terms);

    @GET(GET_URL_POSTFIX)
    Call<List<GitResult>> getRepositories(@Path(USER_NAME)String userName);

    "https://www.googleapis.com/books/v1/volumes?q={search term}&key={YOUR_API_KEY}”

    @GET(URL_POSTFIX)
    Observable<List<GitResult>> getRepositoriesRx(@Path(USER_NAME)String userName);
*/

    @GET(URL_POSTFIX)
    Observable<BookResultSet> getGoogleBooksRx(@Query("q") String search_terms, @Query("key") String api_key);
}
