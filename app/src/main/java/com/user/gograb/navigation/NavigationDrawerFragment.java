package com.user.gograb.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.user.gograb.AppController;
import com.user.gograb.R;
import com.user.gograb.global.GlobalFunctions;
import com.user.gograb.global.GlobalVariables;
import com.user.gograb.services.model.ProfileModel;

import java.util.ArrayList;
import java.util.List;


public class NavigationDrawerFragment extends Fragment {

    static String TAG = "NavigationDrawerFragment";

    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private NavigationDrawerCallbacks mCallbacks;

    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolBar;

    /*private static DatabaseHelper dbhelper;*/
    public static TextView login_tv, header_name_tv, header_email_tv, header_support_tv;
    public static ImageView profile_photo_iv;
    static public DrawerLayout mDrawerLayout;
    private static ListView mDrawerListView;
    private View mFragmentContainerView;

    private static Activity activity;

    private static int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    static private boolean extraMenu = false;

    static private FragmentManager fragmentManager;
    public static Context navigationContext = null;

    public static List<NavigationListItem> navigationList = new ArrayList<NavigationListItem>();
    static NavigationListAdapter navigationListAdapter;

    GlobalFunctions globalFunctions = AppController.getInstance().getGlobalFunctions();
    GlobalVariables globalVariables =AppController.getInstance().getGlobalVariables();

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationContext = getActivity();
        activity = getActivity();
        fragmentManager = getFragmentManager();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(navigationContext);
        /*dbhelper= new DatabaseHelper(navigationContext);*/
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);
        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
        selectItem(mCurrentSelectedPosition);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDrawerListView = (ListView) inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);

        //mDrawerListView.setPadding(0,globalFunctions.getStatusBarHeight(getContext()),0,0);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
       // mToolBar = (Toolbar) inflater.inflate(R.layout.tool_bar,null,false);
        addListView(mDrawerListView, inflater);

        return mDrawerListView;
    }

    private static ListView addListView(ListView listView, final LayoutInflater inflater){

        refreshNavigationMainList();

        navigationListAdapter = new NavigationListAdapter(inflater,navigationList);

       // final View headerView = inflater.inflate(R.layout.nav_header_main1, null, false);

      //  profile_photo_iv = (ImageView) headerView.findViewById(R.id.nav_profile_image);
      //  header_name_tv = (TextView) headerView.findViewById(R.id.tv_nav_fullname);
        //  header_email_tv = (TextView) headerView.findViewById(R.id.navigation_header_mail_id);
        // header_support_tv = (TextView) headerView.findViewById(R.id.navigation_header_support_tv);

        //navigationHeaderRefresh(GlobalFunctions.getProfile(navigationContext));
/*

        header_support_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                //GlobalFunctions.callPhone(activity,activity.getString(R.string.contactNum));

            }
        });
*/

        profile_photo_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                /*if(GlobalFunctions.isLoggedIn(navigationContext)){
                    MainActivity.replaceFragment(new ProfileFragment(), ProfileFragment.TAG, navigationContext.getString(R.string.menu_profile), 0, 0);
                }else{
                    Intent intent = new Intent(navigationContext, LoginActivity.class);
                    Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(navigationContext, R.anim.zoom_in, R.anim.zoom_out).toBundle();
                    navigationContext.startActivity(intent,bundle);
                }*/
            }
        });

       /* if(headerView != null){
            listView.addHeaderView(headerView, null, false);
        }*/
        View footerView = null;

      /*  footerView = inflater.inflate(R.layout.navigation_footer,null,false);
        TextView footer_aboutUs = (TextView) footerView.findViewById(R.id.navigation_footer_about_us);
        TextView footer_terms = (TextView) footerView.findViewById(R.id.navigation_footer_terms);
        TextView footer_privacy = (TextView) footerView.findViewById(R.id.navigation_footer_privacy);*/

     /*   footer_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Fragment aboutFragment = AboutMainFragment.newInstance(GlobalVariables.WEBVIEW_TYPE.TERMS);
                MainActivity.replaceFragment(aboutFragment, AboutMainFragment.TAG, navigationContext.getString(R.string.terms), 0, 0);*//*
                closeDrawer();
            }
        });*/


      /*  footer_aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Fragment aboutFragment = AboutMainFragment.newInstance(GlobalVariables.WEBVIEW_TYPE.ABOUT_US);
                MainActivity.replaceFragment(aboutFragment, AboutMainFragment.TAG, navigationContext.getString(R.string.about_us), 0, 0);*//*
                closeDrawer();
            }
        });*/

    /*    footer_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*Fragment aboutFragment = AboutMainFragment.newInstance(GlobalVariables.WEBVIEW_TYPE.PRIVACY);
                MainActivity.replaceFragment(aboutFragment, AboutMainFragment.TAG, navigationContext.getString(R.string.privacy), 0, 0);*//*
                closeDrawer();
            }
        });*/

        if(footerView!=null){listView.addFooterView(footerView,null,false);}
        listView.setAdapter(navigationListAdapter);
        listView.setItemChecked(mCurrentSelectedPosition, true);

        return listView;
    }

    public static boolean isExtraMenu() {
        return extraMenu;
    }

    public static void setExtraMenu(boolean extra_menu) {
        extraMenu = extra_menu;
    }

    public void setPadding(int left, int top, int right, int bottom){
        mDrawerListView.setPadding(left, top, right, bottom);
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    public static void closeDrawer(){
        mDrawerLayout.closeDrawers();
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),
                mDrawerLayout,
                mToolBar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }

            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
            globalFunctions.setNavigationSelectedPosition(position);
            setItemChecked(position);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }


    @Override
    public void onResume() {
       // navigationHeaderRefresh(GlobalFunctions.getProfile(navigationContext));
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (mDrawerLayout != null && isDrawerOpen()) {
            //inflater.inflate(R.menu.menu_main, menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setItemChecked(int position){
        refreshNavigationMainList();
    }

    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    public static interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(int position);
    }

    private static NavigationListItem getNavigationListObject(String categoryID, String title,int imageID) {
        return new NavigationListItem(categoryID, title, imageID);
    }

    public static void refreshNavigationMainList(){
        navigationList.clear();
        if(!isExtraMenu()) {
            /*navigationList.add(new NavigationListItem(0 + "", navigationContext.getString(R.string.home), R.drawable.ic_home));
            if (GlobalFunctions.getUserType(navigationContext).equals(GlobalVariables.USER_TYPE.USER)) {
                navigationList.add(new NavigationListItem(0 + "", navigationContext.getString(R.string.my_quotations), R.drawable.ic_my_quotation));
            }
            navigationList.add(new NavigationListItem(0 + "", navigationContext.getString(R.string.my_orders), R.drawable.ic_my_orders));
            navigationList.add(new NavigationListItem(0 + "", navigationContext.getString(R.string.notifications), R.drawable.ic_notification_filled));
            if (GlobalFunctions.isLoggedIn(navigationContext)) {
                navigationList.add(new NavigationListItem(0 + "", navigationContext.getString(R.string.menu_profile), R.drawable.ic_profile));
            }
            navigationList.add(new NavigationListItem(0 + "", navigationContext.getString(R.string.share), R.drawable.ic_share_filled));
            navigationList.add(new NavigationListItem(0 + "", navigationContext.getString(R.string.rateUs), R.drawable.ic_rate_filled));*/
            if (navigationListAdapter != null) {
                synchronized (navigationListAdapter) {
                    navigationListAdapter.notifyDataSetChanged();
                }
            }
        }

    }

    public static void navigationHeaderRefresh(ProfileModel profileModel){
        if(profileModel!=null&&navigationContext!=null){
            if(header_name_tv!=null){header_name_tv.setText(profileModel.getFullname());}
            // if(header_email_tv!=null){header_email_tv.setText(profileModel.getEmail());}
            /*if(profileModel.getImage()!=null){
                if(profileModel.getImage()!=null&&!profileModel.getImage().equalsIgnoreCase("")){
                    Picasso.with(navigationContext).load(profileModel.getImage()).fit().centerCrop()
                            .into(profile_photo_iv);
                }
            }*/
        }else{
            if(navigationContext!=null){
                if(header_name_tv!=null){header_name_tv.setText(navigationContext.getString(R.string.guest));}
                //  if(header_email_tv!=null){header_email_tv.setText("");}
                /*if(profile_photo_iv!=null){profile_photo_iv.setImageResource(R.drawable.app_icon_large_new);}*/
            }
        }

    }

    public static NavigationListItem getSelectedItem(int position){
        if(navigationList.size()>=(position-1)&&navigationList.size()>0&&(position-1)>=0)
            return navigationList.get(position-1);
        return null;
    }
}
