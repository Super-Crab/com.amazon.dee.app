package com.amazon.device.messaging;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.device.messaging.ADMConstants;
import com.amazon.device.messaging.ADMRegistrationException;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public class ADM {
    private static final String TAG = "ADM";
    private final Context mContext;
    private final ContentResolver mResolver;

    /* renamed from: com.amazon.device.messaging.ADM$2  reason: invalid class name */
    /* loaded from: classes12.dex */
    class AnonymousClass2 implements Callable<Bundle> {
        final /* synthetic */ Bundle val$extras;
        final /* synthetic */ String val$method;
        final /* synthetic */ Uri val$uri;

        AnonymousClass2(Uri uri, String str, Bundle bundle) {
            this.val$uri = uri;
            this.val$method = str;
            this.val$extras = bundle;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        /* renamed from: call */
        public Bundle mo3827call() throws Exception {
            try {
                return ADM.this.mResolver.call(this.val$uri, this.val$method, (String) null, this.val$extras);
            } catch (IllegalArgumentException e) {
                Log.e("ADM", "", e);
                return null;
            }
        }
    }

    @FireOsSdk
    public ADM(Context context) {
        this.mContext = context.getApplicationContext();
        this.mResolver = this.mContext.getContentResolver();
    }

    private Callable<Bundle> buildContentProviderCallable(final Uri uri, final String str, final Bundle bundle) {
        int i = Build.VERSION.SDK_INT;
        return new Callable<Bundle>() { // from class: com.amazon.device.messaging.ADM.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            @TargetApi(17)
            /* renamed from: call */
            public Bundle mo3826call() throws Exception {
                ContentProviderClient acquireUnstableContentProviderClient = ADM.this.mResolver.acquireUnstableContentProviderClient(uri);
                Bundle bundle2 = null;
                if (acquireUnstableContentProviderClient == null) {
                    return null;
                }
                try {
                    try {
                        bundle2 = acquireUnstableContentProviderClient.call(str, null, bundle);
                    } catch (RemoteException e) {
                        Log.e("ADM", "", e);
                    }
                    return bundle2;
                } finally {
                    acquireUnstableContentProviderClient.release();
                }
            }
        };
    }

    private Intent createDummyIntent() {
        return new Intent().setAction("dummyString").addCategory("dummyString").setClassName("dummyString", "dummyString").setPackage("dummyString");
    }

    private void ensureNotRunningOnMainThread() throws ADMRegistrationException {
        if (Looper.getMainLooper() == null || Looper.getMainLooper() != Looper.myLooper()) {
            return;
        }
        throw new ADMRegistrationException(ADMRegistrationException.ErrorCode.MainThread, "Blocking registration calls are not permitted on the main thread");
    }

    private static void requireADM(Context context) {
        if (isSupported(context)) {
            return;
        }
        throw new IllegalStateException("ADM is not supported on the device");
    }

    private Bundle retryableContentProviderCall(Uri uri, String str, Bundle bundle) throws RPCFailureException {
        Bundle bundle2 = null;
        for (int i = 0; i < 3 && bundle2 == null; i++) {
            if (i > 0) {
                String.format("Retry: #%d", Integer.valueOf(i - 1));
            }
            try {
                bundle2 = buildContentProviderCallable(uri, str, bundle).call();
            } catch (Exception e) {
                Log.e("ADM", "", e);
            }
        }
        if (bundle2 != null) {
            return bundle2;
        }
        throw new RPCFailureException(String.format("Unable to establish an RPC connection after %d attempts", 3));
    }

    @FireOsSdk
    public String getRegistrationId() {
        return getRegistrationId(ApiFlavor.ADM);
    }

    @FireOsSdk
    public boolean isSupported() {
        return isSupported(this.mContext);
    }

    public String register(String... strArr) throws ADMRegistrationException {
        return register(ApiFlavor.ADM, strArr);
    }

    @FireOsSdk
    public void startRegister() {
        requireADM(this.mContext);
        Intent intent = new Intent(ADMConstants.LowLevel.ACTION_REGISTER);
        intent.putExtra(ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT, PendingIntent.getBroadcast(this.mContext, 0, new Intent().setAction("dummyString").addCategory("dummyString").setClassName("dummyString", "dummyString").setPackage("dummyString"), 0));
        this.mContext.sendBroadcast(intent, "com.amazon.device.messaging.permission.SEND");
    }

    @FireOsSdk
    public void startUnregister() {
        requireADM(this.mContext);
        Intent intent = new Intent(ADMConstants.LowLevel.ACTION_UNREGISTER);
        intent.putExtra(ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT, PendingIntent.getBroadcast(this.mContext, 0, createDummyIntent(), 0));
        this.mContext.sendBroadcast(intent, "com.amazon.device.messaging.permission.SEND");
    }

    public void unregister() throws ADMRegistrationException {
        unregister(ApiFlavor.ADM);
    }

    private static boolean isSupported(Context context) {
        PackageManager packageManager = context.getPackageManager();
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(new Intent(ADMConstants.LowLevel.ACTION_REGISTER), 0)) {
            if (resolveInfo.activityInfo.packageName != null && packageManager.checkPermission("com.amazon.device.messaging.permission.SEND", resolveInfo.activityInfo.packageName) == 0) {
                return true;
            }
        }
        return false;
    }

    public String getRegistrationId(ApiFlavor apiFlavor) {
        requireADM(this.mContext);
        try {
            Bundle bundle = new Bundle();
            bundle.putString("package_name", this.mContext.getPackageName());
            bundle.putString(ADMRegistrationConstants.ARG_API_FLAVOR, apiFlavor.name());
            Log.i("ADM", "Retrieving the registration id.");
            return retryableContentProviderCall(ADMRegistrationConstants.CONTENT_URI, ADMRegistrationConstants.METHOD_GET_REGISTRATION, bundle).getString("response");
        } catch (RPCFailureException unused) {
            Log.e("ADM", "Failed to retrieve registration id.");
            return null;
        }
    }

    public String register(ApiFlavor apiFlavor, String... strArr) throws ADMRegistrationException {
        requireADM(this.mContext);
        ensureNotRunningOnMainThread();
        if (strArr != null && strArr.length != 0) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("package_name", this.mContext.getPackageName());
                List asList = Arrays.asList(strArr);
                if (!asList.contains(null)) {
                    bundle.putStringArrayList(ADMRegistrationConstants.ARG_SENDERS, new ArrayList<>(asList));
                    bundle.putString(ADMRegistrationConstants.ARG_API_FLAVOR, apiFlavor.name());
                    Log.i("ADM", "Registering...");
                    Bundle retryableContentProviderCall = retryableContentProviderCall(ADMRegistrationConstants.CONTENT_URI, ADMRegistrationConstants.METHOD_REGISTER, bundle);
                    if (!retryableContentProviderCall.containsKey(ADMRegistrationConstants.CALL_EXCEPTION)) {
                        return retryableContentProviderCall.getString("response");
                    }
                    throw ((ADMRegistrationException) retryableContentProviderCall.getSerializable(ADMRegistrationConstants.CALL_EXCEPTION));
                }
                throw new IllegalArgumentException("A sender may not be null");
            } catch (RPCFailureException e) {
                Log.e("ADM", "Registration failed");
                throw new ADMRegistrationException(ADMRegistrationException.ErrorCode.ServiceNotAvailable, e);
            }
        }
        throw new IllegalArgumentException("Missing senders");
    }

    public void unregister(ApiFlavor apiFlavor) throws ADMRegistrationException {
        requireADM(this.mContext);
        ensureNotRunningOnMainThread();
        try {
            Bundle bundle = new Bundle();
            bundle.putString("package_name", this.mContext.getPackageName());
            bundle.putString(ADMRegistrationConstants.ARG_API_FLAVOR, apiFlavor.name());
            Log.i("ADM", "Unregistering...");
            Bundle retryableContentProviderCall = retryableContentProviderCall(ADMRegistrationConstants.CONTENT_URI, ADMRegistrationConstants.METHOD_UNREGISTER, bundle);
            if (!retryableContentProviderCall.containsKey(ADMRegistrationConstants.CALL_EXCEPTION)) {
                return;
            }
            throw ((ADMRegistrationException) retryableContentProviderCall.getSerializable(ADMRegistrationConstants.CALL_EXCEPTION));
        } catch (RPCFailureException e) {
            Log.e("ADM", "Unregistration failed");
            throw new ADMRegistrationException(ADMRegistrationException.ErrorCode.ServiceNotAvailable, e);
        }
    }
}
