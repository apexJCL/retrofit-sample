package me.apexjcl.retrofitsample.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import me.apexjcl.retrofitsample.R;
import me.apexjcl.retrofitsample.adapters.PostsAdapter;
import me.apexjcl.retrofitsample.api.ServiceGenerator;
import me.apexjcl.retrofitsample.api.interfaces.PostsAPI;
import me.apexjcl.retrofitsample.models.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        Callback<ArrayList<Post>>  {


    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private PostsAdapter mPostAdapter;
    private PostsAPI mPostAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mPostAdapter = new PostsAdapter();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mPostAdapter);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRefreshLayout.setOnRefreshListener(this);
        mPostAPI = ServiceGenerator.generate(PostsAPI.class);
    }

    @Override
    public void onRefresh() {
        mPostAPI.getPosts().enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
        mPostAdapter.setPosts(response.body());
        mPostAdapter.notifyDataSetChanged();
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
        Toast.makeText(getApplicationContext(), R.string.post_fetch_error, Toast.LENGTH_SHORT).show();
        mRefreshLayout.setRefreshing(false);
    }
}
