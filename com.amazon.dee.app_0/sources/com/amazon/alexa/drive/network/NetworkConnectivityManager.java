package com.amazon.alexa.drive.network;

import com.amazon.alexa.protocols.network.NetworkService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import rx.Observable;
/* loaded from: classes7.dex */
public class NetworkConnectivityManager {
    private NetworkService networkService = (NetworkService) GeneratedOutlineSupport1.outline21(NetworkService.class);

    public NetworkConnectivityManager() {
        Preconditions.checkNotNull(this.networkService);
    }

    public Observable<Boolean> getNetworkConnectivityStream() {
        return this.networkService.onConnectivityChanged();
    }

    public boolean isNetworkConnected() {
        return this.networkService.isConnected();
    }
}
