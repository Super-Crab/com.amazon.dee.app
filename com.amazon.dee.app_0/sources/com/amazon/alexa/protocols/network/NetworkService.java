package com.amazon.alexa.protocols.network;

import androidx.annotation.NonNull;
import rx.Observable;
/* loaded from: classes9.dex */
public interface NetworkService {
    public static final String NETWORK_NOT_CONNECTED = "NOT_CONNECTED";

    @NonNull
    String getNetworkType();

    boolean isConnected();

    Observable<Boolean> onConnectivityChanged();
}
