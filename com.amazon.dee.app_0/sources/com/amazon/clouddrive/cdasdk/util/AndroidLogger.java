package com.amazon.clouddrive.cdasdk.util;

import android.util.Log;
import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class AndroidLogger implements Logger {
    @NonNull
    private final String prefix;

    public AndroidLogger(@NonNull String str) {
        this.prefix = GeneratedOutlineSupport1.outline72(str, "_");
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void d(@NonNull String str, @NonNull String str2) {
        String str3 = this.prefix + str;
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void e(@NonNull String str, @NonNull String str2) {
        Log.e(this.prefix + str, str2);
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void i(@NonNull String str, @NonNull String str2) {
        Log.i(this.prefix + str, str2);
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void v(@NonNull String str, @NonNull String str2) {
        String str3 = this.prefix + str;
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void w(@NonNull String str, @NonNull String str2) {
        Log.w(this.prefix + str, str2);
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void wtf(@NonNull String str, @NonNull String str2) {
        Log.wtf(this.prefix + str, str2);
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void d(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        String str3 = this.prefix + str;
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void e(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        Log.e(this.prefix + str, str2, th);
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void i(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        Log.i(this.prefix + str, str2, th);
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void v(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        String str3 = this.prefix + str;
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void w(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        Log.w(this.prefix + str, str2, th);
    }

    @Override // com.amazon.clouddrive.cdasdk.util.Logger
    public void wtf(@NonNull String str, @NonNull String str2, @NonNull Throwable th) {
        Log.wtf(this.prefix + str, str2, th);
    }
}
