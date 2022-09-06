package com.amazon.deecomms.calling.controller;

import android.content.Context;
import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public class CallingContentProviderNotifier {
    private final Context mContext;

    public CallingContentProviderNotifier(@NonNull Context context) {
        this.mContext = context;
    }

    public void notifyObservers() {
        this.mContext.getContentResolver().notifyChange(CallingProviderContract.getCallUri(this.mContext), null);
    }
}
