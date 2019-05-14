package id.ac.umn.kevin;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GitHubService {
        @GET("/search/repositories?q=tetris+stars:>100&sort=stars&order=desc")
        Call<GitHubRepository> getAllRepo();
}
