package com.amazon.photos.uploader;

import android.content.Context;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UploadFrameworkContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\tHÆ\u0003J=\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e¨\u0006!"}, d2 = {"Lcom/amazon/photos/uploader/UploadFrameworkContext;", "", "directedId", "", "applicationContext", "Landroid/content/Context;", "applicationId", "applicationName", "crashReporter", "Lcom/amazon/photos/uploader/CrashReporter;", "(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/photos/uploader/CrashReporter;)V", "getApplicationContext", "()Landroid/content/Context;", "getApplicationId", "()Ljava/lang/String;", "getApplicationName", "getCrashReporter", "()Lcom/amazon/photos/uploader/CrashReporter;", "getDirectedId", "hashedDirectedId", "getHashedDirectedId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadFrameworkContext {
    @NotNull
    private final Context applicationContext;
    @NotNull
    private final String applicationId;
    @NotNull
    private final String applicationName;
    @Nullable
    private final CrashReporter crashReporter;
    @NotNull
    private final String directedId;
    @NotNull
    private final String hashedDirectedId;

    public UploadFrameworkContext(@NotNull String directedId, @NotNull Context applicationContext, @NotNull String applicationId, @NotNull String applicationName, @Nullable CrashReporter crashReporter) {
        Intrinsics.checkParameterIsNotNull(directedId, "directedId");
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(applicationId, "applicationId");
        Intrinsics.checkParameterIsNotNull(applicationName, "applicationName");
        this.directedId = directedId;
        this.applicationContext = applicationContext;
        this.applicationId = applicationId;
        this.applicationName = applicationName;
        this.crashReporter = crashReporter;
        this.hashedDirectedId = String.valueOf(this.directedId.hashCode());
    }

    public static /* synthetic */ UploadFrameworkContext copy$default(UploadFrameworkContext uploadFrameworkContext, String str, Context context, String str2, String str3, CrashReporter crashReporter, int i, Object obj) {
        if ((i & 1) != 0) {
            str = uploadFrameworkContext.directedId;
        }
        if ((i & 2) != 0) {
            context = uploadFrameworkContext.applicationContext;
        }
        Context context2 = context;
        if ((i & 4) != 0) {
            str2 = uploadFrameworkContext.applicationId;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            str3 = uploadFrameworkContext.applicationName;
        }
        String str5 = str3;
        if ((i & 16) != 0) {
            crashReporter = uploadFrameworkContext.crashReporter;
        }
        return uploadFrameworkContext.copy(str, context2, str4, str5, crashReporter);
    }

    @NotNull
    public final String component1() {
        return this.directedId;
    }

    @NotNull
    public final Context component2() {
        return this.applicationContext;
    }

    @NotNull
    public final String component3() {
        return this.applicationId;
    }

    @NotNull
    public final String component4() {
        return this.applicationName;
    }

    @Nullable
    public final CrashReporter component5() {
        return this.crashReporter;
    }

    @NotNull
    public final UploadFrameworkContext copy(@NotNull String directedId, @NotNull Context applicationContext, @NotNull String applicationId, @NotNull String applicationName, @Nullable CrashReporter crashReporter) {
        Intrinsics.checkParameterIsNotNull(directedId, "directedId");
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(applicationId, "applicationId");
        Intrinsics.checkParameterIsNotNull(applicationName, "applicationName");
        return new UploadFrameworkContext(directedId, applicationContext, applicationId, applicationName, crashReporter);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UploadFrameworkContext)) {
                return false;
            }
            UploadFrameworkContext uploadFrameworkContext = (UploadFrameworkContext) obj;
            return Intrinsics.areEqual(this.directedId, uploadFrameworkContext.directedId) && Intrinsics.areEqual(this.applicationContext, uploadFrameworkContext.applicationContext) && Intrinsics.areEqual(this.applicationId, uploadFrameworkContext.applicationId) && Intrinsics.areEqual(this.applicationName, uploadFrameworkContext.applicationName) && Intrinsics.areEqual(this.crashReporter, uploadFrameworkContext.crashReporter);
        }
        return true;
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

    @Nullable
    public final CrashReporter getCrashReporter() {
        return this.crashReporter;
    }

    @NotNull
    public final String getDirectedId() {
        return this.directedId;
    }

    @NotNull
    public final String getHashedDirectedId() {
        return this.hashedDirectedId;
    }

    public int hashCode() {
        String str = this.directedId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Context context = this.applicationContext;
        int hashCode2 = (hashCode + (context != null ? context.hashCode() : 0)) * 31;
        String str2 = this.applicationId;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.applicationName;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        CrashReporter crashReporter = this.crashReporter;
        if (crashReporter != null) {
            i = crashReporter.hashCode();
        }
        return hashCode4 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UploadFrameworkContext(directedId=");
        outline107.append(this.directedId);
        outline107.append(", applicationContext=");
        outline107.append(this.applicationContext);
        outline107.append(", applicationId=");
        outline107.append(this.applicationId);
        outline107.append(", applicationName=");
        outline107.append(this.applicationName);
        outline107.append(", crashReporter=");
        outline107.append(this.crashReporter);
        outline107.append(")");
        return outline107.toString();
    }

    public /* synthetic */ UploadFrameworkContext(String str, Context context, String str2, String str3, CrashReporter crashReporter, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, context, str2, str3, (i & 16) != 0 ? null : crashReporter);
    }
}
