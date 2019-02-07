package com.buszko.myapplication.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buszko.myapplication.R;
import com.buszko.myapplication.viewmodel.GithubViewModel;

public class GithubItemDetailsFragment extends Fragment {

    private GithubViewModel _viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.github_item_details_fragment, container, false);

        TextView text = view.findViewById(R.id.details_text_view);
        text.setText(_viewModel.getSelectedItem().getValue().getName());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _viewModel = ViewModelProviders.of(getActivity()).get(GithubViewModel.class);
    }
}
