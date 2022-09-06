package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.util.Consumer;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.drm.-$$Lambda$Wr1d8d1H59F4_d4ngPgBVhn5KqQ  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$Wr1d8d1H59F4_d4ngPgBVhn5KqQ implements Consumer {
    public static final /* synthetic */ $$Lambda$Wr1d8d1H59F4_d4ngPgBVhn5KqQ INSTANCE = new $$Lambda$Wr1d8d1H59F4_d4ngPgBVhn5KqQ();

    private /* synthetic */ $$Lambda$Wr1d8d1H59F4_d4ngPgBVhn5KqQ() {
    }

    @Override // com.google.android.exoplayer2.util.Consumer
    public final void accept(Object obj) {
        ((DrmSessionEventListener.EventDispatcher) obj).drmSessionAcquired();
    }
}
