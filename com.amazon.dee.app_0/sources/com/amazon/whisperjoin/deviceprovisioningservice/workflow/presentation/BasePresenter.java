package com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation;

import androidx.annotation.NonNull;
/* loaded from: classes13.dex */
public abstract class BasePresenter<V> implements BasePresenterAction<V> {
    protected V mView;

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.BasePresenterAction
    public void attachView(@NonNull V v) {
        if (this.mView == null) {
            this.mView = v;
            return;
        }
        throw new IllegalStateException("View already attached!");
    }

    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.BasePresenterAction, com.amazon.whisperjoin.deviceprovisioningservice.workflow.presentation.AutoDiscoveryPresenterContract.Action
    public void detachView() {
        if (this.mView != null) {
            this.mView = null;
            return;
        }
        throw new IllegalStateException("No view attached!");
    }
}
