package com.amazon.deecomms.exceptions;
/* loaded from: classes12.dex */
public class DeviceTargetingException extends Exception {
    public DeviceTargetingException(Throwable th) {
        super(th + " while trying to fetch the list of devices.");
    }
}
