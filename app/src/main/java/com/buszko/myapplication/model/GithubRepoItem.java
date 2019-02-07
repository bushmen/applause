package com.buszko.myapplication.model;

public class GithubRepoItem {
    private long id;
    private String name;

    public GithubRepoItem(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
