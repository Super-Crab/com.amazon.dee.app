package com.amazon.rtcsc.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import com.amazon.rtcsc.utils.RtcscLogger;
import java.util.ArrayList;
/* loaded from: classes13.dex */
public abstract class ServiceConnectionManager {
    private static RtcscLogger mLog = RtcscLogger.getLogger(ServiceConnectionManager.class);
    private Intent mBindIntent;
    private Context mContext;
    private int mInterfaceVersion;
    private String mPermission;
    private ServiceConnection mServiceConnection;
    private final ArrayList<Callback> mCallbacks = new ArrayList<>();
    private boolean mBoundSuccessfully = false;
    private boolean mConnected = false;

    /* loaded from: classes13.dex */
    public static abstract class Callback {
        public void onBind(boolean z) {
        }

        @Deprecated
        public void onServiceConnected() {
        }

        public void onServiceConnected(int i) {
        }

        public void onServiceDisconnected() {
        }

        public void onUnbind() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean bindCommon(Context context, ServiceConnection serviceConnection, Intent intent, String str) {
        if (intent.getComponent() == null && context.getPackageManager() != null) {
            ResolveInfo resolveBindIntent = ServiceResolver.resolveBindIntent(context, intent, str);
            if (resolveBindIntent == null) {
                RtcscLogger rtcscLogger = mLog;
                rtcscLogger.e("No Service available on device to bind for intent " + intent);
                return false;
            }
            intent.setComponent(new ComponentName(resolveBindIntent.serviceInfo.packageName, resolveBindIntent.serviceInfo.name));
        }
        try {
            return context.bindService(intent, serviceConnection, 1);
        } catch (Throwable th) {
            mLog.e("Exception binding service", th);
            return false;
        }
    }

    private synchronized void reset() {
        this.mContext = null;
        this.mServiceConnection = null;
        this.mBindIntent = null;
        this.mPermission = null;
        this.mBoundSuccessfully = false;
        this.mConnected = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void unbindCommon(Context context, ServiceConnection serviceConnection) {
        try {
            context.unbindService(serviceConnection);
        } catch (Throwable th) {
            mLog.e("Exception unbinding service", th);
        }
    }

    public synchronized void addCallback(Callback callback) {
        if (callback != null) {
            if (!this.mCallbacks.contains(callback)) {
                this.mCallbacks.add(0, callback);
            }
            if (this.mConnected) {
                callback.onServiceConnected();
                callback.onServiceConnected(this.mInterfaceVersion);
            }
        } else {
            throw new IllegalArgumentException("Null callback");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final synchronized void bindComplete(boolean z) {
        this.mBoundSuccessfully = z;
        if (!z) {
            reset();
        }
        for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
            this.mCallbacks.get(size).onBind(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void bindService(Context context, ServiceConnection serviceConnection, Intent intent, String str) {
        if (context != null && serviceConnection != null && intent != null) {
            if (this.mContext != null) {
                return;
            }
            this.mContext = context;
            this.mServiceConnection = serviceConnection;
            this.mBindIntent = intent;
            performBind(context, serviceConnection, intent, str);
            return;
        }
        throw new IllegalArgumentException("Null input. Context = " + context + ", serviceConnection = " + serviceConnection + ", bindIntent = " + intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void onServiceConnected(int i) {
        this.mConnected = true;
        this.mInterfaceVersion = i;
        for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
            this.mCallbacks.get(size).onServiceConnected();
            this.mCallbacks.get(size).onServiceConnected(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void onServiceDisconnected() {
        this.mConnected = false;
        for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
            this.mCallbacks.get(size).onServiceDisconnected();
        }
        if (this.mContext != null && this.mBoundSuccessfully) {
            Context context = this.mContext;
            ServiceConnection serviceConnection = this.mServiceConnection;
            Intent intent = this.mBindIntent;
            String str = this.mPermission;
            unbindService();
            bindService(context, serviceConnection, intent, str);
        }
    }

    protected abstract void performBind(Context context, ServiceConnection serviceConnection, Intent intent, String str);

    protected abstract void performUnbind(Context context, ServiceConnection serviceConnection);

    public synchronized void removeCallback(Callback callback) {
        this.mCallbacks.remove(callback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final synchronized void unbindComplete() {
        for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
            this.mCallbacks.get(size).onUnbind();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void unbindService() {
        if (this.mContext == null) {
            return;
        }
        performUnbind(this.mContext, this.mServiceConnection);
        reset();
    }
}
