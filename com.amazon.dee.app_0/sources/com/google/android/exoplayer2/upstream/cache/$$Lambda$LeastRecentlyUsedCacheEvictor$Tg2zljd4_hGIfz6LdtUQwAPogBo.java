package com.google.android.exoplayer2.upstream.cache;

import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.upstream.cache.-$$Lambda$LeastRecentlyUsedCacheEvictor$Tg2zljd4_hGIfz6LdtUQwAPogBo  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$LeastRecentlyUsedCacheEvictor$Tg2zljd4_hGIfz6LdtUQwAPogBo implements Comparator {
    public static final /* synthetic */ $$Lambda$LeastRecentlyUsedCacheEvictor$Tg2zljd4_hGIfz6LdtUQwAPogBo INSTANCE = new $$Lambda$LeastRecentlyUsedCacheEvictor$Tg2zljd4_hGIfz6LdtUQwAPogBo();

    private /* synthetic */ $$Lambda$LeastRecentlyUsedCacheEvictor$Tg2zljd4_hGIfz6LdtUQwAPogBo() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = LeastRecentlyUsedCacheEvictor.compare((CacheSpan) obj, (CacheSpan) obj2);
        return compare;
    }
}
