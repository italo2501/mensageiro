package com.github.welingtonveiga.mensageiro.views.main;

import android.app.ListFragment;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.github.welingtonveiga.mensageiro.R;
import com.github.welingtonveiga.mensageiro.domain.persistence.StatusContract;
import com.github.welingtonveiga.mensageiro.domain.persistence.StatusDBHelper;
import com.github.welingtonveiga.mensageiro.util.CursorAdapter;
import com.github.welingtonveiga.mensageiro.util.ListItemBinder;

public class TimelineFragment extends ListFragment {

    private static final String TAG = TimelineFragment.class.getName();

    private SQLiteDatabase db;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        db =  new StatusDBHelper(getActivity()).getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Status order by created_at desc", null);

       CursorAdapter adapter = new CursorAdapter(getActivity(), R.layout.list_item, cursor, 0, new ListItemBinder() {
           @Override
           public void bind(View view, Context context, Cursor cursor) {
               TextView author = (TextView) view.findViewById(R.id.list_item_text_user);
               author.setText(cursor.getString(cursor.getColumnIndex(StatusContract.Column.USER)));

               TextView message = (TextView) view.findViewById(R.id.list_item_text_message);
               message.setText(cursor.getString(cursor.getColumnIndex(StatusContract.Column.MESSAGE)));

               TextView createdAt = (TextView) view.findViewById(R.id.list_item_text_created_at);
               createdAt.setText(cursor.getString(cursor.getColumnIndex(StatusContract.Column.CREATED_AT)));
           }
       });

        this.setListAdapter(adapter);

    }

    @Override
    public void onDestroy() {
        if (db != null) {
            db.close();
        }
        super.onDestroy();
    }
}

