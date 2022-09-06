package com.amazon.alexa.accessory.internal.http;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class HttpStatusUnsuccessfulException extends RuntimeException {
    private final int statusCode;

    public HttpStatusUnsuccessfulException(int i) {
        super(GeneratedOutlineSupport1.outline49("Response has status code: ", i));
        this.statusCode = i;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public HttpStatusUnsuccessfulException(String str, int i) {
        super(GeneratedOutlineSupport1.outline74(str, " Response has status code: ", i));
        this.statusCode = i;
    }
}
