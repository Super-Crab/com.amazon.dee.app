package com.amazon.whisperjoin.softap.wifi.requests;

import com.amazon.alexa.drive.navigation.location.LocationPublisher;
import java.io.IOException;
/* loaded from: classes13.dex */
public interface RequestBuilder {

    /* loaded from: classes13.dex */
    public enum ContentType {
        JSON_UTF_8(LocationPublisher.CONTENT_TYPE_JSON),
        BINARY("application/octet-stream");
        
        private String contentType;

        ContentType(String str) {
            this.contentType = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.contentType;
        }
    }

    /* loaded from: classes13.dex */
    public enum RequestMethod {
        GET,
        POST
    }

    RequestBuilder addQueryParameter(String str, String str2);

    RequestBuilder appendPath(String str);

    Request buildRequest() throws IOException;

    RequestBuilder setAuthority(String str);

    RequestBuilder setContentType(ContentType contentType);

    RequestBuilder setPath(String str);

    RequestBuilder setRequestBody(byte[] bArr);

    RequestBuilder setRequestMethod(RequestMethod requestMethod);

    RequestBuilder setScheme(String str);

    RequestBuilder setTimeout(int i);
}
