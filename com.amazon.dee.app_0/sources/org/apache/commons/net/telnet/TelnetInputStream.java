package org.apache.commons.net.telnet;

import com.amazon.opus.OpusEncoder;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
/* loaded from: classes4.dex */
final class TelnetInputStream extends BufferedInputStream implements Runnable {
    static final int _STATE_CR = 8;
    static final int _STATE_DATA = 0;
    static final int _STATE_DO = 4;
    static final int _STATE_DONT = 5;
    static final int _STATE_IAC = 1;
    static final int _STATE_IAC_SB = 9;
    static final int _STATE_SB = 6;
    static final int _STATE_SE = 7;
    static final int _STATE_WILL = 2;
    static final int _STATE_WONT = 3;
    private int __bytesAvailable;
    private TelnetClient __client;
    private boolean __hasReachedEOF;
    private IOException __ioException;
    private boolean __isClosed;
    private int[] __queue;
    private int __queueHead;
    private int __queueTail;
    private boolean __readIsWaiting;
    private int __receiveState;
    private int[] __suboption;
    private int __suboption_count;
    private Thread __thread;
    private boolean __threaded;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TelnetInputStream(InputStream inputStream, TelnetClient telnetClient, boolean z) {
        super(inputStream);
        this.__suboption = new int[256];
        this.__suboption_count = 0;
        this.__client = telnetClient;
        this.__receiveState = 0;
        this.__isClosed = true;
        this.__hasReachedEOF = false;
        this.__queue = new int[OpusEncoder.OPUS_APPLICATION_AUDIO];
        this.__queueHead = 0;
        this.__queueTail = 0;
        this.__bytesAvailable = 0;
        this.__ioException = null;
        this.__readIsWaiting = false;
        this.__threaded = false;
        if (z) {
            this.__thread = new Thread(this);
        } else {
            this.__thread = null;
        }
    }

    private void __processChar(int i) throws InterruptedException {
        synchronized (this.__queue) {
            while (this.__bytesAvailable >= this.__queue.length - 1) {
                if (this.__threaded) {
                    this.__queue.notify();
                    try {
                        this.__queue.wait();
                    } catch (InterruptedException e) {
                        throw e;
                    }
                }
            }
            if (this.__readIsWaiting && this.__threaded) {
                this.__queue.notify();
            }
            this.__queue[this.__queueTail] = i;
            this.__bytesAvailable++;
            int i2 = this.__queueTail + 1;
            this.__queueTail = i2;
            if (i2 >= this.__queue.length) {
                this.__queueTail = 0;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x00f8, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int __read() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.telnet.TelnetInputStream.__read():int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void _start() {
        if (this.__thread == null) {
            return;
        }
        this.__isClosed = false;
        int priority = Thread.currentThread().getPriority() + 1;
        if (priority > 10) {
            priority = 10;
        }
        this.__thread.setPriority(priority);
        this.__thread.setDaemon(true);
        this.__thread.start();
        this.__threaded = true;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        int i;
        synchronized (this.__queue) {
            i = this.__bytesAvailable;
        }
        return i;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        synchronized (this.__queue) {
            this.__hasReachedEOF = true;
            this.__isClosed = true;
            if (this.__thread != null && this.__thread.isAlive()) {
                this.__thread.interrupt();
            }
            this.__queue.notifyAll();
        }
        this.__threaded = false;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        synchronized (this.__queue) {
            while (this.__ioException == null) {
                if (this.__bytesAvailable == 0) {
                    if (this.__hasReachedEOF) {
                        return -1;
                    }
                    if (this.__threaded) {
                        this.__queue.notify();
                        try {
                            this.__readIsWaiting = true;
                            this.__queue.wait();
                            this.__readIsWaiting = false;
                        } catch (InterruptedException unused) {
                            throw new IOException("Fatal thread interruption during read.");
                        }
                    } else {
                        this.__readIsWaiting = true;
                        do {
                            try {
                                int __read = __read();
                                if (__read < 0 && __read != -2) {
                                    return __read;
                                }
                                if (__read != -2) {
                                    try {
                                        __processChar(__read);
                                    } catch (InterruptedException unused2) {
                                        if (this.__isClosed) {
                                            return -1;
                                        }
                                    }
                                }
                            } catch (InterruptedIOException e) {
                                synchronized (this.__queue) {
                                    this.__ioException = e;
                                    this.__queue.notifyAll();
                                    try {
                                        this.__queue.wait(100L);
                                    } catch (InterruptedException unused3) {
                                    }
                                    return -1;
                                }
                            }
                        } while (super.available() > 0);
                        this.__readIsWaiting = false;
                    }
                } else {
                    int i = this.__queue[this.__queueHead];
                    int i2 = this.__queueHead + 1;
                    this.__queueHead = i2;
                    if (i2 >= this.__queue.length) {
                        this.__queueHead = 0;
                    }
                    this.__bytesAvailable--;
                    if (this.__bytesAvailable == 0 && this.__threaded) {
                        this.__queue.notify();
                    }
                    return i;
                }
            }
            IOException iOException = this.__ioException;
            this.__ioException = null;
            throw iOException;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        int __read;
        while (!this.__isClosed) {
            try {
                try {
                    __read = __read();
                } catch (InterruptedIOException e) {
                    synchronized (this.__queue) {
                        this.__ioException = e;
                        this.__queue.notifyAll();
                        try {
                            this.__queue.wait(100L);
                        } catch (InterruptedException unused) {
                            if (this.__isClosed) {
                                break;
                            }
                        }
                    }
                } catch (RuntimeException unused2) {
                    super.close();
                }
                if (__read < 0) {
                    break;
                }
                try {
                    __processChar(__read);
                } catch (InterruptedException unused3) {
                    if (this.__isClosed) {
                        break;
                    }
                }
            } catch (IOException e2) {
                synchronized (this.__queue) {
                    this.__ioException = e2;
                }
            }
        }
        synchronized (this.__queue) {
            this.__isClosed = true;
            this.__hasReachedEOF = true;
            this.__queue.notify();
        }
        this.__threaded = false;
    }

    TelnetInputStream(InputStream inputStream, TelnetClient telnetClient) {
        this(inputStream, telnetClient, true);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i2 < 1) {
            return 0;
        }
        synchronized (this.__queue) {
            if (i2 > this.__bytesAvailable) {
                i2 = this.__bytesAvailable;
            }
        }
        int read = read();
        if (read == -1) {
            return -1;
        }
        int i4 = i2;
        int i5 = i;
        while (true) {
            i3 = i5 + 1;
            bArr[i5] = (byte) read;
            i4--;
            if (i4 <= 0 || (read = read()) == -1) {
                break;
            }
            i5 = i3;
        }
        return i3 - i;
    }
}
