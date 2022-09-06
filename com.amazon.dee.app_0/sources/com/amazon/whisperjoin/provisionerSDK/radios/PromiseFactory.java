package com.amazon.whisperjoin.provisionerSDK.radios;

import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public interface PromiseFactory<TResult> {
    Future<TResult> getFuture();
}
