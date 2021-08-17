package com.sa.gograb.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.restaurant.RestaurantListActivity;
import com.sa.gograb.services.model.HomeFilterCategoryModel;
import com.sa.gograb.services.model.HomeSubCategoryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {
    public static final String TAG = "HomeCategoryAdapter";

    private final List<HomeFilterCategoryModel> list;
    private final Activity activity;
    String prepare_time= String.valueOf(10);
    public HomeCategoryAdapter(Activity activity, List<HomeFilterCategoryModel> list) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public HomeCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoryAdapter.ViewHolder holder, int position) {
        final HomeFilterCategoryModel model = list.get(position);


        if (GlobalFunctions.isNotNullValue(model.getTitle())) {
            holder.tv_filter_title.setText(model.getTitle());
        }

        if (GlobalFunctions.isNotNullValue(model.getImage())) {
            Picasso.with(activity).load(model.getImage()).placeholder(R.drawable.image).into(holder.iv_home_filter_category);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getType()!=null && model.getId()!=null){

                    if (model.getType().equalsIgnoreCase("1")){

                        Intent intent = new Intent(activity, RestaurantListActivity.class);
                        activity.startActivity(intent);

                    }else if (model.getType().equalsIgnoreCase("2")){
                        if (model.getPreparation()!=null){
                            Intent intent = RestaurantListActivity.newInstance( activity, prepare_time);
                            activity.startActivity( intent );
                        }

                    }else if (model.getType().equalsIgnoreCase("3")){

                        Intent intent = RestaurantListActivity.newInstance( activity, "1");
                        activity.startActivity( intent );

                    }else if (model.getType().equalsIgnoreCase("4")){
                        Intent intent = RestaurantListActivity.newInstance( activity, "2");
                        activity.startActivity( intent );

                    }else if (model.getType().equalsIgnoreCase("5")){

                        Intent intent = RestaurantListActivity.newInstance( activity, model);
                        activity.startActivity( intent );
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView iv_home_filter_category;
        TextView tv_filter_title;
        LinearLayout ln_category_all;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_home_filter_category=itemView.findViewById(R.id.iv_home_filter_category);
            tv_filter_title = itemView.findViewById(R.id.tv_filter_title);
            ln_category_all = itemView.findViewById(R.id.ln_category_all);


        }
    }
}
