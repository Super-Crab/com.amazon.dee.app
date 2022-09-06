package org.apache.thrift.orig.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes4.dex */
public interface TSeekableFile {
    void close() throws IOException;

    InputStream getInputStream() throws IOException;

    OutputStream getOutputStream() throws IOException;

    long length() throws IOException;

    void seek(long j) throws IOException;
}
