package com.amazon.blueshift.bluefront.android.request;

import com.amazon.bluefront.api.v2.ServiceParameters;
import com.amazon.bluefront.api.v2.SpeechToDataMartInputParameters;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.annotation.Nullable;
/* loaded from: classes11.dex */
public class SpeechToStorageIdRequestBuilder<OUTPUT, CONTEXT> extends SpeechRequestBuilderBase<SpeechToStorageIdRequestBuilder<OUTPUT, CONTEXT>, OUTPUT, CONTEXT> {
    private static final String DEFAULT_PATH = "speech/recorder/v2";

    /* JADX INFO: Access modifiers changed from: protected */
    public SpeechToStorageIdRequestBuilder(Class<OUTPUT> cls, @Nullable CONTEXT context) {
        super(cls, context);
    }

    @Override // com.amazon.blueshift.bluefront.android.request.SpeechRequestBuilderBase
    public SpeechRequest<OUTPUT> build() {
        ServiceParameters buildBaseParameters = buildBaseParameters();
        SpeechToDataMartInputParameters speechToDataMartInputParameters = new SpeechToDataMartInputParameters();
        speechToDataMartInputParameters.setServiceParams(buildBaseParameters);
        try {
            return new SpeechRequest<>(new SpeechRequestConfig(getRequestId(), new SpeechRequestParameters(speechToDataMartInputParameters, getContext()), getLocaleString(), getProfile()), getCompleteUri(), getTimeouts(), getDevice(), getOutputForm(), getHeaders());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error building request json", e);
        }
    }

    @Override // com.amazon.blueshift.bluefront.android.request.SpeechRequestBuilderBase
    protected String getDefaultPath() {
        return DEFAULT_PATH;
    }
}
