package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation;

import androidx.annotation.NonNull;
/* loaded from: classes13.dex */
interface BasePresenterAction<V> {
    void attachView(@NonNull V v);

    void detachView();
}
