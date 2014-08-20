package com.lsjwzh.loadingeverywhere.sample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.lsjwzh.widget.OverlayLayout;


public class OverlayLayoutDemo extends ActionBarActivity {

    OverlayLayout mOverlayLayout;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        mOverlayLayout = new OverlayLayout(this) {
            @Override
            protected View createOverlayView() {
                TextView overlayTextView = new TextView(OverlayLayoutDemo.this);
                overlayTextView.setText("This is a overlay textview");
                return overlayTextView;
            }
        };
        mOverlayLayout.attachTo(textView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            mOverlayLayout.showOverlay();
            new AsyncTask<Void,Void,Void>(){

                @Override
                protected Void doInBackground(Void... params) {
                    SystemClock.sleep(3000);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    mOverlayLayout.hideOverlay();
                }
            }.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
