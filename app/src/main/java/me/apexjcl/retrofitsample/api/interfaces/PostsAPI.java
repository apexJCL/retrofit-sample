package me.apexjcl.retrofitsample.api.interfaces;

import java.util.ArrayList;

import me.apexjcl.retrofitsample.models.Comment;
import me.apexjcl.retrofitsample.models.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface that defines the Post API
 * for https://jsonplaceholder.typicode.com/
 *
 * Created by apex on 02/05/2017.
 */

public interface PostsAPI {

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int id);

    @GET("posts")
    Call<ArrayList<Post>> getPosts();

    @GET("posts/{id}/comments")
    Call<ArrayList<Comment>> getComments(@Path("id") int postId);

}
