package com.amazon.alexa.logupload;

import okhttp3.Request;
/* loaded from: classes9.dex */
public interface LogReport {
    Request getNetworkRequest() throws LogUploadException;
}
