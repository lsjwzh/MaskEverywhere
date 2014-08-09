package com.lsjwzh.loadingeverywhere;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Layout with a loading mask.
 *
 * Created by lsjwzh on 14-8-9.
 */
public class LoadingLayout extends FrameLayout {
    /**
     * create a LoadingLayout to wrap and replace the targetView
     * @param targetView
     * @return
     */
    public static LoadingLayout attachTo(View targetView){
        if(targetView.getParent()!=null&&targetView.getParent() instanceof ViewGroup){
            LoadingLayout loadingLayout = new LoadingLayout(targetView.getContext());
            ViewGroup.LayoutParams layoutParams = targetView.getLayoutParams();
            loadingLayout.setLayoutParams(layoutParams);
            ViewGroup targetViewParent = (ViewGroup) targetView.getParent();
            int targetViewPosInParent = targetViewParent.indexOfChild(targetView);
            targetViewParent.addView(loadingLayout,targetViewPosInParent);
            return loadingLayout;
        }
        return null;
    }


    View mLoadingMask;

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
        if(mLoadingMask==null){
            mLoadingMask = createLoadingMask();
            addView(mLoadingMask);
        }
        mLoadingMask.setVisibility(VISIBLE);
    }

    public void hideLoading(){
        if(mLoadingMask!=null){
            mLoadingMask.setVisibility(GONE);
        }
    }

    /**
     * create loading mask
     * @return
     */
    protected View createLoadingMask(){
        LinearLayout ll = new LinearLayout(getContext());
        ll.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        ll.setGravity(Gravity.CENTER);
        ProgressBar progressBar = new ProgressBar(getContext(),null,android.R.attr.progressBarStyleLarge);
        ll.addView(progressBar);
        return ll;
    }
}
