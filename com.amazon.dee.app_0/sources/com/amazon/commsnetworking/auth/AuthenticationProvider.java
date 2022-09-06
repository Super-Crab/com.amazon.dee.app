package com.amazon.commsnetworking.auth;

import androidx.annotation.NonNull;
import com.amazon.commsnetworking.api.INetworkRequest;
/* loaded from: classes12.dex */
public interface AuthenticationProvider {
    void authenticateRequest(@NonNull INetworkRequest iNetworkRequest);
}
