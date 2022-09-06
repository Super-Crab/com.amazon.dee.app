package com.amazon.commsnetworking.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import okhttp3.Call;
import okhttp3.Request;
/* loaded from: classes12.dex */
public interface INetworkRequest {
    Request buildRequest();

    boolean canRetry(INetworkResponse iNetworkResponse);

    void cancel();

    int getAttemptCount();

    @Nullable
    String getHeaderByName(@NonNull String str);

    @NonNull
    String getRequestId();

    void prepareForRetry(INetworkResponse iNetworkResponse);

    INetworkRequest removeHeader(@NonNull String str);

    void setCall(@NonNull Call call);

    INetworkRequest withHeader(@NonNull String str, @NonNull String str2);

    INetworkRequest withMediaType(@NonNull String str);

    INetworkRequest withParameter(@NonNull String str, @NonNull String str2);

    INetworkRequest withPayload(@NonNull String str, @NonNull File file);

    INetworkRequest withPayload(@NonNull String str, @NonNull String str2);

    INetworkRequest withPayload(@NonNull String str, byte[] bArr);

    INetworkRequest withRetry(@NonNull RetryHandler retryHandler, int i);
}
