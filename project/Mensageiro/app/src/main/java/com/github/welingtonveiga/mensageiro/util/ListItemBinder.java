package com.github.welingtonveiga.mensageiro.util;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

public interface ListItemBinder {

    public void bind(View view, Context context, Cursor cursor);

}
