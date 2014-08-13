package com.lsjwzh.loadingeverywhere;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Layout with a mask.
 * Created by lsjwzh on 14-8-9.
 */
public class MaskLayout extends OverlayLayout {

    /**
     * create a MaskLayout to wrap and replace the targetView.
     * Note: if you attachTo targetView on 'onCreate' method,targetView may be not layout complete.
     *
     * @param targetView
     * @return
     */
    public static MaskLayout wrap(final View targetView){
        if(targetView==null){
            throw new IllegalArgumentException();
        }

        final MaskLayout maskLayout = new MaskLayout(targetView.getContext());
        maskLayout.attachTo(targetView);
        return maskLayout;
    }



    public MaskLayout(Context context) {
        super(context);
    }

    public MaskLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void showMask(){
        showOverlay();
    }

    public void hideMask(){
        hideOverlay();
    }

    /**
     * create mask
     * @return
     */
    @Override
    View createOverlayView() {
        LinearLayout ll = new LinearLayout(getContext());
        ll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        ll.setGravity(Gravity.CENTER);
        return ll;
    }

}
