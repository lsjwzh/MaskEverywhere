package com.lsjwzh.loadingeverywhere.sample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lsjwzh.loadingeverywhere.GenericStatusLayout;
import com.lsjwzh.widget.OverlayLayout;


public class GenericStatusLayoutDemo extends ActionBarActivity {

    GenericStatusLayout mGenericStatusLayout;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        mGenericStatusLayout = new GenericStatusLayout(this);
//        mGenericStatusLayout.setLayerCreator(new );
        mGenericStatusLayout.attachTo(textView);

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
            mGenericStatusLayout.showLoading();
            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... params) {
                    SystemClock.sleep(3000);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    mGenericStatusLayout.hideLoading();
                }
            }.execute();
            return true;
        }else if (id == 1) {
            mGenericStatusLayout.showEmpty();
            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... params) {
                    SystemClock.sleep(3000);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    mGenericStatusLayout.hideEmpty();
                }
            }.execute();
            return true;
        }else if (id == 2) {
            mGenericStatusLayout.showError();
            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... params) {
                    SystemClock.sleep(3000);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    mGenericStatusLayout.hideError();
                }
            }.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
