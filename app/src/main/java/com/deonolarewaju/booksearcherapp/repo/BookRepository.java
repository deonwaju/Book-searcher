package com.deonolarewaju.booksearcherapp.repo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.deonolarewaju.booksearcherapp.models.VolumesResponse;
import com.deonolarewaju.booksearcherapp.network.BookSearchService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {
    private static final String BOOK_SEARCH_SERVICE_BASE_URL = "https://www.googleapis.com/";

    private BookSearchService bookSearchService;


    private MutableLiveData<VolumesResponse> volumesResponseMutableLiveData;

    public BookRepository() {
        volumesResponseMutableLiveData = new MutableLiveData<>();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        bookSearchService = new Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookSearchService.class);
    }

    public void searchVolumes(String keyWord, String author, String apiKey) {
        bookSearchService.searchVolumes(keyWord, author, apiKey)
                .enqueue(new Callback<VolumesResponse>() {
                    @Override
                    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response) {
                        if (response.body() !=null) {
                            volumesResponseMutableLiveData.postValue(response.body());

                        }
                    }

                    @Override
                    public void onFailure(Call<VolumesResponse> call, Throwable t) {
                        volumesResponseMutableLiveData.postValue(null);

                    }
                });
    }

    public LiveData<VolumesResponse> getVolumeResponseLiveData() {
        return volumesResponseMutableLiveData;
    }

}
