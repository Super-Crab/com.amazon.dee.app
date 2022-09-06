package com.amazon.org.codehaus.jackson;
/* loaded from: classes13.dex */
public class JsonGenerationException extends JsonProcessingException {
    static final long serialVersionUID = 123;

    public JsonGenerationException(Throwable th) {
        super(th);
    }

    public JsonGenerationException(String str) {
        super(str, (JsonLocation) null);
    }

    public JsonGenerationException(String str, Throwable th) {
        super(str, null, th);
    }
}
