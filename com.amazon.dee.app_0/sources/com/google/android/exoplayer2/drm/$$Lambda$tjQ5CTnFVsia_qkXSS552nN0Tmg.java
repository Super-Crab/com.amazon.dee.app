package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.util.Consumer;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.drm.-$$Lambda$tjQ5CTnFVsia_qkXSS552nN0Tmg  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$tjQ5CTnFVsia_qkXSS552nN0Tmg implements Consumer {
    public static final /* synthetic */ $$Lambda$tjQ5CTnFVsia_qkXSS552nN0Tmg INSTANCE = new $$Lambda$tjQ5CTnFVsia_qkXSS552nN0Tmg();

    private /* synthetic */ $$Lambda$tjQ5CTnFVsia_qkXSS552nN0Tmg() {
    }

    @Override // com.google.android.exoplayer2.util.Consumer
    public final void accept(Object obj) {
        ((DrmSessionEventListener.EventDispatcher) obj).drmSessionReleased();
    }
}
