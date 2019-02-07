package com.buszko.myapplication.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.buszko.myapplication.R;
import com.buszko.myapplication.adapter.GithubAdapter;
import com.buszko.myapplication.model.GithubRepoItem;
import com.buszko.myapplication.viewmodel.GithubViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GithubItemsListFragment extends Fragment implements SearchView.OnQueryTextListener, GithubAdapter.Listener {
    private GithubViewModel _viewModel;

    private List<GithubRepoItem> _items;

    private RecyclerView _recyclerView;
    private GithubAdapter _adapter;

    private static final Comparator<GithubRepoItem> ALPHABETICAL_COMPARATOR = (a, b) -> a.getName().compareTo(b.getName());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.github_items_list_fragment, container, false);
        _recyclerView = view.findViewById(R.id.recycler_view);
        _recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));


        _viewModel.getItems().observe(this, githubRepoItems ->
        {
            _adapter = new GithubAdapter(githubRepoItems, ALPHABETICAL_COMPARATOR, this);
            _recyclerView.setAdapter(_adapter);
            _items = githubRepoItems;
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _viewModel = ViewModelProviders.of(getActivity()).get(GithubViewModel.class);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextChange(String query) {
        // Here is where we are going to implement the filter logic
        _adapter.replaceAll(filter(_items, query));
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<GithubRepoItem> filter(List<GithubRepoItem> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<GithubRepoItem> filteredModelList = new ArrayList<>();
        for (GithubRepoItem model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public void onItemClick(GithubRepoItem item) {
        _viewModel.selectItem(item);
    }
}
