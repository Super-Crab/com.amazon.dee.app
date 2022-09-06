package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkResponse;
import com.amazon.commsnetworking.api.RetryHandler;
import com.amazon.commsnetworking.metrics.MetricMetadata;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
/* loaded from: classes12.dex */
public class CommsNetworkRequest implements INetworkRequest {
    static int MAX_ATTEMPT = 5;
    int attempt;
    Request.Builder builder;
    private Call call;
    Integer maxAttempt;
    String mediaType;
    private Method method;
    byte[] payloadBytes;
    File payloadFile;
    String payloadString;
    private PayloadType payloadType;
    private String requestId;
    RetryHandler retryHandler;
    HttpUrl.Builder urlBuilder;

    /* renamed from: com.amazon.commsnetworking.CommsNetworkRequest$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method = new int[Method.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$PayloadType;

        static {
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method[Method.GET.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method[Method.POST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method[Method.PUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method[Method.PATCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$Method[Method.DELETE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$PayloadType = new int[PayloadType.values().length];
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$PayloadType[PayloadType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$PayloadType[PayloadType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$commsnetworking$CommsNetworkRequest$PayloadType[PayloadType.FILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class Builder {
        Method method = null;
        RequestTag requestTag = null;
        Request.Builder builder = null;
        HttpUrl.Builder urlBuilder = null;

        private void attachRequestTag() {
            this.builder.tag(RequestTag.class, this.requestTag);
        }

        public CommsNetworkRequest build() {
            if (this.urlBuilder != null) {
                if (this.builder == null) {
                    withDefaultRequestBuilder();
                }
                if (this.requestTag != null) {
                    attachRequestTag();
                    return new CommsNetworkRequest(this);
                }
                throw new IllegalStateException("A RequestTag must be set.");
            }
            throw new IllegalStateException("URL must be set.");
        }

        @VisibleForTesting
        String generateRequestId(@Nullable String str) {
            StringBuilder sb = new StringBuilder();
            if (str == null) {
                str = "";
            }
            sb.append(str);
            sb.append(UUID.randomUUID().toString());
            return sb.toString();
        }

        public Builder withCommsNetworkingRequestTag(@Nullable MetricMetadata metricMetadata) {
            return withCommsNetworkingRequestTag(null, metricMetadata);
        }

        @VisibleForTesting
        Builder withDefaultRequestBuilder() {
            this.builder = new Request.Builder();
            return this;
        }

        public Builder withMethod(@NonNull Method method) {
            this.method = method;
            return this;
        }

        @VisibleForTesting
        public Builder withRequestTag(@NonNull RequestTag requestTag) {
            this.requestTag = requestTag;
            return this;
        }

        public Builder withUrl(@NonNull String str, @NonNull String str2) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "/");
            if (str2.startsWith("/")) {
                str2 = str2.substring(1);
            }
            outline113.append(str2);
            this.urlBuilder = HttpUrl.parse(outline113.toString()).newBuilder();
            return this;
        }

        public Builder withCommsNetworkingRequestTag(@Nullable String str, @Nullable MetricMetadata metricMetadata) {
            withRequestTag(new RequestTag(generateRequestId(str), metricMetadata));
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public enum Method {
        GET,
        POST,
        PUT,
        PATCH,
        DELETE
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public enum PayloadType {
        STRING,
        FILE,
        BYTES,
        NONE
    }

    CommsNetworkRequest(Builder builder) {
        this.payloadType = PayloadType.NONE;
        this.attempt = 0;
        this.method = builder.method;
        this.requestId = builder.requestTag.getRequestId();
        this.urlBuilder = builder.urlBuilder;
        this.builder = builder.builder;
    }

    public static CommsNetworkRequest delete(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        return new CommsNetworkRequest(Method.DELETE, str, str2, str3, str4);
    }

    public static CommsNetworkRequest get(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        return new CommsNetworkRequest(Method.GET, str, str2, str3, str4);
    }

    public static CommsNetworkRequest patch(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        return new CommsNetworkRequest(Method.PATCH, str, str2, str3, str4);
    }

    public static CommsNetworkRequest post(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        return new CommsNetworkRequest(Method.POST, str, str2, str3, str4);
    }

    private RequestBody prepareRequestBody() {
        int ordinal = this.payloadType.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return RequestBody.create(MediaType.parse(this.mediaType), this.payloadFile);
            }
            if (ordinal != 2) {
                String str = this.mediaType;
                return RequestBody.create(str == null ? null : MediaType.parse(str), new byte[0]);
            }
            return RequestBody.create(MediaType.parse(this.mediaType), this.payloadBytes);
        }
        return RequestBody.create(MediaType.parse(this.mediaType), this.payloadString);
    }

    public static CommsNetworkRequest put(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        return new CommsNetworkRequest(Method.PUT, str, str2, str3, str4);
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public Request buildRequest() {
        int ordinal = this.method.ordinal();
        if (ordinal == 0) {
            this.builder.get();
        } else if (ordinal == 1) {
            this.builder.post(prepareRequestBody());
        } else if (ordinal == 2) {
            this.builder.put(prepareRequestBody());
        } else if (ordinal == 3) {
            this.builder.patch(prepareRequestBody());
        } else if (ordinal != 4) {
            return null;
        } else {
            this.builder.delete(prepareRequestBody());
        }
        this.builder.addHeader("X-Amzn-RequestId", this.requestId);
        return this.builder.url(this.urlBuilder.build()).build();
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public boolean canRetry(INetworkResponse iNetworkResponse) {
        int i = this.attempt;
        return i < MAX_ATTEMPT && this.retryHandler != null && i < this.maxAttempt.intValue() && this.retryHandler.shouldRetry(iNetworkResponse);
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public void cancel() {
        Call call = this.call;
        if (call != null) {
            call.cancel();
        }
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public int getAttemptCount() {
        return this.attempt;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    @Nullable
    public String getHeaderByName(@NonNull String str) {
        try {
            return this.builder.build().header(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public String getRequestBody() {
        try {
            Buffer buffer = new Buffer();
            prepareRequestBody().writeTo(buffer);
            return buffer.readUtf8();
        } catch (IOException unused) {
            return "Error while writing to buffer";
        }
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    @NonNull
    public String getRequestId() {
        return this.requestId;
    }

    public String getRequestMethod() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
        outline107.append(this.method);
        return outline107.toString();
    }

    @NonNull
    public String getRequestUrl() {
        return this.urlBuilder.build().toString();
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public void prepareForRetry(INetworkResponse iNetworkResponse) {
        RetryHandler retryHandler = this.retryHandler;
        if (retryHandler != null) {
            retryHandler.prepareRetryRequest(this, iNetworkResponse);
        }
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public INetworkRequest removeHeader(@NonNull String str) {
        this.builder.removeHeader(str);
        return this;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public void setCall(@NonNull Call call) {
        this.attempt++;
        this.call = call;
    }

    @VisibleForTesting
    void setRequestBuilder(Request.Builder builder) {
        this.builder = builder;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public INetworkRequest withHeader(@NonNull String str, @NonNull String str2) {
        this.builder.header(str, str2);
        return this;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public INetworkRequest withMediaType(@NonNull String str) {
        this.mediaType = str;
        return this;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public INetworkRequest withParameter(@NonNull String str, @NonNull String str2) {
        this.urlBuilder.addQueryParameter(str, str2);
        return this;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public INetworkRequest withPayload(@NonNull String str, @NonNull String str2) {
        this.payloadType = PayloadType.STRING;
        this.mediaType = str;
        this.payloadString = str2;
        return this;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public INetworkRequest withRetry(@NonNull RetryHandler retryHandler, int i) {
        this.retryHandler = retryHandler;
        this.maxAttempt = Integer.valueOf(i);
        return this;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public INetworkRequest withPayload(@NonNull String str, @NonNull File file) {
        this.payloadType = PayloadType.FILE;
        this.mediaType = str;
        this.payloadFile = file;
        return this;
    }

    @Override // com.amazon.commsnetworking.api.INetworkRequest
    public INetworkRequest withPayload(@NonNull String str, byte[] bArr) {
        this.payloadType = PayloadType.BYTES;
        this.mediaType = str;
        this.payloadBytes = bArr;
        return this;
    }

    CommsNetworkRequest(@NonNull Method method, @NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        this(new Builder().withMethod(method).withUrl(str2, str3).withDefaultRequestBuilder().withCommsNetworkingRequestTag(new MetricMetadata(str, str4)));
    }
}
