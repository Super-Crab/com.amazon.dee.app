package com.amazon.commsnetworking.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
/* loaded from: classes12.dex */
public interface INetworkResponse extends AutoCloseable {
    @Override // java.lang.AutoCloseable
    void close() throws IOException;

    @Nullable
    String getAmazonRID();

    @NonNull
    String getAmazonRequestID();

    @Nullable
    String getBody();

    @Nullable
    InputStream getByteStream();

    @Nullable
    byte[] getBytes();

    Long getCallDuration();

    Map<String, List<String>> getHeaders();

    int getStatusCode();

    boolean isSuccessful();
}
