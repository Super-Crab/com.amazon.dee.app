package com.amazon.photos.discovery.internal.util;

import android.util.Log;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.facebook.common.callercontext.ContextChain;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AndroidLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J \u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u0010"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/AndroidLogger;", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "()V", "d", "", "tag", "", "message", "t", "", "e", ContextChain.TAG_INFRA, "logCustomerEvent", "v", "w", "wtf", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AndroidLogger implements Logger {
    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void d(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void d(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void e(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.e(tag, message);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void i(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.i(tag, message);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void logCustomerEvent(@NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void v(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void v(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void w(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.w(tag, message);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void wtf(@NotNull String tag, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Log.wtf(tag, message);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void e(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        Log.e(tag, message, t);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void i(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        Log.i(tag, message, t);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void w(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        Log.w(tag, message, t);
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.Logger
    public void wtf(@NotNull String tag, @NotNull String message, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(t, "t");
        Log.wtf(tag, message, t);
    }
}
