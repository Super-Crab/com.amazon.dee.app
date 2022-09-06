package com.amazon.blueshift.bluefront.android.request;

import com.amazon.bluefront.api.v2.IntentParameters;
import com.amazon.bluefront.api.v2.ServiceParameters;
import com.amazon.bluefront.api.v2.SpeechToIntentInputParameters;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Preconditions;
/* loaded from: classes11.dex */
public class SpeechToIntentRequestBuilder<OUTPUT, CONTEXT> extends SpeechRequestBuilderBase<SpeechToIntentRequestBuilder<OUTPUT, CONTEXT>, OUTPUT, CONTEXT> {
    private static final String DEFAULT_PATH = "speech/interpreter/v2";
    private int mMaxResultCount;

    /* JADX INFO: Access modifiers changed from: protected */
    public SpeechToIntentRequestBuilder(Class<OUTPUT> cls, CONTEXT context) {
        super(cls, context);
        this.mMaxResultCount = 1;
    }

    @Override // com.amazon.blueshift.bluefront.android.request.SpeechRequestBuilderBase
    public SpeechRequest<OUTPUT> build() {
        ServiceParameters buildBaseParameters = buildBaseParameters();
        IntentParameters intentParameters = new IntentParameters();
        intentParameters.setMaxNBest(this.mMaxResultCount);
        SpeechToIntentInputParameters speechToIntentInputParameters = new SpeechToIntentInputParameters();
        speechToIntentInputParameters.setServiceParams(buildBaseParameters);
        speechToIntentInputParameters.setIntentParams(intentParameters);
        try {
            return new SpeechRequest<>(new SpeechRequestConfig(getRequestId(), new SpeechRequestParameters(speechToIntentInputParameters, getContext()), getLocaleString(), getProfile()), getCompleteUri(), getTimeouts(), getDevice(), getOutputForm(), getHeaders());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error building request json", e);
        }
    }

    @Override // com.amazon.blueshift.bluefront.android.request.SpeechRequestBuilderBase
    protected String getDefaultPath() {
        return DEFAULT_PATH;
    }

    public SpeechToIntentRequestBuilder<OUTPUT, CONTEXT> maxResultCount(int i) {
        Preconditions.checkArgument(i > 0, "Max result count must be greater than 0");
        this.mMaxResultCount = i;
        return this;
    }
}
