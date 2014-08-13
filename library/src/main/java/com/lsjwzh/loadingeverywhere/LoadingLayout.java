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
 * Layout with a loading mask.
 * Created by lsjwzh on 14-8-9.
 */
public class LoadingLayout extends OverlayLayout {

    /**
     * create a LoadingLayout to wrap and replace the targetView.
     * Note: if you attachTo targetView on 'onCreate' method,targetView may be not layout complete.
     *
     * @param targetView
     * @return
     */
    public static LoadingLayout wrap(final View targetView){
         return wrap(targetView,android.R.attr.progressBarStyleLarge);
    }
    /**
     * create a LoadingLayout to wrap and replace the targetView.
     * Note: if you attachTo targetView on 'onCreate' method,targetView may be not layout complete.
     *
     * @param targetView
     * @return
     */
    public static LoadingLayout wrap(final View targetView, final int defStyle){
        if(targetView==null){
            throw new IllegalArgumentException();
        }

        final LoadingLayout loadingLayout = new LoadingLayout(targetView.getContext()){
            @Override
            protected View createProgressBar() {
                return new ProgressBar(getContext(),null,defStyle);
            }
        };
        loadingLayout.attachTo(targetView);
        return loadingLayout;
    }

    public LoadingLayout(Context context) {
        super(context);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void showLoading(){
        showOverlay();
    }

    public void hideLoading(){
        hideOverlay();
    }

    public void setIsHideTargetViewWhenLoading(boolean isHideTargetViewWhenLoading){
        setIsHideTargetViewWhenOverlayShown(isHideTargetViewWhenLoading);
    }

    public boolean isLoadingMaskShown(){
        return isOverlayShown();
    }

    public View getLoadingMask(){
        return super.mOverlay;
    }

    /**
     * create loading mask
     * @return
     */
    @Override
    View createOverlayView() {
        LinearLayout ll = new LinearLayout(getContext());
        ll.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        ll.setGravity(Gravity.CENTER);
        View progressBar = createProgressBar();
        ll.addView(progressBar);
        return ll;
    }

    protected View createProgressBar() {
        return new ProgressBar(getContext(),null,android.R.attr.progressBarStyleLarge);
    }
}
