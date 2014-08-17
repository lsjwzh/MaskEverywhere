package com.lsjwzh.widget;

import android.view.View;

/**
 * Created by panwenye on 14-8-17.
 */
public interface MultiOverlayAdapter {
    /**
     * get overlays count
     * @return
     */
    public int getCount();

    /**
     * get overlay view
     * @param index
     * @param targetView
     * @return
     */
    public View getView(int index,View targetView);
}
