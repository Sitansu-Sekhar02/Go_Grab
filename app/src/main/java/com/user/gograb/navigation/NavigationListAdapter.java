package com.user.gograb.navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.user.gograb.AppController;
import com.user.gograb.R;
import com.user.gograb.global.GlobalFunctions;
import com.user.gograb.global.GlobalVariables;

import java.util.List;


/**
 * Created by Sivasabharish Chinnaswamy on 17-07-2015.
 */
public class NavigationListAdapter  extends ArrayAdapter<NavigationListItem> {

    private final List<NavigationListItem> list;
    private final LayoutInflater layoutInflater;

    GlobalFunctions globalFunctions = AppController.getInstance().getGlobalFunctions();
    GlobalVariables globalVariables =AppController.getInstance().getGlobalVariables();

    public NavigationListAdapter(LayoutInflater layoutInflater, List<NavigationListItem> list) {
        super(layoutInflater.getContext(), R.layout.navigation_item_row, list);
        this.layoutInflater = layoutInflater;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView text;
        protected ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.navigation_item_row, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.navigation_rowText);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.navigation_rowIcon);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(list.get(position).getTitle());
        holder.imageView.setImageResource(list.get(position).getImageID());
        if(globalFunctions.getNavigationSelectedPosition()==position+1){
            //holder.imageView.setImageDrawable(null);
            if((position-1)!=GlobalVariables.TYPE_SHARE&&(position-1)!=GlobalVariables.TYPE_RATE&&(position-1)!=GlobalVariables.TYPE_VENDOR_SHARE&&(position-1)!=GlobalVariables.TYPE_VENDOR_RATE){
//                holder.text.setTextColor(getContext().getResources().getColor(R.color.navigation_selected_color));
            }
            /*int imageID = 0;
            if(position==globalVariables.TYPE_HOME_MENU){imageID = R.drawable.ic_navigation_home;}
            else if(position==globalVariables.TYPE_FAVOURITES){imageID = R.drawable.ic_navigation_favourites;}
            else if(position==globalVariables.TYPE_RATEUS){imageID = R.drawable.ic_navigation_rate_us;}
            else if(position==globalVariables.TYPE_SHARE){imageID = R.drawable.ic_navigation_share;}
            else if(position==globalVariables.TYPE_ABOUT_SOL){imageID = R.drawable.ic_about_sol;}
            holder.imageView.setImageResource(imageID);*/
        }else{
//                holder.text.setTextColor(getContext().getResources().getColor(R.color.app_fontColor));

        }
        return view;
    }
}
