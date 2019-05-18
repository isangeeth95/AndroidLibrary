package com.example.ashimi.androidlibrary;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class Inventory_list extends ArrayAdapter<Book> {
    private Activity context;
    private List<Book> bookList;

    public Inventory_list(Activity context, List<Book> bookList) {
        super( context, R.layout.inventory_list_layout, bookList );
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate( R.layout.inventory_list_layout,null,true );

        TextView textViewId = (TextView)listViewItem.findViewById( R.id.textViewId );
        TextView textViewName = (TextView)listViewItem.findViewById( R.id.textViewName );
        TextView textViewCat = (TextView)listViewItem.findViewById( R.id.textViewCat );
        TextView textViewlocation = (TextView)listViewItem.findViewById( R.id.textViewLocation );

        Book book = bookList.get( position );

        textViewName.setText( book.getBook_id() );
        textViewName.setText( book.getBook_name() );
        textViewCat.setText( book.getCategory() );
        textViewName.setText( book.getLocation() );

        return listViewItem;
    }
}
