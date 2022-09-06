package com.google.android.exoplayer2.util;

import com.google.android.exoplayer2.util.SlidingPercentile;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.util.-$$Lambda$SlidingPercentile$UufTq1Ma5g1qQu0Vqc6f2CE68bE  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$SlidingPercentile$UufTq1Ma5g1qQu0Vqc6f2CE68bE implements Comparator {
    public static final /* synthetic */ $$Lambda$SlidingPercentile$UufTq1Ma5g1qQu0Vqc6f2CE68bE INSTANCE = new $$Lambda$SlidingPercentile$UufTq1Ma5g1qQu0Vqc6f2CE68bE();

    private /* synthetic */ $$Lambda$SlidingPercentile$UufTq1Ma5g1qQu0Vqc6f2CE68bE() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compare;
        compare = Float.compare(((SlidingPercentile.Sample) obj).value, ((SlidingPercentile.Sample) obj2).value);
        return compare;
    }
}
