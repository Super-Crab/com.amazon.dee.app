package com.amazonaws.mobileconnectors.remoteconfiguration.internal.net;

import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.NetworkException;
/* loaded from: classes13.dex */
public class RequestThrottledException extends NetworkException {
    public RequestThrottledException(String str) {
        super(str);
    }

    public RequestThrottledException(String str, Throwable th) {
        super(str, th);
    }
}
