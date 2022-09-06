package com.amazon.blueshift.bluefront.android.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
/* loaded from: classes11.dex */
public class SpeechRequestParameters<PARAMS, CONTEXT> {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final Optional<String> mSerializedContext;
    private final String mSerializedParameters;

    public SpeechRequestParameters(PARAMS params, Optional<CONTEXT> optional) throws JsonProcessingException {
        Preconditions.checkNotNull(params, "params cannot be null for a speech request");
        this.mSerializedParameters = MAPPER.writeValueAsString(params);
        if (optional.isPresent()) {
            this.mSerializedContext = Optional.of(MAPPER.writeValueAsString(optional.get()));
        } else {
            this.mSerializedContext = Optional.absent();
        }
    }

    public Optional<String> getSerializedContext() {
        return this.mSerializedContext;
    }

    public String getSerializedParameters() {
        return this.mSerializedParameters;
    }
}
