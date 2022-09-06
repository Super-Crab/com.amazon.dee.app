package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.bootstrapSSO.IBootstrapSSOService;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bi {
    private static final String TAG = "com.amazon.identity.auth.device.bi";
    private final Callback g;
    private final Context mContext;
    private final ServiceConnection gZ = new ServiceConnection() { // from class: com.amazon.identity.auth.device.bi.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            bi.this.gX.set(true);
            bi.a(bi.this, IBootstrapSSOService.Stub.asInterface(iBinder));
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            bi.this.gX.set(false);
            io.e(bi.TAG, "Unexpectedly disconnected from service");
            bi.this.a(MAPAccountManager.BootstrapError.SERVICE_ERROR, "Unexpectedly disconnected from service");
        }
    };
    private final AtomicBoolean gW = new AtomicBoolean(false);
    private final Intent gV = new Intent("com.amazon.identity.action.BOOTSTRAP_SSO");
    private final Timer dX = new Timer(bi.class.getSimpleName());
    private final AtomicBoolean gX = new AtomicBoolean(false);
    private final ExecutorService gY = Executors.newFixedThreadPool(1);

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    class a extends TimerTask {
        private a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            bi.this.a(MAPAccountManager.BootstrapError.TIMEOUT_SERVICE, "No response received from the service");
        }

        /* synthetic */ a(bi biVar, byte b) {
            this();
        }
    }

    public bi(Context context, Callback callback) {
        this.mContext = context;
        this.g = callback;
    }

    private void bs() {
        if (this.gX.getAndSet(false)) {
            this.mContext.unbindService(this.gZ);
        }
    }

    public void br() {
        this.gW.set(false);
        Context context = this.mContext;
        if (!iq.o(context, context.getPackageName(), "MAPBootstrapSSOTargetApplication").booleanValue() && !IsolatedModeSwitcher.isAppInStaticIsolatedMode(this.mContext)) {
            a(MAPAccountManager.BootstrapError.BOOTSTRAP_NOT_ALLOWED, "Bootstrap not allowed for your application. Currently it is allowed for applications explicitly declaring meta-data \"MAPBootstrapSSOTargetApplication\" in manifest, or for isolated applications");
        } else {
            this.gY.submit(new Runnable() { // from class: com.amazon.identity.auth.device.bi.2
                @Override // java.lang.Runnable
                public void run() {
                    List<ResolveInfo> queryIntentServices = bi.this.mContext.getPackageManager().queryIntentServices(bi.this.gV, 64);
                    io.dm(bi.TAG);
                    if (queryIntentServices.isEmpty()) {
                        io.dm(bi.TAG);
                        bi.this.a(MAPAccountManager.BootstrapError.NO_SERVICE_AVAILABLE, "No service was found");
                        return;
                    }
                    ServiceInfo d = bi.this.d(queryIntentServices);
                    if (d == null) {
                        io.dm(bi.TAG);
                        bi.this.a(MAPAccountManager.BootstrapError.NO_SIGNATURE, "No app found with valid signature");
                        return;
                    }
                    bi.this.gV.setClassName(d.applicationInfo.packageName, d.name);
                    String unused = bi.TAG;
                    io.a("Service found. Starting service with package %s and class %s", d.applicationInfo.packageName, d.name);
                    bi.this.dX.schedule(new a(bi.this, (byte) 0), 5000L);
                    bi.this.mContext.bindService(bi.this.gV, bi.this.gZ, 1);
                }
            });
        }
    }

    protected ServiceInfo d(List<ResolveInfo> list) {
        Set<String> l = be.l(this.mContext);
        if (l != null && !l.isEmpty()) {
            for (ResolveInfo resolveInfo : list) {
                if (!this.mContext.getPackageName().equals(resolveInfo.serviceInfo.applicationInfo.packageName)) {
                    for (String str : bj.c(this.mContext, resolveInfo.serviceInfo.applicationInfo.packageName)) {
                        if (l.contains(str)) {
                            return resolveInfo.serviceInfo;
                        }
                    }
                    continue;
                }
            }
            return null;
        }
        io.dm(TAG);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MAPAccountManager.BootstrapError bootstrapError, String str) {
        bs();
        if (!this.gW.getAndSet(true)) {
            Bundle bundle = new Bundle();
            bundle.putInt("com.amazon.dcp.sso.ErrorCode", bootstrapError.value());
            bundle.putString("com.amazon.dcp.sso.ErrorMessage", str);
            this.g.onError(bundle);
        }
    }

    static /* synthetic */ void a(bi biVar, final IBootstrapSSOService iBootstrapSSOService) {
        biVar.gY.execute(new Runnable() { // from class: com.amazon.identity.auth.device.bi.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("appPackageName", bi.this.mContext.getPackageName());
                    Bundle bootstrapForPackage = iBootstrapSSOService.bootstrapForPackage(bundle);
                    bi.this.dX.cancel();
                    bi.a(bi.this, bootstrapForPackage);
                } catch (RemoteException e) {
                    io.e(bi.TAG, "Unexpected error from service", e);
                    bi biVar2 = bi.this;
                    MAPAccountManager.BootstrapError bootstrapError = MAPAccountManager.BootstrapError.SERVICE_ERROR;
                    biVar2.a(bootstrapError, "Unexpected error from service: " + e.getMessage());
                }
            }
        });
    }

    static /* synthetic */ void a(bi biVar, Bundle bundle) {
        biVar.bs();
        if (!biVar.gW.getAndSet(true)) {
            boolean z = bundle.getBoolean("bootstrapSuccess", false);
            bundle.remove("bootstrapSuccess");
            if (z) {
                biVar.g.onSuccess(bundle);
            } else {
                biVar.g.onError(bundle);
            }
        }
    }
}
