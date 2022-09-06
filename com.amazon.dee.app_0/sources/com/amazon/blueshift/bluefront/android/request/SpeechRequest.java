package com.amazon.blueshift.bluefront.android.request;

import android.net.Uri;
import com.amazon.bluefront.api.common.Arrf;
import com.amazon.bluefront.api.v2.DataMartStorageId;
import com.amazon.blueshift.bluefront.android.common.AndroidDevice;
import com.amazon.blueshift.bluefront.android.common.RequestTimeouts;
import com.amazon.blueshift.bluefront.android.http.part.OptionalSerializedJsonPart;
import com.amazon.blueshift.bluefront.android.http.part.Part;
import com.amazon.blueshift.bluefront.android.http.part.SerializedJsonPart;
import com.google.common.base.Preconditions;
import java.util.Map;
/* loaded from: classes11.dex */
public class SpeechRequest<OUTPUT> {
    private static final String CONTEXT_PART_NAME = "context";
    private static final String REQUEST_PARAMS_PART_NAME = "parameters";
    private final AndroidDevice mDevice;
    private final Map<String, String> mHeaders;
    private final Class<OUTPUT> mOutputType;
    private final SpeechRequestConfig mRequestConfig;
    private final Uri mRequestUri;
    private final RequestTimeouts mTimeouts;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpeechRequest(SpeechRequestConfig speechRequestConfig, Uri uri, RequestTimeouts requestTimeouts, AndroidDevice androidDevice, Class<OUTPUT> cls, Map<String, String> map) {
        Preconditions.checkNotNull(speechRequestConfig, "requestConfig cannot be null");
        Preconditions.checkNotNull(uri, "request endpoint cannot be null");
        Preconditions.checkNotNull(requestTimeouts, "timeouts cannot be null");
        Preconditions.checkNotNull(androidDevice, "device cannot be null");
        Preconditions.checkNotNull(cls, "outputType cannot be null");
        Preconditions.checkNotNull(map, "headers cannot be null");
        this.mRequestConfig = speechRequestConfig;
        this.mRequestUri = uri;
        this.mTimeouts = requestTimeouts;
        this.mDevice = androidDevice;
        this.mOutputType = cls;
        this.mHeaders = map;
    }

    public static <OUTPUT, CONTEXT> SpeechToIntentRequestBuilder<OUTPUT, CONTEXT> speechToIntentRequestBuilder(Class<OUTPUT> cls, CONTEXT context) {
        return new SpeechToIntentRequestBuilder<>(cls, context);
    }

    public static SpeechToStorageIdRequestBuilder<DataMartStorageId, ?> speechToStorageIdBuilder() {
        return speechToStorageIdRequestBuilder(DataMartStorageId.class, null);
    }

    public static <OUTPUT, CONTEXT> SpeechToStorageIdRequestBuilder<OUTPUT, CONTEXT> speechToStorageIdRequestBuilder(Class<OUTPUT> cls, CONTEXT context) {
        return new SpeechToStorageIdRequestBuilder<>(cls, context);
    }

    public static SpeechToTextRequestBuilder<Arrf, ?> speechToTextRequestBuilder() {
        return speechToTextRequestBuilder(Arrf.class, null);
    }

    public Part createContextPart() {
        return new OptionalSerializedJsonPart(this.mRequestConfig.getRequestParameters().getSerializedContext(), CONTEXT_PART_NAME);
    }

    public Part createParametersPart() {
        return new SerializedJsonPart(this.mRequestConfig.getRequestParameters().getSerializedParameters(), REQUEST_PARAMS_PART_NAME);
    }

    public AndroidDevice getDevice() {
        return this.mDevice;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }

    public String getLocale() {
        return this.mRequestConfig.getLocale();
    }

    public Class<OUTPUT> getOutputType() {
        return this.mOutputType;
    }

    public String getProfile() {
        return this.mRequestConfig.getProfile();
    }

    SpeechRequestConfig getRequestConfig() {
        return this.mRequestConfig;
    }

    public String getRequestId() {
        return this.mRequestConfig.getRequestId();
    }

    public Uri getRequestUri() {
        return this.mRequestUri;
    }

    public RequestTimeouts getTimeouts() {
        return this.mTimeouts;
    }

    public static <OUTPUT, CONTEXT> SpeechToTextRequestBuilder<OUTPUT, CONTEXT> speechToTextRequestBuilder(Class<OUTPUT> cls, CONTEXT context) {
        return new SpeechToTextRequestBuilder<>(cls, context);
    }
}
