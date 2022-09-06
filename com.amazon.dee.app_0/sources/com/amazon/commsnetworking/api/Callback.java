package com.amazon.commsnetworking.api;

import com.amazon.commsnetworking.NetworkException;
/* loaded from: classes12.dex */
public interface Callback {
    void onFailure(INetworkRequest iNetworkRequest, NetworkException networkException);

    void onResult(INetworkRequest iNetworkRequest, INetworkResponse iNetworkResponse);
}
