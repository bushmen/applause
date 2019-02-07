package com.buszko.myapplication;

import com.buszko.myapplication.model.GithubRepoItem;
import com.buszko.myapplication.service.GithubService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockGithubService implements GithubService {
    private final BehaviorDelegate<GithubService> delegate;

    public MockGithubService(BehaviorDelegate<GithubService> service) {
        this.delegate = service;
    }

    @Override
    public Call<List<GithubRepoItem>> getRepos(int page, int pageSize) {
        List<GithubRepoItem> items = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            items.add(new GithubRepoItem(i, "test" + i));
        }
        return delegate.returningResponse(items).getRepos(page, pageSize);
    }
}
