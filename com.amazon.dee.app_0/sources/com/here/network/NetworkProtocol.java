package com.here.network;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.OperationCanceledException;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes3.dex */
public class NetworkProtocol {
    public static final int AUTHORIZATION_ERROR = -2;
    public static final int CANCELLED_ERROR = -5;
    public static final int INVALID_URL_ERROR = -3;
    public static final int IO_ERROR = -1;
    public static final int OFFLINE_ERROR = -4;
    public static final int TIMEOUT_ERROR = -7;
    private final String LOGTAG = "NetworkProtocol";
    private int m_clientId = (int) (System.currentTimeMillis() % 10000);
    private ExecutorService m_executor = Executors.newFixedThreadPool(8);

    @SuppressLint({"StaticFieldLeak"})
    /* loaded from: classes3.dex */
    private class GetTask extends AsyncTask<Request, Void, Void> {
        private GetTask() {
        }

        private void checkCancelled() {
            if (!isCancelled()) {
                return;
            }
            throw new OperationCanceledException();
        }

        private void cleanup(HttpURLConnection httpURLConnection) {
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

        private void clearInputStream(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return;
            }
            do {
            } while (inputStream.read(new byte[8192]) > 0);
        }

        public synchronized boolean cancelTask(boolean z) {
            return cancel(z);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't wrap try/catch for region: R(47:(3:8|9|10)|36|(3:38|(1:40)(1:239)|41)(1:240)|42|(4:44|(2:46|(1:48))(1:52)|49|(1:51))|53|(1:55)|(3:57|(1:59)(2:61|(1:63)(2:64|(1:66)(2:67|(1:69)(2:70|(1:72)(1:73)))))|60)|74|(3:76|(2:77|(3:79|(2:81|82)(1:84)|83)(1:85))|86)(1:238)|87|(2:90|(1:92)(1:93))|94|(1:96)|97|(1:99)(1:237)|100|(4:213|214|(6:220|221|222|(1:224)|186|187)|233)(1:102)|103|(1:105)(1:212)|106|(1:108)(1:211)|109|(26:113|(2:208|209)(1:115)|116|117|118|(21:123|(1:125)(5:196|197|198|199|200)|126|(20:130|(2:193|194)(1:132)|133|134|135|(2:138|136)|139|140|(1:142)|143|144|145|146|147|148|149|(2:150|(1:152)(1:153))|154|155|156)|195|135|(1:136)|139|140|(0)|143|144|145|146|147|148|149|(3:150|(0)(0)|152)|154|155|156)|204|126|(21:128|130|(0)(0)|133|134|135|(1:136)|139|140|(0)|143|144|145|146|147|148|149|(3:150|(0)(0)|152)|154|155|156)|195|135|(1:136)|139|140|(0)|143|144|145|146|147|148|149|(3:150|(0)(0)|152)|154|155|156)|210|118|(22:120|123|(0)(0)|126|(0)|195|135|(1:136)|139|140|(0)|143|144|145|146|147|148|149|(3:150|(0)(0)|152)|154|155|156)|204|126|(0)|195|135|(1:136)|139|140|(0)|143|144|145|146|147|148|149|(3:150|(0)(0)|152)|154|155|156) */
        /* JADX WARN: Code restructure failed: missing block: B:161:0x02dc, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:163:0x02de, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:165:0x02e0, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:166:0x02e1, code lost:
            if (r11 != null) goto L168;
         */
        /* JADX WARN: Code restructure failed: missing block: B:167:0x02e3, code lost:
            r0 = new java.io.BufferedInputStream(r11.getErrorStream());
         */
        /* JADX WARN: Code restructure failed: missing block: B:172:0x030a, code lost:
            throw r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:173:0x030b, code lost:
            r5 = r10 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:175:0x0311, code lost:
            if (r10 < r8.maxRetries()) goto L183;
         */
        /* JADX WARN: Code restructure failed: missing block: B:176:0x0313, code lost:
            r0 = r34.this$0;
            r7 = r8.clientId();
         */
        /* JADX WARN: Code restructure failed: missing block: B:180:0x0323, code lost:
            if (r15 != 304) goto L175;
         */
        /* JADX WARN: Code restructure failed: missing block: B:184:0x032a, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:185:0x032b, code lost:
            if (r15 == 0) goto L163;
         */
        /* JADX WARN: Code restructure failed: missing block: B:190:0x0349, code lost:
            throw r0;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:130:0x0225  */
        /* JADX WARN: Removed duplicated region for block: B:140:0x0258 A[Catch: all -> 0x034a, Exception -> 0x034d, UnknownHostException -> 0x0350, SocketTimeoutException -> 0x0352, OperationCanceledException -> 0x0354, MalformedURLException -> 0x0356, SSLException -> 0x0359, TryCatch #5 {UnknownHostException -> 0x0350, blocks: (B:9:0x001c, B:10:0x0022, B:12:0x0028, B:14:0x002e, B:15:0x0030, B:18:0x0050, B:20:0x0056, B:22:0x0063, B:24:0x0071, B:26:0x008b, B:28:0x008f, B:29:0x0096, B:31:0x009a, B:33:0x00a0, B:36:0x00aa, B:37:0x00ae, B:40:0x00b9, B:43:0x00c4, B:46:0x00cf, B:50:0x00dd, B:53:0x00e5, B:55:0x00ea, B:58:0x0104, B:61:0x010b, B:64:0x012f, B:66:0x0135, B:67:0x013e, B:68:0x0141, B:70:0x0145, B:71:0x0147, B:73:0x0151, B:97:0x0195, B:99:0x019b, B:100:0x01a1, B:177:0x031b, B:178:0x0320, B:102:0x01aa, B:106:0x01ba, B:110:0x01c7, B:112:0x01d7, B:114:0x01df, B:123:0x020c, B:125:0x0214, B:128:0x021d, B:138:0x0250, B:140:0x0258, B:142:0x0260, B:152:0x028e, B:154:0x0294, B:155:0x0297, B:157:0x029e, B:158:0x02b1, B:159:0x02d0, B:168:0x02ed, B:169:0x02ef, B:171:0x02f5, B:186:0x032d, B:174:0x030d, B:176:0x0313, B:190:0x0349, B:167:0x02e3, B:172:0x030a, B:149:0x027b, B:136:0x023e, B:121:0x01fa, B:74:0x0161, B:16:0x0035, B:17:0x004c), top: B:227:0x001c }] */
        /* JADX WARN: Removed duplicated region for block: B:145:0x026f A[Catch: Exception -> 0x027a, all -> 0x034a, TryCatch #18 {all -> 0x034a, blocks: (B:9:0x001c, B:10:0x0022, B:12:0x0028, B:14:0x002e, B:15:0x0030, B:18:0x0050, B:20:0x0056, B:22:0x0063, B:24:0x0071, B:26:0x008b, B:28:0x008f, B:29:0x0096, B:31:0x009a, B:33:0x00a0, B:36:0x00aa, B:37:0x00ae, B:40:0x00b9, B:43:0x00c4, B:46:0x00cf, B:50:0x00dd, B:53:0x00e5, B:55:0x00ea, B:58:0x0104, B:61:0x010b, B:64:0x012f, B:66:0x0135, B:67:0x013e, B:68:0x0141, B:70:0x0145, B:71:0x0147, B:73:0x0151, B:77:0x0169, B:84:0x017d, B:97:0x0195, B:99:0x019b, B:100:0x01a1, B:177:0x031b, B:178:0x0320, B:102:0x01aa, B:106:0x01ba, B:110:0x01c7, B:112:0x01d7, B:114:0x01df, B:116:0x01e9, B:118:0x01f2, B:123:0x020c, B:125:0x0214, B:128:0x021d, B:138:0x0250, B:140:0x0258, B:142:0x0260, B:144:0x026a, B:146:0x0273, B:152:0x028e, B:154:0x0294, B:155:0x0297, B:157:0x029e, B:158:0x02b1, B:159:0x02d0, B:168:0x02ed, B:169:0x02ef, B:171:0x02f5, B:186:0x032d, B:174:0x030d, B:176:0x0313, B:190:0x0349, B:167:0x02e3, B:172:0x030a, B:145:0x026f, B:149:0x027b, B:131:0x0228, B:133:0x022c, B:136:0x023e, B:117:0x01ee, B:121:0x01fa, B:74:0x0161, B:16:0x0035, B:17:0x004c), top: B:227:0x001c }] */
        /* JADX WARN: Removed duplicated region for block: B:154:0x0294 A[Catch: all -> 0x034a, Exception -> 0x034d, UnknownHostException -> 0x0350, SocketTimeoutException -> 0x0352, OperationCanceledException -> 0x0354, MalformedURLException -> 0x0356, SSLException -> 0x0359, LOOP:3: B:152:0x028e->B:154:0x0294, LOOP_END, TryCatch #5 {UnknownHostException -> 0x0350, blocks: (B:9:0x001c, B:10:0x0022, B:12:0x0028, B:14:0x002e, B:15:0x0030, B:18:0x0050, B:20:0x0056, B:22:0x0063, B:24:0x0071, B:26:0x008b, B:28:0x008f, B:29:0x0096, B:31:0x009a, B:33:0x00a0, B:36:0x00aa, B:37:0x00ae, B:40:0x00b9, B:43:0x00c4, B:46:0x00cf, B:50:0x00dd, B:53:0x00e5, B:55:0x00ea, B:58:0x0104, B:61:0x010b, B:64:0x012f, B:66:0x0135, B:67:0x013e, B:68:0x0141, B:70:0x0145, B:71:0x0147, B:73:0x0151, B:97:0x0195, B:99:0x019b, B:100:0x01a1, B:177:0x031b, B:178:0x0320, B:102:0x01aa, B:106:0x01ba, B:110:0x01c7, B:112:0x01d7, B:114:0x01df, B:123:0x020c, B:125:0x0214, B:128:0x021d, B:138:0x0250, B:140:0x0258, B:142:0x0260, B:152:0x028e, B:154:0x0294, B:155:0x0297, B:157:0x029e, B:158:0x02b1, B:159:0x02d0, B:168:0x02ed, B:169:0x02ef, B:171:0x02f5, B:186:0x032d, B:174:0x030d, B:176:0x0313, B:190:0x0349, B:167:0x02e3, B:172:0x030a, B:149:0x027b, B:136:0x023e, B:121:0x01fa, B:74:0x0161, B:16:0x0035, B:17:0x004c), top: B:227:0x001c }] */
        /* JADX WARN: Removed duplicated region for block: B:157:0x029e A[Catch: all -> 0x034a, Exception -> 0x034d, UnknownHostException -> 0x0350, SocketTimeoutException -> 0x0352, OperationCanceledException -> 0x0354, MalformedURLException -> 0x0356, SSLException -> 0x0359, LOOP:4: B:156:0x029c->B:157:0x029e, LOOP_END, TryCatch #5 {UnknownHostException -> 0x0350, blocks: (B:9:0x001c, B:10:0x0022, B:12:0x0028, B:14:0x002e, B:15:0x0030, B:18:0x0050, B:20:0x0056, B:22:0x0063, B:24:0x0071, B:26:0x008b, B:28:0x008f, B:29:0x0096, B:31:0x009a, B:33:0x00a0, B:36:0x00aa, B:37:0x00ae, B:40:0x00b9, B:43:0x00c4, B:46:0x00cf, B:50:0x00dd, B:53:0x00e5, B:55:0x00ea, B:58:0x0104, B:61:0x010b, B:64:0x012f, B:66:0x0135, B:67:0x013e, B:68:0x0141, B:70:0x0145, B:71:0x0147, B:73:0x0151, B:97:0x0195, B:99:0x019b, B:100:0x01a1, B:177:0x031b, B:178:0x0320, B:102:0x01aa, B:106:0x01ba, B:110:0x01c7, B:112:0x01d7, B:114:0x01df, B:123:0x020c, B:125:0x0214, B:128:0x021d, B:138:0x0250, B:140:0x0258, B:142:0x0260, B:152:0x028e, B:154:0x0294, B:155:0x0297, B:157:0x029e, B:158:0x02b1, B:159:0x02d0, B:168:0x02ed, B:169:0x02ef, B:171:0x02f5, B:186:0x032d, B:174:0x030d, B:176:0x0313, B:190:0x0349, B:167:0x02e3, B:172:0x030a, B:149:0x027b, B:136:0x023e, B:121:0x01fa, B:74:0x0161, B:16:0x0035, B:17:0x004c), top: B:227:0x001c }] */
        /* JADX WARN: Removed duplicated region for block: B:171:0x02f5 A[Catch: SocketTimeoutException -> 0x02dc, ProtocolException -> 0x02de, FileNotFoundException -> 0x032a, all -> 0x034a, Exception -> 0x034d, UnknownHostException -> 0x0350, OperationCanceledException -> 0x0354, MalformedURLException -> 0x0356, SSLException -> 0x0359, LOOP:5: B:169:0x02ef->B:171:0x02f5, LOOP_END, TryCatch #5 {UnknownHostException -> 0x0350, blocks: (B:9:0x001c, B:10:0x0022, B:12:0x0028, B:14:0x002e, B:15:0x0030, B:18:0x0050, B:20:0x0056, B:22:0x0063, B:24:0x0071, B:26:0x008b, B:28:0x008f, B:29:0x0096, B:31:0x009a, B:33:0x00a0, B:36:0x00aa, B:37:0x00ae, B:40:0x00b9, B:43:0x00c4, B:46:0x00cf, B:50:0x00dd, B:53:0x00e5, B:55:0x00ea, B:58:0x0104, B:61:0x010b, B:64:0x012f, B:66:0x0135, B:67:0x013e, B:68:0x0141, B:70:0x0145, B:71:0x0147, B:73:0x0151, B:97:0x0195, B:99:0x019b, B:100:0x01a1, B:177:0x031b, B:178:0x0320, B:102:0x01aa, B:106:0x01ba, B:110:0x01c7, B:112:0x01d7, B:114:0x01df, B:123:0x020c, B:125:0x0214, B:128:0x021d, B:138:0x0250, B:140:0x0258, B:142:0x0260, B:152:0x028e, B:154:0x0294, B:155:0x0297, B:157:0x029e, B:158:0x02b1, B:159:0x02d0, B:168:0x02ed, B:169:0x02ef, B:171:0x02f5, B:186:0x032d, B:174:0x030d, B:176:0x0313, B:190:0x0349, B:167:0x02e3, B:172:0x030a, B:149:0x027b, B:136:0x023e, B:121:0x01fa, B:74:0x0161, B:16:0x0035, B:17:0x004c), top: B:227:0x001c }] */
        /* JADX WARN: Removed duplicated region for block: B:230:0x0228 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:232:0x026a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:252:0x0344 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:260:0x032d A[EDGE_INSN: B:260:0x032d->B:186:0x032d ?: BREAK  , SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r0v8 */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Void doInBackground(com.here.network.NetworkProtocol.Request... r35) {
            /*
                Method dump skipped, instructions count: 1034
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.here.network.NetworkProtocol.GetTask.doInBackground(com.here.network.NetworkProtocol$Request[]):java.lang.Void");
        }
    }

    /* loaded from: classes3.dex */
    public enum HttpVerb {
        GET,
        POST,
        HEAD,
        PUT,
        DELETE,
        PATCH
    }

    /* loaded from: classes3.dex */
    public class Request {
        private final String m_certificatePath;
        private final int m_clientId;
        private final int m_connectTimeout;
        private final String[] m_headers;
        private final int m_maxRetries;
        private final byte[] m_postData;
        private final int m_proxyPort;
        private final String m_proxyServer;
        private final Proxy.Type m_proxyType;
        private final int m_requestId;
        private final int m_requestTimeout;
        private final String m_url;
        private final HttpVerb m_verb;

        public Request(String str, HttpVerb httpVerb, int i, int i2, int i3, int i4, String[] strArr, byte[] bArr, boolean z, String str2, int i5, int i6, String str3, int i7) {
            Proxy.Type type;
            this.m_url = str;
            this.m_verb = httpVerb;
            this.m_clientId = i;
            this.m_requestId = i2;
            this.m_connectTimeout = i3;
            this.m_requestTimeout = i4;
            this.m_headers = strArr;
            this.m_postData = bArr;
            this.m_proxyServer = str2;
            this.m_proxyPort = i5;
            this.m_certificatePath = str3;
            if (i6 != 0) {
                if (i6 == 4) {
                    type = Proxy.Type.SOCKS;
                    this.m_proxyType = type;
                    this.m_maxRetries = i7;
                } else if (i6 == 5 || i6 == 6 || i6 == 7) {
                    this.m_proxyType = Proxy.Type.SOCKS;
                    Log.e("NetworkProtocol", "NetworkProtocol::Request(): Unsupported proxy version (" + i6 + "). Falling back to SOCKS4(4)");
                    this.m_maxRetries = i7;
                } else {
                    Log.e("NetworkProtocol", "NetworkProtocol::Request(): Unsupported proxy version (" + i6 + "). Falling back to HTTP(0)");
                }
            }
            type = Proxy.Type.HTTP;
            this.m_proxyType = type;
            this.m_maxRetries = i7;
        }

        public final String certificatePath() {
            return this.m_certificatePath;
        }

        public final int clientId() {
            return this.m_clientId;
        }

        public final int connectTimeout() {
            return this.m_connectTimeout;
        }

        public final boolean hasProxy() {
            String str = this.m_proxyServer;
            return str != null && !str.isEmpty();
        }

        public final String[] headers() {
            return this.m_headers;
        }

        public final int maxRetries() {
            return this.m_maxRetries;
        }

        public final boolean noProxy() {
            return hasProxy() && this.m_proxyServer.equals("No");
        }

        public final byte[] postData() {
            return this.m_postData;
        }

        public final int proxyPort() {
            return this.m_proxyPort;
        }

        public final String proxyServer() {
            return this.m_proxyServer;
        }

        public final Proxy.Type proxyType() {
            return this.m_proxyType;
        }

        public final int requestId() {
            return this.m_requestId;
        }

        public final int requestTimeout() {
            return this.m_requestTimeout;
        }

        public final String url() {
            return this.m_url;
        }

        public final HttpVerb verb() {
            return this.m_verb;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void completeRequest(int i, int i2, int i3, String str, int i4, int i5, String str2, String str3);

    /* JADX INFO: Access modifiers changed from: private */
    public native void dataCallback(int i, int i2, byte[] bArr, int i3);

    /* JADX INFO: Access modifiers changed from: private */
    public native void dateAndOffsetCallback(int i, int i2, long j, long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void headersCallback(int i, int i2, String[] strArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native void resetRequest(int i, int i2);

    public static HttpVerb toHttpVerb(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? HttpVerb.GET : HttpVerb.PATCH : HttpVerb.DELETE : HttpVerb.PUT : HttpVerb.HEAD : HttpVerb.POST : HttpVerb.GET;
    }

    public int registerClient() {
        int i = this.m_clientId;
        this.m_clientId = i + 1;
        return i;
    }

    public GetTask send(String str, int i, int i2, int i3, int i4, int i5, String[] strArr, byte[] bArr, boolean z, String str2, int i6, int i7, String str3, int i8) {
        Request request = new Request(str, toHttpVerb(i), i2, i3, i4, i5, strArr, bArr, z, str2, i6, i7, str3, i8);
        GetTask getTask = new GetTask();
        getTask.executeOnExecutor(this.m_executor, request);
        return getTask;
    }

    public void shutdown() {
        ExecutorService executorService = this.m_executor;
        if (executorService != null) {
            executorService.shutdown();
            this.m_executor = null;
        }
    }
}
