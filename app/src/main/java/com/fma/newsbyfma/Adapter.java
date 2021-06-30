package com.fma.newsbyfma;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> modelArrayList;  //Model = Model Class, Main Model = Main News

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewDetails.class);
                intent.putExtra("url", modelArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
        holder.time.setText("Published At : " + modelArrayList.get(position).getPublishedAt());
        holder.author.setText(modelArrayList.get(position).getAuthor());
        holder.heading.setText(modelArrayList.get(position).getTitle());
        holder.content.setText(modelArrayList.get(position).getDescription());
        Glide.with(context).load(modelArrayList.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView heading, content, author, time;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.main_heading_of_news);
            content = itemView.findViewById(R.id.content_of_news);
            author = itemView.findViewById(R.id.author_of_news);
            time = itemView.findViewById(R.id.time_of_news);
            cardView = itemView.findViewById(R.id.card_view);
            imageView = itemView.findViewById(R.id.image_of_news);
        }
    }
}
