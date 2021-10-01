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

package com.user.gograb.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.user.gograb.R;


public class CustomAlertDialog extends Dialog {


    TextView description = null;
    ImageView titleImage = null;
    TextView okButton = null;

    String message= null, okButtonString = null;
    View.OnClickListener okButtonListener = null;
    int ImageRes = 0;

    static String TAG = "CustomAlertDialog";

    public CustomAlertDialog(Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.custom_alert_dialog);

        description = (TextView) findViewById(R.id.alert_dialog_message_tv);
        titleImage = (ImageView) findViewById(R.id.imageView);
        okButton = (TextView) findViewById(R.id.alert_dialog_positive_button);

        if(ImageRes == 0){titleImage.setVisibility(View.GONE);}
        else{titleImage.setVisibility(View.VISIBLE);titleImage.setImageResource(ImageRes);}

        if(message == null){description.setVisibility(View.GONE);}
        else{description.setVisibility(View.VISIBLE);description.setText(message);}

        okButton.setText(okButtonString);
        okButton.setTextColor(getContext().getResources().getColor(R.color.ColorPrimaryDark));
        okButton.setOnClickListener(okButtonListener);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);

    }

    public void setIcon(int resID){
        this.ImageRes = resID;
    }

    public void setMessage(String descriptionString){
        this.message = descriptionString;
    }

    public void setPositiveButton(String name, View.OnClickListener listener){
        okButtonString = name;
        okButtonListener = listener;
    }

    public void setIsCancelable(boolean cancelable){
        this.setCancelable(cancelable);
    }

}

