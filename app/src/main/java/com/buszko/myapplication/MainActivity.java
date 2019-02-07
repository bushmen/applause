package com.buszko.myapplication;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.buszko.myapplication.fragments.GithubItemDetailsFragment;
import com.buszko.myapplication.fragments.GithubItemsListFragment;
import com.buszko.myapplication.viewmodel.GithubViewModel;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new GithubItemsListFragment())
                .commit();

        GithubViewModel viewModel = ViewModelProviders.of(this).get(GithubViewModel.class);
        viewModel.getSelectedItem().observe(this, githubRepoItem -> {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("")
                    .replace(R.id.fragment_container, new GithubItemDetailsFragment())
                    .commit();
        });
    }
}
