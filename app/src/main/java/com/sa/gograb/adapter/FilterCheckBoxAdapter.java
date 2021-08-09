package com.sa.gograb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.model.FilterDistanceModel;
import com.sa.gograb.services.model.FilterModel;

import java.util.List;

public class FilterCheckBoxAdapter extends  RecyclerView.Adapter<FilterCheckBoxAdapter.ViewHolder>{

    public static final String TAG = "FilterAdapter";

    private final List<FilterDistanceModel> list;
    private final Activity activity;


    public FilterCheckBoxAdapter(Activity activity, List<FilterDistanceModel> list) {
        this.list = list;
        this.activity = activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder instanceof FilterCheckBoxAdapter.ViewHolder) {

            final FilterCheckBoxAdapter.ViewHolder holders = (FilterCheckBoxAdapter.ViewHolder) holder;
            final FilterDistanceModel model = list.get(position);

            if (GlobalFunctions.isNotNullValue(model.getName())) {
                holder.filter_check.setText(model.getName());
            }else if (GlobalFunctions.isNotNullValue(model.getDistance())) {
                holder.filter_check.setText(model.getDistance());
            }else if (GlobalFunctions.isNotNullValue(model.getPreTime())) {
                holder.filter_check.setText(model.getPreTime());
            }

            holder.filter_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        model.setSelected("1");
                    }else {
                        model.setSelected("0");
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox filter_check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            filter_check=itemView.findViewById(R.id.filter_check);
        }
    }
}
