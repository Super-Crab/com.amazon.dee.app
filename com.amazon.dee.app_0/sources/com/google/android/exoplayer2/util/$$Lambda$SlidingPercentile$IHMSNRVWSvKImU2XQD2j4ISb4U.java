package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.util.SlidingPercentile;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.util.-$$Lambda$SlidingPercentile$IHMSNRVWSvKImU2XQD2j4ISb4-U  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$SlidingPercentile$IHMSNRVWSvKImU2XQD2j4ISb4U implements Comparator {
    public static final /* synthetic */ $$Lambda$SlidingPercentile$IHMSNRVWSvKImU2XQD2j4ISb4U INSTANCE = new $$Lambda$SlidingPercentile$IHMSNRVWSvKImU2XQD2j4ISb4U();

    private /* synthetic */ $$Lambda$SlidingPercentile$IHMSNRVWSvKImU2XQD2j4ISb4U() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return SlidingPercentile.lambda$static$0((SlidingPercentile.Sample) obj, (SlidingPercentile.Sample) obj2);
    }
}
