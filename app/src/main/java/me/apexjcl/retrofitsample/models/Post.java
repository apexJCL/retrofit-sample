package me.apexjcl.retrofitsample.models;

import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * A model for post
 * <p>
 * Created by apex on 02/05/2017.
 */

public class Post extends RealmObject {

    @Index
    private long id;

    private long userId;

    private String title;

    private String body;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
