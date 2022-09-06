package com.amazon.alexa.device.setup.echo.softap.thrift.exception;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class ThriftRequestException extends Exception {
    final String errorStatus;
    final String message;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThriftRequestException(String str, String str2) {
        super(GeneratedOutlineSupport1.outline72(str, str2));
        this.message = str;
        this.errorStatus = str2;
    }

    public String getErrorStatus() {
        return this.errorStatus;
    }
}
