package org.apache.commons.net.io;

import java.io.IOException;
/* loaded from: classes4.dex */
public class CopyStreamException extends IOException {
    private IOException ioException;
    private long totalBytesTransferred;

    public CopyStreamException(String str, long j, IOException iOException) {
        super(str);
        this.totalBytesTransferred = j;
        this.ioException = iOException;
    }

    public IOException getIOException() {
        return this.ioException;
    }

    public long getTotalBytesTransferred() {
        return this.totalBytesTransferred;
    }
}
