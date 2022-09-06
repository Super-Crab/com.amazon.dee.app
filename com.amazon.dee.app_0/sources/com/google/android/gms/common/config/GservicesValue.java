package com.google.android.gms.common.config;

import android.content.Context;
import android.os.Binder;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashSet;
import javax.annotation.concurrent.GuardedBy;
@KeepForSdk
/* loaded from: classes2.dex */
public abstract class GservicesValue<T> {
    private static final Object sLock = new Object();
    private static zza zzbm;
    private static int zzbn;
    private static Context zzbo;
    @GuardedBy("sLock")
    private static HashSet<String> zzbp;
    protected final String mKey;
    protected final T zzbq;
    private T zzbr = null;

    /* loaded from: classes2.dex */
    private interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zza(String str, Boolean bool);

        Float zza(String str, Float f);

        Integer zza(String str, Integer num);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GservicesValue(String str, T t) {
        this.mKey = str;
        this.zzbq = t;
    }

    @KeepForSdk
    public static boolean isInitialized() {
        synchronized (sLock) {
        }
        return false;
    }

    @KeepForSdk
    public static GservicesValue<Boolean> value(String str, boolean z) {
        return new com.google.android.gms.common.config.zza(str, Boolean.valueOf(z));
    }

    private static boolean zzi() {
        synchronized (sLock) {
        }
        return false;
    }

    @KeepForSdk
    public final T get() {
        T t = this.zzbr;
        if (t != null) {
            return t;
        }
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        synchronized (sLock) {
        }
        synchronized (sLock) {
            zzbp = null;
            zzbo = null;
        }
        try {
            try {
                return zzd(this.mKey);
            } catch (SecurityException unused) {
                long clearCallingIdentity = Binder.clearCallingIdentity();
                T zzd = zzd(this.mKey);
                Binder.restoreCallingIdentity(clearCallingIdentity);
                return zzd;
            }
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    @KeepForSdk
    @Deprecated
    public final T getBinderSafe() {
        return get();
    }

    @VisibleForTesting
    @KeepForSdk
    public void override(T t) {
        Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        this.zzbr = t;
        synchronized (sLock) {
            zzi();
        }
    }

    @VisibleForTesting
    @KeepForSdk
    public void resetOverride() {
        this.zzbr = null;
    }

    protected abstract T zzd(String str);

    @KeepForSdk
    public static GservicesValue<Long> value(String str, Long l) {
        return new zzb(str, l);
    }

    @KeepForSdk
    public static GservicesValue<Integer> value(String str, Integer num) {
        return new zzc(str, num);
    }

    @KeepForSdk
    public static GservicesValue<Float> value(String str, Float f) {
        return new zzd(str, f);
    }

    @KeepForSdk
    public static GservicesValue<String> value(String str, String str2) {
        return new zze(str, str2);
    }
}
