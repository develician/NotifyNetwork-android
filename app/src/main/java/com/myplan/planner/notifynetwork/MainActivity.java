package com.myplan.planner.notifynetwork;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.myplan.planner.notifynetwork.network.NetworkStateReceiver;

public class MainActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {

    private NetworkStateReceiver networkStateReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void networkAvailable() {
        Log.d("tommydevall", "I'm in, baby!");
        Toast.makeText(this, "Network is available", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void networkUnavailable() {
        Log.d("tommydevall", "I'm dancing with myself");
        Toast.makeText(this, "Network is unavailable", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);
    }
}
