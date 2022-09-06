package com.amazon.photos.uploader.blockers.unauthorizedaccess;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: UnauthorizedAccessPersistence.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0002\b\u000eJ\u0014\u0010\u000b\u001a\u0004\u0018\u00010\u000f2\b\u0010\r\u001a\u0004\u0018\u00010\u000fH\u0002J\u0015\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\fH\u0000¢\u0006\u0002\b\u0012R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/photos/uploader/blockers/unauthorizedaccess/UnauthorizedAccessPersistence;", "", "uploaderContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "clearAuthState", "", "clearAuthState$AndroidPhotosUploader_release", "getAuthStateWithDefaultFallback", "Lcom/amazon/photos/uploader/blockers/unauthorizedaccess/AuthState;", "defaultAuthState", "getAuthStateWithDefaultFallback$AndroidPhotosUploader_release", "", "persistAuthState", "authState", "persistAuthState$AndroidPhotosUploader_release", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UnauthorizedAccessPersistence {
    @NotNull
    public static final String AUTH_EVALUATOR_PREFERENCES = "UNAUTHORIZED_ACCESS_EVALUATOR";
    @NotNull
    public static final String AUTH_STATE = "BLOCKER_AUTH_STATE";
    public static final Companion Companion = new Companion(null);
    private final SharedPreferences sharedPreferences;
    private final UploadFrameworkContext uploaderContext;

    /* compiled from: UnauthorizedAccessPersistence.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/uploader/blockers/unauthorizedaccess/UnauthorizedAccessPersistence$Companion;", "", "()V", "AUTH_EVALUATOR_PREFERENCES", "", "AUTH_STATE", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public UnauthorizedAccessPersistence(@NotNull UploadFrameworkContext uploaderContext) {
        Intrinsics.checkParameterIsNotNull(uploaderContext, "uploaderContext");
        this.uploaderContext = uploaderContext;
        Context applicationContext = this.uploaderContext.getApplicationContext();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UNAUTHORIZED_ACCESS_EVALUATOR_");
        outline107.append(this.uploaderContext.getHashedDirectedId());
        outline107.append(')');
        this.sharedPreferences = applicationContext.getSharedPreferences(outline107.toString(), 0);
    }

    private final String getAuthStateWithDefaultFallback(String str) {
        return this.sharedPreferences.getString(AUTH_STATE, str);
    }

    public final void clearAuthState$AndroidPhotosUploader_release() {
        this.sharedPreferences.edit().clear().apply();
    }

    @NotNull
    public final AuthState getAuthStateWithDefaultFallback$AndroidPhotosUploader_release(@NotNull AuthState defaultAuthState) {
        Intrinsics.checkParameterIsNotNull(defaultAuthState, "defaultAuthState");
        String authStateWithDefaultFallback = getAuthStateWithDefaultFallback(defaultAuthState.name());
        if (authStateWithDefaultFallback == null) {
            authStateWithDefaultFallback = defaultAuthState.name();
        }
        return AuthState.valueOf(authStateWithDefaultFallback);
    }

    public final void persistAuthState$AndroidPhotosUploader_release(@NotNull AuthState authState) {
        Intrinsics.checkParameterIsNotNull(authState, "authState");
        if (!Intrinsics.areEqual(getAuthStateWithDefaultFallback(null), authState.name())) {
            this.sharedPreferences.edit().putString(AUTH_STATE, authState.name()).apply();
        }
    }
}
