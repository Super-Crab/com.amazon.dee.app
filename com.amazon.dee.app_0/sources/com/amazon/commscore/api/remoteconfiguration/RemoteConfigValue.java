package com.amazon.commscore.api.remoteconfiguration;
/* loaded from: classes12.dex */
public interface RemoteConfigValue {
    Object getRawValue();

    Integer toInteger() throws NumberFormatException, NullPointerException;

    String toString();
}
