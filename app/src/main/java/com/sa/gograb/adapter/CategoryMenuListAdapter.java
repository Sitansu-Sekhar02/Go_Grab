package com.sa.gograb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.AppController;
import com.sa.gograb.R;
import com.sa.gograb.adapter.interfaces.CartClickListener;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.model.CategoryMenuModel;
import com.sa.gograb.services.model.MenuCatModel;
import com.sa.gograb.services.model.TrendingMenuModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryMenuListAdapter extends RecyclerView.Adapter<CategoryMenuListAdapter.ViewHolder>   {

    public static final String TAG = "CategoryMenuListAdapter";

    private final List<CategoryMenuModel> list;
    private final Activity activity;
    GlobalFunctions globalFunctions;
    CategoryMenuSubListAdapter adapter;
    CartClickListener listener;

    public CategoryMenuListAdapter(Activity activity, List<CategoryMenuModel> list,CartClickListener listener) {
        this.list = list;
        this.activity = activity;
        this.listener = listener;
        globalFunctions = AppController.getInstance().getGlobalFunctions();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_menu_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CategoryMenuModel model = list.get(position);

        if (GlobalFunctions.isNotNullValue(model.getName())) {
            holder.tv_category_menu.setText(model.getName());
        }

        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false);
        List<MenuCatModel> productList = new ArrayList<MenuCatModel>();

        if (model.getMenuCatListModel() != null && model.getMenuCatListModel().getMenuCatModels().size() > 0) {
            productList.clear();
            productList.addAll(model.getMenuCatListModel().getMenuCatModels());
        }

        if (productList.size() > 0) {
            adapter = new CategoryMenuSubListAdapter(activity, productList,listener);
            holder.recycler_sub_menu_item.setLayoutManager(layoutManager);
            holder.recycler_sub_menu_item.setAdapter(adapter);

            if (model.getSelected() != null && model.getSelected().equalsIgnoreCase("1")) {
                holder.recycler_sub_menu_item.setVisibility(View.VISIBLE);
                holder.iv_category_dropDown.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_drop_down));
            }else {
                holder.recycler_sub_menu_item.setVisibility(View.GONE);
                holder.iv_category_dropDown.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_drop_up));
            }


//            holder.recycler_sub_menu_item.setVisibility(View.VISIBLE);
        } else {
            holder.recycler_sub_menu_item.setVisibility(View.GONE);
        }

        holder.rl_menu_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (model.getSelected() != null && model.getSelected().equalsIgnoreCase("1")) {
                    model.setSelected("0");
                }else {
                    model.setSelected("1");
                }

                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView product_iv,minus_iv,add_iv,iv_menu_food_type,iv_category_dropDown;
        TextView tv_category_name,item_title_tv,unit_price_tv,product_description_tv,tv_menu_type,tv_category_menu;
        RecyclerView recycler_sub_menu_item;
        RelativeLayout rl_menu_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            product_iv = itemView.findViewById(R.id.product_iv);
            minus_iv = itemView.findViewById(R.id.minus_iv);
            add_iv = itemView.findViewById(R.id.add_iv);
            iv_menu_food_type = itemView.findViewById(R.id.iv_menu_food_type);
            iv_category_dropDown = itemView.findViewById(R.id.iv_category_dropDown);

            item_title_tv = itemView.findViewById(R.id.item_title_tv);
            tv_category_menu = itemView.findViewById(R.id.tv_category_menu);
            unit_price_tv = itemView.findViewById(R.id.unit_price_tv);
            product_description_tv = itemView.findViewById(R.id.product_description_tv);
            tv_menu_type = itemView.findViewById(R.id.tv_menu_type);
            recycler_sub_menu_item = itemView.findViewById(R.id.recycler_sub_menu_item);
            rl_menu_title = itemView.findViewById(R.id.rl_menu_title);

        }
    }
}
