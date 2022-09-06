package com.amazon.alexa;

import com.amazon.alexa.Kqq;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.google.auto.value.AutoValue;
/* compiled from: SystemErrorEvent.java */
@AutoValue
/* loaded from: classes.dex */
public abstract class TTH extends Kqq.zZm {

    /* compiled from: SystemErrorEvent.java */
    /* loaded from: classes.dex */
    public enum zZm {
        UNSUPPORTED_OPERATION,
        UNEXPECTED_INFORMATION_RECEIVED,
        INTERNAL_ERROR,
        NO_NETWORK_CONNECTIVITY
    }

    public static TTH zZm(String str, String str2) {
        return zZm(zZm.UNSUPPORTED_OPERATION, str, str2, false, DialogRequestIdentifier.NONE);
    }

    public static TTH zZm(String str) {
        return zZm(zZm.INTERNAL_ERROR, str, null, false, DialogRequestIdentifier.NONE);
    }

    public static TTH zZm(String str, zZm zzm) {
        if (zzm.equals(zZm.NO_NETWORK_CONNECTIVITY)) {
            return zZm(zZm.NO_NETWORK_CONNECTIVITY, str, null, true, DialogRequestIdentifier.NONE);
        }
        return zZm(zZm.INTERNAL_ERROR, str, null, true, DialogRequestIdentifier.NONE);
    }

    public static TTH zZm(String str, zZm zzm, DialogRequestIdentifier dialogRequestIdentifier) {
        if (zzm.equals(zZm.NO_NETWORK_CONNECTIVITY)) {
            return zZm(zZm.NO_NETWORK_CONNECTIVITY, str, null, true, dialogRequestIdentifier);
        }
        return zZm(zZm.INTERNAL_ERROR, str, null, true, dialogRequestIdentifier);
    }

    public static TTH zZm(zZm zzm, String str, String str2, boolean z, DialogRequestIdentifier dialogRequestIdentifier) {
        return new nAN(zzm, str, str2, z, dialogRequestIdentifier);
    }
}
