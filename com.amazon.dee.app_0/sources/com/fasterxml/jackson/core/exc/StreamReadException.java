package com.fasterxml.jackson.core.exc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.RequestPayload;
/* loaded from: classes2.dex */
public abstract class StreamReadException extends JsonProcessingException {
    static final long serialVersionUID = 1;
    protected transient JsonParser _processor;
    protected RequestPayload _requestPayload;

    public StreamReadException(JsonParser jsonParser, String str) {
        super(str, jsonParser == null ? null : jsonParser.getCurrentLocation());
        this._processor = jsonParser;
    }

    @Override // com.fasterxml.jackson.core.JsonProcessingException, java.lang.Throwable
    public String getMessage() {
        String message = super.getMessage();
        if (this._requestPayload != null) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(message, "\nRequest payload : ");
            outline113.append(this._requestPayload.toString());
            return outline113.toString();
        }
        return message;
    }

    public RequestPayload getRequestPayload() {
        return this._requestPayload;
    }

    public String getRequestPayloadAsString() {
        RequestPayload requestPayload = this._requestPayload;
        if (requestPayload != null) {
            return requestPayload.toString();
        }
        return null;
    }

    /* renamed from: withParser */
    public abstract StreamReadException mo7001withParser(JsonParser jsonParser);

    /* renamed from: withRequestPayload */
    public abstract StreamReadException mo7002withRequestPayload(RequestPayload requestPayload);

    @Override // com.fasterxml.jackson.core.JsonProcessingException, com.fasterxml.jackson.core.JacksonException
    /* renamed from: getProcessor */
    public JsonParser mo7003getProcessor() {
        return this._processor;
    }

    public StreamReadException(JsonParser jsonParser, String str, Throwable th) {
        super(str, jsonParser == null ? null : jsonParser.getCurrentLocation(), th);
        this._processor = jsonParser;
    }

    public StreamReadException(JsonParser jsonParser, String str, JsonLocation jsonLocation) {
        super(str, jsonLocation, null);
        this._processor = jsonParser;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StreamReadException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
        this._location = jsonLocation;
    }
}
