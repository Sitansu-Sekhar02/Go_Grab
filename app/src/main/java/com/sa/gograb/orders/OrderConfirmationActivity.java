package com.sa.gograb.orders;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.mukesh.OtpView;
import com.sa.gograb.MainActivity;
import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.OrderListModel;
import com.sa.gograb.services.model.OrderMainModel;
import com.sa.gograb.services.model.OrderModel;
import com.sa.gograb.services.model.RatingNFeedbackModel;
import com.sa.gograb.services.model.StatusMainModel;
import com.sa.gograb.services.model.StatusModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrderConfirmationActivity extends AppCompatActivity {

    private static final String TAG = "OrderConfirmationActivity",
                                 BUNDLE_CONFIRM_ORDER = "OrderConfirmId";

    Context context;
    private static Activity activity;
    View mainView;

    static Toolbar toolbar;
    static ActionBar actionBar;
    static String mTitle;
    static int mResourceID;
    static int titleResourseID;
    static TextView toolbar_title;
    static ImageView toolbar_logo, tool_bar_back_icon;

    private TextView tv_feedback_title,tv_preparation_time,tv_order_no,tv_chat_with_restro;
    private  EditText et_feedback_comment;
    private CircleImageView iv_product_image;
    private ImageView iv_call;
    private Button btn_redeemOffer;
    private Button btn_submit;
    public OtpView redeem_insert;

    GlobalFunctions globalFunctions;

    String rating = "1";
    String restaurant_id=null;

    RatingNFeedbackModel ratingNFeedbackModel;

    public static Intent newInstance(Activity activity, String restaurant_id) {
        Intent intent = new Intent(activity, OrderConfirmationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_CONFIRM_ORDER, restaurant_id);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        context = this;
        activity = this;

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tool_bar_back_icon = (ImageView) toolbar.findViewById(R.id.toolbar_icon);
        tool_bar_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (MainActivity.header_tv.getText()!=null){

            toolbar_title.setText(MainActivity.header_tv.getText());

        }
        tv_preparation_time = findViewById(R.id.tv_preparation_time);
        tv_order_no = findViewById(R.id.tv_order_no);
        iv_call = findViewById(R.id.iv_call);
        tv_chat_with_restro = findViewById(R.id.tv_chat_with_restro);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.ColorStatusBar));
        }

        if (getIntent().hasExtra(BUNDLE_CONFIRM_ORDER)) {
            restaurant_id = getIntent().getStringExtra(BUNDLE_CONFIRM_ORDER);
        } else {
            restaurant_id = null;
        }

        mainView=toolbar;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                // Show your dialog here
                userFeedbackPopup();

            }
        }, 1500);

        orderDetails();


        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
       // setTitle(getString(R.string.places_search_error), 0, 0);

    }

    private void orderDetails() {
        GlobalFunctions.showProgress(context, getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.getOrderList(context, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                OrderMainModel orderMainModel = (OrderMainModel) arg0;
                OrderListModel orderListModel=orderMainModel.getOrderListModel();

               /* if (orderListModel.getOrderModels()!= null) {
                    setOrderDetails(orderListModel);
                }*/


            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnFailureFromServer(String msg) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Failure : " + msg);
                GlobalFunctions.displayMessaage(context, mainView, msg);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnError(String msg) {
                GlobalFunctions.hideProgress();
                Log.d(TAG, "Error : " + msg);
                GlobalFunctions.displayMessaage(context, mainView, msg);
            }
        }, "Review List");
    }

    private void userFeedbackPopup() {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.feedback_activity);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        iv_product_image=dialog.findViewById(R.id.iv_product_image);
        tv_feedback_title=dialog.findViewById(R.id.tv_product_title);
        et_feedback_comment=dialog.findViewById(R.id.etv_Comment);
        btn_submit=dialog.findViewById(R.id.btn_submit);
        et_feedback_comment.clearFocus();

        final ImageView rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5;

        rating_iv1 = dialog.findViewById(R.id.rating_iv1);
        rating_iv2 = dialog.findViewById(R.id.rating_iv2);
        rating_iv3 = dialog.findViewById(R.id.rating_iv3);
        rating_iv4 = dialog.findViewById(R.id.rating_iv4);
        rating_iv5 = dialog.findViewById(R.id.rating_iv5);

        setImageView(5, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);


        rating_iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(1, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(2, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(3, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(4, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        rating_iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageView(5, rating_iv1, rating_iv2, rating_iv3, rating_iv4, rating_iv5);
            }

        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_feedback_comment != null) {
                    String
                            //rating = String.valueOf(rt_rating.getRating()),
                            comment = et_feedback_comment.getText().toString().trim();
                       /* if (rating.isEmpty()) {
                            rt_rating.setError(getString(R.string.pleaseFillMandatoryDetails));
                            rt_rating.setFocusableInTouchMode(true);
                            rt_rating.requestFocus();
                        }*//* if (comment.isEmpty()) {
                            et_feedback_comment.setError(getString(R.string.please_give_a_comment));
                            et_feedback_comment.setFocusableInTouchMode(true);
                            et_feedback_comment.requestFocus();
                        } else {*/
                    if (ratingNFeedbackModel == null) {
                        ratingNFeedbackModel = new RatingNFeedbackModel();
                    }
                    ratingNFeedbackModel.setRestaurant_id(restaurant_id);
                    ratingNFeedbackModel.setRating(rating);
                    ratingNFeedbackModel.setComment(comment);
                    dialog.dismiss();

                    insertFeedback(activity, ratingNFeedbackModel);

                }
            }

        });
    }
    private void setImageView(int position, ImageView rating_iv1, ImageView rating_iv2, ImageView rating_iv3, ImageView rating_iv4, ImageView rating_iv5) {
        switch (position) {
            case 1:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_grey_star);
                rating_iv3.setImageResource(R.drawable.ic_grey_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 2:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_grey_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 3:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_yellow_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 4:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_yellow_star);
                rating_iv4.setImageResource(R.drawable.ic_yellow_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = position + "";
                break;

            case 5:
                rating_iv1.setImageResource(R.drawable.ic_yellow_star);
                rating_iv2.setImageResource(R.drawable.ic_yellow_star);
                rating_iv3.setImageResource(R.drawable.ic_yellow_star);
                rating_iv4.setImageResource(R.drawable.ic_yellow_star);
                rating_iv5.setImageResource(R.drawable.ic_yellow_star);
                rating = position + "";
                break;

            default:
                rating_iv1.setImageResource(R.drawable.ic_grey_star);
                rating_iv2.setImageResource(R.drawable.ic_grey_star);
                rating_iv3.setImageResource(R.drawable.ic_grey_star);
                rating_iv4.setImageResource(R.drawable.ic_grey_star);
                rating_iv5.setImageResource(R.drawable.ic_grey_star);
                rating = "1";
        }
    }

    private void insertFeedback(Activity activity, RatingNFeedbackModel ratingNFeedbackModel) {
        globalFunctions.showProgress(activity, activity.getString(R.string.loading));
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.giveFeedback(context, ratingNFeedbackModel, new ServerResponseInterface() {
            @SuppressLint("LongLogTag")
            @Override
            public void OnSuccessFromServer(Object arg0) {
                globalFunctions.hideProgress();
                Log.d(TAG, "Response : " + arg0.toString());
                //StatusModel model = (StatusModel) arg0;
                validateOutputAfterFeedback(arg0);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnFailureFromServer(String msg) {
                globalFunctions.hideProgress();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Failure : " + msg);
            }

            @SuppressLint("LongLogTag")
            @Override
            public void OnError(String msg) {
                globalFunctions.hideProgress();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Error : " + msg);
            }
        }, "feedback");
    }
    private void validateOutputAfterFeedback(Object arg0) {
        if (arg0 instanceof StatusMainModel) {
            StatusMainModel feedbackNRatingMainModel = (StatusMainModel) arg0;
            StatusModel statusModel = feedbackNRatingMainModel.getStatusModel();
            if (!feedbackNRatingMainModel.isStatusLogin()) {
                globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());

            } else {
                globalFunctions.displayMessaage(activity, mainView, statusModel.getMessage());
                //goToMainActivity();

            }
        }
    }

    @Override
    public void onStop () {
        super.onStop();
    }

    public static void setTitle (String title,int titleImageID, int backgroundResourceID){
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

    @SuppressLint("LongLogTag")
    private static void restoreToolbar () {
        //toolbar = (Toolbar) findViewById(R.id.tool_bar);
        Log.d(TAG, "Restore Tool Bar");
        if (actionBar != null) {
            Log.d(TAG, "Restore Action Bar not Null");
            Log.d(TAG, "Title : " + mTitle);
            toolbar_title.setText(mTitle);
            if (mResourceID != 0) toolbar.setBackgroundResource(mResourceID);
            //actionBar.setTitle("");
            // actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void onBackPressed () {

        closeThisActivity();
        super.onBackPressed();
    }

    public static void closeThisActivity () {
        if (activity != null) {
            activity.finish();
            //activity.overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
        }
    }
    @Override
    public void onPause () {
        super.onPause();
        if (getFragmentManager().findFragmentByTag(TAG) != null)
            getFragmentManager().findFragmentByTag(TAG).setRetainInstance(true);
    }

    @Override
    public void onStart () {

       /* if(hint != null) {
            hint.launchAutomaticHintForCall(activity.findViewById(R.id.action_call));
        }*/
//       globalFunctions.launchAutomaticHintForSearch(mainView, getString(R.string.search_title),  getString(R.string.search_description));
        super.onStart();
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    }
}
