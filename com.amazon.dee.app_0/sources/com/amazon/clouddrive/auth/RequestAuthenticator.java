package com.amazon.clouddrive.auth;

import okhttp3.Request;
/* loaded from: classes11.dex */
public interface RequestAuthenticator {
    Request authenticateRequest(Request request, boolean z);
}
