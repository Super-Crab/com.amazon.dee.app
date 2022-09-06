package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes12.dex */
public interface Function<F, T> {
    @CanIgnoreReturnValue
    @NullableDecl
    /* renamed from: apply */
    T mo8172apply(@NullableDecl F f);

    boolean equals(@NullableDecl Object obj);
}
