package com.lsjwzh.loadingeverywhere;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsjwzh.widget.MultiOverlayAdapter;
import com.lsjwzh.widget.MultiOverlayLayout;

/**
 * Layout with three generic status : loading,error,empty
 * Created by panwenye on 14-8-17.
 */
public class GenericStatusLayout extends MultiOverlayLayout {
    public interface ILayerCreator{
        View createLoadingLayer();
        View createEmptyLayer();
        View createErrorLayer();
    }
    public static final int POSITION_LOADING = 0;
    public static final int POSITION_EMPTY = 1;
    public static final int POSITION_ERROR = 2;

    ILayerCreator mLayerCreator;

    public GenericStatusLayout(Context context) {
        super(context);
        setDefalutAdapter();
    }

    public GenericStatusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDefalutAdapter();
    }

    public GenericStatusLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setDefalutAdapter();
    }

    private void setDefalutAdapter() {
        super.setAdapter(new MultiOverlayAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public View getView(int index, View targetView) {
                View retView = null;
                switch (index) {
                    default:
                    case POSITION_LOADING:
                        if(mLayerCreator!=null){
                            retView = mLayerCreator.createLoadingLayer();
                        }else {
                            retView = LayoutInflater.from(getContext()).inflate(R.layout.loading, (ViewGroup) getParent(), false);
                        }
                        break;
                    case POSITION_EMPTY:
                        if(mLayerCreator!=null){
                            retView = mLayerCreator.createEmptyLayer();
                        }else {
                            retView = LayoutInflater.from(getContext()).inflate(R.layout.empty, (ViewGroup) getParent(), false);
                        }
                        break;
                    case POSITION_ERROR:
                        if(mLayerCreator!=null){
                            retView = mLayerCreator.createErrorLayer();
                        }else {
                            retView = LayoutInflater.from(getContext()).inflate(R.layout.error, (ViewGroup) getParent(), false);
                        }
                        break;
                }
                return retView;
            }
        });
    }

    /**
     * this method in GenericStatusLayout is disabled,
     * you can custom layers by {@link #setLayerCreator(ILayerCreator)}
     * @param multiOverlayAdapter
     */
    @Override
    public void setAdapter(MultiOverlayAdapter multiOverlayAdapter) {
        //disable setAdapter
    }

    public void setLayerCreator(ILayerCreator layerCreator){
        mLayerCreator = layerCreator;
    }

    public void showLoading(){
        showOverlay(POSITION_LOADING);
    }
    public void showEmpty(){
        showOverlay(POSITION_EMPTY);
    }
    public void showError(){
        showOverlay(POSITION_ERROR);
    }


    public void hideLoading(){
        hideOverlay(POSITION_LOADING);
    }
    public void hideEmpty(){
        hideOverlay(POSITION_EMPTY);
    }
    public void hideError(){
        hideOverlay(POSITION_ERROR);
    }


}
