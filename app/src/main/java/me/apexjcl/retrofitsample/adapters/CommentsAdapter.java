package me.apexjcl.retrofitsample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.apexjcl.retrofitsample.R;
import me.apexjcl.retrofitsample.models.Comment;

/**
 * Created by apex on 02/05/2017.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentHolder> {

    private ArrayList<Comment> comments = new ArrayList<>(0);

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        holder.update(comments.get(position));
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    class CommentHolder extends RecyclerView.ViewHolder {

        private TextView mEmail;
        private TextView mBody;

        public CommentHolder(View itemView) {
            super(itemView);
            mEmail = (TextView) itemView.findViewById(R.id.email);
            mBody = (TextView) itemView.findViewById(R.id.body);
        }

        public void update(Comment comment) {
            mEmail.setText(comment.getEmail());
            mBody.setText(comment.getBody());
        }
    }

}
