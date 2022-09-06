package com.amazon.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterInputStream;
/* loaded from: classes12.dex */
public final class CompressionStreamFactory {
    public static DeflaterOutputStream createDeflaterOutputStream(Encoding encoding, OutputStream outputStream) throws IOException, IllegalArgumentException {
        if (encoding != null) {
            if (outputStream != null) {
                if (encoding == Encoding.GZIP) {
                    return new GZIPOutputStream(outputStream);
                }
                throw new IllegalArgumentException("Unsupported encoding type");
            }
            throw new IllegalArgumentException("InputStream is null");
        }
        throw new IllegalArgumentException("Encoding type is null");
    }

    public static InflaterInputStream createInflaterInputStream(Encoding encoding, InputStream inputStream) throws IOException, IllegalArgumentException {
        if (encoding != null) {
            if (inputStream != null) {
                if (encoding == Encoding.GZIP) {
                    return new GZIPInputStream(inputStream);
                }
                throw new IllegalArgumentException("Unsupported encoding type");
            }
            throw new IllegalArgumentException("InputStream is null");
        }
        throw new IllegalArgumentException("Encoding type is null");
    }
}
