package me.apexjcl.retrofitsample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by apex on 02/05/2017.
 */

public class PostsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        Callback<ArrayList<Post>> {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private PostsAdapter mPostAdapter;
    private PostsAPI mPostAPI;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_posts, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        mPostAdapter = new PostsAdapter();
        mRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mPostAdapter);
        mRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
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
        Toast.makeText(getContext(), R.string.post_fetch_error, Toast.LENGTH_SHORT).show();
        mRefreshLayout.setRefreshing(false);
    }
}
