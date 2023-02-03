package com.example.memeapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    adapter ad;
    ArrayList<row_model> data;
    Boolean is_scrolling=false;
    int current,total,out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data=new ArrayList<>();
        recyclerView=findViewById(R.id.rv);
        ad=new adapter(data,getApplicationContext());
        manager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(ad);
        for(int i=0;i<2;i++)
        {
            getDat();
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    is_scrolling=true;
                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                current=manager.getChildCount();
                total=manager.getItemCount();
                out = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if(is_scrolling && (current+out==total)){
//                   progressBar.setVisibility(View.VISIBLE);
                    getDat();
//                   progressBar.setVisibility(View.GONE);
                    is_scrolling=false;

                }
            }
        });
    }

    private void getDat() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://meme-api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyApiCall myApiCall=retrofit.create(MyApiCall.class);
        Call<DataModel> call =myApiCall.getData();

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                String link=" ";
                String title=" ";
                String author=" ";
                author=response.body().getAuthor();
                link = response.body().getUrl();
                title=response.body().getTitle();
                Log.i("Tag",link);
                Log.i("Tag",title);
                row_model model=new row_model(title,link,author);
                data.add(model);
//                data.add(link);
                ad.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });
    }
}