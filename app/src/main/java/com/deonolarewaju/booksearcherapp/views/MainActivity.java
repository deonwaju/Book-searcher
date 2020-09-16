package com.deonolarewaju.booksearcherapp.views;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deonolarewaju.booksearcherapp.R;
import com.deonolarewaju.booksearcherapp.adapter.BookSearchAdapter;
import com.deonolarewaju.booksearcherapp.models.VolumesResponse;
import com.deonolarewaju.booksearcherapp.viewmodels.BookSearchViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private BookSearchViewModel bookSearchViewModel;
    private BookSearchAdapter bookSearchAdapter;

    private TextInputEditText keyword;
    private TextInputEditText author;
    private Button searchBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookSearchAdapter = new BookSearchAdapter(this);
        bookSearchViewModel = ViewModelProviders.of(this).get(BookSearchViewModel.class);
        bookSearchViewModel.init();
        bookSearchViewModel.getVolumesResponseLiveData().observe(this, new Observer<VolumesResponse>() {
            @Override
            public void onChanged(VolumesResponse volumesResponse) {
                if (volumesResponse != null) {
                    bookSearchAdapter.setResults(volumesResponse.getItems());
                }
            }
        });

        keyword = findViewById(R.id.fragment_booksearch_keyword);
        author = findViewById(R.id.fragment_booksearch_author);
        searchBtn = findViewById(R.id.fragment_booksearch_search);

        RecyclerView recyclerView = findViewById(R.id.fragment_booksearch_searchResultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookSearchAdapter);

        searchBtn.setOnClickListener(view -> doSearch());

    }

    private void doSearch() {
        String searchKeyword = keyword.getEditableText().toString();
        String authorName = author.getEditableText().toString();

        bookSearchViewModel.searchVolumes(searchKeyword, authorName);
    }

}