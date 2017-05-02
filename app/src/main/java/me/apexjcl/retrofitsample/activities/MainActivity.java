package me.apexjcl.retrofitsample.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import me.apexjcl.retrofitsample.R;
import me.apexjcl.retrofitsample.fragments.CommentsFragment;
import me.apexjcl.retrofitsample.fragments.PostsFragment;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        loadPostsFragment();
    }

    public void loadPostsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, new PostsFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public void loadCommentsFragment(int post_id) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new CommentsFragment();
        Bundle args = new Bundle();
        args.putInt("post_id", post_id);
        fragment.setArguments(args);
        ft.replace(R.id.frame_layout, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}
