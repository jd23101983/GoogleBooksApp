package com.bigbang.googlebookschallenge.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.bigbang.googlebookschallenge.R;
import com.bigbang.googlebookschallenge.model.Item;
import com.bigbang.googlebookschallenge.util.Constants;
import com.bigbang.googlebookschallenge.util.DebugLogger;
import com.bigbang.googlebookschallenge.viewmodel.GoogleBooksViewModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    private GoogleBooksViewModel googleBooksViewModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable(); // RxJava

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleBooksViewModel = ViewModelProviders.of(this).get(GoogleBooksViewModel.class);

//        RxJava--------------------------------------------------------------------------->
        compositeDisposable.add(googleBooksViewModel.getGoogleBooksRx("James Bond", Constants.API_KEY).subscribe(googleBookResults -> {
            displayInformationRx(googleBookResults);
        }, throwable -> {
            DebugLogger.logError(throwable);

        }));
//        RxJava--------------------------------------------------------------------------->

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    //    RxJava
    private void displayInformationRx(List<Item> googleBookResults) {
        for (int i = 0; i < googleBookResults.size(); i++) {
            DebugLogger.logDebug("RxJava : " + googleBookResults.get(i).getSearchInfo());
        }
    }

}
