package com.amazon.alexa.wakeword.davs;

import com.amazon.alexa.utils.validation.Assertions;
import com.amazon.alexa.utils.validation.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.Response;
/* loaded from: classes11.dex */
public class ServerResponse {
    private final DavsResponse davsResponse;
    private final Response response;

    public ServerResponse(Response response) {
        Preconditions.notNull(response, "response is null");
        this.response = response;
        this.davsResponse = retrieveDavsResponse(response.code());
    }

    private static DavsResponse retrieveDavsResponse(int i) {
        if (i != 200) {
            if (i == 304) {
                return DavsResponse.UP_TO_DATE;
            }
            if (i == 400) {
                return DavsResponse.ILLEGAL_ARGUMENT;
            }
            if (i == 401 || i == 403) {
                return DavsResponse.UNAUTHORIZED;
            }
            if (i != 404) {
                return DavsResponse.OTHER;
            }
            return DavsResponse.NO_ARTIFACT_FOUND;
        }
        return DavsResponse.SUCCESS;
    }

    public InputStream getBodyStream() {
        Assertions.notNull(this.response, "response is null");
        return this.response.body().byteStream();
    }

    public String getBodyString() throws IOException {
        Assertions.notNull(this.response, "response is null");
        return new String(this.response.body().bytes());
    }

    public long getContentLength() {
        Assertions.notNull(this.response, "response is null");
        return this.response.body().contentLength();
    }

    public DavsResponse getDavsResponse() {
        return this.davsResponse;
    }

    public boolean isSuccessful() {
        return DavsResponse.SUCCESS == this.davsResponse;
    }

    public ServerResponse(DavsResponse davsResponse) {
        Preconditions.notNull(davsResponse, "davs response is null");
        this.response = null;
        this.davsResponse = davsResponse;
        if (!isSuccessful()) {
            return;
        }
        throw new IllegalArgumentException("successful response can not be set this way");
    }
}
