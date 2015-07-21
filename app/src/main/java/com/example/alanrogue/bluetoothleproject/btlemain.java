package com.example.alanrogue.bluetoothleproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class btlemain extends Activity {

    private Button mBtnHRMSetup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btlemain);

        mBtnHRMSetup = (Button)findViewById(R.id.btnSetup);

        mBtnHRMSetup.setOnClickListener(btnHRMSetupOnClick);
    }

    private View.OnClickListener btnHRMSetupOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent hrmSetup_it = new Intent();
            hrmSetup_it.setClass(btlemain.this, hrm_setup.class);
            startActivity(hrmSetup_it);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_btlemain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
