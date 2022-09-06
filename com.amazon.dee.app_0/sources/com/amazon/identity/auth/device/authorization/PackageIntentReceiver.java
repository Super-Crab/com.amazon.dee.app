package com.amazon.identity.auth.device.authorization;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class PackageIntentReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = PackageIntentReceiver.class.getName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Package Intent Received. Clearing Service Data. action=");
        outline107.append(intent.getAction());
        MAPLog.i(str, outline107.toString());
        ThirdPartyServiceHelper.clearCachedService(context);
    }
}
