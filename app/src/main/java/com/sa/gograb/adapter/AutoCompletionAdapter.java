package com.sa.gograb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sa.gograb.R;
import com.sa.gograb.search.SearchActivity;
import com.sa.gograb.services.model.SearchResponseModel;

import java.util.ArrayList;


public class AutoCompletionAdapter extends RecyclerView.Adapter<AutoCompletionAdapter.ViewHolder> {
    private final String MY_DEBUG_TAG = "AutoCompletionAdapter";
    private ArrayList<SearchResponseModel> items;
    private ArrayList<SearchResponseModel> itemsAll;
    private ArrayList<SearchResponseModel> suggestions;
    private View mainView;
    Context context;

    public AutoCompletionAdapter(Context context, ArrayList<SearchResponseModel> items) {
        this.context = context;
        this.items = items;
        this.itemsAll = (ArrayList<SearchResponseModel>) items.clone();
        this.suggestions = new ArrayList<SearchResponseModel>();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.autocomplete_row_item, null);
        }
        SearchResponseModel keyValue = items.get(position);
        if (keyValue != null) {
            TextView customerNameLabel = (TextView) v.findViewById(R.id.autoCompleteLabel);
           // TextView customerNameLabelSubtitle = (TextView) v.findViewById(R.id.autoCompleteLabel_subtitle);
            if (customerNameLabel != null) {

                if (keyValue.getName() != null) {
                    customerNameLabel.setText(keyValue.getName());
                }
            }
        }
        return v;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView customerNameLabel, customerNameLabelSubtitle;

        public ViewHolder(View view) {
            super(view);
            customerNameLabel = (TextView) view.findViewById(R.id.autoCompleteLabel);
            //customerNameLabelSubtitle = (TextView) view.findViewById(R.id.autoCompleteLabel_subtitle);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.autocomplete_row_item, parent, false);
        mainView = itemView;
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SearchResponseModel item = items.get(position);
        if (item != null) {

            if (item.getName() != null) {
                holder.customerNameLabel.setText(item.getName());
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((SearchActivity) context).setResult(true, item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}