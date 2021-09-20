package com.sauthi.gograb.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class AppButton extends androidx.appcompat.widget.AppCompatButton {
    public AppButton(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public AppButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public AppButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.get("app_medium.otf", context);
        setTypeface(customFont);
    }

}
