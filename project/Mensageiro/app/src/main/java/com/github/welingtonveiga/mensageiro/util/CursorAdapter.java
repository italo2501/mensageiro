package com.github.welingtonveiga.mensageiro.util;


import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.github.welingtonveiga.mensageiro.R;

public class CursorAdapter extends ResourceCursorAdapter {

    private int[] from;
    private int[] to;


    public CursorAdapter(Context context, int layout, Cursor c, int flags,) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}