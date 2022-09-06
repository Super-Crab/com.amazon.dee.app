package com.amazon.dee.app.services.clouddrive;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.clouddrive.auth.RequestAuthenticator;
import com.amazon.dee.app.util.Utils;
import okhttp3.Request;
/* loaded from: classes12.dex */
public class AlexaRequestAuthenticator implements RequestAuthenticator {
    private static final String ACCESS_TOKEN_HEADER = "x-amz-access-token";
    static final String TAG = Utils.safeTag(AlexaRequestAuthenticator.class.getSimpleName());
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
