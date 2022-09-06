package com.amazon.photos.uploader.blockers;

import com.amazon.photos.uploader.PauseResume;
import javax.security.auth.Destroyable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PauseGlobalBlockerEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\u0007H\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/photos/uploader/blockers/PauseGlobalBlockerEvaluator;", "Lcom/amazon/photos/uploader/blockers/GlobalBlockerEvaluator;", "Ljavax/security/auth/Destroyable;", "pauseResumeState", "Lcom/amazon/photos/uploader/blockers/PauseResumeState;", "(Lcom/amazon/photos/uploader/blockers/PauseResumeState;)V", "destroyed", "", "destroy", "", "getBlocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "isDestroyed", "queryBlocker", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PauseGlobalBlockerEvaluator implements GlobalBlockerEvaluator, Destroyable {
    private boolean destroyed;
    private final PauseResumeState pauseResumeState;

    public PauseGlobalBlockerEvaluator(@NotNull PauseResumeState pauseResumeState) {
        Intrinsics.checkParameterIsNotNull(pauseResumeState, "pauseResumeState");
        this.pauseResumeState = pauseResumeState;
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() {
        this.pauseResumeState.clearState();
        this.destroyed = true;
    }

    @Override // com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator
    @Nullable
    public Blocker getBlocker() {
        return queryBlocker();
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override // com.amazon.photos.uploader.blockers.GlobalBlockerEvaluator
    @Nullable
    public Blocker queryBlocker() {
        if (Intrinsics.areEqual(this.pauseResumeState.getPauseResumeState(), PauseResume.PAUSE.name())) {
            return PauseBlocker.INSTANCE;
        }
        return null;
    }
}
