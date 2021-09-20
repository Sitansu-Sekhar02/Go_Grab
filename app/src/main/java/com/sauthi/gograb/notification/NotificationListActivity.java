package com.sauthi.gograb.notification;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.sauthi.gograb.global.GlobalFunctions;
import com.sauthi.gograb.services.model.NotificationListModel;
import com.sauthi.gograb.services.model.NotificationModel;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.ArrayList;
import java.util.List;


public class NotificationListActivity extends AppCompatActivity {

    public static final String TAG = "NotificationListAct";

    Context context = null;
    static Activity activity = null;
    static Window window = null;

    static Toolbar toolbar;
    static ActionBar actionBar;
    static String mTitle;
    static int mResourceID, titleResourseID;
    static TextView toolbar_title;

    ListView listView;
    View mainView;

    NotificationListAdapter adapter;

    ProgressLinearLayout progressLinearLayout;

    NotificationListModel details = new NotificationListModel();

    List<NotificationModel> list = new ArrayList<NotificationModel>();

    /*public static Fragment newInstance(ProductListModel productListModel){
        Fragment fragment = new OrdersListFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_KEY_PRODUCT_LIST_FRAGMENT_PRODUCT_LIST_MODEL, productListModel);
        fragment.setArguments(args);
        return fragment;
    }
*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.notification_list_fragment);

        context = this;
        activity = this;
        window = getWindow();


//        progressLinearLayout = (ProgressLinearLayout) findViewById(R.id.details_progressActivity);
//        listView = (ListView) findViewById(R.id.orders_list_fragment_listView);
//        list = details.getNotificationList();
//        adapter = new NotificationListAdapter(activity, list);
//        listView.setAdapter(adapter);
//        mainView = listView;
//        loadList(context);


//        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
//        toolbar.setPadding(0, GlobalFunctions.getStatusBarHeight(context), 0, 0);
//        toolbar.setNavigationIcon(R.drawable.ic_back_dark);
//        toolbar.setContentInsetsAbsolute(0, 0);
//        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
//
//        setSupportActionBar(toolbar);
//        actionBar = getSupportActionBar();
        /*actionBar.setShowHideAnimationEnabled(true);*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //enable translucent statusbar via flags
            GlobalFunctions.setTranslucentStatusFlag(window, true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //we don't need the translucent flag this is handled by the theme
            GlobalFunctions.setTranslucentStatusFlag(window, true);
            //set the statusbarcolor transparent to remove the black shadow
            this.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //setTitle(getString(R.string.notifications), 0, 0);

        GlobalFunctions.setNotification(context, false);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = OrderDetailActivity.newInstance(context,details.getNotificationList().get(position));
                startActivity(intent);*/

               /* Intent intent = GlobalFunctions.getNotificationIntent(activity, list.get(position).getResponse());
                activity.startActivity(intent);*/
            }
        });

    }

    public static void setTitle(String title, int titleImageID, int backgroundResourceID) {
        mTitle = title;
        if (backgroundResourceID != 0) {
            mResourceID = backgroundResourceID;
        } else {
            mResourceID = 0;
        }
        if (titleImageID != 0) {
            titleResourseID = titleImageID;
        } else {
            titleResourseID = 0;
        }
        restoreToolbar();
    }

    private static void restoreToolbar() {
        //toolbar = (Toolbar) findViewById(R.id.tool_bar);
        Log.d(TAG, "Restore Tool Bar");
        if (actionBar != null) {
            Log.d(TAG, "Restore Action Bar not Null");
            Log.d(TAG, "Title : " + mTitle);
            toolbar_title.setText(mTitle);
            if (mResourceID != 0) toolbar.setBackgroundResource(mResourceID);
            //actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void setUpList(NotificationListModel orderListModel) {
        if (orderListModel != null && list != null) {
            details = orderListModel;
            list.clear();
            list.addAll(details.getNotificationList());
            if (adapter != null) {
                synchronized (adapter) {
                    adapter.notifyDataSetChanged();
                }
            }
            if (list.size() > 0) {
                showContent();
            } else {
                //showEmptyPage();
            }
        }
    }

    private void loadList(final Context context) {
        showLoading();
        /*ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getNotification(context, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                if (!isFinishing()) {
                    showContent();
                    Log.d(TAG, "Response : " + arg0.toString());
                    NotificationListModel model = (NotificationListModel) arg0;
                    setUpList(model);
                }
            }

            @Override
            public void OnFailureFromServer(String msg) {
                if (!isFinishing()) {
                    showErrorPage();
                    GlobalFunctions.displayMessaage(context, mainView, msg);
                    Log.d(TAG, "Failure : " + msg);
                }
            }

            @Override
            public void OnError(String msg) {
                if (!isFinishing()) {
                    showErrorPage();
                    GlobalFunctions.displayMessaage(context, mainView, msg);
                    Log.d(TAG, "Error : " + msg);
                }
            }
        }, "NotificationList");*/
    }

    private void showLoading() {
        if (progressLinearLayout != null) {
            progressLinearLayout.showLoading();
        }
    }

    private void showContent() {
        if (progressLinearLayout != null) {
            progressLinearLayout.showContent();
        }
    }

    /*private void showErrorPage() {
        if (progressLinearLayout != null) {
            progressLinearLayout.showError(getResources().getDrawable(R.drawable.rezq_logo), getString(R.string.noConnection),
                    getString(R.string.noConnectionErrorMessage),
                    getString(R.string.tryAgain), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadList(context);
                        }
                    });
        }
    }

    private void showEmptyPage() {
        if (progressLinearLayout != null) {
            progressLinearLayout.showEmpty(getResources().getDrawable(R.drawable.app_icon), getString(R.string.emptyList),
                    getString(R.string.emptyListMessage));
        }
    }*/

    private void refreshList() {
        if (!isFinishing()) {
            if (list != null && listView != null && adapter != null) {
                synchronized (adapter) {
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_nothing, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
       // inflater.inflate(R.menu.menu_nothing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                closeThisActivity();
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        closeThisActivity();
        super.onBackPressed();
    }

    public static void closeThisActivity() {
        if (activity != null) {
            activity.finish();
            //activity.overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
        }
    }
}
