package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSpec;
/* loaded from: classes2.dex */
public interface CacheKeyFactory {
    public static final CacheKeyFactory DEFAULT = $$Lambda$CacheKeyFactory$1jLlWe689L0MorqySW00RrqEDjI.INSTANCE;

    String buildCacheKey(DataSpec dataSpec);
}
