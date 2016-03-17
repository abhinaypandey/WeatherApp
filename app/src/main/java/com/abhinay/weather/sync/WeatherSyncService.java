package com.abhinay.weather.sync;

/**
 * Created by pandabhi on 3/17/2016.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class WeatherSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static WeatherSyncAdapter weatherSyncAdapter = null;

    @Override
    public void onCreate() {
        Log.d("WeatherSyncService", "onCreate - WeatherSyncService");
        synchronized (sSyncAdapterLock) {
            if (weatherSyncAdapter == null) {
                weatherSyncAdapter = new WeatherSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return weatherSyncAdapter.getSyncAdapterBinder();
    }
}
