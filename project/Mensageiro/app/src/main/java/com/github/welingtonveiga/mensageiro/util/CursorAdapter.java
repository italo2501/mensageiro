package com.github.welingtonveiga.mensageiro.util;


import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;

public class CursorAdapter extends ResourceCursorAdapter {

    private final ListItemBinder binder;


    public CursorAdapter(Context context, int layout, Cursor c, int flags, ListItemBinder binder) {
        super(context, layout, c, flags);
        this.binder = binder;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        binder.bind(view, context, cursor);
    }
}