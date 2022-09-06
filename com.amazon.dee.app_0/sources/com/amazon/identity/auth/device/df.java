package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class df {
    private static final String TAG = "com.amazon.identity.auth.device.df";
    private static final long cK = TimeUnit.MILLISECONDS.convert(3, TimeUnit.SECONDS);
    private ServiceConnection cN;
    private boolean cR;
    private final Object[] jl = new Object[0];
    private final Intent jm;
    private final int jn;
    private final Context mContext;

    public df(Context context, Intent intent, int i) {
        if (context != null && intent != null) {
            this.mContext = context;
            this.jm = intent;
            this.cR = false;
            this.cN = new ServiceConnection() { // from class: com.amazon.identity.auth.device.df.1
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    String str = df.TAG;
                    String.format("Connected to service: %s", componentName.toString());
                    io.dm(str);
                    df.this.cR = true;
                    try {
                        df.this.useService(componentName, iBinder);
                    } catch (RemoteException unused) {
                        io.e(df.TAG, String.format("Service died: %s", componentName.toString()));
                        df.this.unbind();
                    }
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    synchronized (df.this.jl) {
                        df.this.cN = null;
                    }
                    df.this.serviceDisconnected();
                    String str = df.TAG;
                    String.format("Disconnected from service: %s", componentName.toString());
                    io.dm(str);
                }
            };
            int i2 = Build.VERSION.SDK_INT;
            this.jn = i | 4 | 16;
            return;
        }
        throw new IllegalArgumentException();
    }

    public final boolean call() {
        boolean bindService;
        synchronized (this.jl) {
            if (this.cN != null) {
                bindService = this.mContext.bindService(this.jm, this.cN, this.jn);
            } else {
                throw new IllegalStateException("Attempted to reuse a BoundServiceCaller.  Call method can only be executed once.");
            }
        }
        if (!bindService) {
            io.e(TAG, "Failed to bind to service.");
            return false;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.amazon.identity.auth.device.df.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (df.this.jl) {
                    if (df.this.cR) {
                        return;
                    }
                    io.e(df.TAG, String.format("Application timed out trying to bind to %s", df.this.jm.getComponent().getPackageName()));
                    df.this.cN = null;
                    df.this.serviceTimedOut();
                }
            }
        }, cK);
        return bindService;
    }

    protected void serviceDisconnected() {
    }

    protected void serviceTimedOut() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void unbind() {
        synchronized (this.jl) {
            if (this.cN != null) {
                try {
                    this.mContext.unbindService(this.cN);
                } catch (IllegalArgumentException unused) {
                    io.w(TAG, String.format("IllegalArgumentException is received during unbinding from %s. Ignored.", this.jm.getComponent().getPackageName()));
                }
                this.cN = null;
            }
        }
    }

    protected void useService(ComponentName componentName, IBinder iBinder) throws RemoteException {
        useService(iBinder);
    }

    protected void useService(IBinder iBinder) throws RemoteException {
    }
}
