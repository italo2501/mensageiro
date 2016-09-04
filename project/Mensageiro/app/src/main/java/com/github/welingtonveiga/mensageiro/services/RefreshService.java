package com.github.welingtonveiga.mensageiro.services;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.github.welingtonveiga.mensageiro.domain.services.StatusService;

public class RefreshService extends IntentService {
    private static final String TAG = RefreshService.class.getName();

    private final StatusService statusService = new StatusService();

    public RefreshService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreated");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i(TAG, statusService.getLastStatuses().toString());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroyed");
    }
}