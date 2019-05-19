package com.example.ashimi.androidlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class EBookDetailsActivity extends AppCompatActivity {

    private EditText mISBN_editTxt;
    private EditText mEBookTitle_editTxt;
    private EditText mCategory_editTxt;
    private EditText mAuthor_editTxt;
    private EditText mEdition_editTxt;
    private EditText mPublishDate_editTxt;
    private EditText mLanguage_editTxt;

    private Button mUpdate_btn;
    private Button mDelete_btn;

    private String key;
    private String isbn;
    private String eBookTitle;
    private String category;
    private String author;
    private String edition;
    private String publishDate;
    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook_details);

        key = getIntent().getStringExtra("key");
        isbn = getIntent().getStringExtra("isbn");
        eBookTitle = getIntent().getStringExtra("eBookTitle");
        category = getIntent().getStringExtra("category");
        author = getIntent().getStringExtra("author");
        edition = getIntent().getStringExtra("edition");
        publishDate = getIntent().getStringExtra("publishDate");
        language = getIntent().getStringExtra("language");

        mISBN_editTxt = (EditText) findViewById(R.id.ISBN);
        mISBN_editTxt.setText(isbn);
        mEBookTitle_editTxt = (EditText) findViewById(R.id.EBookTitle);
        mEBookTitle_editTxt.setText(eBookTitle);
        mCategory_editTxt = (EditText) findViewById(R.id.Category);
        mCategory_editTxt.setText(category);
        mAuthor_editTxt = (EditText) findViewById(R.id.Author);
        mAuthor_editTxt.setText(author);
        mEdition_editTxt = (EditText) findViewById(R.id.Edition);
        mEdition_editTxt.setText(edition);
        mPublishDate_editTxt = (EditText) findViewById(R.id.PublishDate);
        mPublishDate_editTxt.setText(publishDate);
        mLanguage_editTxt = (EditText) findViewById(R.id.Language);
        mLanguage_editTxt.setText(language);

        mUpdate_btn = (Button) findViewById(R.id.UpdateEbook);
        mDelete_btn = (Button) findViewById(R.id.DeleteEbook);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EBook ebook = new EBook();
                ebook.setISBN(mISBN_editTxt.getText().toString());
                ebook.seteBookTitle(mEBookTitle_editTxt.getText().toString());
                ebook.setCategory(mCategory_editTxt.getText().toString());
                ebook.setAuthor(mAuthor_editTxt.getText().toString());
                ebook.setEdition(mEdition_editTxt.getText().toString());
                ebook.setPublishDate(mPublishDate_editTxt.getText().toString());
                ebook.setLanguage(mLanguage_editTxt.getText().toString());

                new FirebaseDatabaseHelper().updateEBook(key, ebook, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<EBook> EBook, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(EBookDetailsActivity.this, "EBook record has been" + "updated successfully", Toast.LENGTH_LONG).show();
                        finish(); return;
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteEBook(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<EBook> EBook, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(EBookDetailsActivity.this, "EBook record has been" + "deleted succesfully!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private int getIndex_SpinnerItem(Spinner spinner, String item){
        int index = 0;
        for(int i = 0; i<spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).equals(item)){
                index = i;
                break;
            }
        }
        return index;
    }
}
