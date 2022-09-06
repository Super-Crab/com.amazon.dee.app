package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.RequestPayload;
/* loaded from: classes2.dex */
public class InputCoercionException extends StreamReadException {
    private static final long serialVersionUID = 1;
    protected final JsonToken _inputType;
    protected final Class<?> _targetType;

    public InputCoercionException(JsonParser jsonParser, String str, JsonToken jsonToken, Class<?> cls) {
        super(jsonParser, str);
        this._inputType = jsonToken;
        this._targetType = cls;
    }

    public JsonToken getInputType() {
        return this._inputType;
    }

    public Class<?> getTargetType() {
        return this._targetType;
    }

    @Override // com.fasterxml.jackson.core.exc.StreamReadException
    /* renamed from: withParser */
    public InputCoercionException mo7001withParser(JsonParser jsonParser) {
        this._processor = jsonParser;
        return this;
    }

    @Override // com.fasterxml.jackson.core.exc.StreamReadException
    /* renamed from: withRequestPayload */
    public InputCoercionException mo7002withRequestPayload(RequestPayload requestPayload) {
        this._requestPayload = requestPayload;
        return this;
    }
}
