package com.github.welingtonveiga.mensageiro.services;


import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.github.welingtonveiga.mensageiro.domain.model.Status;
import com.github.welingtonveiga.mensageiro.domain.persistence.StatusContract;
import com.github.welingtonveiga.mensageiro.domain.persistence.StatusDBHelper;
import com.github.welingtonveiga.mensageiro.util.RestClient;

import java.util.List;

public class RefreshService extends IntentService {
    private static final String TAG = RefreshService.class.getName();
    public static final String STATUSES_BY_CREATION_DESC = "https://service-api.herokuapp.com/statuses?_sort=created_at&_order=DESC";

    private final RestClient restClient = new RestClient();

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

        List<Status> timeline = restClient.getAll(STATUSES_BY_CREATION_DESC, Status[].class);

        SQLiteDatabase db =  new StatusDBHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        for (Status status : timeline) {

            Log.i(TAG, String.format("Inserindo status %s", timeline));

            values.clear();
            values.put(StatusContract.Column.ID, status.getId());
            values.put(StatusContract.Column.USER, status.getAuthor());
            values.put(StatusContract.Column.MESSAGE, status.getMessage());
            values.put(StatusContract.Column.CREATED_AT, status.getCreatedAt().getTime());
            db.insertWithOnConflict(StatusContract.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroyed");
    }
}