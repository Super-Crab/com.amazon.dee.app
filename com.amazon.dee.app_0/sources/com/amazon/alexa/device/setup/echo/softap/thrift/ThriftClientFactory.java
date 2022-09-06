package com.amazon.alexa.device.setup.echo.softap.thrift;
/* loaded from: classes6.dex */
public final class ThriftClientFactory {
    private ThriftClientFactory() {
    }

    public static ThriftClient getThriftClient() {
        return new ThriftClientImpl();
    }
}
