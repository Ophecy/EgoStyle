package com.epsi.egostyleapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import com.lionzxy.trex_offline.TRexOfflineActivity;
import androidx.appcompat.app.AppCompatActivity;


public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        R.id.trex.
                addProgressListener {
            swipeToRefresh.isRefreshing = it
        }
        swipeToRefresh.setOnRefreshListener {
            trex.refresh()
        }
        open_activity.setOnClickListener {
            TRexOfflineActivity.open(this)
        }

}
}