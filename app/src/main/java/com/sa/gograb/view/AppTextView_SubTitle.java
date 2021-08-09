package com.sa.gograb.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class AppTextView_SubTitle extends androidx.appcompat.widget.AppCompatTextView {
    public AppTextView_SubTitle(Context context) {
        super(context);
        init();
    }

    public AppTextView_SubTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppTextView_SubTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "app_book.otf");
        setTypeface(tf);
    }
}
