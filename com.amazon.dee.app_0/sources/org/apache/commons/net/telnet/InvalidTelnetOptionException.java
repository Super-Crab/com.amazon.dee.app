package org.apache.commons.net.telnet;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
/* loaded from: classes4.dex */
public class InvalidTelnetOptionException extends Exception {
    private String msg;
    private int optionCode;

    public InvalidTelnetOptionException(String str, int i) {
        this.optionCode = -1;
        this.optionCode = i;
        this.msg = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.msg);
        stringBuffer.append(RealTimeTextConstants.COLON_SPACE);
        stringBuffer.append(this.optionCode);
        return stringBuffer.toString();
    }
}
