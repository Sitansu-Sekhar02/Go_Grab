package com.sa.gograb.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sa.gograb.AppController;
import com.sa.gograb.R;
import com.sa.gograb.adapter.AutoCompletionAdapter;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.restaurant.RestaurantListActivity;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.RestaurantModel;
import com.sa.gograb.services.model.SearchMainModel;
import com.sa.gograb.services.model.SearchModel;
import com.sa.gograb.services.model.SearchResponseListModel;
import com.sa.gograb.services.model.SearchResponseModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    public static final String TAG = "SearchActivity";
    public static final String
            BUNDLE_SEARCH_RESPONSE_MODEL = "Bundle_Search_Response_Model",
            BUNDLE_SEARCH_TYPE = "BundleSearchType";

    Context context;
    static Activity activity;
    View mainView;
    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;
    EditText searchBox_actv;
    ArrayList<SearchResponseModel> autoSuggesstionsList = new ArrayList<SearchResponseModel>();
    AutoCompletionAdapter autoCompletionAdapter;

    ProgressBar searchLoader;
    ImageView backButton;
    ImageButton searchButton;
    TextView no_result_tv;
    LinearLayout noResult_ll;

    String phrase_display = null;
   /* Toolbar toolbar;
    ActionBar actionBar;*/
   String type = "1";


    GlobalFunctions globalFunctions;
    GlobalVariables globalVariables;

    public static Intent newInstance(Activity activity, String type) {
        Intent intent = new Intent(activity, SearchActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_SEARCH_TYPE, type);
        intent.putExtras(bundle);
        return intent;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        context = this;
        activity = this;

        globalFunctions = AppController.getInstance().getGlobalFunctions();
        globalVariables = AppController.getInstance().getGlobalVariables();

        recyclerView = findViewById(R.id.recyclerview);
        searchBox_actv = findViewById(R.id.search_fragment_etv);
        searchLoader = (ProgressBar) findViewById(R.id.search_fragment_progressBar);
        backButton = (ImageView) findViewById(R.id.search_fragment_back);
        noResult_ll = (LinearLayout) findViewById(R.id.search_fragment_no_result_ll);
        no_result_tv = (TextView) findViewById(R.id.search_fragment_no_result_tv);
        searchButton = (ImageButton) findViewById(R.id.search_fragment_search_button);

        mainView = searchBox_actv;

        searchBox_actv.setFocusable(true);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.black_trans));
        }

        if (getIntent().hasExtra(BUNDLE_SEARCH_TYPE)) {

            type = (String) getIntent().getStringExtra(BUNDLE_SEARCH_TYPE);
        } else {
            type =null;
        }


        layoutManager = new LinearLayoutManager(context);
        autoCompletionAdapter = new AutoCompletionAdapter(context, autoSuggesstionsList);
        //searchBox_actv.setAdapter(autoCompletionAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(autoCompletionAdapter);

        searchBox_actv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setNoResultView(1);
                if (s.length() >= 2) {
                    SearchModel model = new SearchModel();
                    phrase_display = s.toString().trim();
                    // model.setTitle(s.toString().trim());
                    model.setSearch(s.toString().trim());
                    if (GlobalFunctions.isNotNullValue(type)){
                        model.setType(type);
                    }
                    loadSearch(context, model);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = searchBox_actv.getText().toString().trim();
                globalFunctions.closeKeyboard(activity);
                if (!txt.isEmpty()) {
                    SearchModel model = new SearchModel();
                    model.setSearch(txt);
                    if (GlobalFunctions.isNotNullValue(type)){
                        model.setType(type);
                    }
                    loadSearch(context, model);
                } else {
                    globalFunctions.displayMessaage(activity, mainView, getString(R.string.search_phrase_missing));
                }
            }
        });

        searchBox_actv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                setNoResultView(1);
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_SEARCH) {
                    globalFunctions.closeKeyboard(activity);
                    SearchModel model = new SearchModel();
                    phrase_display = searchBox_actv.getText().toString().trim();
                    model.setSearch(searchBox_actv.getText().toString().trim());
                    if (GlobalFunctions.isNotNullValue(type)){
                        model.setType(type);
                    }
                    loadSearch(context, model);
                    return true;
                } else {
                    return false;
                }
            }
        });
        


    }

    public void setNoResultView(int count) {
        if (phrase_display != null) {
            String message = "\"" + phrase_display + "\"";
            no_result_tv.setText(message);
            noResult_ll.setVisibility(count <= 0 ? View.VISIBLE : View.GONE);
        } else {
            noResult_ll.setVisibility(View.GONE);
        }

    }

    private void setAutoSuggestions(SearchResponseListModel listModels) {
        if (context != null && autoSuggesstionsList != null && listModels != null && searchBox_actv != null) {
            autoSuggesstionsList.clear();
            autoSuggesstionsList.addAll(listModels.getSearchResponseList());
            synchronized (autoCompletionAdapter) {
                autoCompletionAdapter.notifyDataSetChanged();
            }
            //  if(searchBox_actv!=null&&!isFinishing())searchBox_actv.showDropDown();
            setNoResultView(autoSuggesstionsList.size());
        }
    }

    private void showSearchLoading() {
        if (searchLoader != null) {
            searchLoader.setVisibility(View.VISIBLE);
        }
    }

    private void hideSearchLoading() {
        if (searchLoader != null) {
            searchLoader.setVisibility(View.GONE);
        }
    }

    private boolean isSearchLoading() {
        if (searchLoader != null) {
            if (searchLoader.getVisibility() == View.VISIBLE) {
                return true;
            }
        }
        return false;
    }

    private void loadSearch(final Context context, SearchModel model) {
        showSearchLoading();
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.search(context, model, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                hideSearchLoading();
                Log.d(TAG, "Response : " + arg0.toString());
                if (arg0 instanceof SearchMainModel) {
                    SearchMainModel searchMainModel = (SearchMainModel) arg0;
                    SearchResponseListModel model = searchMainModel.getSearchResponseListModel();
                    setAutoSuggestions(model);
                }
            }

            @Override
            public void OnFailureFromServer(String msg) {
                hideSearchLoading();
                globalFunctions.displayMessaage(activity, mainView, msg);
                Log.d(TAG, "Failure : " + msg);
            }

            @Override
            public void OnError(String msg) {
                hideSearchLoading();
                globalFunctions.displayMessaage(activity, mainView, msg);
                Log.d(TAG, "Error : " + msg);
            }
        }, "Search");
    }

    private void sendMessage(Bundle bundle) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent(globalVariables.NOTIFICATION_LOCAL_BROADCAST_KEY);
        intent.putExtras(bundle);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public void setResult(boolean isSuccess, SearchResponseModel searchResponseModel) {
        Intent intent = new Intent();
        intent.putExtra(BUNDLE_SEARCH_RESPONSE_MODEL, searchResponseModel);
        if (isSuccess) setResult(RESULT_OK, intent);
        else setResult(RESULT_CANCELED, intent);
        closeThisActivity();
    }

    @Override
    public void onBackPressed() {
        closeThisActivity();
        super.onBackPressed();
    }

    public static void closeThisActivity() {
        if (activity != null) {
            activity.finish();
//             activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

}
