package com.bigbang.googlebookschallenge.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bigbang.googlebookschallenge.model.Item;
import com.bigbang.googlebookschallenge.network.GoogleBooksRetrofitInstance;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GoogleBooksViewModel extends AndroidViewModel {

    private GoogleBooksRetrofitInstance googleBooksRetrofitInstance;

    public GoogleBooksViewModel(@NonNull Application application) {
        super(application);

        googleBooksRetrofitInstance = new GoogleBooksRetrofitInstance();
    }

    public Observable<List<Item>> getGoogleBooksRx(String search_terms, String api_key) {
        return  googleBooksRetrofitInstance
                .getGoogleBooksRx(search_terms, api_key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
