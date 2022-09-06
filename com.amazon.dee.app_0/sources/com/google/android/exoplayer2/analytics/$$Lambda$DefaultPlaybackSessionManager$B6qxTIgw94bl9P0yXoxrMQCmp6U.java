package com.google.android.exoplayer2.analytics;

import com.google.common.base.Supplier;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.analytics.-$$Lambda$DefaultPlaybackSessionManager$B6qxTIgw94bl9P0yXoxrMQCmp6U  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$DefaultPlaybackSessionManager$B6qxTIgw94bl9P0yXoxrMQCmp6U implements Supplier {
    public static final /* synthetic */ $$Lambda$DefaultPlaybackSessionManager$B6qxTIgw94bl9P0yXoxrMQCmp6U INSTANCE = new $$Lambda$DefaultPlaybackSessionManager$B6qxTIgw94bl9P0yXoxrMQCmp6U();

    private /* synthetic */ $$Lambda$DefaultPlaybackSessionManager$B6qxTIgw94bl9P0yXoxrMQCmp6U() {
    }

    @Override // com.google.common.base.Supplier
    /* renamed from: get */
    public final Object mo8291get() {
        String generateDefaultSessionId;
        generateDefaultSessionId = DefaultPlaybackSessionManager.generateDefaultSessionId();
        return generateDefaultSessionId;
    }
}
