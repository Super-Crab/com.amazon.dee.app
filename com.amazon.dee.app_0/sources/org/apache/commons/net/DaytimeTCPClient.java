package org.apache.commons.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/* loaded from: classes4.dex */
public final class DaytimeTCPClient extends SocketClient {
    public static final int DEFAULT_PORT = 13;
    private char[] __buffer = new char[64];

    public DaytimeTCPClient() {
        setDefaultPort(13);
    }

    public String getTime() throws IOException {
        StringBuffer stringBuffer = new StringBuffer(this.__buffer.length);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this._input_));
        while (true) {
            char[] cArr = this.__buffer;
            int read = bufferedReader.read(cArr, 0, cArr.length);
            if (read <= 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append(this.__buffer, 0, read);
        }
    }
}
