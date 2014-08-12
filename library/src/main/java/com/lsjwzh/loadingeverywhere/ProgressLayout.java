package com.lsjwzh.loadingeverywhere;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created by panwenye on 14-8-11.
 */
public class ProgressLayout extends LoadingLayout {
    /**
     * create a ProgressLayout to wrap and replace the targetView.
     * Note: if you attachTo targetView on 'onCreate' method,targetView may be not layout complete.
     *
     * @param targetView
     * @return
     */
    public static ProgressLayout wrap(final View targetView){
        if(targetView==null){
            throw new IllegalArgumentException();
        }

        final ProgressLayout progressLayout = new ProgressLayout(targetView.getContext());
        progressLayout.attachTo(targetView);
        return progressLayout;
    }
    protected ProgressBar mProgressBar;
    protected int mProgress = -1;
    protected int mMax = -1;



    public ProgressLayout(Context context) {
        super(context);
    }

    public ProgressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    public void setMax(int max){
        mMax = max;
        if(mProgressBar!=null){
            mProgressBar.setMax(max);
        }
    }
    public void setProgress(int progress){
        mProgress = progress;
        if(mProgressBar!=null){
            mProgressBar.setProgress(progress);
        }
    }

    /**
     * create progressbar
     * @return
     */
    @Override
    protected View createProgressBar() {
        mProgressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleHorizontal);
        if(mProgress>=0){
            mProgressBar.setProgress(mProgress);
        }
        if(mMax>=0){
            mProgressBar.setMax(mMax);
        }
        return mProgressBar;
    }

}
