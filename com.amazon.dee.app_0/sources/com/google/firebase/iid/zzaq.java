package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;
import javax.servlet.http.HttpServletResponse;
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
public final class zzaq {
    private static zzaq zza;
    @Nullable
    @GuardedBy("this")
    private String zzb = null;
    private Boolean zzc = null;
    private Boolean zzd = null;
    private final Queue<Intent> zze = new ArrayDeque();

    private zzaq() {
    }

    public static synchronized zzaq zza() {
        zzaq zzaqVar;
        synchronized (zzaq.class) {
            if (zza == null) {
                zza = new zzaq();
            }
            zzaqVar = zza;
        }
        return zzaqVar;
    }

    @Nullable
    private final synchronized String zzc(Context context, Intent intent) {
        ServiceInfo serviceInfo;
        if (this.zzb != null) {
            return this.zzb;
        }
        ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService != null && (serviceInfo = resolveService.serviceInfo) != null) {
            if (context.getPackageName().equals(serviceInfo.packageName) && serviceInfo.name != null) {
                if (serviceInfo.name.startsWith(".")) {
                    String valueOf = String.valueOf(context.getPackageName());
                    String valueOf2 = String.valueOf(serviceInfo.name);
                    this.zzb = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                } else {
                    this.zzb = serviceInfo.name;
                }
                return this.zzb;
            }
            String str = serviceInfo.packageName;
            String str2 = serviceInfo.name;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 94 + String.valueOf(str2).length());
            sb.append("Error resolving target intent service, skipping classname enforcement. Resolved service was: ");
            sb.append(str);
            sb.append("/");
            sb.append(str2);
            Log.e("FirebaseInstanceId", sb.toString());
            return null;
        }
        Log.e("FirebaseInstanceId", "Failed to resolve target intent service, skipping classname enforcement");
        return null;
    }

    @MainThread
    public final Intent zzb() {
        return this.zze.poll();
    }

    private final int zzb(Context context, Intent intent) {
        ComponentName startService;
        String zzc = zzc(context, intent);
        if (zzc != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                if (zzc.length() != 0) {
                    "Restricting intent to a specific service: ".concat(zzc);
                } else {
                    new String("Restricting intent to a specific service: ");
                }
            }
            intent.setClassName(context.getPackageName(), zzc);
        }
        try {
            if (zza(context)) {
                startService = zzaw.zza(context, intent);
            } else {
                startService = context.startService(intent);
            }
            if (startService != null) {
                return -1;
            }
            Log.e("FirebaseInstanceId", "Error while delivering the message: ServiceIntent not found.");
            return 404;
        } catch (IllegalStateException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(valueOf.length() + 45);
            sb.append("Failed to start service while in background: ");
            sb.append(valueOf);
            Log.e("FirebaseInstanceId", sb.toString());
            return HttpServletResponse.SC_PAYMENT_REQUIRED;
        } catch (SecurityException e2) {
            Log.e("FirebaseInstanceId", "Error while delivering the message to the serviceIntent", e2);
            return HttpServletResponse.SC_UNAUTHORIZED;
        }
    }

    @MainThread
    public final int zza(Context context, Intent intent) {
        Log.isLoggable("FirebaseInstanceId", 3);
        this.zze.offer(intent);
        Intent intent2 = new Intent("com.google.firebase.MESSAGING_EVENT");
        intent2.setPackage(context.getPackageName());
        return zzb(context, intent2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(Context context) {
        if (this.zzc == null) {
            this.zzc = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
        }
        if (!this.zzc.booleanValue()) {
            Log.isLoggable("FirebaseInstanceId", 3);
        }
        return this.zzc.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzb(Context context) {
        if (this.zzd == null) {
            this.zzd = Boolean.valueOf(context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0);
        }
        if (!this.zzc.booleanValue()) {
            Log.isLoggable("FirebaseInstanceId", 3);
        }
        return this.zzd.booleanValue();
    }
}
