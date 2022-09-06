package com.amazon.photos.uploadbundle.internal;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FrameworkContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/photos/uploadbundle/internal/FrameworkContext;", "", "directedId", "", "applicationContext", "Landroid/content/Context;", "applicationId", "applicationName", "(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V", "getApplicationContext", "()Landroid/content/Context;", "getApplicationId", "()Ljava/lang/String;", "getApplicationName", "getDirectedId", "AndroidPhotosUploadBundle_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class FrameworkContext {
    @NotNull
    private final Context applicationContext;
    @NotNull
    private final String applicationId;
    @NotNull
    private final String applicationName;
    @NotNull
    private final String directedId;

    public FrameworkContext(@NotNull String directedId, @NotNull Context applicationContext, @NotNull String applicationId, @NotNull String applicationName) {
        Intrinsics.checkParameterIsNotNull(directedId, "directedId");
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(applicationId, "applicationId");
        Intrinsics.checkParameterIsNotNull(applicationName, "applicationName");
        this.directedId = directedId;
        this.applicationContext = applicationContext;
        this.applicationId = applicationId;
        this.applicationName = applicationName;
    }

    @NotNull
    public final Context getApplicationContext() {
        return this.applicationContext;
    }

    @NotNull
    public final String getApplicationId() {
        return this.applicationId;
    }

    @NotNull
    public final String getApplicationName() {
        return this.applicationName;
    }

    @NotNull
    public final String getDirectedId() {
        return this.directedId;
    }
}
