package com.amazon.alexa.sharing.media.clouddrive;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import okhttp3.Request;
/* loaded from: classes10.dex */
public class AlexaRequestAuthenticator implements RequestAuthenticator {
    private static final String ACCESS_TOKEN_HEADER = "x-amz-access-token";
    static final String TAG = "AlexaRequestAuthenticator";
    private final IdentityService identityService;

    public AlexaRequestAuthenticator(IdentityService identityService) {
        this.identityService = identityService;
    }

    @Override // com.amazon.clouddrive.auth.RequestAuthenticator
    public Request authenticateRequest(Request request, boolean z) {
        Request.Builder newBuilder = request.newBuilder();
        newBuilder.addHeader("x-amz-access-token", this.identityService.getAccessToken(TAG));
        return newBuilder.build();
    }
}
