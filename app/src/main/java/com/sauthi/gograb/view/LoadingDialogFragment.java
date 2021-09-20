package com.sauthi.gograb.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.sauthi.gograb.R;


public class LoadingDialogFragment extends DialogFragment {
    public static final String TAG = "LoadingFragment";
    private ImageView ivLoading;
    private TextView message_tv;

    private String message=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.progress_dialog, container, false);

        if (rootView != null) {
            ivLoading = (ImageView) rootView.findViewById(R.id.progress_dialog_logo);
            message_tv = (TextView) rootView.findViewById(R.id.progress_dialog_text);
            ivLoading.setBackgroundResource(R.drawable.circle_white);
            if(message==null){message=getString(R.string.loading);}
        }
        return rootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            message_tv.setText(message);

            AnimationDrawable loadingAnimation = (AnimationDrawable) ivLoading.getBackground();
            loadingAnimation.start();
        }
    }

    public void setIsCancelable(boolean cancelable){
        this.setCancelable(cancelable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}