package com.amazon.alexa.photos.uploadV2;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.clouddrive.cdasdk.TokenProvider;
/* loaded from: classes9.dex */
public class MAPTokenProvider implements TokenProvider {
    private static final String TAG = "MAPTokenProvider";
    @NonNull
    private IdentityService identityService;

    public MAPTokenProvider(@NonNull IdentityService identityService) {
        this.identityService = identityService;
    }

    @Override // com.amazon.clouddrive.cdasdk.TokenProvider
    public String getAccessTokenBlocking(@NonNull Context context) {
        return this.identityService.getAccessToken(TAG);
    }
}
