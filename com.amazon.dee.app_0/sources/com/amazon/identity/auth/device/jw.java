package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class jw implements kn {
    private static final String TAG = "com.amazon.identity.auth.device.jw";
    private final ej bR;
    lk iQ;
    private final Context mContext;
    km rS;
    md rT;

    public jw(Context context, md mdVar, km kmVar, lk lkVar, ej ejVar) {
        this.rS = null;
        this.rT = null;
        this.iQ = null;
        this.rT = mdVar;
        this.iQ = lkVar;
        this.rS = kmVar;
        this.mContext = context;
        this.bR = ejVar;
    }

    private static boolean a(md mdVar, lk lkVar) {
        if (mdVar.iE()) {
            if (lkVar != null) {
                return lkVar.b(mdVar);
            }
            io.e(TAG, "The request requires authentication, but no authentication credentials were supplied.");
            return false;
        }
        return true;
    }

    public static <T extends URLConnection> T b(T t) {
        String uuid = UUID.randomUUID().toString();
        t.setRequestProperty("X-Amzn-RequestId", uuid);
        io.i(TAG, "X-Amzn-RequestId:".concat(String.valueOf(uuid)));
        t.setConnectTimeout(hc());
        t.setReadTimeout(hc());
        return t;
    }

    private static int hc() {
        return (int) TimeUnit.MILLISECONDS.convert(30L, TimeUnit.SECONDS);
    }

    @Override // com.amazon.identity.auth.device.kn
    public void cD() {
        if (!a(this.rT, this.iQ)) {
            String str = TAG;
            io.i(str, "Failed to sign request, aborting call to " + this.rT.getUrl());
            this.bR.bA("FailureToSignRequest");
            this.rS.cF();
            return;
        }
        io.i(TAG, "Starting web request");
        io.b("URL: %s", this.rT.getUrl());
        try {
            a(this.mContext, this.rT, new jx() { // from class: com.amazon.identity.auth.device.jw.1
                @Override // com.amazon.identity.auth.device.jx
                public Object a(me meVar, byte[] bArr) throws ParseErrorException, IOException {
                    jw.this.rS.a(meVar);
                    if (bArr != null) {
                        jw.this.rS.a(bArr, bArr.length);
                    }
                    io.i(jw.TAG, "Request complete");
                    jw.this.rS.cE();
                    return null;
                }

                @Override // com.amazon.identity.auth.device.jx
                public String g(HttpURLConnection httpURLConnection) {
                    return null;
                }
            }, this.bR);
        } catch (ParseErrorException unused) {
        } catch (IOException unused2) {
            this.rS.onNetworkError();
        } catch (UnsupportedOperationException unused3) {
            this.rS.onNetworkError();
        }
    }

    public static Object a(Context context, md mdVar, jx jxVar, lk lkVar, ej ejVar) throws IOException, ParseErrorException {
        if (!a(mdVar, lkVar)) {
            String str = TAG;
            io.i(str, "Failed to sign request, aborting call to " + mdVar.getUrl());
            return null;
        }
        io.i(TAG, "Starting web request");
        io.b("URL: %s", mdVar.getUrl());
        return a(context, mdVar, jxVar, ejVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.amazon.identity.auth.device.md] */
    public static Object a(Context context, md mdVar, jx jxVar, ej ejVar) throws IOException, ParseErrorException {
        String url = mdVar.getUrl();
        fn.ey();
        ?? r4 = "WebserviceCallTime";
        mv a = mv.a(fn.mP, r4, mp.eS(url));
        a.start();
        HttpURLConnection httpURLConnection = null;
        byte[] a2 = null;
        httpURLConnection = null;
        try {
            try {
                jy jyVar = new jy(context, jxVar);
                HttpURLConnection a3 = mdVar.a(jyVar, context, ejVar);
                try {
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) b(a3);
                    try {
                        try {
                            io.i(TAG, "X-Amzn-RequestId:" + httpURLConnection2.getRequestProperty("X-Amzn-RequestId"));
                            io.i(TAG, "Opened WebRequest Connection.");
                            jyVar.h(httpURLConnection2);
                            me a4 = a((md) mdVar, httpURLConnection2);
                            try {
                                r4 = httpURLConnection2.getInputStream();
                            } catch (IOException unused) {
                                r4 = httpURLConnection2.getErrorStream();
                            }
                            if (r4 != 0) {
                                try {
                                    a2 = jd.a((InputStream) r4);
                                } catch (IOException e) {
                                    e = e;
                                    io.e(TAG, "IOException making request to " + a(mdVar), e);
                                    if (mp.a(context, a)) {
                                        String url2 = mdVar.getUrl();
                                        fn.ey();
                                        fn.c("NetworkNotAvailable", mp.eS(url2) + "NetworkNotAvailable");
                                    }
                                    fn.bS(mdVar.getUrl());
                                    throw e;
                                } catch (UnsupportedOperationException e2) {
                                    e = e2;
                                    io.e(TAG, "UnsupportedOperationException making request to " + a(mdVar), e);
                                    fn.bS(mdVar.getUrl());
                                    throw e;
                                } catch (Throwable th) {
                                    th = th;
                                    httpURLConnection = httpURLConnection2;
                                    if (r4 != 0) {
                                        try {
                                            r4.close();
                                        } catch (IOException e3) {
                                            io.e(TAG, "Error closing stream to " + a(mdVar), e3);
                                        }
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    a.stop();
                                    throw th;
                                }
                            }
                            Object a5 = jxVar.a(a4, a2);
                            if (r4 != 0) {
                                try {
                                    r4.close();
                                } catch (IOException e4) {
                                    io.e(TAG, "Error closing stream to " + a(mdVar), e4);
                                }
                            }
                            httpURLConnection2.disconnect();
                            a.stop();
                            return a5;
                        } catch (IOException e5) {
                            e = e5;
                        }
                    } catch (UnsupportedOperationException e6) {
                        e = e6;
                    } catch (Throwable th2) {
                        th = th2;
                        r4 = 0;
                    }
                } catch (IOException e7) {
                    e = e7;
                } catch (UnsupportedOperationException e8) {
                    e = e8;
                } catch (Throwable th3) {
                    th = th3;
                    r4 = 0;
                    httpURLConnection = a3;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (IOException e9) {
            e = e9;
        } catch (UnsupportedOperationException e10) {
            e = e10;
        } catch (Throwable th5) {
            th = th5;
            r4 = 0;
        }
    }

    private static String a(md mdVar) {
        return mdVar.getUrl().replace(mdVar.iz(), "");
    }

    public static me a(md mdVar, HttpURLConnection httpURLConnection) throws IOException {
        String headerField;
        HttpVerb iA = mdVar.iA();
        int i = 1;
        if (iA == HttpVerb.HttpVerbGet) {
            httpURLConnection.setRequestMethod("GET");
        } else if (iA == HttpVerb.HttpVerbPost) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
        } else if (iA == HttpVerb.HttpVerbPut) {
            httpURLConnection.setRequestMethod(SmartDeviceDataProvider.METHOD_HTTP_PUT);
            httpURLConnection.setDoOutput(true);
        } else {
            throw new UnsupportedOperationException("unrecognized HttpVerb: ".concat(String.valueOf(iA)));
        }
        for (int i2 = 0; i2 < mdVar.iC(); i2++) {
            httpURLConnection.setRequestProperty(mdVar.o(i2), mdVar.p(i2));
        }
        if (iA == HttpVerb.HttpVerbPost || iA == HttpVerb.HttpVerbPut) {
            byte[] iD = mdVar.iD();
            if (iD != null && iD.length != 0) {
                httpURLConnection.setFixedLengthStreamingMode(iD.length);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    outputStream.write(iD);
                } finally {
                    try {
                        outputStream.flush();
                    } catch (IOException e) {
                        io.e(TAG, "Couldn't flush write body stream", e);
                    }
                    try {
                        outputStream.close();
                    } catch (IOException e2) {
                        io.e(TAG, "Couldn't close write body stream", e2);
                    }
                }
            }
            io.i(TAG, "Finished write body.");
        }
        io.dm(TAG);
        for (Map.Entry<String, List<String>> entry : httpURLConnection.getRequestProperties().entrySet()) {
            StringBuilder sb = new StringBuilder();
            for (String str : entry.getValue()) {
                sb.append(str);
                sb.append(" ");
            }
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(entry.getKey());
            sb2.append(RealTimeTextConstants.COLON_SPACE);
            sb2.append(sb.toString());
            io.dm(str2);
        }
        io.i(TAG, "Starting get response code");
        int d = RetryLogic.d(httpURLConnection);
        io.i(TAG, "Received response: ".concat(String.valueOf(d)));
        if (d != -1) {
            me meVar = new me();
            meVar.a(d);
            do {
                String headerFieldKey = httpURLConnection.getHeaderFieldKey(i);
                headerField = httpURLConnection.getHeaderField(i);
                if (headerField != null) {
                    meVar.addHeader(headerFieldKey, headerField);
                    i++;
                    continue;
                }
            } while (headerField != null);
            return meVar;
        }
        throw new IOException("Invalid response code");
    }
}
