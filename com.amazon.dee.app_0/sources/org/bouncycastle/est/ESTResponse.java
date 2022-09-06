package org.bouncycastle.est;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import org.apache.logging.log4j.util.Chars;
import org.bouncycastle.est.HttpUtil;
import org.bouncycastle.util.Properties;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class ESTResponse {
    private static final Long ZERO = 0L;
    private String HttpVersion;
    private Long absoluteReadLimit;
    private Long contentLength;
    private final HttpUtil.Headers headers;
    private InputStream inputStream;
    private final byte[] lineBuffer;
    private final ESTRequest originalRequest;
    private long read = 0;
    private final Source source;
    private int statusCode;
    private String statusMessage;

    /* loaded from: classes4.dex */
    private class PrintingInputStream extends InputStream {
        private final InputStream src;

        private PrintingInputStream(InputStream inputStream) {
            this.src = inputStream;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.src.available();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.src.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            return this.src.read();
        }
    }

    public ESTResponse(ESTRequest eSTRequest, Source source) throws IOException {
        this.originalRequest = eSTRequest;
        this.source = source;
        if (source instanceof LimitedSource) {
            this.absoluteReadLimit = ((LimitedSource) source).getAbsoluteReadLimit();
        }
        Set<String> asKeySet = Properties.asKeySet("org.bouncycastle.debug.est");
        this.inputStream = (asKeySet.contains("input") || asKeySet.contains("all")) ? new PrintingInputStream(source.getInputStream()) : source.getInputStream();
        this.headers = new HttpUtil.Headers();
        this.lineBuffer = new byte[1024];
        process();
    }

    static /* synthetic */ long access$108(ESTResponse eSTResponse) {
        long j = eSTResponse.read;
        eSTResponse.read = 1 + j;
        return j;
    }

    private void process() throws IOException {
        this.HttpVersion = readStringIncluding(Chars.SPACE);
        this.statusCode = Integer.parseInt(readStringIncluding(Chars.SPACE));
        this.statusMessage = readStringIncluding('\n');
        while (true) {
            String readStringIncluding = readStringIncluding('\n');
            if (readStringIncluding.length() <= 0) {
                break;
            }
            int indexOf = readStringIncluding.indexOf(58);
            if (indexOf > -1) {
                this.headers.add(Strings.toLowerCase(readStringIncluding.substring(0, indexOf).trim()), readStringIncluding.substring(indexOf + 1).trim());
            }
        }
        this.contentLength = getContentLength();
        int i = this.statusCode;
        if (i == 204 || i == 202) {
            Long l = this.contentLength;
            if (l == null) {
                this.contentLength = 0L;
            } else if (this.statusCode == 204 && l.longValue() > 0) {
                throw new IOException("Got HTTP status 204 but Content-length > 0.");
            }
        }
        Long l2 = this.contentLength;
        if (l2 != null) {
            if (l2.equals(ZERO)) {
                this.inputStream = new InputStream() { // from class: org.bouncycastle.est.ESTResponse.1
                    @Override // java.io.InputStream
                    public int read() throws IOException {
                        return -1;
                    }
                };
            }
            if (this.contentLength.longValue() < 0) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Server returned negative content length: ");
                outline107.append(this.absoluteReadLimit);
                throw new IOException(outline107.toString());
            } else if (this.absoluteReadLimit == null || this.contentLength.longValue() < this.absoluteReadLimit.longValue()) {
                this.inputStream = wrapWithCounter(this.inputStream, this.absoluteReadLimit);
                if (!"base64".equalsIgnoreCase(getHeader("content-transfer-encoding"))) {
                    return;
                }
                this.inputStream = new CTEBase64InputStream(this.inputStream, getContentLength());
                return;
            } else {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Content length longer than absolute read limit: ");
                outline1072.append(this.absoluteReadLimit);
                outline1072.append(" Content-Length: ");
                outline1072.append(this.contentLength);
                throw new IOException(outline1072.toString());
            }
        }
        throw new IOException("No Content-length header.");
    }

    public void close() throws IOException {
        InputStream inputStream = this.inputStream;
        if (inputStream != null) {
            inputStream.close();
        }
        this.source.close();
    }

    public Long getContentLength() {
        String firstValue = this.headers.getFirstValue("Content-Length");
        if (firstValue == null) {
            return null;
        }
        try {
            return Long.valueOf(Long.parseLong(firstValue));
        } catch (RuntimeException e) {
            StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Content Length: '", firstValue, "' invalid. ");
            outline115.append(e.getMessage());
            throw new RuntimeException(outline115.toString());
        }
    }

    public String getHeader(String str) {
        return this.headers.getFirstValue(str);
    }

    public HttpUtil.Headers getHeaders() {
        return this.headers;
    }

    public String getHttpVersion() {
        return this.HttpVersion;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public ESTRequest getOriginalRequest() {
        return this.originalRequest;
    }

    public Source getSource() {
        return this.source;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    protected String readStringIncluding(char c) throws IOException {
        int read;
        int i;
        int i2 = 0;
        while (true) {
            read = this.inputStream.read();
            byte[] bArr = this.lineBuffer;
            i = i2 + 1;
            bArr[i2] = (byte) read;
            if (i >= bArr.length) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Server sent line > ");
                outline107.append(this.lineBuffer.length);
                throw new IOException(outline107.toString());
            } else if (read == c || read <= -1) {
                break;
            } else {
                i2 = i;
            }
        }
        if (read != -1) {
            return new String(this.lineBuffer, 0, i).trim();
        }
        throw new EOFException();
    }

    protected InputStream wrapWithCounter(final InputStream inputStream, final Long l) {
        return new InputStream() { // from class: org.bouncycastle.est.ESTResponse.2
            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                if (ESTResponse.this.contentLength == null || ESTResponse.this.contentLength.longValue() - 1 <= ESTResponse.this.read) {
                    if (inputStream.available() > 0) {
                        throw new IOException("Stream closed with extra content in pipe that exceeds content length.");
                    }
                    inputStream.close();
                    return;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Stream closed before limit fully read, Read: ");
                outline107.append(ESTResponse.this.read);
                outline107.append(" ContentLength: ");
                outline107.append(ESTResponse.this.contentLength);
                throw new IOException(outline107.toString());
            }

            @Override // java.io.InputStream
            public int read() throws IOException {
                int read = inputStream.read();
                if (read > -1) {
                    ESTResponse.access$108(ESTResponse.this);
                    if (l != null && ESTResponse.this.read >= l.longValue()) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Absolute Read Limit exceeded: ");
                        outline107.append(l);
                        throw new IOException(outline107.toString());
                    }
                }
                return read;
            }
        };
    }
}
