package com.amazon.dee.app.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.amazon.alexa.identity.api.IdentityConstant;
/* loaded from: classes12.dex */
public class ApplicationUpgradeReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.i(ApplicationUpgradeReceiver.class.getName(), "App has been upgraded. Removing shared preferences related to identity refresh ");
        context.getSharedPreferences(IdentityConstant.IDENTITY_STORAGE_NAME, 0).edit().clear().apply();
    }
}
