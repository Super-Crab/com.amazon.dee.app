package com.amazon.blueshift.bluefront.android.request;

import com.amazon.bluefront.api.v2.RecognitionOutputType;
import com.amazon.bluefront.api.v2.RecognitionParameters;
import com.amazon.bluefront.api.v2.ServiceParameters;
import com.amazon.bluefront.api.v2.SpeechToTextInputParameters;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
/* loaded from: classes11.dex */
public class SpeechToTextRequestBuilder<OUTPUT, CONTEXT> extends SpeechRequestBuilderBase<SpeechToTextRequestBuilder<OUTPUT, CONTEXT>, OUTPUT, CONTEXT> {
    private static final String DEFAULT_PATH = "speech/recognizer/v2";
    private int mMaxResultCount;
    private String mRecognitionOutputType;

    /* JADX INFO: Access modifiers changed from: protected */
    public SpeechToTextRequestBuilder(Class<OUTPUT> cls, CONTEXT context) {
        super(cls, context);
        this.mRecognitionOutputType = RecognitionOutputType.ARRF;
        this.mMaxResultCount = 1;
    }

    @Override // com.amazon.blueshift.bluefront.android.request.SpeechRequestBuilderBase
    public SpeechRequest<OUTPUT> build() {
        ServiceParameters buildBaseParameters = buildBaseParameters();
        RecognitionParameters recognitionParameters = new RecognitionParameters();
        recognitionParameters.setMaxNBest(this.mMaxResultCount);
        recognitionParameters.setOutputFormat(this.mRecognitionOutputType);
        SpeechToTextInputParameters speechToTextInputParameters = new SpeechToTextInputParameters();
        speechToTextInputParameters.setServiceParams(buildBaseParameters);
        speechToTextInputParameters.setRecognitionParams(recognitionParameters);
        try {
            return new SpeechRequest<>(new SpeechRequestConfig(getRequestId(), new SpeechRequestParameters(speechToTextInputParameters, getContext()), getLocaleString(), getProfile()), getCompleteUri(), getTimeouts(), getDevice(), getOutputForm(), getHeaders());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error building request json", e);
        }
    }

    @Override // com.amazon.blueshift.bluefront.android.request.SpeechRequestBuilderBase
    protected String getDefaultPath() {
        return DEFAULT_PATH;
    }

    public SpeechToTextRequestBuilder<OUTPUT, CONTEXT> maxResultCount(int i) {
        Preconditions.checkArgument(i > 0, "Max result count must be greater than 0");
        this.mMaxResultCount = i;
        return this;
    }

    public SpeechToTextRequestBuilder<OUTPUT, CONTEXT> recognitionOutputType(String str) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "recognitionOutputType cannot be empty or null");
        this.mRecognitionOutputType = str;
        return this;
    }
}
