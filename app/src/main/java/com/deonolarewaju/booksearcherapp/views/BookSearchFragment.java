package com.deonolarewaju.booksearcherapp.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deonolarewaju.booksearcherapp.R;
import com.deonolarewaju.booksearcherapp.adapter.BookSearchAdapter;
import com.deonolarewaju.booksearcherapp.models.VolumesResponse;
import com.deonolarewaju.booksearcherapp.viewmodels.BookSearchViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class BookSearchFragment extends Fragment {

    private BookSearchViewModel bookSearchViewModel;
    private BookSearchAdapter bookSearchAdapter;
    Context context;

    private TextInputEditText keyword;
    private TextInputEditText author;
    private Button searchBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookSearchAdapter = new BookSearchAdapter(context);
        bookSearchViewModel = ViewModelProviders.of(this).get(BookSearchViewModel.class);
        bookSearchViewModel.init();
        bookSearchViewModel.getVolumesResponseLiveData().observe(this, new Observer<VolumesResponse>() {
            @Override
            public void onChanged(VolumesResponse volumesResponse) {
                if (volumesResponse != null){
                    bookSearchAdapter.setResults(volumesResponse.getItems());
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booksearch, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_booksearch_searchResultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(bookSearchAdapter);

        keyword = view.findViewById(R.id.fragment_booksearch_keyword);
        author = view.findViewById(R.id.fragment_booksearch_author);
        searchBtn = view.findViewById(R.id.fragment_booksearch_search);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSearch();
            }
        });

        return view;
    }

    private void doSearch() {
        String searchKeyword = keyword.getEditableText().toString();
        String authorName = author.getEditableText().toString();

        bookSearchViewModel.searchVolumes(searchKeyword, authorName);
    }

}
