package com.amazon.alexa.auth.map;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
/* loaded from: classes6.dex */
public class MAPProxyService extends Service {
    private AuthorizationServiceProxy proxy;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.proxy.asBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.proxy = new MAPAuthorizationServiceProxy(new MAPAccountManagerWrapper(this));
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        super.onUnbind(intent);
        try {
            this.proxy.killService();
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }
}
