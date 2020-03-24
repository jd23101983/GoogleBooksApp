package com.bigbang.googlebookschallenge.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bigbang.googlebookschallenge.R;
import com.bigbang.googlebookschallenge.adapter.GoogleBooksAdapter;
import com.bigbang.googlebookschallenge.model.Item;
import com.bigbang.googlebookschallenge.util.Constants;
import com.bigbang.googlebookschallenge.util.DebugLogger;
import com.bigbang.googlebookschallenge.viewmodel.GoogleBooksViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.search_edittext)
    EditText searchEditText;

    @BindView(R.id.book_results_recycler_view)
    RecyclerView bookResultsRecyclerView;

    private GoogleBooksViewModel googleBooksViewModel;

    // RxJava
    private CompositeDisposable compositeDisposable = new CompositeDisposable(); // RxJava

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        googleBooksViewModel = ViewModelProviders.of(this).get(GoogleBooksViewModel.class);

        // RxJava
        compositeDisposable.add(googleBooksViewModel.getGoogleBooksRx("James Bond", Constants.API_KEY).subscribe(googleBookResults -> {
            displayInformationRx(googleBookResults.getItems());
        }, throwable -> {
            DebugLogger.logError(throwable);
        }));

    }

    @OnClick(R.id.search_button)
    public void performSearch(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    // RxJava
    private void displayInformationRx(List<Item> googleBookResults) {

        updateRecyclerView(googleBookResults);

        for (int i = 0; i < googleBookResults.size(); i++) {
            DebugLogger.logDebug("RxJava : " + googleBookResults.get(i).getVolumeInfo().getImageLinks().getThumbnail());
            DebugLogger.logDebug("RxJava : " + googleBookResults.get(i).getVolumeInfo().getTitle());
            DebugLogger.logDebug("RxJava : " + googleBookResults.get(i).getVolumeInfo().getAuthors());
            DebugLogger.logDebug("RxJava : " + googleBookResults.get(i).getVolumeInfo().getDescription());
        }
    }

    private void updateRecyclerView(List<Item> googleBookResults) {
        GoogleBooksAdapter adapter = new GoogleBooksAdapter(googleBookResults);
        bookResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookResultsRecyclerView.setAdapter(adapter);
    }

}
