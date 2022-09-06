package org.apache.commons.net;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/* loaded from: classes4.dex */
public class FingerClient extends SocketClient {
    public static final int DEFAULT_PORT = 79;
    private static final String __LONG_FLAG = "/W ";
    private transient StringBuffer __query = new StringBuffer(64);
    private transient char[] __buffer = new char[1024];

    public FingerClient() {
        setDefaultPort(79);
    }

    public InputStream getInputStream(boolean z, String str) throws IOException {
        this.__query.setLength(0);
        if (z) {
            this.__query.append(__LONG_FLAG);
        }
        this.__query.append(str);
        this.__query.append("\r\n");
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(this._output_, 1024));
        dataOutputStream.writeBytes(this.__query.toString());
        dataOutputStream.flush();
        return this._input_;
    }

    public String query(boolean z, String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer(this.__buffer.length);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getInputStream(z, str)));
        while (true) {
            char[] cArr = this.__buffer;
            int read = bufferedReader.read(cArr, 0, cArr.length);
            if (read <= 0) {
                bufferedReader.close();
                return stringBuffer.toString();
            }
            stringBuffer.append(this.__buffer, 0, read);
        }
    }

    public String query(boolean z) throws IOException {
        return query(z, "");
    }

    public InputStream getInputStream(boolean z) throws IOException {
        return getInputStream(z, "");
    }
}
