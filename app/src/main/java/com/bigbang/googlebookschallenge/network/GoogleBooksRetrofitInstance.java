package com.bigbang.googlebookschallenge.network;

import com.bigbang.googlebookschallenge.model.BookResultSet;
import com.bigbang.googlebookschallenge.model.Item;
import com.bigbang.googlebookschallenge.util.Constants;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleBooksRetrofitInstance {

    private GoogleBooksService googleBooksService;
    private OkHttpClient client;

    public GoogleBooksRetrofitInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        googleBooksService = createGoogleBooksService(getRetrofitInstance());
    }

    private Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private GoogleBooksService createGoogleBooksService(Retrofit retrofitInstance) {
        return retrofitInstance.create(GoogleBooksService.class);
    }

    public Observable<BookResultSet> getGoogleBooksRx(String search_terms, String api_key) {
        return googleBooksService.getGoogleBooksRx(search_terms, api_key);
    }
}
