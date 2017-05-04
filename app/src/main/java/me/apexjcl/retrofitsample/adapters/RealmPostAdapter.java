package me.apexjcl.retrofitsample.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import me.apexjcl.retrofitsample.R;
import me.apexjcl.retrofitsample.models.Post;

/**
 * Created by apex on 04/05/2017.
 */

public class RealmPostAdapter extends RealmRecyclerViewAdapter<Post, RealmPostAdapter.ViewHolder> {

    public RealmPostAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<Post> data) {
        super(context, data, true);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.update(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mBody;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mBody = (TextView) itemView.findViewById(R.id.body);
        }


        public void update(int position) {
            Post mPost = getItem(position);
            mTitle.setText(mPost.getTitle());
            mBody.setText(mPost.getBody());
        }
    }

}
