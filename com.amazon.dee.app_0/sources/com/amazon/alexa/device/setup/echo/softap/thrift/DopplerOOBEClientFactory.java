package com.amazon.alexa.device.setup.echo.softap.thrift;

import android.util.Log;
import com.amazon.device.setup.thrift.DopplerOOBE;
import org.apache.thrift.orig.protocol.TJSONProtocol;
import org.apache.thrift.orig.transport.THttpClient;
import org.apache.thrift.orig.transport.TTransportException;
/* loaded from: classes6.dex */
public final class DopplerOOBEClientFactory {
    private static final String TAG = "DopplerOOBEClientFactory";
    private static THttpClient transport;

    private DopplerOOBEClientFactory() {
    }

    public static DopplerOOBE.Client getEchoThriftClient(String str, int i, int i2) {
        try {
            transport = new THttpClient(str);
            transport.setConnectTimeout(i);
            transport.setReadTimeout(i2);
            return new DopplerOOBE.Client(new TJSONProtocol(transport));
        } catch (TTransportException e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }

    public static DopplerOOBE.Client getInitialEchoThriftClient(String str) {
        return getEchoThriftClient(str, 1000, 1000);
    }

    public static void setTimeout(int i) {
        transport.setReadTimeout(i);
        transport.setConnectTimeout(i);
    }
}
