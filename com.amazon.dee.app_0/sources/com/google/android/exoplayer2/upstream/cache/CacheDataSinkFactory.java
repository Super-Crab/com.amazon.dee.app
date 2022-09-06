package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.upstream.DataSink;
@Deprecated
/* loaded from: classes2.dex */
public final class CacheDataSinkFactory implements DataSink.Factory {
    private final int bufferSize;
    private final Cache cache;
    private final long fragmentSize;

    public CacheDataSinkFactory(Cache cache, long j) {
        this(cache, j, CacheDataSink.DEFAULT_BUFFER_SIZE);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSink.Factory
    public DataSink createDataSink() {
        return new CacheDataSink(this.cache, this.fragmentSize, this.bufferSize);
    }

    public CacheDataSinkFactory(Cache cache, long j, int i) {
        this.cache = cache;
        this.fragmentSize = j;
        this.bufferSize = i;
    }
}
