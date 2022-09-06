package com.here.olp.network;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.OperationCanceledException;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes3.dex */
public class HttpClient {
    public static final int AUTHORIZATION_ERROR = -2;
    public static final int CANCELLED_ERROR = -5;
    public static final int INVALID_URL_ERROR = -3;
    public static final int IO_ERROR = -1;
    private static final String LOGTAG = "HttpClient";
    public static final int OFFLINE_ERROR = -4;
    public static final int THREAD_POOL_SIZE = 8;
    public static final int TIMEOUT_ERROR = -7;
    private ExecutorService executor = Executors.newFixedThreadPool(8);
    private long nativePtr;

    /* renamed from: com.here.olp.network.HttpClient$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$here$olp$network$HttpClient$HttpVerb;

        static {
            int[] iArr = new int[HttpVerb.values().length];
            $SwitchMap$com$here$olp$network$HttpClient$HttpVerb = iArr;
            try {
                HttpVerb httpVerb = HttpVerb.HEAD;
                iArr[2] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = $SwitchMap$com$here$olp$network$HttpClient$HttpVerb;
                HttpVerb httpVerb2 = HttpVerb.PUT;
                iArr2[3] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = $SwitchMap$com$here$olp$network$HttpClient$HttpVerb;
                HttpVerb httpVerb3 = HttpVerb.DELETE;
                iArr3[4] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = $SwitchMap$com$here$olp$network$HttpClient$HttpVerb;
                HttpVerb httpVerb4 = HttpVerb.PATCH;
                iArr4[5] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                int[] iArr5 = $SwitchMap$com$here$olp$network$HttpClient$HttpVerb;
                HttpVerb httpVerb5 = HttpVerb.OPTIONS;
                iArr5[6] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    /* loaded from: classes3.dex */
    private class HttpTask extends AsyncTask<Request, Void, Void> {
        private AtomicBoolean cancelled;

        private HttpTask() {
            this.cancelled = new AtomicBoolean(false);
        }

        /* synthetic */ HttpTask(HttpClient httpClient, AnonymousClass1 anonymousClass1) {
            this();
        }

        private final int calculateHeadersSize(Map<String, List<String>> map) throws IOException {
            int i = 0;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (key != null) {
                    i += key.length();
                }
                for (String str : value) {
                    if (str != null) {
                        i += str.length();
                    }
                }
            }
            return i;
        }

        private final void checkCancelled() {
            if (!this.cancelled.get()) {
                return;
            }
            throw new OperationCanceledException();
        }

        private final void cleanup(HttpURLConnection httpURLConnection) {
            if (httpURLConnection == null) {
                return;
            }
            if (httpURLConnection.getDoOutput()) {
                try {
                    if (httpURLConnection.getOutputStream() != null) {
                        httpURLConnection.getOutputStream().flush();
                    }
                } catch (IOException unused) {
                }
            }
            try {
                clearInputStream(httpURLConnection.getInputStream());
            } catch (IOException unused2) {
            }
            try {
                clearInputStream(httpURLConnection.getErrorStream());
            } catch (IOException unused3) {
            }
            if (httpURLConnection.getDoOutput()) {
                try {
                    if (httpURLConnection.getOutputStream() != null) {
                        httpURLConnection.getOutputStream().close();
                    }
                } catch (IOException unused4) {
                }
            }
            try {
                if (httpURLConnection.getInputStream() != null) {
                    httpURLConnection.getInputStream().close();
                }
            } catch (IOException unused5) {
            }
            try {
                if (httpURLConnection.getErrorStream() != null) {
                    httpURLConnection.getErrorStream().close();
                }
            } catch (IOException unused6) {
            }
            httpURLConnection.disconnect();
        }

        private final void clearInputStream(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return;
            }
            do {
            } while (inputStream.read(new byte[8192]) > 0);
        }

        public synchronized void cancelTask() {
            this.cancelled.set(true);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't wrap try/catch for region: R(13:(13:(4:237|238|(4:244|245|246|(2:248|(3:165|166|167)(1:164))(1:249))|260)(1:91)|134|135|137|138|139|140|141|142|(3:143|144|(6:146|147|148|149|(2:151|152)(1:154)|153)(1:171))|172|173|(0)(0))|111|112|(1:114)|115|(2:118|116)|119|120|121|(4:123|124|125|126)|131|132|133) */
        /* JADX WARN: Can't wrap try/catch for region: R(13:(4:237|238|(4:244|245|246|(2:248|(3:165|166|167)(1:164))(1:249))|260)(1:91)|134|135|137|138|139|140|141|142|(3:143|144|(6:146|147|148|149|(2:151|152)(1:154)|153)(1:171))|172|173|(0)(0)) */
        /* JADX WARN: Code restructure failed: missing block: B:140:0x0228, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:141:0x0229, code lost:
            r4 = r10;
         */
        /* JADX WARN: Code restructure failed: missing block: B:143:0x022d, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:145:0x0230, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:146:0x0231, code lost:
            if (r9 != null) goto L207;
         */
        /* JADX WARN: Code restructure failed: missing block: B:147:0x0233, code lost:
            r0 = new java.io.BufferedInputStream(r9.getErrorStream());
         */
        /* JADX WARN: Code restructure failed: missing block: B:166:0x0264, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:167:0x0265, code lost:
            r16 = r12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:168:0x0268, code lost:
            r16 = r12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:169:0x026a, code lost:
            throw r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:170:0x026b, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:171:0x026c, code lost:
            r4 = r10;
         */
        /* JADX WARN: Code restructure failed: missing block: B:192:0x02bd, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:193:0x02be, code lost:
            r4 = r10;
         */
        /* JADX WARN: Code restructure failed: missing block: B:203:0x02e9, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:204:0x02ea, code lost:
            r14 = r5;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:175:0x0275 A[Catch: Exception -> 0x0285, UnknownHostException -> 0x028d, SocketTimeoutException -> 0x0294, OperationCanceledException -> 0x029b, MalformedURLException -> 0x02a2, SSLException -> 0x02a9, all -> 0x034d, TryCatch #20 {all -> 0x034d, blocks: (B:10:0x001e, B:11:0x0024, B:13:0x002a, B:15:0x0030, B:16:0x0032, B:19:0x0052, B:21:0x0058, B:24:0x005e, B:35:0x0077, B:41:0x008a, B:45:0x0096, B:47:0x0099, B:50:0x00b3, B:53:0x00be, B:56:0x00df, B:58:0x00e5, B:59:0x00ee, B:60:0x00f1, B:62:0x00f5, B:63:0x00f7, B:65:0x0102, B:67:0x010b, B:75:0x012f, B:82:0x0143, B:95:0x015c, B:97:0x0162, B:176:0x027b, B:177:0x0284, B:101:0x0179, B:105:0x0189, B:107:0x0193, B:109:0x019b, B:111:0x01a5, B:113:0x01ae, B:112:0x01aa, B:116:0x01b4, B:118:0x01c6, B:120:0x01d0, B:122:0x01d6, B:124:0x01d9, B:126:0x01df, B:127:0x01e2, B:131:0x01eb, B:135:0x0202, B:137:0x0208, B:138:0x021c, B:148:0x023d, B:150:0x0240, B:152:0x0246, B:154:0x024d, B:173:0x026f, B:175:0x0275, B:195:0x02c1, B:200:0x02e6, B:147:0x0233, B:169:0x026a, B:72:0x0128, B:17:0x0037, B:18:0x004e), top: B:292:0x001e }] */
        /* JADX WARN: Removed duplicated region for block: B:199:0x02de A[LOOP:1: B:8:0x001a->B:199:0x02de, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:323:0x0284 A[EDGE_INSN: B:323:0x0284->B:177:0x0284 ?: BREAK  , SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:329:0x02d9 A[SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r0v13 */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Void doInBackground(com.here.olp.network.HttpClient.Request... r36) {
            /*
                Method dump skipped, instructions count: 1087
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.here.olp.network.HttpClient.HttpTask.doInBackground(com.here.olp.network.HttpClient$Request[]):java.lang.Void");
        }
    }

    /* loaded from: classes3.dex */
    public enum HttpVerb {
        GET,
        POST,
        HEAD,
        PUT,
        DELETE,
        PATCH,
        OPTIONS
    }

    /* loaded from: classes3.dex */
    public final class Request {
        private final int connectionTimeout;
        private final String[] headers;
        private final int maxRetries;
        private final byte[] postData;
        private final int proxyPort;
        private final String proxyServer;
        private final Proxy.Type proxyType;
        private final long requestId;
        private final int requestTimeout;
        private final String url;
        private final HttpVerb verb;

        public Request(String str, HttpVerb httpVerb, long j, int i, int i2, String[] strArr, byte[] bArr, String str2, int i3, int i4, int i5) {
            Proxy.Type type;
            this.url = str;
            this.verb = httpVerb;
            this.requestId = j;
            this.connectionTimeout = i;
            this.requestTimeout = i2;
            this.headers = strArr;
            this.postData = bArr;
            this.proxyServer = str2;
            this.proxyPort = i3;
            if (i4 != 0) {
                if (i4 != 1) {
                    if (i4 == 2) {
                        type = Proxy.Type.SOCKS;
                    } else if (i4 == 3 || i4 == 4 || i4 == 5) {
                        this.proxyType = Proxy.Type.SOCKS;
                        Log.w(HttpClient.LOGTAG, "HttpClient::Request(): Unsupported proxy version (" + i4 + "). Falling back to SOCKS4(4)");
                        this.maxRetries = i5;
                    } else {
                        Log.w(HttpClient.LOGTAG, "HttpClient::Request(): Unsupported proxy version (" + i4 + "). Falling back to HTTP(0)");
                    }
                }
                type = Proxy.Type.HTTP;
            } else {
                type = Proxy.Type.DIRECT;
            }
            this.proxyType = type;
            this.maxRetries = i5;
        }

        public final int connectTimeout() {
            return this.connectionTimeout;
        }

        public final boolean hasProxy() {
            String str = this.proxyServer;
            return str != null && !str.isEmpty();
        }

        public final String[] headers() {
            return this.headers;
        }

        public final int maxRetries() {
            return this.maxRetries;
        }

        public final boolean noProxy() {
            return (hasProxy() && this.proxyServer.equals("No")) || this.proxyType == Proxy.Type.DIRECT;
        }

        public final byte[] postData() {
            return this.postData;
        }

        public final int proxyPort() {
            return this.proxyPort;
        }

        public final String proxyServer() {
            return this.proxyServer;
        }

        public final Proxy.Type proxyType() {
            return this.proxyType;
        }

        public final long requestId() {
            return this.requestId;
        }

        public final int requestTimeout() {
            return this.requestTimeout;
        }

        public final String url() {
            return this.url;
        }

        public final HttpVerb verb() {
            return this.verb;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native synchronized void completeRequest(long j, int i, int i2, int i3, String str, String str2);

    /* JADX INFO: Access modifiers changed from: private */
    public native synchronized void dataCallback(long j, byte[] bArr, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native synchronized void dateAndOffsetCallback(long j, long j2, long j3);

    /* JADX INFO: Access modifiers changed from: private */
    public native synchronized void headersCallback(long j, String[] strArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native synchronized void resetRequest(long j);

    public static HttpVerb toHttpVerb(int i) {
        switch (i) {
            case 0:
                return HttpVerb.GET;
            case 1:
                return HttpVerb.POST;
            case 2:
                return HttpVerb.HEAD;
            case 3:
                return HttpVerb.PUT;
            case 4:
                return HttpVerb.DELETE;
            case 5:
                return HttpVerb.PATCH;
            case 6:
                return HttpVerb.OPTIONS;
            default:
                return HttpVerb.GET;
        }
    }

    public HttpTask send(String str, int i, long j, int i2, int i3, String[] strArr, byte[] bArr, String str2, int i4, int i5, int i6) {
        Request request = new Request(str, toHttpVerb(i), j, i2, i3, strArr, bArr, str2, i4, i5, i6);
        HttpTask httpTask = new HttpTask(this, null);
        httpTask.executeOnExecutor(this.executor, request);
        return httpTask;
    }

    public void shutdown() {
        ExecutorService executorService = this.executor;
        if (executorService != null) {
            executorService.shutdown();
            this.executor = null;
        }
        synchronized (this) {
            this.nativePtr = 0L;
        }
    }
}
