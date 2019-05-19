package com.example.ashimi.androidlibrary;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private EBooksAdaper mEBooksAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<EBook> EBook, List<String> keys ){
        mContext = context;
        mEBooksAdapter = new EBooksAdaper(EBook, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mEBooksAdapter);
    }

    class EBookItemView extends RecyclerView.ViewHolder{
        private TextView mISBN;
        private TextView mEBookTitle;
        private TextView mCategory;
        private TextView mAuthor;
        private TextView mEdition;
        private TextView mPublishDate;
        private TextView mLanguage;
        private ImageView mPicture;

        private String key;

        public EBookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.ebook_list_item, parent,false));

            mISBN = (TextView) itemView.findViewById(R.id.isbn);
            mEBookTitle = (TextView) itemView.findViewById(R.id.eBookTitle);
            mCategory = (TextView) itemView.findViewById(R.id.category);
            mAuthor = (TextView) itemView.findViewById(R.id.author);
            mEdition = (TextView) itemView.findViewById(R.id.edition);
            mPublishDate = (TextView) itemView.findViewById(R.id.publishDate);
            mLanguage = (TextView) itemView.findViewById(R.id.language);
            mPicture = (ImageView) itemView.findViewById(R.id.picture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, EBookDetailsActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("isbn", mISBN.getText().toString());
                    intent.putExtra("eBookTitle", mEBookTitle.getText().toString());
                    intent.putExtra("category", mCategory.getText().toString());
                    intent.putExtra("author", mAuthor.getText().toString());
                    intent.putExtra("edition", mEdition.getText().toString());
                    intent.putExtra("publishDate", mPublishDate.getText().toString());
                    intent.putExtra("language", mLanguage.getText().toString());

                    mContext.startActivity(intent);
                }
            });
        }

        public void bind(EBook ebook, String key){
            mISBN.setText(ebook.getISBN());
            mEBookTitle.setText(ebook.geteBookTitle());
            mCategory.setText(ebook.getCategory());
            mAuthor.setText(ebook.getAuthor());
            mEdition.setText(ebook.getEdition());
            mPublishDate.setText(ebook.getPublishDate());
            mLanguage.setText(ebook.getLanguage());
            this.key = key;
        }
    }

    class EBooksAdaper extends RecyclerView.Adapter<EBookItemView>{
        private List<EBook> mEBookList;
        private List<String> mKeys;

        public EBooksAdaper(List<EBook> mEBookList, List<String> mKeys) {
            this.mEBookList = mEBookList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public EBookItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new EBookItemView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull EBookItemView eBookItemView, int i) {
            eBookItemView.bind(mEBookList.get(i),mKeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mEBookList.size();
        }
    }
}
