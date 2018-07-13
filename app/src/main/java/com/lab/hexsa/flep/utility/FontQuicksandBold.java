package com.lab.hexsa.flep.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class FontQuicksandBold extends AppCompatTextView {
    public FontQuicksandBold(Context c, AttributeSet as){
        super(c, as);
        this.setTypeface(Typeface.createFromAsset(c.getAssets(), "font/Quicksand-Bold.ttf"));
    }
}
