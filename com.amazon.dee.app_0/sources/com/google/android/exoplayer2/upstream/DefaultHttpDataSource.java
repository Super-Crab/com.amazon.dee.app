package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.animated.InterpolationAnimatedNode;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Predicate;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes2.dex */
public class DefaultHttpDataSource extends BaseDataSource implements HttpDataSource {
    private static final Pattern CONTENT_RANGE_HEADER = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 8000;
    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 8000;
    private static final int HTTP_STATUS_PERMANENT_REDIRECT = 308;
    private static final int HTTP_STATUS_TEMPORARY_REDIRECT = 307;
    private static final long MAX_BYTES_TO_DRAIN = 2048;
    private static final int MAX_REDIRECTS = 20;
    private static final String TAG = "DefaultHttpDataSource";
    private final boolean allowCrossProtocolRedirects;
    private long bytesRead;
    private long bytesSkipped;
    private long bytesToRead;
    private long bytesToSkip;
    private final int connectTimeoutMillis;
    @Nullable
    private HttpURLConnection connection;
    @Nullable
    private Predicate<String> contentTypePredicate;
    @Nullable
    private DataSpec dataSpec;
    @Nullable
    private final HttpDataSource.RequestProperties defaultRequestProperties;
    @Nullable
    private InputStream inputStream;
    private boolean opened;
    private final int readTimeoutMillis;
    private final HttpDataSource.RequestProperties requestProperties;
    private int responseCode;
    private byte[] skipBuffer;
    @Nullable
    private final String userAgent;

    /* loaded from: classes2.dex */
    public static final class Factory implements HttpDataSource.Factory {
        private boolean allowCrossProtocolRedirects;
        @Nullable
        private Predicate<String> contentTypePredicate;
        @Nullable
        private TransferListener transferListener;
        @Nullable
        private String userAgent;
        private final HttpDataSource.RequestProperties defaultRequestProperties = new HttpDataSource.RequestProperties();
        private int connectTimeoutMs = 8000;
        private int readTimeoutMs = 8000;

        @Override // com.google.android.exoplayer2.upstream.HttpDataSource.Factory
        @Deprecated
        public final HttpDataSource.RequestProperties getDefaultRequestProperties() {
            return this.defaultRequestProperties;
        }

        public Factory setAllowCrossProtocolRedirects(boolean z) {
            this.allowCrossProtocolRedirects = z;
            return this;
        }

        public Factory setConnectTimeoutMs(int i) {
            this.connectTimeoutMs = i;
            return this;
        }

        public Factory setContentTypePredicate(@Nullable Predicate<String> predicate) {
            this.contentTypePredicate = predicate;
            return this;
        }

        @Override // com.google.android.exoplayer2.upstream.HttpDataSource.Factory
        /* renamed from: setDefaultRequestProperties  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ HttpDataSource.Factory mo7459setDefaultRequestProperties(Map map) {
            return mo7459setDefaultRequestProperties((Map<String, String>) map);
        }

        public Factory setReadTimeoutMs(int i) {
            this.readTimeoutMs = i;
            return this;
        }

        public Factory setTransferListener(@Nullable TransferListener transferListener) {
            this.transferListener = transferListener;
            return this;
        }

        public Factory setUserAgent(@Nullable String str) {
            this.userAgent = str;
            return this;
        }

        @Override // com.google.android.exoplayer2.upstream.HttpDataSource.Factory
        /* renamed from: setDefaultRequestProperties */
        public final Factory mo7459setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }

        @Override // com.google.android.exoplayer2.upstream.HttpDataSource.Factory, com.google.android.exoplayer2.upstream.DataSource.Factory
        /* renamed from: createDataSource  reason: collision with other method in class */
        public DefaultHttpDataSource mo7468createDataSource() {
            DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(this.userAgent, this.connectTimeoutMs, this.readTimeoutMs, this.allowCrossProtocolRedirects, this.defaultRequestProperties, this.contentTypePredicate);
            TransferListener transferListener = this.transferListener;
            if (transferListener != null) {
                defaultHttpDataSource.addTransferListener(transferListener);
            }
            return defaultHttpDataSource;
        }
    }

    private void closeConnectionQuietly() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
                Log.e(TAG, "Unexpected error while disconnecting", e);
            }
            this.connection = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static long getContentLength(java.net.HttpURLConnection r10) {
        /*
            java.lang.String r0 = "Content-Length"
            java.lang.String r0 = r10.getHeaderField(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            java.lang.String r2 = "]"
            java.lang.String r3 = "DefaultHttpDataSource"
            if (r1 != 0) goto L2c
            long r4 = java.lang.Long.parseLong(r0)     // Catch: java.lang.NumberFormatException -> L15
            goto L2e
        L15:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r4 = "Unexpected Content-Length ["
            r1.append(r4)
            r1.append(r0)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.google.android.exoplayer2.util.Log.e(r3, r1)
        L2c:
            r4 = -1
        L2e:
            java.lang.String r1 = "Content-Range"
            java.lang.String r10 = r10.getHeaderField(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r10)
            if (r1 != 0) goto Lb0
            java.util.regex.Pattern r1 = com.google.android.exoplayer2.upstream.DefaultHttpDataSource.CONTENT_RANGE_HEADER
            java.util.regex.Matcher r1 = r1.matcher(r10)
            boolean r6 = r1.find()
            if (r6 == 0) goto Lb0
            r6 = 2
            java.lang.String r6 = r1.group(r6)     // Catch: java.lang.NumberFormatException -> L99
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r6)     // Catch: java.lang.NumberFormatException -> L99
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.NumberFormatException -> L99
            long r6 = java.lang.Long.parseLong(r6)     // Catch: java.lang.NumberFormatException -> L99
            r8 = 1
            java.lang.String r1 = r1.group(r8)     // Catch: java.lang.NumberFormatException -> L99
            java.lang.Object r1 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r1)     // Catch: java.lang.NumberFormatException -> L99
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.NumberFormatException -> L99
            long r8 = java.lang.Long.parseLong(r1)     // Catch: java.lang.NumberFormatException -> L99
            long r6 = r6 - r8
            r8 = 1
            long r6 = r6 + r8
            r8 = 0
            int r1 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r1 >= 0) goto L70
            r4 = r6
            goto Lb0
        L70:
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 == 0) goto Lb0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> L99
            r1.<init>()     // Catch: java.lang.NumberFormatException -> L99
            java.lang.String r8 = "Inconsistent headers ["
            r1.append(r8)     // Catch: java.lang.NumberFormatException -> L99
            r1.append(r0)     // Catch: java.lang.NumberFormatException -> L99
            java.lang.String r0 = "] ["
            r1.append(r0)     // Catch: java.lang.NumberFormatException -> L99
            r1.append(r10)     // Catch: java.lang.NumberFormatException -> L99
            r1.append(r2)     // Catch: java.lang.NumberFormatException -> L99
            java.lang.String r0 = r1.toString()     // Catch: java.lang.NumberFormatException -> L99
            com.google.android.exoplayer2.util.Log.w(r3, r0)     // Catch: java.lang.NumberFormatException -> L99
            long r0 = java.lang.Math.max(r4, r6)     // Catch: java.lang.NumberFormatException -> L99
            r4 = r0
            goto Lb0
        L99:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unexpected Content-Range ["
            r0.append(r1)
            r0.append(r10)
            r0.append(r2)
            java.lang.String r10 = r0.toString()
            com.google.android.exoplayer2.util.Log.e(r3, r10)
        Lb0:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.DefaultHttpDataSource.getContentLength(java.net.HttpURLConnection):long");
    }

    private static URL handleRedirect(URL url, @Nullable String str) throws IOException {
        if (str != null) {
            URL url2 = new URL(url, str);
            String protocol = url2.getProtocol();
            if (!"https".equals(protocol) && !"http".equals(protocol)) {
                throw new ProtocolException(GeneratedOutlineSupport1.outline72("Unsupported protocol redirect: ", protocol));
            }
            return url2;
        }
        throw new ProtocolException("Null location redirect");
    }

    private static boolean isCompressed(HttpURLConnection httpURLConnection) {
        return "gzip".equalsIgnoreCase(httpURLConnection.getHeaderField("Content-Encoding"));
    }

    private HttpURLConnection makeConnection(DataSpec dataSpec) throws IOException {
        HttpURLConnection makeConnection;
        int i;
        byte[] bArr;
        DataSpec dataSpec2 = dataSpec;
        URL url = new URL(dataSpec2.uri.toString());
        int i2 = dataSpec2.httpMethod;
        byte[] bArr2 = dataSpec2.httpBody;
        long j = dataSpec2.position;
        long j2 = dataSpec2.length;
        int i3 = 1;
        boolean isFlagSet = dataSpec2.isFlagSet(1);
        if (!this.allowCrossProtocolRedirects) {
            return makeConnection(url, i2, bArr2, j, j2, isFlagSet, true, dataSpec2.httpRequestHeaders);
        }
        int i4 = 0;
        while (true) {
            int i5 = i4 + 1;
            if (i4 <= 20) {
                byte[] bArr3 = bArr2;
                int i6 = i3;
                long j3 = j2;
                long j4 = j;
                makeConnection = makeConnection(url, i2, bArr2, j, j2, isFlagSet, false, dataSpec2.httpRequestHeaders);
                int responseCode = makeConnection.getResponseCode();
                String headerField = makeConnection.getHeaderField("Location");
                if ((i2 == i6 || i2 == 3) && (responseCode == 300 || responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == 307 || responseCode == 308)) {
                    makeConnection.disconnect();
                    url = handleRedirect(url, headerField);
                    i = i2;
                    bArr = bArr3;
                } else if (i2 != 2 || (responseCode != 300 && responseCode != 301 && responseCode != 302 && responseCode != 303)) {
                    break;
                } else {
                    makeConnection.disconnect();
                    bArr = null;
                    url = handleRedirect(url, headerField);
                    i = i6;
                }
                i3 = i6;
                j2 = j3;
                j = j4;
                bArr2 = bArr;
                i2 = i;
                i4 = i5;
                dataSpec2 = dataSpec;
            } else {
                throw new NoRouteToHostException(GeneratedOutlineSupport1.outline49("Too many redirects: ", i5));
            }
        }
        return makeConnection;
    }

    private static void maybeTerminateInputStream(@Nullable HttpURLConnection httpURLConnection, long j) {
        int i;
        if (httpURLConnection != null && (i = Util.SDK_INT) >= 19 && i <= 20) {
            try {
                InputStream inputStream = httpURLConnection.getInputStream();
                if (j == -1) {
                    if (inputStream.read() == -1) {
                        return;
                    }
                } else if (j <= 2048) {
                    return;
                }
                String name = inputStream.getClass().getName();
                if (!"com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream".equals(name) && !"com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream".equals(name)) {
                    return;
                }
                Method declaredMethod = ((Class) Assertions.checkNotNull(inputStream.getClass().getSuperclass())).getDeclaredMethod("unexpectedEndOfInput", new Class[0]);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(inputStream, new Object[0]);
            } catch (Exception unused) {
            }
        }
    }

    private int readInternal(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesToRead;
        if (j != -1) {
            long j2 = j - this.bytesRead;
            if (j2 == 0) {
                return -1;
            }
            i2 = (int) Math.min(i2, j2);
        }
        int read = ((InputStream) Util.castNonNull(this.inputStream)).read(bArr, i, i2);
        if (read == -1) {
            if (this.bytesToRead != -1) {
                throw new EOFException();
            }
            return -1;
        }
        this.bytesRead += read;
        bytesTransferred(read);
        return read;
    }

    private void skipInternal() throws IOException {
        if (this.bytesSkipped == this.bytesToSkip) {
            return;
        }
        if (this.skipBuffer == null) {
            this.skipBuffer = new byte[4096];
        }
        while (true) {
            long j = this.bytesSkipped;
            long j2 = this.bytesToSkip;
            if (j == j2) {
                return;
            }
            int read = ((InputStream) Util.castNonNull(this.inputStream)).read(this.skipBuffer, 0, (int) Math.min(j2 - j, this.skipBuffer.length));
            if (Thread.currentThread().isInterrupted()) {
                throw new InterruptedIOException();
            }
            if (read != -1) {
                this.bytesSkipped += read;
                bytesTransferred(read);
            } else {
                throw new EOFException();
            }
        }
    }

    protected final long bytesRead() {
        return this.bytesRead;
    }

    protected final long bytesRemaining() {
        long j = this.bytesToRead;
        return j == -1 ? j : j - this.bytesRead;
    }

    protected final long bytesSkipped() {
        return this.bytesSkipped;
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void clearRequestProperty(String str) {
        Assertions.checkNotNull(str);
        this.requestProperties.remove(str);
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public void close() throws HttpDataSource.HttpDataSourceException {
        try {
            InputStream inputStream = this.inputStream;
            if (inputStream != null) {
                maybeTerminateInputStream(this.connection, bytesRemaining());
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new HttpDataSource.HttpDataSourceException(e, (DataSpec) Util.castNonNull(this.dataSpec), 3);
                }
            }
        } finally {
            this.inputStream = null;
            closeConnectionQuietly();
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        }
    }

    @Nullable
    protected final HttpURLConnection getConnection() {
        return this.connection;
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public int getResponseCode() {
        int i;
        if (this.connection == null || (i = this.responseCode) <= 0) {
            return -1;
        }
        return i;
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource, com.google.android.exoplayer2.upstream.HttpDataSource
    public Map<String, List<String>> getResponseHeaders() {
        HttpURLConnection httpURLConnection = this.connection;
        return httpURLConnection == null ? Collections.emptyMap() : httpURLConnection.getHeaderFields();
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    @Nullable
    public Uri getUri() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    @Override // com.google.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        byte[] bArr;
        this.dataSpec = dataSpec;
        long j = 0;
        this.bytesRead = 0L;
        this.bytesSkipped = 0L;
        transferInitializing(dataSpec);
        try {
            this.connection = makeConnection(dataSpec);
            HttpURLConnection httpURLConnection = this.connection;
            try {
                this.responseCode = httpURLConnection.getResponseCode();
                String responseMessage = httpURLConnection.getResponseMessage();
                int i = this.responseCode;
                if (i >= 200 && i <= 299) {
                    String contentType = httpURLConnection.getContentType();
                    Predicate<String> predicate = this.contentTypePredicate;
                    if (predicate != null && !predicate.apply(contentType)) {
                        closeConnectionQuietly();
                        throw new HttpDataSource.InvalidContentTypeException(contentType, dataSpec);
                    }
                    if (this.responseCode == 200) {
                        long j2 = dataSpec.position;
                        if (j2 != 0) {
                            j = j2;
                        }
                    }
                    this.bytesToSkip = j;
                    boolean isCompressed = isCompressed(httpURLConnection);
                    if (!isCompressed) {
                        long j3 = dataSpec.length;
                        long j4 = -1;
                        if (j3 != -1) {
                            this.bytesToRead = j3;
                        } else {
                            long contentLength = getContentLength(httpURLConnection);
                            if (contentLength != -1) {
                                j4 = contentLength - this.bytesToSkip;
                            }
                            this.bytesToRead = j4;
                        }
                    } else {
                        this.bytesToRead = dataSpec.length;
                    }
                    try {
                        this.inputStream = httpURLConnection.getInputStream();
                        if (isCompressed) {
                            this.inputStream = new GZIPInputStream(this.inputStream);
                        }
                        this.opened = true;
                        transferStarted(dataSpec);
                        return this.bytesToRead;
                    } catch (IOException e) {
                        closeConnectionQuietly();
                        throw new HttpDataSource.HttpDataSourceException(e, dataSpec, 1);
                    }
                }
                Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                InputStream errorStream = httpURLConnection.getErrorStream();
                try {
                    bArr = errorStream != null ? Util.toByteArray(errorStream) : Util.EMPTY_BYTE_ARRAY;
                } catch (IOException unused) {
                    bArr = Util.EMPTY_BYTE_ARRAY;
                }
                byte[] bArr2 = bArr;
                closeConnectionQuietly();
                HttpDataSource.InvalidResponseCodeException invalidResponseCodeException = new HttpDataSource.InvalidResponseCodeException(this.responseCode, responseMessage, headerFields, dataSpec, bArr2);
                if (this.responseCode == 416) {
                    invalidResponseCodeException.initCause(new DataSourceException(0));
                }
                throw invalidResponseCodeException;
            } catch (IOException e2) {
                closeConnectionQuietly();
                throw new HttpDataSource.HttpDataSourceException("Unable to connect", e2, dataSpec, 1);
            }
        } catch (IOException e3) {
            String message = e3.getMessage();
            if (message != null && Util.toLowerInvariant(message).matches("cleartext http traffic.*not permitted.*")) {
                throw new HttpDataSource.CleartextNotPermittedException(e3, dataSpec);
            }
            throw new HttpDataSource.HttpDataSourceException("Unable to connect", e3, dataSpec, 1);
        }
    }

    @VisibleForTesting
    HttpURLConnection openConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    @Override // com.google.android.exoplayer2.upstream.DataReader
    public int read(byte[] bArr, int i, int i2) throws HttpDataSource.HttpDataSourceException {
        try {
            skipInternal();
            return readInternal(bArr, i, i2);
        } catch (IOException e) {
            throw new HttpDataSource.HttpDataSourceException(e, (DataSpec) Util.castNonNull(this.dataSpec), 2);
        }
    }

    @Deprecated
    public void setContentTypePredicate(@Nullable Predicate<String> predicate) {
        this.contentTypePredicate = predicate;
    }

    @Override // com.google.android.exoplayer2.upstream.HttpDataSource
    public void setRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        this.requestProperties.set(str, str2);
    }

    @Deprecated
    public DefaultHttpDataSource() {
        this(null, 8000, 8000);
    }

    @Deprecated
    public DefaultHttpDataSource(@Nullable String str) {
        this(str, 8000, 8000);
    }

    @Deprecated
    public DefaultHttpDataSource(@Nullable String str, int i, int i2) {
        this(str, i, i2, false, null);
    }

    @Deprecated
    public DefaultHttpDataSource(@Nullable String str, int i, int i2, boolean z, @Nullable HttpDataSource.RequestProperties requestProperties) {
        this(str, i, i2, z, requestProperties, null);
    }

    private DefaultHttpDataSource(@Nullable String str, int i, int i2, boolean z, @Nullable HttpDataSource.RequestProperties requestProperties, @Nullable Predicate<String> predicate) {
        super(true);
        this.userAgent = str;
        this.connectTimeoutMillis = i;
        this.readTimeoutMillis = i2;
        this.allowCrossProtocolRedirects = z;
        this.defaultRequestProperties = requestProperties;
        this.contentTypePredicate = predicate;
        this.requestProperties = new HttpDataSource.RequestProperties();
    }

    private HttpURLConnection makeConnection(URL url, int i, @Nullable byte[] bArr, long j, long j2, boolean z, boolean z2, Map<String, String> map) throws IOException {
        HttpURLConnection openConnection = openConnection(url);
        openConnection.setConnectTimeout(this.connectTimeoutMillis);
        openConnection.setReadTimeout(this.readTimeoutMillis);
        HashMap hashMap = new HashMap();
        HttpDataSource.RequestProperties requestProperties = this.defaultRequestProperties;
        if (requestProperties != null) {
            hashMap.putAll(requestProperties.getSnapshot());
        }
        hashMap.putAll(this.requestProperties.getSnapshot());
        hashMap.putAll(map);
        for (Map.Entry entry : hashMap.entrySet()) {
            openConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        if (j != 0 || j2 != -1) {
            String outline57 = GeneratedOutlineSupport1.outline57("bytes=", j, ProcessIdUtil.DEFAULT_PROCESSID);
            if (j2 != -1) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(outline57);
                outline107.append((j + j2) - 1);
                outline57 = outline107.toString();
            }
            openConnection.setRequestProperty("Range", outline57);
        }
        String str = this.userAgent;
        if (str != null) {
            openConnection.setRequestProperty("User-Agent", str);
        }
        openConnection.setRequestProperty("Accept-Encoding", z ? "gzip" : InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
        openConnection.setInstanceFollowRedirects(z2);
        openConnection.setDoOutput(bArr != null);
        openConnection.setRequestMethod(DataSpec.getStringForHttpMethod(i));
        if (bArr != null) {
            openConnection.setFixedLengthStreamingMode(bArr.length);
            openConnection.connect();
            OutputStream outputStream = openConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
        } else {
            openConnection.connect();
        }
        return openConnection;
    }
}
