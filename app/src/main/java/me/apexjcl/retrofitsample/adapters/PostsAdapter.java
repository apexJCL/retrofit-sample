package me.apexjcl.retrofitsample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.apexjcl.retrofitsample.R;
import me.apexjcl.retrofitsample.models.Post;

/**
 * Created by apex on 02/05/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostHolder> {

    private ArrayList<Post> posts = new ArrayList<>(0);

    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(PostHolder holder, int position) {
        holder.update(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public class PostHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mBody;

        public PostHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mBody = (TextView) itemView.findViewById(R.id.body);
        }

        public void update(Post post) {
            mTitle.setText(post.getTitle());
            mBody.setText(post.getBody());
        }
    }

}
