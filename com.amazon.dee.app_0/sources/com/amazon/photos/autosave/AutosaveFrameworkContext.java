package com.amazon.photos.autosave;

import android.content.Context;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AutosaveFrameworkContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/amazon/photos/autosave/AutosaveFrameworkContext;", "", "directedId", "", "applicationContext", "Landroid/content/Context;", "applicationId", "applicationName", "(Ljava/lang/String;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V", "getApplicationContext", "()Landroid/content/Context;", "getApplicationId", "()Ljava/lang/String;", "getApplicationName", "getDirectedId", "hashedDirectedId", "getHashedDirectedId$AndroidPhotosAutosave_release", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AutosaveFrameworkContext {
    @NotNull
    private final Context applicationContext;
    @NotNull
    private final String applicationId;
    @NotNull
    private final String applicationName;
    @NotNull
    private final String directedId;
    @NotNull
    private final String hashedDirectedId;

    public AutosaveFrameworkContext(@NotNull String directedId, @NotNull Context applicationContext, @NotNull String applicationId, @NotNull String applicationName) {
        Intrinsics.checkParameterIsNotNull(directedId, "directedId");
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(applicationId, "applicationId");
        Intrinsics.checkParameterIsNotNull(applicationName, "applicationName");
        this.directedId = directedId;
        this.applicationContext = applicationContext;
        this.applicationId = applicationId;
        this.applicationName = applicationName;
        this.hashedDirectedId = String.valueOf(this.directedId.hashCode());
    }

    public static /* synthetic */ AutosaveFrameworkContext copy$default(AutosaveFrameworkContext autosaveFrameworkContext, String str, Context context, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = autosaveFrameworkContext.directedId;
        }
        if ((i & 2) != 0) {
            context = autosaveFrameworkContext.applicationContext;
        }
        if ((i & 4) != 0) {
            str2 = autosaveFrameworkContext.applicationId;
        }
        if ((i & 8) != 0) {
            str3 = autosaveFrameworkContext.applicationName;
        }
        return autosaveFrameworkContext.copy(str, context, str2, str3);
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

    @NotNull
    public final AutosaveFrameworkContext copy(@NotNull String directedId, @NotNull Context applicationContext, @NotNull String applicationId, @NotNull String applicationName) {
        Intrinsics.checkParameterIsNotNull(directedId, "directedId");
        Intrinsics.checkParameterIsNotNull(applicationContext, "applicationContext");
        Intrinsics.checkParameterIsNotNull(applicationId, "applicationId");
        Intrinsics.checkParameterIsNotNull(applicationName, "applicationName");
        return new AutosaveFrameworkContext(directedId, applicationContext, applicationId, applicationName);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AutosaveFrameworkContext)) {
                return false;
            }
            AutosaveFrameworkContext autosaveFrameworkContext = (AutosaveFrameworkContext) obj;
            return Intrinsics.areEqual(this.directedId, autosaveFrameworkContext.directedId) && Intrinsics.areEqual(this.applicationContext, autosaveFrameworkContext.applicationContext) && Intrinsics.areEqual(this.applicationId, autosaveFrameworkContext.applicationId) && Intrinsics.areEqual(this.applicationName, autosaveFrameworkContext.applicationName);
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

    @NotNull
    public final String getDirectedId() {
        return this.directedId;
    }

    @NotNull
    public final String getHashedDirectedId$AndroidPhotosAutosave_release() {
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
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AutosaveFrameworkContext(directedId=");
        outline107.append(this.directedId);
        outline107.append(", applicationContext=");
        outline107.append(this.applicationContext);
        outline107.append(", applicationId=");
        outline107.append(this.applicationId);
        outline107.append(", applicationName=");
        return GeneratedOutlineSupport1.outline91(outline107, this.applicationName, ")");
    }
}
