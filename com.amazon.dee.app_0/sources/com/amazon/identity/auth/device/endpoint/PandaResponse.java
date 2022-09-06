package com.amazon.identity.auth.device.endpoint;

import com.amazon.identity.auth.device.AuthError;
/* loaded from: classes12.dex */
public interface PandaResponse {
    int getStatusCode() throws AuthError;

    void parse() throws AuthError;
}
