package com.karangdigital.iknetznews.adapter.news;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karangdigital.iknetznews.R;
import com.karangdigital.iknetznews.entity.news.Articles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dimata005 on 8/6/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ReportsViewHolder> {
    private static ClickListener clickListener;
    ArrayList<Articles> listNews = new ArrayList<>();

    public NewsAdapter(ArrayList<Articles> reportsList) {
        this.listNews = reportsList;
    }

    @Override
    public ReportsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news, parent, false);
        ReportsViewHolder vH = new ReportsViewHolder(v);

        return vH;
    }

    @Override
    public void onBindViewHolder(ReportsViewHolder holder, int position) {
        Articles articles = listNews.get(position);
        //Articles articles = laporanNews.getvArticles().get(position);
        holder.tvTitle.setText(articles.getTitle());
        holder.tvAuthor.setText(articles.getAuthor());
        String kronologi =""+articles.getDescription();
        if(kronologi.length()>200){
            kronologi=kronologi.substring(0,200);
        }
        holder.tvDesc.setText(""+kronologi);
        Context context = holder.imageView.getContext();

        String photoUrl = articles.getUrlToImage();
        Picasso.with(context).load(photoUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public interface ClickListener{
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener){
        NewsAdapter.clickListener = clickListener;
    }


    public class ReportsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvAuthor;
        public TextView tvTitle;
        public TextView tvDesc;
        //public RelativeLayout cardSearch;
        ImageView imageView;
        public ReportsViewHolder (View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            //cardSearch  = (RelativeLayout) itemView.findViewById(R.id.cardSearch);
            tvAuthor       = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvTitle     = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDesc    = (TextView) itemView.findViewById(R.id.tvDesc);
            imageView = (ImageView) itemView.findViewById(R.id.tvImage);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}
