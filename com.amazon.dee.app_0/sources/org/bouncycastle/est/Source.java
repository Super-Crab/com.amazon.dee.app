package org.bouncycastle.est;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes4.dex */
public interface Source<T> {
    void close() throws IOException;

    InputStream getInputStream() throws IOException;

    OutputStream getOutputStream() throws IOException;

    T getSession();
}
