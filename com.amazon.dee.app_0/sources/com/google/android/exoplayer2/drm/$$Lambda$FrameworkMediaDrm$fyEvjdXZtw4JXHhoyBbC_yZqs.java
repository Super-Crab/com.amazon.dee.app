package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.ExoMediaDrm;
import java.util.UUID;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.drm.-$$Lambda$FrameworkMediaDrm$-fyEvj-dXZtw4JXHhoyBbC_yZqs  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$FrameworkMediaDrm$fyEvjdXZtw4JXHhoyBbC_yZqs implements ExoMediaDrm.Provider {
    public static final /* synthetic */ $$Lambda$FrameworkMediaDrm$fyEvjdXZtw4JXHhoyBbC_yZqs INSTANCE = new $$Lambda$FrameworkMediaDrm$fyEvjdXZtw4JXHhoyBbC_yZqs();

    private /* synthetic */ $$Lambda$FrameworkMediaDrm$fyEvjdXZtw4JXHhoyBbC_yZqs() {
    }

    @Override // com.google.android.exoplayer2.drm.ExoMediaDrm.Provider
    public final ExoMediaDrm acquireExoMediaDrm(UUID uuid) {
        return FrameworkMediaDrm.lambda$static$0(uuid);
    }
}
