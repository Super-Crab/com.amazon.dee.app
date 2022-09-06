package com.google.android.gms.common.logging;

import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;
@KeepForSdk
/* loaded from: classes2.dex */
public class Logger {
    private final String mTag;
    private final String zzei;
    private final GmsLogger zzew;
    private final int zzex;

    /* JADX WARN: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Logger(java.lang.String r7, java.lang.String... r8) {
        /*
            r6 = this;
            int r0 = r8.length
            if (r0 != 0) goto L6
            java.lang.String r8 = ""
            goto L32
        L6:
            r0 = 91
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline104(r0)
            int r1 = r8.length
            r2 = 0
        Le:
            if (r2 >= r1) goto L24
            r3 = r8[r2]
            int r4 = r0.length()
            r5 = 1
            if (r4 <= r5) goto L1e
            java.lang.String r4 = ","
            r0.append(r4)
        L1e:
            r0.append(r3)
            int r2 = r2 + 1
            goto Le
        L24:
            r8 = 93
            r0.append(r8)
            r8 = 32
            r0.append(r8)
            java.lang.String r8 = r0.toString()
        L32:
            r6.<init>(r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.logging.Logger.<init>(java.lang.String, java.lang.String[]):void");
    }

    private final String format(String str, @Nullable Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        return this.zzei.concat(str);
    }

    @KeepForSdk
    public void d(String str, @Nullable Object... objArr) {
        if (isLoggable(3)) {
            format(str, objArr);
        }
    }

    @KeepForSdk
    public void e(String str, @Nullable Object... objArr) {
        Log.e(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    public void i(String str, @Nullable Object... objArr) {
        Log.i(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    public boolean isLoggable(int i) {
        return this.zzex <= i;
    }

    @KeepForSdk
    public void v(String str, @Nullable Object... objArr) {
        if (isLoggable(2)) {
            format(str, objArr);
        }
    }

    @KeepForSdk
    public void w(String str, @Nullable Object... objArr) {
        Log.w(this.mTag, format(str, objArr));
    }

    @KeepForSdk
    public void wtf(String str, Throwable th, @Nullable Object... objArr) {
        Log.wtf(this.mTag, format(str, objArr), th);
    }

    @KeepForSdk
    public void e(String str, Throwable th, @Nullable Object... objArr) {
        Log.e(this.mTag, format(str, objArr), th);
    }

    @KeepForSdk
    public void wtf(Throwable th) {
        Log.wtf(this.mTag, th);
    }

    private Logger(String str, String str2) {
        this.zzei = str2;
        this.mTag = str;
        this.zzew = new GmsLogger(str);
        int i = 2;
        while (7 >= i && !Log.isLoggable(this.mTag, i)) {
            i++;
        }
        this.zzex = i;
    }
}
