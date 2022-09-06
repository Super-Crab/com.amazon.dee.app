package com.amazon.alexa.accessory.internal.http;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public class HttpRequest {
    private static HttpRequestBuilderFactory builderFactory = new HttpRequestBuilderFactory();
    private final HttpBody body;
    private final Map<String, String> headers;
    private final HttpMethod method;
    private final SSLSocketFactory sslSocketFactory;
    private final String url;

    /* loaded from: classes.dex */
    public static class Builder {
        HttpBody body;
        Map<String, String> headers;
        HttpMethod method;
        SSLSocketFactory sslSocketFactory;
        String url;

        public Builder body(HttpBody httpBody) {
            this.body = httpBody;
            return this;
        }

        public HttpRequest build() {
            Preconditions.notNull(this.url, "url");
            if (this.method == null) {
                this.method = HttpMethod.GET;
            }
            if (this.headers == null) {
                this.headers = Collections.emptyMap();
            }
            return new HttpRequest(this);
        }

        public Builder header(String str, String str2) {
            if (this.headers == null) {
                this.headers = new HashMap();
            }
            this.headers.put(str, str2);
            return this;
        }

        public Builder headers(Map<String, String> map) {
            this.headers = map;
            return this;
        }

        public Builder method(HttpMethod httpMethod) {
            this.method = httpMethod;
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.sslSocketFactory = sSLSocketFactory;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }

        private Builder() {
        }

        public Builder header(String str, int i) {
            if (this.headers == null) {
                this.headers = new HashMap();
            }
            this.headers.put(str, String.valueOf(i));
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class HttpRequestBuilderFactory {
        public Builder createBuilder() {
            return new Builder();
        }
    }

    HttpRequest(Builder builder) {
        this.headers = builder.headers;
        this.method = builder.method;
        this.url = builder.url;
        this.body = builder.body;
        this.sslSocketFactory = builder.sslSocketFactory;
    }

    public static Builder createBuilder() {
        return builderFactory.createBuilder();
    }

    @VisibleForTesting
    public static void setBuilderFactory(HttpRequestBuilderFactory httpRequestBuilderFactory) {
        builderFactory = httpRequestBuilderFactory;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || HttpRequest.class != obj.getClass()) {
            return false;
        }
        HttpRequest httpRequest = (HttpRequest) obj;
        if (!this.headers.equals(httpRequest.headers) || this.method != httpRequest.method || !this.url.equals(httpRequest.url)) {
            return false;
        }
        HttpBody httpBody = this.body;
        HttpBody httpBody2 = httpRequest.body;
        return httpBody != null ? httpBody.equals(httpBody2) : httpBody2 == null;
    }

    public HttpBody getBody() {
        return this.body;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.sslSocketFactory;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean hasBody() {
        return this.body != null;
    }

    public int hashCode() {
        int outline7 = GeneratedOutlineSupport1.outline7(this.url, (this.method.hashCode() + (this.headers.hashCode() * 31)) * 31, 31);
        HttpBody httpBody = this.body;
        return outline7 + (httpBody != null ? httpBody.hashCode() : 0);
    }

    public HttpCall newCall() {
        return new HttpCall(this);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("HttpRequest{headers=");
        outline107.append(this.headers);
        outline107.append(", method=");
        outline107.append(this.method);
        outline107.append(", url='");
        GeneratedOutlineSupport1.outline176(outline107, this.url, Chars.QUOTE, ", body=");
        outline107.append(this.body);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
