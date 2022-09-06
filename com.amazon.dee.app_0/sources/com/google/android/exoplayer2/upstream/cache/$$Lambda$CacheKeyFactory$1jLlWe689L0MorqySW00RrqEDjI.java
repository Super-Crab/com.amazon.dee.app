package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSpec;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.upstream.cache.-$$Lambda$CacheKeyFactory$1jLlWe689L0MorqySW00RrqEDjI  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$CacheKeyFactory$1jLlWe689L0MorqySW00RrqEDjI implements CacheKeyFactory {
    public static final /* synthetic */ $$Lambda$CacheKeyFactory$1jLlWe689L0MorqySW00RrqEDjI INSTANCE = new $$Lambda$CacheKeyFactory$1jLlWe689L0MorqySW00RrqEDjI();

    private /* synthetic */ $$Lambda$CacheKeyFactory$1jLlWe689L0MorqySW00RrqEDjI() {
    }

    @Override // com.google.android.exoplayer2.upstream.cache.CacheKeyFactory
    public final String buildCacheKey(DataSpec dataSpec) {
        return dataSpec.key;
    }
}
