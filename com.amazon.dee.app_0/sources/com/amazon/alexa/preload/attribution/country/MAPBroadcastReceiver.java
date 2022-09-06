package com.amazon.alexa.preload.attribution.country;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
/* loaded from: classes9.dex */
public class MAPBroadcastReceiver extends BroadcastReceiver {
    static final String INTENT_MAP_ACCOUNT_ADDED = "com.amazon.dcp.sso.action.account.added";
    static final String MAP_BROADCAST_PERMISSION = "com.amazon.dcp.sso.permission.account.changed";
    private static final String TAG = MAPBroadcastReceiver.class.getSimpleName();
    private final AccountAddedListener mAccountAddedListener;

    public MAPBroadcastReceiver(@NonNull AccountAddedListener accountAddedListener) {
        this.mAccountAddedListener = accountAddedListener;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if ("com.amazon.dcp.sso.action.account.added".equals(intent.getAction())) {
            this.mAccountAddedListener.onAccountAdded();
        }
    }
}
