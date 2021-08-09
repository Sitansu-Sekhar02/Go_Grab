package com.sa.gograb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.R;

import java.util.List;


public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder>{

    public static final String TAG = "CategoryListAdapter";

    private final List<String> list;
    private final Activity activity;
    public CategoryListAdapter(Activity activity, List<String> list) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_sub_category_list_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, int position) {
        String animal = list.get(position);
        holder.myTextView.setText(animal);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tv_cusine_one);


        }
    }
}
