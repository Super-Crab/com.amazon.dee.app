package com.amazonaws.auth;
/* loaded from: classes13.dex */
public interface AWSIdentityProvider {
    String getToken();

    String refresh();
}
