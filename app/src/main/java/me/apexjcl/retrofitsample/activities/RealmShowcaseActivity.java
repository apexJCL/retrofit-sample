package me.apexjcl.retrofitsample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;
import me.apexjcl.retrofitsample.R;
import me.apexjcl.retrofitsample.adapters.RealmPostAdapter;
import me.apexjcl.retrofitsample.models.Post;

public class RealmShowcaseActivity extends AppCompatActivity {


    private Realm realm;

    private EditText mTitle;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_showcase);
        mTitle = (EditText) findViewById(R.id.title);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        realm = Realm.getDefaultInstance(); // cada que la aplicación inicie, obtendremos el realm
        mRecyclerView.setAdapter(new RealmPostAdapter(
                getApplicationContext(),
                realm.where(Post.class).findAllAsync()
        ));
    }

    public void addPost(View v) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Post p = bgRealm.createObject(Post.class);
                p.setTitle(mTitle.getText().toString());
                p.setBody("Nuevo");
                p.setId(1);
                bgRealm.copyToRealm(p);
            }
        });
    }


    @Override
    protected void onStop() {
        if (realm != null)
            realm.close();
        realm = null;
        super.onStop();
    }
}
