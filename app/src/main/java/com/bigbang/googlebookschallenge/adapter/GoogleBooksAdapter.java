package com.bigbang.googlebookschallenge.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bigbang.googlebookschallenge.R;
import com.bigbang.googlebookschallenge.model.Item;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoogleBooksAdapter extends RecyclerView.Adapter<GoogleBooksAdapter.BookViewHolder> {

    private List<Item> bookResults;

    private ViewGroup theParent;

    public GoogleBooksAdapter(List<Item> bookResults) {
        this.bookResults = bookResults;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        theParent = parent;

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item_layout, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        Glide.with(theParent.getContext())
                .load(bookResults.get(position).getVolumeInfo().getImageLinks().getThumbnail())
                .into(holder.bookImage);

        holder.bookTitle.setText(bookResults.get(position).getVolumeInfo().getTitle());
        holder.bookAuthors.setText(bookResults.get(position).getVolumeInfo().getAuthors().toString());
    }

    @Override
    public int getItemCount() {
        return bookResults.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.book_image)
        ImageView bookImage;

        @BindView(R.id.book_title_textview)
        TextView bookTitle;

        @BindView(R.id.book_authors_textview)
        TextView bookAuthors;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
