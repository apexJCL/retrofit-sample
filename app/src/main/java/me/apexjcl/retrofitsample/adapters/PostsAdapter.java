package me.apexjcl.retrofitsample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import me.apexjcl.retrofitsample.R;
import me.apexjcl.retrofitsample.activities.MainActivity;
import me.apexjcl.retrofitsample.models.Post;

/**
 * Created by apex on 02/05/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostHolder> {

    private ArrayList<Post> posts = new ArrayList<>(0);

    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
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

    public class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int mPostId;
        private TextView mTitle;
        private TextView mBody;

        public PostHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mBody = (TextView) itemView.findViewById(R.id.body);
        }

        public void update(Post post) {
            mPostId = post.getId();
            mTitle.setText(post.getTitle());
            mBody.setText(post.getBody());
        }

        @Override
        public void onClick(View v) {
            ((MainActivity)v.getContext()).loadCommentsFragment(mPostId);
        }
    }

}
