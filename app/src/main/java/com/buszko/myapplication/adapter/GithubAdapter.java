package com.buszko.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buszko.myapplication.R;
import com.buszko.myapplication.model.GithubRepoItem;

import java.util.Comparator;
import java.util.List;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.GithubItemViewHolder> {
    private final Comparator<GithubRepoItem> _comparator;
    private final Listener _listener;

    public GithubAdapter(List<GithubRepoItem> items, Comparator<GithubRepoItem> comparator, Listener listener) {
        _comparator = comparator;
        _listener = listener;
        _sortedList.addAll(items);
    }

    @NonNull
    @Override
    public GithubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.github_item, viewGroup, false);
        return new GithubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubItemViewHolder githubItemViewHolder, int i) {
        GithubRepoItem item = _sortedList.get(i);
        githubItemViewHolder.nameTextView.setText(item.getName());
        githubItemViewHolder.itemView.setOnClickListener(view -> _listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return _sortedList.size();
    }

    static class GithubItemViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;

        public GithubItemViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.item_name);
        }
    }

    private final SortedList<GithubRepoItem> _sortedList = new SortedList<>(GithubRepoItem.class, new SortedList.Callback<GithubRepoItem>() {
        @Override
        public int compare(GithubRepoItem a, GithubRepoItem b) {
            return _comparator.compare(a, b);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(GithubRepoItem oldItem, GithubRepoItem newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areItemsTheSame(GithubRepoItem item1, GithubRepoItem item2) {
            return item1.getId() == item2.getId();
        }
    });

    public void replaceAll(List<GithubRepoItem> models) {
        _sortedList.beginBatchedUpdates();
        for (int i = _sortedList.size() - 1; i >= 0; i--) {
            final GithubRepoItem model = _sortedList.get(i);
            if (!models.contains(model)) {
                _sortedList.remove(model);
            }
        }
        _sortedList.addAll(models);
        _sortedList.endBatchedUpdates();
    }

    public interface Listener {
        void onItemClick(GithubRepoItem item);
    }
}
