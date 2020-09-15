package com.deonolarewaju.booksearcherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.deonolarewaju.booksearcherapp.R;
import com.deonolarewaju.booksearcherapp.models.Volume;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BookSearchAdapter extends RecyclerView.Adapter<BookSearchAdapter.BookSearchHolder> {

    private List<Volume> results = new ArrayList<>();

    @NonNull
    @Override
    public BookSearchAdapter.BookSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);

        return new BookSearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookSearchAdapter.BookSearchHolder holder, int position) {
        Volume volume = results.get(position);

        holder.titleTextView.setText(volume.getVolumeInfo().getTitle());
        holder.publishedDateTextView.setText(volume.getVolumeInfo().getPublishedDate());

        if (volume.getVolumeInfo().getImageLinks() != null) {
            String imageUrl = volume.getVolumeInfo().getImageLinks().getSmallThumbnail()
                    .replace("http://", "https://");

            Glide.with(holder.itemView)
                    .load(imageUrl)
                    .into(holder.smallThumbnailImageView);
        }

        if (volume.getVolumeInfo().getAuthors() != null) {
            String joinedAuthors = StringUtils.join(volume.getVolumeInfo().getAuthors(), ", ");
            holder.authorsTextView.setText(joinedAuthors);

        }

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void setResults(List<Volume> results){
        this.results = results;
        notifyDataSetChanged();
    }

    public class BookSearchHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView authorsTextView;
        private TextView publishedDateTextView;
        private ImageView smallThumbnailImageView;

        public BookSearchHolder(@NonNull View itemView) {
            super(itemView);


            titleTextView = itemView.findViewById(R.id.book_item_title);
            authorsTextView = itemView.findViewById(R.id.book_item_authors);
            publishedDateTextView = itemView.findViewById(R.id.book_item_publishedDate);
            smallThumbnailImageView = itemView.findViewById(R.id.book_item_smallThumbnail);

        }
    }
}
