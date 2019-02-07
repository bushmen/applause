package com.buszko.myapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.buszko.myapplication.model.GithubRepoItem;
import com.buszko.myapplication.network.ApplauseClient;
import com.buszko.myapplication.service.GithubService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubViewModel extends ViewModel {
    private static final int PAGE_SIZE = 10;
    private static final int PAGE = 1;

    private GithubService _githubService;
    private MutableLiveData<List<GithubRepoItem>> _items;
    private MutableLiveData<GithubRepoItem> _selectedItem = new MutableLiveData<>();

    public GithubViewModel() {
        super();
    }

    public GithubViewModel(GithubService _githubService) {
        this._githubService = _githubService;
    }

    public LiveData<List<GithubRepoItem>> getItems() {
        if (_items == null) {
            _items = new MutableLiveData<>();
            loadItems();
        }
        return _items;
    }

    public LiveData<GithubRepoItem> getSelectedItem() {
        return _selectedItem;
    }

    public void selectItem(GithubRepoItem item) {
        _selectedItem.postValue(item);
    }

    private void loadItems() {
        if (_githubService == null) {
            _githubService = ApplauseClient.getClient().create(GithubService.class);
        }

        _githubService.getRepos(PAGE, PAGE_SIZE).enqueue(new Callback<List<GithubRepoItem>>() {
            @Override
            public void onResponse(Call<List<GithubRepoItem>> call, Response<List<GithubRepoItem>> response) {
                _items.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<GithubRepoItem>> call, Throwable t) {
                //log error
                t.printStackTrace();
            }
        });
    }
}
