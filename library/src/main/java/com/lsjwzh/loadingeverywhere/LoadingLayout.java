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
public class LoadingLayout extends FrameLayout {

    /**
     * create a LoadingLayout to wrap and replace the targetView.
     * Note: if you attachTo targetView on 'onCreate' method,targetView may be not layout complete.
     *
     * @param targetView
     * @return
     */
    public static LoadingLayout wrap(final View targetView){
        if(targetView==null){
            throw new IllegalArgumentException();
        }

        final LoadingLayout loadingLayout = new LoadingLayout(targetView.getContext());
        loadingLayout.attachTo(targetView);
        return loadingLayout;
    }


    protected View mLoadingMask;
    protected View mTargetView;
    protected boolean mIsHideTargetViewWhenLoading = true;


    public LoadingLayout(Context context) {
        super(context);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyle) {
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
            ViewUtil.addGlobalLayoutListenerOnce(targetView,new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if(targetView.getParent()==null){
                        return;
                    }
                    ViewGroup targetViewParent = (ViewGroup) targetView.getParent();
                    int targetViewPosInParent = targetViewParent.indexOfChild(targetView);
                    targetViewParent.removeView(targetView);
                    targetViewParent.addView(LoadingLayout.this,targetViewPosInParent);
                    LoadingLayout.this.addView(targetView);
                }
            });
        }
    }

    public void showLoading(){
        if(mLoadingMask==null){
            mLoadingMask = createLoadingMask();
            addView(mLoadingMask);
        }
        mLoadingMask.setVisibility(VISIBLE);
        if(mIsHideTargetViewWhenLoading&&mTargetView!=null){
            mTargetView.setVisibility(GONE);
        }
    }

    public void hideLoading(){
        if(mLoadingMask!=null){
            mLoadingMask.setVisibility(GONE);
        }
        if(mIsHideTargetViewWhenLoading&&mTargetView!=null){
            mTargetView.setVisibility(VISIBLE);
        }
    }

    public void setIsHideTargetViewWhenLoading(boolean isHideTargetViewWhenLoading){
        mIsHideTargetViewWhenLoading = isHideTargetViewWhenLoading;
    }

    public boolean isLoadingMaskShown(){
        return mLoadingMask!=null&&mLoadingMask.getVisibility()==VISIBLE;
    }

    public View getLoadingMask(){
        return mLoadingMask;
    }

    /**
     * create loading mask
     * @return
     */
    protected View createLoadingMask(){
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
