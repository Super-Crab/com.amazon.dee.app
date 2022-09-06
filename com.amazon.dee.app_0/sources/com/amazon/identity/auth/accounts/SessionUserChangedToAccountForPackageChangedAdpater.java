package com.amazon.identity.auth.accounts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.ds;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.ji;
import com.amazon.identity.auth.device.l;
/* compiled from: DCP */
@Deprecated
/* loaded from: classes12.dex */
public class SessionUserChangedToAccountForPackageChangedAdpater extends BroadcastReceiver {
    private static final String TAG = SessionUserChangedToAccountForPackageChangedAdpater.class.getName();

    public static boolean a(ds dsVar) {
        return dsVar.dm();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        io.dm(TAG);
        ji.b(new Runnable() { // from class: com.amazon.identity.auth.accounts.SessionUserChangedToAccountForPackageChangedAdpater.1
            @Override // java.lang.Runnable
            public void run() {
                io.dm(SessionUserChangedToAccountForPackageChangedAdpater.TAG);
                ed M = ed.M(context);
                if (!SessionUserChangedToAccountForPackageChangedAdpater.a((ds) M.getSystemService("sso_platform"))) {
                    io.dm(SessionUserChangedToAccountForPackageChangedAdpater.TAG);
                } else {
                    l.c(M);
                }
            }
        });
    }
}
