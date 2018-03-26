package com.example.mentorship.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mentorship.AppConstants;
import com.example.mentorship.model.MyApplication;
import com.example.mentorship.R;
import com.example.mentorship.Adapter.RecyclerViewAdapter;
import com.example.mentorship.model.Image;
import com.example.mentorship.model.SearchResponse;
import com.example.mentorship.network.RequestInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private static final String ARG_PARAM1 = "SearchQuery";
    private String searchQuery;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.next)
    Button next;
    private RecyclerViewAdapter recyclerViewAdapter;
    int currentPage=1,resultsPerPage = 20;
    private MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        myApplication= (MyApplication) getApplicationContext();

        searchQuery=getIntent().getStringExtra(ARG_PARAM1);

        toolbar.setTitle(searchQuery);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerViewAdapter=new RecyclerViewAdapter(this, new ArrayList<Image>());
        recyclerView.setAdapter(recyclerViewAdapter);

        searchQuery=getIntent().getStringExtra(ARG_PARAM1);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<Image> list=myApplication.getDatabase().imageDao().getImages(searchQuery);
                Log.d("Size",list.size()+"");
                if(list.size()!=0){
                    recyclerViewAdapter.addItems(list);
                    recyclerView.smoothScrollToPosition(0);
                    progressBar.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);
                    currentPage=list.size()/10;
                }else {
                    getContent();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage++;
                Toast.makeText(ListActivity.this, "Page "+currentPage, Toast.LENGTH_SHORT).show();
                getContent();
                nestedScrollView.scrollTo(0,0);
            }
        });

    }

    private void getContent() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        Call<SearchResponse> response = requestInterface.search(AppConstants.API_KEY,
                "photo",
                searchQuery,
                currentPage,
                resultsPerPage);

        response.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();
                if (searchResponse != null) {
                    next.setVisibility(View.VISIBLE);
                    final List<Image> fetchedImages = searchResponse.getImages();

                    for (Image image:fetchedImages){
                        image.setTags(searchQuery);
                    }

                    recyclerViewAdapter.addItems(fetchedImages);
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            myApplication.getDatabase().imageDao().insertImages(fetchedImages);
                        }
                    });
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_1:
                recyclerView.setLayoutManager(new GridLayoutManager(this,2));
                break;
            case R.id.action_2:
                recyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
        }
        return false;
    }


}
