package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.AccessTokenProvider;
/* loaded from: classes.dex */
public class UnavailableAccessTokenProvider implements AccessTokenProvider {
    @Override // com.amazon.alexa.accessory.AccessTokenProvider
    public String getAccessToken() {
        return null;
    }
}
