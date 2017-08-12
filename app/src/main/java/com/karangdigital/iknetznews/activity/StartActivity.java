package com.karangdigital.iknetznews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.karangdigital.iknetznews.R;
import com.karangdigital.iknetznews.adapter.news.NewsAdapter;
import com.karangdigital.iknetznews.entity.news.Articles;
import com.karangdigital.iknetznews.entity.news.ListNewsResponse;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    private Gson gson = new Gson();
    private RecyclerView rvView;
    private RecyclerView.LayoutManager layoutManager;

    private ListNewsResponse listNewsResponse;
    private ArrayList<Articles> vListNews = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private Gson mGson;
    public TextView tvSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //daalam membuat reycler view
        //a. view holder
        //b. Adapter
        //c. layout manager

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        newsAdapter = new NewsAdapter(vListNews);
        rvView.setAdapter(newsAdapter);

        getReportsfromAPI("https://newsapi.org/v1/articles?source=mtv-news&sortBy=top&apiKey=e76c364a226a477ab68485e7aaacaae4");

        /*btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Filter by Bank Account Number
                String newUrl = "http://api.dianiopiari.com/api.php/v_laporan?include=v_bukti,v_account,v_user&filter=nolaporan,cs," + etSearch.getText() + "&transform=1&order=idlaporan,desc";
                getReportsfromAPI(newUrl);
            }
        });*/

        newsAdapter.setOnItemClickListener(new NewsAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Articles model = vListNews.get(position);
                //Intent toTarget = new Intent(SearchActivity.this, DetailPostListActivity.class);
                //mGson = new Gson();
                //String dataExtra = mGson.toJson(model);
                //toTarget.putExtra("KEY", dataExtra);// ngirim
                // getintent extra
                //v_list_laporan fromJsonToModel = mGson.fromJson(dataExtra, v_list_laporan.class);
                //startActivity(toTarget);
                Toast.makeText(StartActivity.this," hayo mau klik ya", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void getReportsfromAPI(String url) {
        vListNews.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listNewsResponse = gson.fromJson(response, ListNewsResponse.class);
                for (Articles report : listNewsResponse.getV_laporan()) {
                    vListNews.add(report);
                }
                //Toast.makeText(StartActivity.this," data found", Toast.LENGTH_SHORT).show();
                newsAdapter.notifyDataSetChanged();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StartActivity.this, "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                responseListener,
                errorListener
        );
        requestQueue.add(request);
    }

}
