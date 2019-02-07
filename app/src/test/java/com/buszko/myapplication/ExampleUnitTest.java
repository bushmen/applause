package com.buszko.myapplication;

import com.buszko.myapplication.model.GithubRepoItem;
import com.buszko.myapplication.service.GithubService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class ExampleUnitTest {
    private MockRetrofit mockRetrofit;
    private Retrofit retrofit;

    @Before
    public void setup() {
        retrofit = new Retrofit.Builder().baseUrl("http://test.com")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

    @Test
    public void test_if_return_10_elements_on_page() throws Exception{
        BehaviorDelegate<GithubService> delegate = mockRetrofit.create(GithubService.class);
        GithubService githubService = new MockGithubService(delegate);

        Call<List<GithubRepoItem>> call = githubService.getRepos(1, 10);
        Response<List<GithubRepoItem>> response = call.execute();

        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(10, response.body().size());
    }
}