package com.amazon.photos.uploader.blockers;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.work.Operation;
import com.amazon.photos.uploader.PauseResume;
import com.amazon.photos.uploader.SchedulingCallback;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PauseResumeState.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/photos/uploader/blockers/PauseResumeState;", "", "context", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "schedulingCallback", "Lcom/amazon/photos/uploader/SchedulingCallback;", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;Lcom/amazon/photos/uploader/SchedulingCallback;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "clearState", "", "getPauseResumeState", "", "setPauseResumeState", "Landroidx/work/Operation;", "state", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PauseResumeState {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String PAUSE_RESUME_STATE_PREFERENCES = "UPLOADER_PAUSE_RESUME_STATE";
    @NotNull
    public static final String UPLOAD_PAUSE_RESUME_STATE = "UPLOAD_PAUSE_RESUME_STATE";
    private final SchedulingCallback schedulingCallback;
    private final SharedPreferences sharedPreferences;

    /* compiled from: PauseResumeState.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/uploader/blockers/PauseResumeState$Companion;", "", "()V", "PAUSE_RESUME_STATE_PREFERENCES", "", PauseResumeState.UPLOAD_PAUSE_RESUME_STATE, "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public PauseResumeState(@NotNull UploadFrameworkContext context, @NotNull SchedulingCallback schedulingCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(schedulingCallback, "schedulingCallback");
        this.schedulingCallback = schedulingCallback;
        Context applicationContext = context.getApplicationContext();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UPLOADER_PAUSE_RESUME_STATE_");
        outline107.append(context.getHashedDirectedId());
        outline107.append(')');
        this.sharedPreferences = applicationContext.getSharedPreferences(outline107.toString(), 0);
    }

    public final void clearState() {
        this.sharedPreferences.edit().clear().apply();
    }

    @NotNull
    public final String getPauseResumeState() {
        String string = this.sharedPreferences.getString(UPLOAD_PAUSE_RESUME_STATE, PauseResume.RESUME.name());
        return string == null ? PauseResume.RESUME.name() : string;
    }

    @Nullable
    public final Operation setPauseResumeState(@NotNull String state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        if (!Intrinsics.areEqual(state, getPauseResumeState())) {
            SharedPreferences.Editor edit = this.sharedPreferences.edit();
            edit.putString(UPLOAD_PAUSE_RESUME_STATE, state);
            edit.apply();
            return this.schedulingCallback.reevaluateBlockers();
        }
        return null;
    }
}
