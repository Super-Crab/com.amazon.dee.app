package com.google.firebase.iid;

import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* loaded from: classes3.dex */
public final class zzax {
    @GuardedBy("this")
    private int zza = 0;
    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzb = new ArrayMap();
    @GuardedBy("itself")
    private final zzat zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzax(zzat zzatVar) {
        this.zzc = zzatVar;
    }

    @Nullable
    @GuardedBy("this")
    private final String zzb() {
        String zza;
        synchronized (this.zzc) {
            zza = this.zzc.zza();
        }
        if (!TextUtils.isEmpty(zza)) {
            String[] split = zza.split(",");
            if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                return split[1];
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized Task<Void> zza(String str) {
        String zza;
        TaskCompletionSource<Void> taskCompletionSource;
        synchronized (this.zzc) {
            zza = this.zzc.zza();
            zzat zzatVar = this.zzc;
            StringBuilder sb = new StringBuilder(String.valueOf(zza).length() + 1 + String.valueOf(str).length());
            sb.append(zza);
            sb.append(",");
            sb.append(str);
            zzatVar.zza(sb.toString());
        }
        taskCompletionSource = new TaskCompletionSource<>();
        this.zzb.put(Integer.valueOf(this.zza + (TextUtils.isEmpty(zza) ? 0 : zza.split(",").length - 1)), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    private final synchronized boolean zzb(String str) {
        synchronized (this.zzc) {
            String zza = this.zzc.zza();
            String valueOf = String.valueOf(str);
            if (zza.startsWith(valueOf.length() != 0 ? ",".concat(valueOf) : new String(","))) {
                String valueOf2 = String.valueOf(str);
                this.zzc.zza(zza.substring((valueOf2.length() != 0 ? ",".concat(valueOf2) : new String(",")).length()));
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized boolean zza() {
        return zzb() != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public final boolean zza(FirebaseInstanceId firebaseInstanceId) throws IOException {
        TaskCompletionSource<Void> remove;
        while (true) {
            synchronized (this) {
                String zzb = zzb();
                if (zzb == null) {
                    FirebaseInstanceId.zzd();
                    return true;
                } else if (!zza(firebaseInstanceId, zzb)) {
                    return false;
                } else {
                    synchronized (this) {
                        remove = this.zzb.remove(Integer.valueOf(this.zza));
                        zzb(zzb);
                        this.zza++;
                    }
                    if (remove != null) {
                        remove.setResult(null);
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
        if (r3 == 1) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0036, code lost:
        r6.zzc(r7);
        com.google.firebase.iid.FirebaseInstanceId.zzd();
     */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean zza(com.google.firebase.iid.FirebaseInstanceId r6, java.lang.String r7) throws java.io.IOException {
        /*
            java.lang.String r0 = "!"
            java.lang.String[] r7 = r7.split(r0)
            int r0 = r7.length
            r1 = 1
            r2 = 2
            if (r0 != r2) goto L91
            r0 = 0
            r2 = r7[r0]
            r7 = r7[r1]
            r3 = -1
            int r4 = r2.hashCode()     // Catch: java.io.IOException -> L44
            r5 = 83
            if (r4 == r5) goto L28
            r5 = 85
            if (r4 == r5) goto L1e
            goto L31
        L1e:
            java.lang.String r4 = "U"
            boolean r2 = r2.equals(r4)     // Catch: java.io.IOException -> L44
            if (r2 == 0) goto L31
            r3 = r1
            goto L31
        L28:
            java.lang.String r4 = "S"
            boolean r2 = r2.equals(r4)     // Catch: java.io.IOException -> L44
            if (r2 == 0) goto L31
            r3 = r0
        L31:
            if (r3 == 0) goto L3d
            if (r3 == r1) goto L36
            goto L91
        L36:
            r6.zzc(r7)     // Catch: java.io.IOException -> L44
            com.google.firebase.iid.FirebaseInstanceId.zzd()     // Catch: java.io.IOException -> L44
            goto L91
        L3d:
            r6.zzb(r7)     // Catch: java.io.IOException -> L44
            com.google.firebase.iid.FirebaseInstanceId.zzd()     // Catch: java.io.IOException -> L44
            goto L91
        L44:
            r6 = move-exception
            java.lang.String r7 = r6.getMessage()
            java.lang.String r1 = "SERVICE_NOT_AVAILABLE"
            boolean r7 = r1.equals(r7)
            java.lang.String r1 = "FirebaseInstanceId"
            if (r7 != 0) goto L6d
            java.lang.String r7 = r6.getMessage()
            java.lang.String r2 = "INTERNAL_SERVER_ERROR"
            boolean r7 = r2.equals(r7)
            if (r7 == 0) goto L60
            goto L6d
        L60:
            java.lang.String r7 = r6.getMessage()
            if (r7 != 0) goto L6c
            java.lang.String r6 = "Topic operation failed without exception message. Will retry Topic operation."
            android.util.Log.e(r1, r6)
            return r0
        L6c:
            throw r6
        L6d:
            java.lang.String r6 = r6.getMessage()
            r7 = 53
            int r7 = com.android.tools.r8.GeneratedOutlineSupport1.outline6(r6, r7)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r7)
            java.lang.String r7 = "Topic operation failed: "
            r2.append(r7)
            r2.append(r6)
            java.lang.String r6 = ". Will retry Topic operation."
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            android.util.Log.e(r1, r6)
            return r0
        L91:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzax.zza(com.google.firebase.iid.FirebaseInstanceId, java.lang.String):boolean");
    }
}
