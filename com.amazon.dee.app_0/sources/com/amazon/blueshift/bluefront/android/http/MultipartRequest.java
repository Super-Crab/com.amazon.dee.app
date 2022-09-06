package com.amazon.blueshift.bluefront.android.http;

import com.amazon.blueshift.bluefront.android.SpeechClientException;
import com.amazon.blueshift.bluefront.android.http.part.Part;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import com.google.common.io.Closeables;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;
import javax.net.ssl.HttpsURLConnection;
/* loaded from: classes11.dex */
public final class MultipartRequest {
    protected static final String BOUNDARY_PREFIX = "--";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String MULTIPART_FORMDATA = "multipart/form-data; boundary=";
    protected static final String NEW_LINE = "\r\n";
    private static final String TAG = "com.amazon.blueshift.bluefront.android.http.MultipartRequest";
    private final String mBoundary;
    private final HttpsURLConnection mConnection;
    private final Part[] mParts;

    public MultipartRequest(HttpsURLConnection httpsURLConnection, Part... partArr) {
        this(httpsURLConnection, Long.toHexString(System.currentTimeMillis()), partArr);
    }

    private byte[] readInputStream(InputStream inputStream) throws IOException {
        try {
            return ByteStreams.toByteArray(inputStream);
        } finally {
            Closeables.close(inputStream, true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] execute() throws com.amazon.blueshift.bluefront.android.SpeechClientException {
        /*
            r7 = this;
            java.lang.String r0 = "Failed to close request stream"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "multipart/form-data; boundary="
            r1.<init>(r2)
            java.lang.String r2 = r7.mBoundary
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "--"
            r2.<init>(r3)
            java.lang.String r3 = r7.mBoundary
            java.lang.String r4 = "\r\n"
            java.lang.String r2 = com.android.tools.r8.GeneratedOutlineSupport1.outline91(r2, r3, r4)
            javax.net.ssl.HttpsURLConnection r3 = r7.mConnection
            java.lang.String r4 = "Content-Type"
            r3.setRequestProperty(r4, r1)
            javax.net.ssl.HttpsURLConnection r1 = r7.mConnection
            r3 = 1
            r1.setDoOutput(r3)
            r1 = 0
            javax.net.ssl.HttpsURLConnection r3 = r7.mConnection     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e com.amazon.blueshift.bluefront.android.SpeechClientException -> L79
            r3.connect()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e com.amazon.blueshift.bluefront.android.SpeechClientException -> L79
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e com.amazon.blueshift.bluefront.android.SpeechClientException -> L79
            javax.net.ssl.HttpsURLConnection r4 = r7.mConnection     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e com.amazon.blueshift.bluefront.android.SpeechClientException -> L79
            java.io.OutputStream r4 = r4.getOutputStream()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e com.amazon.blueshift.bluefront.android.SpeechClientException -> L79
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e com.amazon.blueshift.bluefront.android.SpeechClientException -> L79
            com.amazon.blueshift.bluefront.android.http.part.Part[] r1 = r7.mParts     // Catch: java.lang.Exception -> L68 com.amazon.blueshift.bluefront.android.SpeechClientException -> L6a java.lang.Throwable -> L7d
            int r4 = r1.length     // Catch: java.lang.Exception -> L68 com.amazon.blueshift.bluefront.android.SpeechClientException -> L6a java.lang.Throwable -> L7d
            r5 = 0
        L43:
            if (r5 >= r4) goto L4d
            r6 = r1[r5]     // Catch: java.lang.Exception -> L68 com.amazon.blueshift.bluefront.android.SpeechClientException -> L6a java.lang.Throwable -> L7d
            r6.writePart(r3, r2)     // Catch: java.lang.Exception -> L68 com.amazon.blueshift.bluefront.android.SpeechClientException -> L6a java.lang.Throwable -> L7d
            int r5 = r5 + 1
            goto L43
        L4d:
            r7.sendFooter(r3)     // Catch: java.lang.Exception -> L68 com.amazon.blueshift.bluefront.android.SpeechClientException -> L6a java.lang.Throwable -> L7d
            r3.close()     // Catch: java.lang.Exception -> L68 com.amazon.blueshift.bluefront.android.SpeechClientException -> L6a java.lang.Throwable -> L7d
            javax.net.ssl.HttpsURLConnection r1 = r7.mConnection     // Catch: java.lang.Exception -> L68 com.amazon.blueshift.bluefront.android.SpeechClientException -> L6a java.lang.Throwable -> L7d
            byte[] r1 = r7.fetchResponse(r1)     // Catch: java.lang.Exception -> L68 com.amazon.blueshift.bluefront.android.SpeechClientException -> L6a java.lang.Throwable -> L7d
            r3.close()     // Catch: java.io.IOException -> L5d
            goto L62
        L5d:
            java.lang.String r2 = com.amazon.blueshift.bluefront.android.http.MultipartRequest.TAG
            android.util.Log.w(r2, r0)
        L62:
            javax.net.ssl.HttpsURLConnection r0 = r7.mConnection
            r0.disconnect()
            return r1
        L68:
            r1 = move-exception
            goto L71
        L6a:
            r1 = move-exception
            goto L7c
        L6c:
            r2 = move-exception
            goto L80
        L6e:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L71:
            com.amazon.blueshift.bluefront.android.SpeechClientConnectionException r2 = new com.amazon.blueshift.bluefront.android.SpeechClientConnectionException     // Catch: java.lang.Throwable -> L7d
            java.lang.String r4 = "Failed to execute HTTP request"
            r2.<init>(r4, r1)     // Catch: java.lang.Throwable -> L7d
            throw r2     // Catch: java.lang.Throwable -> L7d
        L79:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L7c:
            throw r1     // Catch: java.lang.Throwable -> L7d
        L7d:
            r1 = move-exception
            r2 = r1
            r1 = r3
        L80:
            if (r1 == 0) goto L8b
            r1.close()     // Catch: java.io.IOException -> L86
            goto L8b
        L86:
            java.lang.String r1 = com.amazon.blueshift.bluefront.android.http.MultipartRequest.TAG
            android.util.Log.w(r1, r0)
        L8b:
            javax.net.ssl.HttpsURLConnection r0 = r7.mConnection
            r0.disconnect()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.blueshift.bluefront.android.http.MultipartRequest.execute():byte[]");
    }

    byte[] fetchResponse(HttpsURLConnection httpsURLConnection) throws SpeechClientException {
        Preconditions.checkNotNull(httpsURLConnection, "No connection when fetching response");
        try {
            int responseCode = httpsURLConnection.getResponseCode();
            String str = "response code: " + responseCode;
            if (responseCode == 200) {
                return readInputStream(new BufferedInputStream(httpsURLConnection.getInputStream()));
            }
            String str2 = new String(readInputStream(new BufferedInputStream(httpsURLConnection.getErrorStream())), Charsets.UTF_8.displayName());
            r0 = "Non-successful response: " + str2;
            throw new SpeechClientException(responseCode + RealTimeTextConstants.COLON_SPACE + str2);
        } catch (SocketTimeoutException e) {
            throw new SpeechClientException("Processing Timeout", e);
        } catch (IOException e2) {
            throw new SpeechClientException("Failed to read response", e2);
        }
    }

    void sendFooter(OutputStream outputStream) throws IOException {
        outputStream.write(GeneratedOutlineSupport1.outline92(new StringBuilder("--"), this.mBoundary, "--", "\r\n").getBytes(Charsets.UTF_8.displayName()));
    }

    MultipartRequest(HttpsURLConnection httpsURLConnection, String str, Part... partArr) {
        Preconditions.checkNotNull(httpsURLConnection, "Connection cannot be null");
        Preconditions.checkNotNull(partArr, "Parts cannot be null.");
        Preconditions.checkArgument(partArr.length > 0, "Parts cannot be empty");
        this.mConnection = httpsURLConnection;
        this.mBoundary = str;
        this.mParts = partArr;
    }
}
