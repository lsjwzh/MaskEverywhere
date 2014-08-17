package com.lsjwzh.loadingeverywhere;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.lsjwzh.widget.MultiOverlayLayout;

/**
 * Created by panwenye on 14-8-17.
 */
public class GenericStatusLayout extends MultiOverlayLayout {
    public GenericStatusLayout(Context context) {
        super(context);
    }

    public GenericStatusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GenericStatusLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    @Override
    public View createOverlayView(int index) {
        return null;
    }
}
