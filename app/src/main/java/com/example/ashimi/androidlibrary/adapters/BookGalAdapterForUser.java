package com.example.ashimi.androidlibrary.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ashimi.androidlibrary.R;
import com.example.ashimi.androidlibrary.activities.Update;
import com.example.ashimi.androidlibrary.helpers.BookDatabaseHelper;
import com.example.ashimi.androidlibrary.helpers.Config;
import com.example.ashimi.androidlibrary.models.Book;

import java.util.ArrayList;

public class BookGalAdapterForUser extends BaseAdapter {

    private ArrayList<Book> books;
    private Context context;
    private BookDatabaseHelper bookDatabaseHelper;

    public BookGalAdapterForUser(ArrayList<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate( R.layout.user_custom_gal,null);
        ImageView coverPhoto,option;
        if(convertView==null){
            coverPhoto=new ImageView(context);
        }
        coverPhoto=(ImageView)convertView.findViewById( R.id.cover);
        option=(ImageView)convertView.findViewById( R.id.option);
        TextView title=(TextView)convertView.findViewById( R.id.title);
        TextView author=(TextView)convertView.findViewById( R.id.author);
        TextView category=(TextView)convertView.findViewById( R.id.category);
        TextView location=(TextView)convertView.findViewById( R.id.location);
        RatingBar rating=(RatingBar)convertView.findViewById( R.id.rating);
        final Book book=books.get(position);
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        category.setText(book.getCategory());
        location.setText(book.getLocation());
        rating.setRating(book.getRating());
        bookDatabaseHelper=new BookDatabaseHelper(context);
        Glide.with(context).load(book.getCoverPhotoURL()).crossFade(1000).diskCacheStrategy( DiskCacheStrategy.ALL).into(coverPhoto);

        return convertView;
    }
}
