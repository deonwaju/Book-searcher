package com.deonolarewaju.booksearcherapp.views;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.deonolarewaju.booksearcherapp.R;

public class BookDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_BOOKS = "extra_books";

    private ImageView bookPhoto;
    private TextView bookTitle;
    private TextView bookDescription;
    private TextView bookAuthor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        bookDescription = findViewById(R.id.book_description);
        bookAuthor = findViewById(R.id.txt_author);
        bookTitle = findViewById(R.id.txt_title);
        bookPhoto = findViewById(R.id.book_img);


        setUp();

    }

    private void setUp() {

        if (getIntent().hasExtra("title") && getIntent().hasExtra("description") && getIntent().hasExtra("author") && getIntent().hasExtra("photoUrl")) {

            String title = getIntent().getStringExtra("title");
            String author = getIntent().getStringExtra("author");
            String description = getIntent().getStringExtra("description");
            String photoUrl = getIntent().getStringExtra("photoUrl");

            if (photoUrl != null && !photoUrl.isEmpty()) {
                Glide.with(this).load(photoUrl).into(bookPhoto);
            }

            bookTitle.setText(title);
            bookAuthor.setText(author);
            bookDescription.setText(description);
        }
    }
}
