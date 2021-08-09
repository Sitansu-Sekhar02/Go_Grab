/*
 *
 * Copyright (c) 2015.
 * For the development of personal software in the android platform,
 * Subjected too the idea of SUM Enterprices
 * This information and the oding style and the functionalities are not reusealblee withhout permissios.
 * The Information and the coding from these module if found to be used any where it is an offence.
 *
 * Created by Sivasabharish Chinnaswamy
 * Created on 26/6/15 2:01 AM
 */

package com.sa.gograb.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sa.gograb.R;


public class ProgressDialog extends Dialog {


    TextView message_tv = null;
    //ImageView logo = null;

    String message= null;

    static String TAG = "ProgressDialog";

    public ProgressDialog(Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.progress_dialog);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        message_tv = (TextView) findViewById(R.id.progress_dialog_text);

        //logo = (ImageView) findViewById(R.id.progress_dialog_logo);

        if(message == null) {message = "Loading...";}
        message_tv.setText(message);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);

    }

    public void setMessage(String descriptionString){
        this.message = descriptionString;
    }

    public void setIsCancelable(boolean cancelable){
        this.setCancelable(cancelable);
    }

    @Override
    public void onDetachedFromWindow() {
        this.cancel();
        this.dismiss();
        try {
            this.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        Log.d(TAG, "onDetachedFromWindow : called");
        super.onDetachedFromWindow();
    }
}

