package com.google.android.gms.internal.vision;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.dynamite.DynamiteModule;
import javax.annotation.concurrent.GuardedBy;
import org.aspectj.lang.JoinPoint;
/* loaded from: classes2.dex */
public abstract class zzl<T> {
    private static String PREFIX = "com.google.android.gms.vision.dynamite";
    private final String tag;
    private final String zzci;
    private final String zzcj;
    @GuardedBy(JoinPoint.SYNCHRONIZATION_LOCK)
    private T zzcl;
    private final Context zze;
    private final Object lock = new Object();
    private boolean zzck = false;

    public zzl(Context context, String str, String str2) {
        this.zze = context;
        this.tag = str;
        String str3 = PREFIX;
        this.zzci = GeneratedOutlineSupport1.outline30(GeneratedOutlineSupport1.outline6(str2, GeneratedOutlineSupport1.outline6(str3, 1)), str3, ".", str2);
        this.zzcj = PREFIX;
    }

    public final boolean isOperational() {
        return zzp() != null;
    }

    protected abstract T zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.LoadingException;

    protected abstract void zzm() throws RemoteException;

    public final void zzo() {
        synchronized (this.lock) {
            if (this.zzcl == null) {
                return;
            }
            try {
                zzm();
            } catch (RemoteException e) {
                Log.e(this.tag, "Could not finalize native handle", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final T zzp() {
        synchronized (this.lock) {
            if (this.zzcl != null) {
                return this.zzcl;
            }
            DynamiteModule dynamiteModule = null;
            try {
                try {
                    try {
                        dynamiteModule = DynamiteModule.load(this.zze, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION, this.zzci);
                    } catch (DynamiteModule.LoadingException e) {
                        try {
                            Log.e(this.tag, "Error Loading module", e);
                        } catch (DynamiteModule.LoadingException e2) {
                            e = e2;
                            Log.e(this.tag, "Error creating remote native handle", e);
                            if (this.zzck) {
                            }
                            if (this.zzck) {
                                Log.w(this.tag, "Native handle is now available.");
                            }
                            return this.zzcl;
                        }
                    }
                } catch (DynamiteModule.LoadingException unused) {
                    dynamiteModule = DynamiteModule.load(this.zze, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION, this.zzcj);
                }
                if (dynamiteModule != null) {
                    this.zzcl = zza(dynamiteModule, this.zze);
                }
            } catch (RemoteException e3) {
                e = e3;
            }
            if (this.zzck && this.zzcl == null) {
                Log.w(this.tag, "Native handle not yet available. Reverting to no-op handle.");
                this.zzck = true;
            } else if (this.zzck && this.zzcl != null) {
                Log.w(this.tag, "Native handle is now available.");
            }
            return this.zzcl;
        }
    }
}
