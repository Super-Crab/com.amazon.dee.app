package com.amazon.communication;

import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.dp.logger.DPFormattedMessage;
import com.amazon.dp.logger.DPLogger;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes12.dex */
public class ServiceSideInputStreamProxy extends InputStream implements IBinder.DeathRecipient {
    public static final int MAX_ALLOWED_BYTES_READ_ACROSS_IPC = 102400;
    private static final DPLogger log = new DPLogger("TComm.ServiceSideInputStreamProxy");
    private IInputStream mInputStream;

    public ServiceSideInputStreamProxy(IInputStream iInputStream) {
        this.mInputStream = iInputStream;
        try {
            iInputStream.asBinder().linkToDeath(this, 0);
        } catch (RemoteException e) {
            log.verbose("constructor", "Binder has already died.  Swallowing and allowing read call path to discover this", e);
            binderDied();
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        IInputStream iInputStream;
        synchronized (this) {
            if (this.mInputStream != null) {
                iInputStream = this.mInputStream;
            } else {
                log.warn("available", "Client-side callback object is now stale", new Object[0]);
                throw new IOException("Client-side callback object is now stale");
            }
        }
        try {
            int available = iInputStream.available();
            if (available == -2) {
                throw new IOException("IOException in client-side InputStream");
            }
            if (available == -3) {
                throw new IOException("Client caused exception on application side - ignore");
            }
            return available;
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }

    @Override // android.os.IBinder.DeathRecipient
    public synchronized void binderDied() {
        this.mInputStream = null;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        IInputStream iInputStream;
        synchronized (this) {
            if (this.mInputStream != null) {
                iInputStream = this.mInputStream;
            } else {
                log.warn("close", "Client-side callback object is now stale", new Object[0]);
                throw new IOException("Client-side callback object is now stale");
            }
        }
        try {
            boolean close = iInputStream.close();
            iInputStream.asBinder().unlinkToDeath(this, 0);
            synchronized (this) {
                this.mInputStream = null;
            }
            if (close) {
                return;
            }
            throw new IOException("IOException in client-side InputStream");
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        IInputStream iInputStream;
        char c = 2;
        if (i < 0 || i2 < 0 || i > bArr.length) {
            throw new IndexOutOfBoundsException(DPFormattedMessage.toDPFormat("read(byte[],int,int)", "Index out of bounds", "array.length", Integer.valueOf(bArr.length), "offset", Integer.valueOf(i), "length", Integer.valueOf(i2)));
        }
        if (i2 == 0) {
            return 0;
        }
        synchronized (this) {
            if (this.mInputStream != null) {
                iInputStream = this.mInputStream;
            } else {
                log.warn("read(byte[],int,int)", "Client-side callback object is now stale", new Object[0]);
                throw new IOException("Client-side callback object is now stale");
            }
        }
        int i3 = i2;
        int i4 = 0;
        while (true) {
            int i5 = 102400;
            if (i3 <= 102400) {
                i5 = i3;
            }
            try {
                DPLogger dPLogger = log;
                Object[] objArr = new Object[8];
                objArr[0] = "bytesToRead";
                objArr[1] = Integer.valueOf(i5);
                objArr[c] = "Size of buffer";
                objArr[3] = Integer.valueOf(bArr.length);
                objArr[4] = "totalBytesRead";
                objArr[5] = Integer.valueOf(i4);
                objArr[6] = "remainingBytesToRead";
                objArr[7] = Integer.valueOf(i3);
                dPLogger.debug("read(byte[],int,int)", "About to read", objArr);
                int readBytesIntoOffset = iInputStream.readBytesIntoOffset(bArr, i + i4, i5);
                if (readBytesIntoOffset == -2) {
                    throw new IOException("IOException in client-side InputStream");
                }
                if (readBytesIntoOffset == -3) {
                    throw new IOException("Client caused exception on application side - ignore");
                }
                if (readBytesIntoOffset == -1) {
                    return i4 > 0 ? i4 : readBytesIntoOffset;
                }
                i4 += readBytesIntoOffset;
                i3 -= readBytesIntoOffset;
                if (readBytesIntoOffset < i5 || i3 <= 0) {
                    return i4;
                }
                c = 2;
            } catch (RemoteException e) {
                log.error("read(byte[],int,int)", "RemoteException occurred reading from InputStream", e);
                throw new IOException(e);
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        IInputStream iInputStream;
        synchronized (this) {
            if (this.mInputStream != null) {
                iInputStream = this.mInputStream;
            } else {
                log.warn("read()", "Client-side callback object is now stale", new Object[0]);
                throw new IOException("Client-side callback object is now stale");
            }
        }
        try {
            int readByte = iInputStream.readByte();
            if (readByte == -2) {
                throw new IOException("IOException in client-side InputStream");
            }
            if (readByte == -3) {
                throw new IOException("Client caused exception on application side - ignore");
            }
            return readByte;
        } catch (RemoteException e) {
            throw new IOException(e);
        }
    }
}
