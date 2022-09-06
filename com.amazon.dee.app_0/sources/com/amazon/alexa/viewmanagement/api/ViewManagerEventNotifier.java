package com.amazon.alexa.viewmanagement.api;
@Deprecated
/* loaded from: classes10.dex */
public interface ViewManagerEventNotifier extends ViewManagerLoadingDelegate {
    @Deprecated
    default void onLoadingComplete() {
        setLoadingState(false);
    }
}
