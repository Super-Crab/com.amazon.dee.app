package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import okhttp3.Response;
/* loaded from: classes12.dex */
public class CommsNetworkResponse implements INetworkResponse {
    private INetworkRequest request;
    private Response response;

    public CommsNetworkResponse(@NonNull INetworkRequest iNetworkRequest, @NonNull Response response) {
        this.request = iNetworkRequest;
        this.response = response;
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse, java.lang.AutoCloseable
    public void close() throws IOException {
        this.response.close();
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    @Nullable
    public String getAmazonRID() {
        Map<String, List<String>> headers = getHeaders();
        if (!headers.containsKey(Constants.HEADER_AMAZON_RID)) {
            return null;
        }
        return headers.get(Constants.HEADER_AMAZON_RID).get(0);
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    @NonNull
    public String getAmazonRequestID() {
        String headerByName = this.request.getHeaderByName("X-Amzn-RequestId");
        return headerByName == null ? "REQUEST_ID_NOT_SET" : headerByName;
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    @Nullable
    public String getBody() {
        if (this.response.body() == null) {
            return null;
        }
        try {
            return this.response.body().string();
        } catch (IOException unused) {
            return null;
        } finally {
            this.response.close();
        }
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    @Nullable
    public InputStream getByteStream() {
        if (this.response.body() == null) {
            return null;
        }
        return this.response.body().byteStream();
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    @Nullable
    public byte[] getBytes() {
        if (this.response.body() == null) {
            return null;
        }
        try {
            return this.response.body().bytes();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    public Long getCallDuration() {
        return Long.valueOf(this.response.receivedResponseAtMillis() - this.response.sentRequestAtMillis());
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    public Map<String, List<String>> getHeaders() {
        return this.response.headers().toMultimap();
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    public int getStatusCode() {
        return this.response.code();
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    public boolean isSuccessful() {
        return this.response.isSuccessful();
    }
}
