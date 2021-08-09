package com.sa.gograb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.R;
import com.sa.gograb.adapter.interfaces.OnFilterItemClickInvoke;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.model.FilterModel;

import java.util.List;

public class FilterTitleAdapter extends  RecyclerView.Adapter<FilterTitleAdapter.ViewHolder>{

    public static final String TAG = "FilterAdapter";

    private final List<FilterModel> list;
    private final Activity activity;
    OnFilterItemClickInvoke onFilterItemClickInvoke;
    int selectedItem=-1;
    int selectedPos=0;


    public FilterTitleAdapter(Activity activity, List<FilterModel> list,OnFilterItemClickInvoke onFilterItemClickInvoke) {
            this.list = list;
        this.activity = activity;
        this.onFilterItemClickInvoke = onFilterItemClickInvoke;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_title_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder instanceof FilterTitleAdapter.ViewHolder) {

            final FilterTitleAdapter.ViewHolder holders = (FilterTitleAdapter.ViewHolder) holder;
            final FilterModel model = list.get(position);

            if (GlobalFunctions.isNotNullValue(model.getTitle())) {
                holder.tv_filter_title.setText(model.getTitle());
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.setType(position+"");
                    selectedPos=position;
                    onFilterItemClickInvoke.OnFilterItemClickInvoke(model);
                    notifyDataSetChanged();
                }
            });

            if (selectedItem==-1){
                model.setType(position+"");
                onFilterItemClickInvoke.OnFilterItemClickInvoke(model);
                selectedItem=position;
                selectedPos=position;
            }

            if (selectedPos==position){
                holder.tv_filter_title.setTextColor(activity.getResources().getColor(R.color.red));

            }else {
                holder.tv_filter_title.setTextColor(activity.getResources().getColor(R.color.semi_color));

            }

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_filter_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_filter_title=itemView.findViewById(R.id.tv_filter_title);
        }
    }
}
