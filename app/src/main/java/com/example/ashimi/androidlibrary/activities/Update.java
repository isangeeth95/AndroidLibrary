package com.example.ashimi.androidlibrary.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ashimi.androidlibrary.R;
import com.example.ashimi.androidlibrary.helpers.BookDatabaseHelper;
import com.example.ashimi.androidlibrary.helpers.Config;

public class Update extends AppCompatActivity implements View.OnClickListener {
    private EditText titleField,authorField,categoryField,locationField,ISBNField,quantityField;
    private RatingBar ratingField;
    private ImageView coverPhotoField;
    private Button editBook;
    private Uri coverPhotoURL;
    private BookDatabaseHelper bookDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_update);
       // setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bookDatabaseHelper=new BookDatabaseHelper(this);
        titleField=(EditText)findViewById( R.id.book_title_field);
        authorField=(EditText)findViewById( R.id.book_author_field);
        categoryField=(EditText)findViewById( R.id.book_category);
        locationField=(EditText)findViewById( R.id.book_location);
        ISBNField = (EditText) findViewById( R.id.book_ISBN_field );
        quantityField = (EditText) findViewById( R.id.book_quantity );
        ratingField=(RatingBar)findViewById( R.id.rating);
        coverPhotoField=(ImageView)findViewById( R.id.book_cover);
        editBook=(Button)findViewById( R.id.edit_book);
        titleField.setText(getIntent().getExtras().getString(Config.BOOK_TITLE));
        ISBNField.setText(getIntent().getExtras().getString(Config.BOOK_ISBNF));
        quantityField.setText(getIntent().getExtras().getString(Config.Book_QUANTITY));
        authorField.setText(getIntent().getExtras().getString(Config.BOOK_AUTHOR));
        categoryField.setText(getIntent().getExtras().getString(Config.BOOK_CATEGORY));
        locationField.setText(getIntent().getExtras().getString(Config.BOOK_LOCATION));
        ratingField.setRating(getIntent().getExtras().getFloat(Config.BOOK_RATING));
        Glide.with(this).load(getIntent().getExtras().getString(Config.BOOK_COVER_PHOTO_URL)).
                crossFade(1000).diskCacheStrategy(DiskCacheStrategy.ALL).into(coverPhotoField);
        editBook.setOnClickListener(this);
        coverPhotoField.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_book:
                update();
                break;
            case R.id.book_cover:
                captureCoverPhoto();
                break;
        }
    }

    // capture book cover photo
    private void captureCoverPhoto() {
        Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,Config.COVER_PHOTO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Config.COVER_PHOTO_REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            coverPhotoURL=data.getData();
            coverPhotoField.setImageURI(coverPhotoURL);
        }
    }

    // update book according to id
    private void update() {
        String getTitle=titleField.getText().toString();
        String getAuthor=authorField.getText().toString();
        String getCategory=categoryField.getText().toString();
        String getLocation=locationField.getText().toString();
        String getISBNF=ISBNField.getText().toString();
        int getQuantity = Integer.valueOf(quantityField.getText().toString());
        float getRating=ratingField.getRating();
        boolean isISBNFEmpty=ISBNField.getText().toString().isEmpty();
        boolean isQuantityEmpty=quantityField.getText().toString().isEmpty();
        boolean isTitleEmpty=titleField.getText().toString().isEmpty();
        boolean isAuthorEmpty=authorField.getText().toString().isEmpty();
        boolean isCategoryEmpty=categoryField.getText().toString().isEmpty();
        boolean isLocationEmpty=locationField.getText().toString().isEmpty();
        boolean isNoRating=ratingField.getRating()==0;
        boolean isNullPhotoURL=coverPhotoURL==null;
        String book_id=getIntent().getExtras().getString(Config.BOOK_ID);

        if(isTitleEmpty){
            titleField.setError(Config.TITLE_EMPTY_MESSAGE);
        }

        if(isAuthorEmpty){
            authorField.setError(Config.AUTHOR_EMPTY_MESSAGE);
        }

        if(isISBNFEmpty){
            titleField.setError(Config.ISBNF_EMPTY_MESSAGE);
        }

        if(isQuantityEmpty){
            authorField.setError(Config.QUANTITY_EMPTY_MESSAGE);
        }

        if(isCategoryEmpty){
            categoryField.setError(Config.CATEGORY_EMPTY_MESSAGE);
        }

        if(isLocationEmpty){
            locationField.setError(Config.LOCATION_EMPTY_MESSAGE);
        }

        if(isNoRating){
            Toast.makeText(this, Config.RATING_ZERO_MESSAGE, Toast.LENGTH_SHORT).show();
        }

        if(isNullPhotoURL){
            Toast.makeText(this, Config.IMAGE_URL_NULL_MESSAGE, Toast.LENGTH_SHORT).show();
        }

        if(!isTitleEmpty && !isAuthorEmpty && !isISBNFEmpty && !isQuantityEmpty &&!isNoRating && !isNullPhotoURL){
            bookDatabaseHelper.edit(getApplicationContext(),book_id,getTitle,getCategory,getLocation,getAuthor,getRating,coverPhotoURL,getISBNF,getQuantity);
        }
    }
}
