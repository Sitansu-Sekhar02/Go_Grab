package com.user.gograb.notification;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.user.gograb.R;
import com.user.gograb.services.model.NotificationModel;

import java.util.List;


public class NotificationListAdapter extends ArrayAdapter<NotificationModel> {

    public static final String TAG = "NotificationListAdapter";

    private final List<NotificationModel> list;
    private final LayoutInflater layoutInflater;
    private final Activity activity;

    private static View mainView;


    public NotificationListAdapter(Activity activity, List<NotificationModel> list) {
        super(activity, R.layout.notification_list_row_item, list);
        this.activity=activity;
        this.layoutInflater = activity.getLayoutInflater();
        this.list = list;
    }

    static class ViewHolder {
        protected TextView name_tv, message_tv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        NotificationModel model = list.get(position);
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.notification_list_row_item, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.name_tv = (TextView) view.findViewById(R.id.orders_list_row_item_title_tv);
            viewHolder.message_tv = (TextView) view.findViewById(R.id.orders_list_row_item_message_tv);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }
        mainView = view;
        final ViewHolder holder = (ViewHolder) view.getTag();
        final NotificationModel item = model;
        holder.name_tv.setText(item.getTitle());
        holder.message_tv.setText(item.getMessage());
        return view;
    }
}