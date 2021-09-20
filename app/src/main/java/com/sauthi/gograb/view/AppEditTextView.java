package com.sauthi.gograb.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class AppEditTextView extends androidx.appcompat.widget.AppCompatEditText {
    public AppEditTextView(Context context) {
        super(context);
        init();
    }

    public AppEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public AppEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "app_medium.otf");
        setTypeface(tf);
    }


}
