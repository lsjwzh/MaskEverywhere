package com.lsjwzh.maskeverywhere.sample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lsjwzh.maskeverywhere.MaskEverywhere;


public class GenericStatusLayoutDemo extends ActionBarActivity {

    public static final int MASK_LOADING = 0;
    public static final int MASK_EMPTY = 1;
    public static final int MASK_ERROR = 2;

    MaskEverywhere mTextViewMask;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        mTextViewMask = new MaskEverywhere(textView);
        mTextViewMask.addMaskView(new ProgressBar(this));
            TextView emptyTipsView = new TextView(this);
        emptyTipsView.setGravity(Gravity.CENTER);
        emptyTipsView.setText("Empty");
        mTextViewMask.addMaskView(emptyTipsView);
        TextView errorTipsView = new TextView(this);
        errorTipsView.setGravity(Gravity.CENTER);
        errorTipsView.setText("Error");
        mTextViewMask.addMaskView(errorTipsView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuItemCompat.setShowAsAction(menu.add(0,0,0,"loading"),MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        MenuItemCompat.setShowAsAction(menu.add(0,1,0,"empty"),MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        MenuItemCompat.setShowAsAction(menu.add(0,2,0,"error"),MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == 0) {
            mTextViewMask.showMask(MASK_LOADING);
            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... params) {
                    SystemClock.sleep(3000);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    mTextViewMask.hideMask(MASK_LOADING);
                }
            }.execute();
            return true;
        }else if (id == 1) {
            mTextViewMask.showMask(MASK_EMPTY);
            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... params) {
                    SystemClock.sleep(3000);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    mTextViewMask.hideMask(MASK_EMPTY);
                }
            }.execute();
            return true;
        }else if (id == 2) {
            mTextViewMask.showMask(MASK_ERROR);
            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... params) {
                    SystemClock.sleep(3000);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    mTextViewMask.hideMask(MASK_ERROR);
                }
            }.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
