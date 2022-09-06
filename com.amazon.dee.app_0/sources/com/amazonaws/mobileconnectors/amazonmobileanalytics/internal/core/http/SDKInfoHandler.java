package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.http;

import com.amazonaws.Request;
import com.amazonaws.Response;
import com.amazonaws.handlers.RequestHandler2;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.SDKInfo;
@Deprecated
/* loaded from: classes13.dex */
public final class SDKInfoHandler extends RequestHandler2 {
    static final String CLIENT_SDK_VERSION_HEADER_NAME = "x-amzn-ClientSDKVersion";
    private final SDKInfo sdkInfo;

    public SDKInfoHandler(SDKInfo sDKInfo) {
        this.sdkInfo = sDKInfo;
    }

    @Override // com.amazonaws.handlers.RequestHandler2
    public void afterError(Request<?> request, Response<?> response, Exception exc) {
    }

    @Override // com.amazonaws.handlers.RequestHandler2
    public void afterResponse(Request<?> request, Response<?> response) {
    }

    @Override // com.amazonaws.handlers.RequestHandler2
    public void beforeRequest(Request<?> request) {
        if (request != null) {
            request.addHeader(CLIENT_SDK_VERSION_HEADER_NAME, String.format("%s", this.sdkInfo.toString()));
        }
    }
}
