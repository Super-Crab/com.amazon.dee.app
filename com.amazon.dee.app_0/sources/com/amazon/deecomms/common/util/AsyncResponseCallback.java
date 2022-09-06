package com.amazon.deecomms.common.util;
/* loaded from: classes12.dex */
public interface AsyncResponseCallback<T, U> {
    void processFailure();

    void processProgress(U... uArr);

    void processSuccessfulResponse(T t);
}
