package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
@Deprecated
/* loaded from: classes3.dex */
public class ComputationException extends RuntimeException {
    private static final long serialVersionUID = 0;

    public ComputationException(@NullableDecl Throwable th) {
        super(th);
    }
}
