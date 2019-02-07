package com.buszko.myapplication.service;


import com.buszko.myapplication.model.GithubRepoItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {
    @GET("users/applauseoss/repos")
    Call<List<GithubRepoItem>> getRepos(@Query("page") int page, @Query("per_page") int pageSize);
}
