package me.apexjcl.retrofitsample;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by apex on 02/05/2017.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
