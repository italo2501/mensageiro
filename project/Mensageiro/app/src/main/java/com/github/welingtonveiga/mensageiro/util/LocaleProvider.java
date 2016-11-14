package com.github.welingtonveiga.mensageiro.util;

import android.content.Context;

import java.util.Locale;

public final class LocaleProvider {

    public static Locale get(Context context) {
        final Locale current;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
             current = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            current = context.getResources().getConfiguration().locale;
        }
        return current;
    }
}
