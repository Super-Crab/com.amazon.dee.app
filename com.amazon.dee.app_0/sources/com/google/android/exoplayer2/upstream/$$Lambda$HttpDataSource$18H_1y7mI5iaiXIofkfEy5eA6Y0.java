package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Predicate;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.upstream.-$$Lambda$HttpDataSource$18H_1y7mI5iaiXIofkfEy5eA6Y0  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$HttpDataSource$18H_1y7mI5iaiXIofkfEy5eA6Y0 implements Predicate {
    public static final /* synthetic */ $$Lambda$HttpDataSource$18H_1y7mI5iaiXIofkfEy5eA6Y0 INSTANCE = new $$Lambda$HttpDataSource$18H_1y7mI5iaiXIofkfEy5eA6Y0();

    private /* synthetic */ $$Lambda$HttpDataSource$18H_1y7mI5iaiXIofkfEy5eA6Y0() {
    }

    @Override // com.google.common.base.Predicate
    public final boolean apply(Object obj) {
        return Util.toLowerInvariant((String) obj);
    }
}
