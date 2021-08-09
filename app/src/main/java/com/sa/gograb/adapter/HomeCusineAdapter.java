package com.sa.gograb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.model.CusineModel;

import java.util.List;


public class HomeCusineAdapter extends RecyclerView.Adapter<HomeCusineAdapter.ViewHolder>{

    public static final String TAG = "HomeCusineAdapter";

    private final List<CusineModel> list;
    private final Activity activity;
    public HomeCusineAdapter(Activity activity, List<CusineModel> list) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public HomeCusineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cusines_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCusineAdapter.ViewHolder holder, int position) {
        final CusineModel model = list.get(position);

        if (GlobalFunctions.isNotNullValue(model.getName())) {
            holder.tv_cusine.setText(model.getName());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cusine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_cusine = itemView.findViewById(R.id.tv_cusine);


        }
    }
}
