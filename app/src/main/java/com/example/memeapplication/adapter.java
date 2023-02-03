package com.example.memeapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.Viewholder> {
    private ArrayList<row_model> links;
    private Context context;

    public adapter(ArrayList<row_model> link, Context c) {
        this.links = link;
        this.context = c;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {


        row_model model=links.get(position);
        String title=model.getTitle();
        String link =model.getLink();
        String auth_name=model.getAuthor();



        /**
         * alternate way
         * Picasso.get().load(title).into(holder.meme);
         */
        Glide.with(context).load(link).into(holder.meme);
        holder.tv.setText(title);
        holder.auth.setText(auth_name);
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,model.link);
                intent.setType("text/plain");
                Intent.createChooser(intent,"send to");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return links.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView tv,auth;
        ImageView meme;
        Button share;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            auth=itemView.findViewById(R.id.author_name);
            tv = itemView.findViewById(R.id.title_name);
            meme = itemView.findViewById(R.id.memephoto);
            share=itemView.findViewById(R.id.share_btn);


        }
    }
}
