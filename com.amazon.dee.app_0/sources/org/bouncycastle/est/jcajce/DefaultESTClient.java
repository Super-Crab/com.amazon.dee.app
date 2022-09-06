package org.bouncycastle.est.jcajce;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import org.bouncycastle.est.ESTClient;
import org.bouncycastle.est.ESTClientSourceProvider;
import org.bouncycastle.est.ESTException;
import org.bouncycastle.est.ESTRequest;
import org.bouncycastle.est.ESTRequestBuilder;
import org.bouncycastle.est.ESTResponse;
/* loaded from: classes4.dex */
class DefaultESTClient implements ESTClient {
    private final ESTClientSourceProvider sslSocketProvider;
    private static final Charset utf8 = Charset.forName("UTF-8");
    private static byte[] CRLF = {13, 10};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class PrintingOutputStream extends OutputStream {
        private final OutputStream tgt;

        public PrintingOutputStream(OutputStream outputStream) {
            this.tgt = outputStream;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            System.out.print(String.valueOf((char) i));
            this.tgt.write(i);
        }
    }

    public DefaultESTClient(ESTClientSourceProvider eSTClientSourceProvider) {
        this.sslSocketProvider = eSTClientSourceProvider;
    }

    private static void writeLine(OutputStream outputStream, String str) throws IOException {
        outputStream.write(str.getBytes());
        outputStream.write(CRLF);
    }

    @Override // org.bouncycastle.est.ESTClient
    public ESTResponse doRequest(ESTRequest eSTRequest) throws IOException {
        ESTResponse performRequest;
        int i = 15;
        while (true) {
            performRequest = performRequest(eSTRequest);
            ESTRequest redirectURL = redirectURL(performRequest);
            if (redirectURL == null || i - 1 <= 0) {
                break;
            }
            eSTRequest = redirectURL;
        }
        if (i != 0) {
            return performRequest;
        }
        throw new ESTException("Too many redirects..");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0066 A[Catch: all -> 0x014f, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0087 A[Catch: all -> 0x014f, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009a A[Catch: all -> 0x014f, TRY_ENTER, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b8 A[Catch: all -> 0x014f, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00f3 A[Catch: all -> 0x014f, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0139 A[Catch: all -> 0x014f, TRY_LEAVE, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0149 A[Catch: all -> 0x014f, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x014f, blocks: (B:3:0x0003, B:5:0x001f, B:6:0x0027, B:8:0x0035, B:11:0x003e, B:13:0x004c, B:15:0x0066, B:17:0x0071, B:19:0x0087, B:20:0x008c, B:23:0x009a, B:24:0x00b4, B:26:0x00bd, B:27:0x00ed, B:29:0x00f3, B:30:0x0100, B:32:0x0103, B:33:0x0125, B:35:0x0139, B:40:0x0149, B:25:0x00b8, B:12:0x0043), top: B:46:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.bouncycastle.est.ESTResponse performRequest(org.bouncycastle.est.ESTRequest r10) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.jcajce.DefaultESTClient.performRequest(org.bouncycastle.est.ESTRequest):org.bouncycastle.est.ESTResponse");
    }

    protected ESTRequest redirectURL(ESTResponse eSTResponse) throws IOException {
        ESTRequest eSTRequest;
        ESTRequestBuilder withURL;
        if (eSTResponse.getStatusCode() < 300 || eSTResponse.getStatusCode() > 399) {
            eSTRequest = null;
        } else {
            switch (eSTResponse.getStatusCode()) {
                case 301:
                case 302:
                case 303:
                case 306:
                case 307:
                    String header = eSTResponse.getHeader("Location");
                    if ("".equals(header)) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Redirect status type: ");
                        outline107.append(eSTResponse.getStatusCode());
                        outline107.append(" but no location header");
                        throw new ESTException(outline107.toString());
                    }
                    ESTRequestBuilder eSTRequestBuilder = new ESTRequestBuilder(eSTResponse.getOriginalRequest());
                    if (header.startsWith("http")) {
                        withURL = eSTRequestBuilder.withURL(new URL(header));
                    } else {
                        URL url = eSTResponse.getOriginalRequest().getURL();
                        withURL = eSTRequestBuilder.withURL(new URL(url.getProtocol(), url.getHost(), url.getPort(), header));
                    }
                    eSTRequest = withURL.build();
                    break;
                case 304:
                case 305:
                default:
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Client does not handle http status code: ");
                    outline1072.append(eSTResponse.getStatusCode());
                    throw new ESTException(outline1072.toString());
            }
        }
        if (eSTRequest != null) {
            eSTResponse.close();
        }
        return eSTRequest;
    }
}
