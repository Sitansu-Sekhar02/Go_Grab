package com.user.gograb.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class AppEditTextViewBold extends androidx.appcompat.widget.AppCompatEditText {
    public AppEditTextViewBold(Context context) {
        super(context);
        init();
    }

    public AppEditTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppEditTextViewBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "app_medium.ttf");
        setTypeface(tf);
    }


}
