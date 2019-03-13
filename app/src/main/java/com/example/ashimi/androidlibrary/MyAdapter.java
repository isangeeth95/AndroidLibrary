package com.example.ashimi.androidlibrary;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHloder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHloder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_books,parent,false);
        return new ViewHloder(v);
    }

    @Override
    public void onBindViewHolder(ViewHloder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDes.setText(listItem.getDes());
        holder.textViewTeam.setText(listItem.getTeam());
        holder.textViewFirstappearance.setText(listItem.getFirstappearance());
        holder.textViewCreatedby.setText(listItem.getCreatedby());
        holder.textViewPublisher.setText(listItem.getPublisher());
        holder.textViewBio.setText(listItem.getBio());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHloder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDes;
        public TextView textViewTeam;
        public TextView textViewFirstappearance;
        public TextView textViewCreatedby;
        public TextView textViewPublisher;
        public TextView textViewBio;

        public ViewHloder(@NonNull View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDes = (TextView) itemView.findViewById(R.id.textViewDes);
            textViewTeam =(TextView) itemView.findViewById(R.id.textViewTeam);
            textViewFirstappearance = (TextView) itemView.findViewById(R.id.textViewFirstappearance);
            textViewCreatedby = (TextView) itemView.findViewById(R.id.textViewCreatedby);
            textViewPublisher = (TextView) itemView.findViewById(R.id.textViewPublisher);
            textViewBio = (TextView) itemView.findViewById(R.id.textViewBio);
        }
    }
}

