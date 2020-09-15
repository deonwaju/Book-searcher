package com.deonolarewaju.booksearcherapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.deonolarewaju.booksearcherapp.models.VolumesResponse;
import com.deonolarewaju.booksearcherapp.repo.BookRepository;

public class BookSearchViewModel extends AndroidViewModel {

    private static final String api_key_alt = "AIzaSyDW0iy0oDeaqqAoCva0U_hFURVOPn7noUI";

    private BookRepository bookRepository;
    private LiveData<VolumesResponse> volumesResponseLiveData;

    public BookSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        bookRepository = new BookRepository();
        volumesResponseLiveData = bookRepository.getVolumeResponseLiveData();
    }

    public void searchVolumes(String keyword, String author) {
        bookRepository.searchVolumes(keyword, author, api_key_alt);
    }

    public LiveData<VolumesResponse> getVolumesResponseLiveData() {
        return volumesResponseLiveData;
    }

}
