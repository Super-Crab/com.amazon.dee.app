package com.amazon.alexa.auth;

import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public interface TokenProvider {
    @Nullable
    AccessToken getToken() throws AuthorizationException;
}
