package com.lsjwzh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * Layout with a Overlay view.
 * Created by lsjwzh on 14-8-9.
 */
public abstract class OverlayLayout extends FrameLayout {

    protected View mOverlay;
    protected View mTargetView;
    protected boolean mIsHideTargetViewWhenOverlayShown = true;


    public OverlayLayout(Context context) {
        super(context);
    }

    public OverlayLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OverlayLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    /**
     * use LoadingLayout itself to wrap and replace the targetView.
     * Note: if you attachTo targetView on 'onCreate' method,targetView may be not layout complete.
     *
     * @param targetView
     * @return
     */
    public void attachTo(final View targetView){
        if(targetView==null){
            throw new IllegalArgumentException();
        }
        mTargetView = targetView;

        ViewGroup.LayoutParams layoutParams = targetView.getLayoutParams();
        this.setLayoutParams(layoutParams);
        if(targetView.getParent()!=null&&targetView.getParent() instanceof ViewGroup){
            ViewGroup targetViewParent = (ViewGroup) targetView.getParent();
            int targetViewPosInParent = targetViewParent.indexOfChild(targetView);
            targetViewParent.removeView(targetView);
            targetViewParent.addView(this,targetViewPosInParent);
            this.addView(targetView);
        }else {
            ViewUtil.addGlobalLayoutListenerOnce(targetView, new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (targetView.getParent() == null) {
                        return;
                    }
                    ViewGroup targetViewParent = (ViewGroup) targetView.getParent();
                    int targetViewPosInParent = targetViewParent.indexOfChild(targetView);
                    targetViewParent.removeView(targetView);
                    targetViewParent.addView(OverlayLayout.this, targetViewPosInParent);
                    OverlayLayout.this.addView(targetView);
                }
            });
        }
    }

    public void showOverlay(){
        if(mOverlay ==null){
            mOverlay = createOverlayView();
            addView(mOverlay);
        }
        mOverlay.setVisibility(VISIBLE);
        if(mIsHideTargetViewWhenOverlayShown &&mTargetView!=null){
            mTargetView.setVisibility(GONE);
        }
    }

    public void hideOverlay(){
        if(mOverlay !=null){
            mOverlay.setVisibility(GONE);
        }
        if(mIsHideTargetViewWhenOverlayShown &&mTargetView!=null){
            mTargetView.setVisibility(VISIBLE);
        }
    }

    public void setIsHideTargetViewWhenOverlayShown(boolean isHideTargetViewWhenOverlayShown){
        mIsHideTargetViewWhenOverlayShown = isHideTargetViewWhenOverlayShown;
    }

    public boolean isOverlayShown(){
        return mOverlay !=null&& mOverlay.getVisibility()==VISIBLE;
    }

    public View getOverlayView(){
        return mOverlay;
    }

    /**
     * create overlay mask
     * @return
     */
    abstract View createOverlayView();
}
