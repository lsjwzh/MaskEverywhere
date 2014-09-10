package com.lsjwzh.widget;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 *
 * Created by lsjwzh on 14-1-10.
 */
public class ViewUtil {
    private static final String TAG = "ViewUtil";

    public static class OnGlobalLayoutListenerQueueExecutor implements ViewTreeObserver.OnGlobalLayoutListener{

        @Override
        public void onGlobalLayout() {

        }
    }
    static WeakHashMap<View,List<ViewTreeObserver.OnGlobalLayoutListener>> sViewTreeObserverListenerMap = new WeakHashMap<View, List<ViewTreeObserver.OnGlobalLayoutListener>>();
    /**
     * execute onGlobalLayoutListener.onGlobalLayout once when ViewTree changed.
     * @param onGlobalLayoutListener
     */
    public static void addGlobalLayoutListenerOnce(final View view, final ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener){
        if(view==null){
            throw new IllegalArgumentException("view can not be null");
        }
        if(onGlobalLayoutListener!=null){
            if(onGlobalLayoutListener!=null){
                if(isCachedViewTreeObserver(view)){
                    Log.d(TAG,"isCachedViewTreeObserver true ");
                    sViewTreeObserverListenerMap.get(view)
                            .add(onGlobalLayoutListener);
                }else {
                    ArrayList<ViewTreeObserver.OnGlobalLayoutListener> listenerList = new ArrayList<ViewTreeObserver.OnGlobalLayoutListener>();
                    sViewTreeObserverListenerMap.put(view, listenerList);
                    listenerList.add(onGlobalLayoutListener);
                    final OnGlobalLayoutListenerQueueExecutor tempListener = new OnGlobalLayoutListenerQueueExecutor() {
                        @Override
                        public void onGlobalLayout() {
                            Log.d(TAG,"OnGlobalLayoutListenerQueueExecutor called ");
                            if(Build.VERSION.SDK_INT<Build.VERSION_CODES.JELLY_BEAN){
                                removeOnGlobalLayoutListener16old(view,this);
                            }else{
                                removeOnGlobalLayoutListener(view, this);
                            }
                            if(isCachedViewTreeObserver(view)){
                                //ToDo thread safe
                                try {
                                    for (ViewTreeObserver.OnGlobalLayoutListener entry : sViewTreeObserverListenerMap.get(view)) {
                                        if (entry != null) {
                                            entry.onGlobalLayout();
                                        }
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                sViewTreeObserverListenerMap.remove(view);
                            }
                        }
                    };
                    Log.d(TAG,"addOnGlobalLayoutListener");
                    view.getViewTreeObserver().addOnGlobalLayoutListener(tempListener);
                }
            }

        }
    }
    @TargetApi(16)
    static void removeOnGlobalLayoutListener(View view,OnGlobalLayoutListenerQueueExecutor onGlobalLayoutListener){
        view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
    }
    static void removeOnGlobalLayoutListener16old(View view,OnGlobalLayoutListenerQueueExecutor onGlobalLayoutListener){
        view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
    }

    private static boolean isCachedViewTreeObserver(View view) {
        return sViewTreeObserverListenerMap.containsKey(view)
            &&sViewTreeObserverListenerMap.get(view)!=null;
    }



//    public static
}
