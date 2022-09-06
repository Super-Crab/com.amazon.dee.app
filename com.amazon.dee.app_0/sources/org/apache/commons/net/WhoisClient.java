package org.apache.commons.net;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes4.dex */
public final class WhoisClient extends FingerClient {
    public static final String DEFAULT_HOST = "whois.internic.net";
    public static final int DEFAULT_PORT = 43;

    public WhoisClient() {
        setDefaultPort(43);
    }

    public InputStream getInputStream(String str) throws IOException {
        return getInputStream(false, str);
    }

    public String query(String str) throws IOException {
        return query(false, str);
    }
}
