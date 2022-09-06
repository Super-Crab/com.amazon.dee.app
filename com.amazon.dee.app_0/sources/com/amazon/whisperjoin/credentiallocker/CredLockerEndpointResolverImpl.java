package com.amazon.whisperjoin.credentiallocker;

import com.google.common.base.Charsets;
import com.google.common.io.Closeables;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Locale;
/* loaded from: classes13.dex */
class CredLockerEndpointResolverImpl implements CredLockerEndpointResolver {
    private static final String TAG = "CredLockerEndpoint";
    private static final String V1_CRED_LOCKER_PATH = "/credentiallocker/v1";
    private static final Gson gson = new Gson();
    private final HttpUrlConnectionFactory connectionFactory;
    private final String defaultEndpoint;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CredLockerEndpointResolverImpl(HttpUrlConnectionFactory httpUrlConnectionFactory, String str) {
        this.connectionFactory = httpUrlConnectionFactory;
        this.defaultEndpoint = str;
    }

    @Override // com.amazon.whisperjoin.credentiallocker.CredLockerEndpointResolver
    public URL getEndpoint() throws IOException {
        InputStreamReader inputStreamReader;
        Throwable th;
        InputStream inputStream;
        URL url = new URL(String.format(Locale.ENGLISH, "%s%s/endpoint", this.defaultEndpoint, V1_CRED_LOCKER_PATH));
        String str = "Getting " + url;
        try {
            inputStream = this.connectionFactory.newConnection(url).getInputStream();
            try {
                inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
                try {
                    WifiEndpointResponse wifiEndpointResponse = (WifiEndpointResponse) gson.fromJson((Reader) inputStreamReader, (Class<Object>) WifiEndpointResponse.class);
                    String str2 = "Response: " + wifiEndpointResponse;
                    URL url2 = new URL(wifiEndpointResponse.getEndpoint());
                    Closeables.closeQuietly(inputStreamReader);
                    Closeables.closeQuietly(inputStream);
                    return url2;
                } catch (Throwable th2) {
                    th = th2;
                    Closeables.closeQuietly(inputStreamReader);
                    Closeables.closeQuietly(inputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                inputStreamReader = null;
                th = th3;
            }
        } catch (Throwable th4) {
            inputStreamReader = null;
            th = th4;
            inputStream = null;
        }
    }
}
