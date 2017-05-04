package me.apexjcl.retrofitsample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import me.apexjcl.retrofitsample.R;

public class RealmShowcaseActivity extends AppCompatActivity {


    private EditText mTitle;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_showcase);
        mTitle = (EditText) findViewById(R.id.title);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    public void addPost(View v) {

    }
}
