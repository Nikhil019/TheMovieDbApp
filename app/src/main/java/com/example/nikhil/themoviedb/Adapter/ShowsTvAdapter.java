package com.example.nikhil.themoviedb.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikhil.themoviedb.Constants;
import com.example.nikhil.themoviedb.R;
import com.example.nikhil.themoviedb.ViewHolder.TabViewHolder;
import com.example.nikhil.themoviedb.entities.TvShows.Shows;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by shubham on 7/21/2018.
 */

public class ShowsTvAdapter extends RecyclerView.Adapter<TabViewHolder> {
    List<Shows> showsArrayList;
    Context context;
    LayoutInflater inflater;
    RowListener listener;

    public ShowsTvAdapter(Context context, List<Shows> showsArrayList, RowListener listener) {
        this.showsArrayList = showsArrayList;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.listener=listener;
    }

    @Override
    public TabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.movie_row_layout,parent,false);
        return new TabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TabViewHolder holder, int position) {
        holder.tvTitle.setText(showsArrayList.get(position).getName());
//        holder.tvOverview.setText(showsArrayList.get(position).getOverview());
        if(showsArrayList.get(position).getPosterPath()!=null)
            Picasso.get().load(Constants.IMAGE_URL+showsArrayList.get(position).getPosterPath()).into(holder.poster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListItemClicked(v,holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return showsArrayList.size();
    }
}
