package com.amazon.identity.kcpsdk.common;

import com.amazon.identity.auth.device.ls;
import java.io.IOException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class BackoffException extends IOException {
    private final ls mBackoffInfo;
    private final int mErrorCode;
    private final String mErrorMsg;

    public BackoffException(String str, ls lsVar) {
        this(str, lsVar, (byte) 0);
    }

    public BackoffException(String str, ls lsVar, byte b) {
        super(str);
        this.mErrorCode = -1;
        this.mErrorMsg = str;
        this.mBackoffInfo = lsVar;
    }
}
