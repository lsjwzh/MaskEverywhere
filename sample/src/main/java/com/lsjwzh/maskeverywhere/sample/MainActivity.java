package com.lsjwzh.maskeverywhere.sample;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        textView = (TextView)findViewById(R.id.textView);
//        mLoadingLayout = LoadingLayout.wrap(textView);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuItemCompat.setShowAsAction(menu.add(0,0,0,"LoadingMask Demo"),MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setShowAsAction(menu.add(0,1,0,"GenericStatusMask Demo"),MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent=null;
        switch (item.getItemId()){
            case 0:
                intent = new Intent(this,LoadingLayoutDemo.class);
                break;
            case 1:
                intent = new Intent(this,GenericStatusLayoutDemo.class);
                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
