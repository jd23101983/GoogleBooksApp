package com.bigbang.googlebookschallenge.network;

import com.bigbang.googlebookschallenge.model.BookResultSet;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.bigbang.googlebookschallenge.util.Constants.*;

public interface GoogleBooksService {

    /*
    "https://www.googleapis.com/books/v1/volumes?q={search term}&key={YOUR_API_KEY}”
    */

    @GET(URL_POSTFIX)
    Observable<BookResultSet> getGoogleBooksRx(@Query("q") String search_terms, @Query("key") String api_key);
}
