package me.apexjcl.retrofitsample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import me.apexjcl.retrofitsample.R;
import me.apexjcl.retrofitsample.adapters.CommentsAdapter;
import me.apexjcl.retrofitsample.api.ServiceGenerator;
import me.apexjcl.retrofitsample.api.interfaces.PostsAPI;
import me.apexjcl.retrofitsample.models.Comment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by apex on 02/05/2017.
 */

public class CommentsFragment extends Fragment implements Callback<ArrayList<Comment>> {


    private CommentsAdapter mCommentsAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private PostsAPI mPostAPI;
    private int post_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        post_id = getArguments().getInt("post_id", -1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comments, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        mCommentsAdapter = new CommentsAdapter();
        mProgressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCommentsAdapter);
        mPostAPI = ServiceGenerator.generate(PostsAPI.class);
        mPostAPI.getComments(post_id).enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
        toggleProgressBar();
        mCommentsAdapter.setComments(response.body());
        mCommentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
        Toast.makeText(getContext(), R.string.comment_fetch_error, Toast.LENGTH_SHORT).show();
    }

    private void toggleProgressBar() {
        mProgressBar.setVisibility(
                mProgressBar.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE
        );
        mRecyclerView.setVisibility(
                mRecyclerView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE
        );
    }
}
