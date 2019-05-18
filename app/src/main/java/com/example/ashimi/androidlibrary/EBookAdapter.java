package com.example.ashimi.androidlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static java.lang.System.load;

public class EBookAdapter extends RecyclerView.Adapter<EBookAdapter.MyViewHolder>{

    Context context1;
    ArrayList<EBook> eBooks;
    public EBookAdapter(Context c, ArrayList<EBook> e){
        context1 = c;
        eBooks = e;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MyViewHolder(LayoutInflater.from(context1).inflate(R.layout.cardview,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.isbn.setText(eBooks.get(position).getISBN());
        myViewHolder.eBookTitle.setText(eBooks.get(position).geteBookTitle());
        myViewHolder.category.setText(eBooks.get(position).getCategory());
        myViewHolder.author.setText(eBooks.get(position).getAuthor());
        myViewHolder.edition.setText(eBooks.get(position).getEdition());
        myViewHolder.publishDate.setText(eBooks.get(position).getPublishDate());
        myViewHolder.language.setText(eBooks.get(position).getLanguage());
        Picasso.with(context1).load(eBooks.get(position).getPicture()).into(myViewHolder.picture);
    }

    @Override
    public int getItemCount() {
        return eBooks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView isbn, eBookTitle, category, author, edition, publishDate, language;
        public ImageView picture;

        public MyViewHolder(@NonNull View itemView1) {
            super(itemView1);
            isbn = (TextView) itemView1.findViewById(R.id.isbn);
            eBookTitle = (TextView) itemView1.findViewById(R.id.eBookTitle);
            category = (TextView) itemView1.findViewById(R.id.category);
            author = (TextView) itemView1.findViewById(R.id.author);
            edition = (TextView) itemView1.findViewById(R.id.edition);
            publishDate =(TextView) itemView1.findViewById(R.id.publishDate);
            language = (TextView) itemView1.findViewById(R.id.language);
            picture = (ImageView) itemView1.findViewById(R.id.picture);
        }
    }
}
