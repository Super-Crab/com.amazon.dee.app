package com.amazon.communication;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes12.dex */
public abstract class HttpRequestResponseConverterBase implements HttpRequestResponseConverter {
    protected static final String COLON_DELIMITER = ":";
    public static final String CONNECTION_TIMEOUT_MS_HEADER = "x-amz-connection-timeout-ms";
    protected static final byte CR = 13;
    protected static final String CRLF = "\r\n";
    protected static final String HTTP_1_1 = "HTTP/1.1";
    protected static final String HTTP_PROTOCOL = "HTTP";
    protected static final int HTTP_PROTOCOL_MAJOR = 1;
    protected static final int HTTP_PROTOCOL_MINOR = 1;
    protected static final byte LF = 10;
    public static final String SOCKET_TIMEOUT_MS_HEADER = "x-amz-socket-timeout-ms";
    protected static final String SPACE_DELIMITER = " ";

    public String getNextLine(StringBuilder sb, InputStream inputStream) throws ProtocolException {
        try {
            int read = inputStream.read();
            while (true) {
                byte b = (byte) read;
                if (read == -1 || b == 13) {
                    break;
                } else if (b != 10) {
                    sb.append((char) b);
                    read = inputStream.read();
                } else {
                    throw new ProtocolException("Invalid message format: LF not preceded by CR");
                }
            }
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw new ProtocolException("Invalid message format: unexpected end of buffer");
            }
            if (((byte) read2) == 10) {
                return sb.toString();
            }
            throw new ProtocolException("Invalid message format: CR not followed by LF");
        } catch (IOException e) {
            throw new ProtocolException("Invalid message format: unexpected end of buffer", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isEndOfHeaders(String str) {
        return str.length() == 0;
    }

    public String getNextLine(InputStream inputStream) throws ProtocolException {
        return getNextLine(new StringBuilder(), inputStream);
    }
}
