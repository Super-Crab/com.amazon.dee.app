package com.facebook.callercontext;

import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public interface CallerContextVerifier {
    void verifyCallerContext(@Nullable Object callerContext, boolean isPrefetch);
}
