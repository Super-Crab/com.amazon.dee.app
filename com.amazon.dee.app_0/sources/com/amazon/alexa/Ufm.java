package com.amazon.alexa;

import amazon.speech.simclient.capability.CapabilityQueryConstants;
/* compiled from: ExceptionCode.java */
/* loaded from: classes.dex */
public enum Ufm {
    UNDEFINED("UNDEFINED"),
    INVALID_REQUEST_EXCEPTION("INVALID_REQUEST_EXCEPTION"),
    UNAUTHORIZED_REQUEST_EXCEPTION("UNAUTHORIZED_REQUEST_EXCEPTION"),
    UNSUPPORTED_MEDIA_TYPE("UNSUPPORTED_MEDIA_TYPE"),
    THROTTLING_EXCEPTION("THROTTLING_EXCEPTION"),
    INTERNAL_SERVICE_EXCEPTION("INTERNAL_SERVICE_EXCEPTION"),
    NOT_AVAILABLE(CapabilityQueryConstants.TARGET_NOT_AVAILABLE);
    
    public final String name;

    Ufm(String str) {
        this.name = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
