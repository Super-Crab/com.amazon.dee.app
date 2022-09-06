package com.amazon.alexa.dialog.api;

import android.content.Context;
/* loaded from: classes7.dex */
public interface Dialog {

    /* loaded from: classes7.dex */
    public interface Builder {
        /* renamed from: build */
        Dialog mo1156build();

        /* renamed from: setCancelable */
        Builder mo1157setCancelable(boolean z);

        /* renamed from: setContext */
        Builder mo1158setContext(Context context);

        /* renamed from: setMessage */
        Builder mo1159setMessage(CharSequence charSequence);

        /* renamed from: setNegativeButton */
        Builder mo1160setNegativeButton(CharSequence charSequence, Runnable runnable);

        /* renamed from: setNeutralButton */
        Builder mo1161setNeutralButton(CharSequence charSequence, Runnable runnable);

        /* renamed from: setOnCancelAction */
        Builder mo1162setOnCancelAction(Runnable runnable);

        /* renamed from: setPositiveButton */
        Builder mo1163setPositiveButton(CharSequence charSequence, Runnable runnable);

        /* renamed from: setTitle */
        Builder mo1164setTitle(CharSequence charSequence);

        boolean show();
    }

    Context getContext();

    boolean isShowing();

    boolean show();
}
