package com.user.gograb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.user.gograb.R;
import com.user.gograb.global.GlobalFunctions;
import com.user.gograb.services.model.FilterDistanceModel;
import com.user.gograb.services.model.FilterModel;
import com.user.gograb.services.model.RestaurantModel;

import java.util.List;

public class FilterCheckBoxAdapter extends  RecyclerView.Adapter<FilterCheckBoxAdapter.ViewHolder>{

    public static final String TAG = "FilterAdapter";

    private final List<FilterDistanceModel> list;
    private final Activity activity;
    RestaurantModel restaurantModel;
    FilterModel filterModel;


    public FilterCheckBoxAdapter(Activity activity, List<FilterDistanceModel> list, RestaurantModel restaurantModel,FilterModel filterModel) {
        this.list = list;
        this.activity = activity;
        this.restaurantModel = restaurantModel;
        this.filterModel = filterModel;

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


            if (filterModel.getType().equalsIgnoreCase("0")){

                if (restaurantModel!=null && restaurantModel.getDistance()!=null && restaurantModel.getDistance().equalsIgnoreCase(model.getDistance())){
                    holder.filter_check.setChecked(true);
                }else {
                    holder.filter_check.setChecked(false);
                }

            }else if (filterModel.getType().equalsIgnoreCase("1")){

                if (restaurantModel!=null && restaurantModel.getCuisine_id()!=null && restaurantModel.getCuisine_id().equalsIgnoreCase(model.getId())){
                    holder.filter_check.setChecked(true);
                }else {
                    holder.filter_check.setChecked(false);
                }

            }else if (filterModel.getType().equalsIgnoreCase("2")){

                if (restaurantModel!=null && restaurantModel.getPreparation_time()!=null && restaurantModel.getPreparation_time().equalsIgnoreCase(model.getPreTime())){
                    holder.filter_check.setChecked(true);
                }else {
                    holder.filter_check.setChecked(false);
                }
            }

            holder.filter_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        model.setSelected("1");
                    }else {
                        model.setSelected("0");
                    }
/*
                    if (GlobalFunctions.isNotNullValue(model.getSelected()) && model.getSelected().equalsIgnoreCase("1")) {
                        model.setSelected("0");
                    }else {
                        model.setSelected("1");
                    }
*/
                    //notifyDataSetChanged();
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

