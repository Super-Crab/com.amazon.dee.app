package amazon.speech.io;

import amazon.speech.io.ByteArrayQueue;
import amazon.speech.io.DataStream;
import android.os.Parcel;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes.dex */
public class InProcDataStream extends DataStream {
    private ByteArrayQueue mByteArrayQueue;
    private ReentrantLock mLock;
    private DataStream.Metadata mMetadata;
    private Condition mNotEmpty;
    private Condition mNotFull;
    private int mNumPipes;
    private List<DataStreamReader> mPendingDataStreamReaders;
    private boolean mWriterAdded = false;
    private boolean mWriterClosed = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class DataStreamWriterImpl extends DataStreamWriter {
        private DataStreamWriterImpl() {
        }

        public long awaitFree(int i) throws IOException {
            InProcDataStream.this.mLock.lock();
            long freeSize = InProcDataStream.this.mByteArrayQueue.getFreeSize();
            while (freeSize == 0) {
                try {
                    if (InProcDataStream.this.mNumPipes == 0) {
                        InProcDataStream.this.mByteArrayQueue.delete(i);
                    } else {
                        try {
                            InProcDataStream.this.mNotFull.await();
                        } catch (InterruptedException e) {
                            throw new IOException("Write interrupted when waiting", e);
                        }
                    }
                    freeSize = InProcDataStream.this.mByteArrayQueue.getFreeSize();
                } finally {
                    InProcDataStream.this.mLock.unlock();
                }
            }
            return freeSize;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            InProcDataStream.this.mLock.lock();
            try {
                InProcDataStream.this.mWriterClosed = true;
                InProcDataStream.this.mWriterAdded = false;
                InProcDataStream.this.mNotEmpty.signalAll();
            } finally {
                InProcDataStream.this.mLock.unlock();
            }
        }

        @Override // amazon.speech.io.DataStreamWriter
        public long getPosition() {
            InProcDataStream.this.mLock.lock();
            try {
                return InProcDataStream.this.mByteArrayQueue.getTailPosition();
            } finally {
                InProcDataStream.this.mLock.unlock();
            }
        }

        @Override // amazon.speech.io.DataStreamWriter
        public long setPosition(long j) {
            InProcDataStream.this.mLock.lock();
            try {
                InProcDataStream.this.mByteArrayQueue.setTailPosition(j);
                return j;
            } finally {
                InProcDataStream.this.mLock.unlock();
            }
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            write(new byte[]{(byte) i}, 0, 1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (i2 <= bArr.length) {
                if (InProcDataStream.this.mWriterClosed) {
                    throw new IOException("OutputStream closed");
                }
                int i3 = i2;
                while (i3 > 0) {
                    long j = i3;
                    long min = Math.min(awaitFree(i3), j);
                    if (min > 0) {
                        long poke = InProcDataStream.this.mByteArrayQueue.poke(bArr, (i2 - i3) + i, (int) min);
                        i3 = (int) (j - min);
                        InProcDataStream.this.mLock.lock();
                        InProcDataStream.this.mByteArrayQueue.setTailPosition(poke);
                        try {
                            InProcDataStream.this.mNotEmpty.signalAll();
                        } finally {
                            InProcDataStream.this.mLock.unlock();
                        }
                    }
                }
                return;
            }
            throw new IllegalArgumentException(String.format("Length (%d) cannot exceed length of array (%d).", Integer.valueOf(i2), Integer.valueOf(bArr.length)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class InProcDataStreamReaderImpl extends DataStreamReader {
        private boolean mClosed;
        private final ByteArrayQueue.ReadPointer mReadPointer;

        private long awaitBytes() throws IOException {
            InProcDataStream.this.mLock.lock();
            try {
                long size = this.mReadPointer.getSize();
                while (true) {
                    if (size != 0) {
                        break;
                    } else if (InProcDataStream.this.mWriterClosed) {
                        size = -1;
                        break;
                    } else {
                        try {
                            InProcDataStream.this.mNotEmpty.await();
                            size = this.mReadPointer.getSize();
                        } catch (InterruptedException e) {
                            throw new IOException("Read interrupted when waiting", e);
                        }
                    }
                }
                return size;
            } finally {
                InProcDataStream.this.mLock.unlock();
            }
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            InProcDataStream.this.mLock.lock();
            try {
                return (int) this.mReadPointer.getSize();
            } finally {
                InProcDataStream.this.mLock.unlock();
            }
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.mClosed) {
                this.mClosed = true;
                InProcDataStream.this.mLock.lock();
                try {
                    this.mReadPointer.release();
                    return;
                } finally {
                    InProcDataStream.this.mLock.unlock();
                }
            }
            throw new IOException("DataStreamReader already closed");
        }

        @Override // amazon.speech.io.DataStreamReader
        public long getPosition() {
            InProcDataStream.this.mLock.lock();
            try {
                return this.mReadPointer.getHeadPosition();
            } finally {
                InProcDataStream.this.mLock.unlock();
            }
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            byte[] bArr = new byte[1];
            int read = read(bArr, 0, 1);
            if (read < 0) {
                return read;
            }
            if (read == 1) {
                return bArr[0];
            }
            throw new IOException(String.format("Incorrect number of bytes read %d", Integer.valueOf(read)));
        }

        @Override // amazon.speech.io.DataStreamReader
        public long setPosition(long j) {
            InProcDataStream.this.mLock.lock();
            try {
                this.mReadPointer.setHeadPosition(j);
                return j;
            } finally {
                InProcDataStream.this.mLock.unlock();
            }
        }

        private InProcDataStreamReaderImpl() {
            InProcDataStream.this.mLock.lock();
            try {
                this.mReadPointer = InProcDataStream.this.mByteArrayQueue.newReadPointer();
            } finally {
                InProcDataStream.this.mLock.unlock();
            }
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (i2 >= 0) {
                if (i2 <= bArr.length) {
                    if (this.mClosed) {
                        throw new IOException("InputStream already closed");
                    }
                    int i3 = i2;
                    while (true) {
                        if (i3 == 0 || i3 != i2) {
                            break;
                        }
                        int min = (int) Math.min(awaitBytes(), i3);
                        if (min > 0) {
                            long peek = this.mReadPointer.peek(min, bArr, (i2 - i3) + i);
                            i3 -= min;
                            InProcDataStream.this.mLock.lock();
                            try {
                                this.mReadPointer.setHeadPosition(peek);
                                InProcDataStream.this.mNotFull.signalAll();
                            } finally {
                                InProcDataStream.this.mLock.unlock();
                            }
                        } else if (min < 0) {
                            if (i3 >= i2) {
                                i3 -= min;
                            }
                        }
                    }
                    return i2 - i3;
                }
                throw new IllegalArgumentException(String.format("Length (%d) cannot exceed length of array (%d).", Integer.valueOf(i2), Integer.valueOf(bArr.length)));
            }
            throw new IllegalArgumentException("length must be >= 0");
        }
    }

    public InProcDataStream(int i, int i2, DataStream.Metadata metadata) {
        if (i > 0) {
            if (i2 >= 0) {
                ReentrantLock reentrantLock = new ReentrantLock();
                init(i2, reentrantLock, reentrantLock.newCondition(), reentrantLock.newCondition(), new ByteArrayQueue(i), metadata);
                return;
            }
            throw new IllegalArgumentException(String.format("Invalid pipes: %d", Integer.valueOf(i2)));
        }
        throw new IllegalArgumentException(String.format("Invalid bufferSize: %s", Integer.valueOf(i)));
    }

    private void init(int i, ReentrantLock reentrantLock, Condition condition, Condition condition2, ByteArrayQueue byteArrayQueue, DataStream.Metadata metadata) {
        this.mNumPipes = i;
        this.mMetadata = metadata;
        this.mLock = reentrantLock;
        this.mNotFull = condition;
        this.mNotEmpty = condition2;
        this.mByteArrayQueue = byteArrayQueue;
        this.mNumPipes = i;
        this.mPendingDataStreamReaders = new ArrayList(this.mNumPipes);
        if (i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                this.mPendingDataStreamReaders.add(new InProcDataStreamReaderImpl());
            }
        }
    }

    @Override // amazon.speech.io.DataStream
    public InputStream createInputStream() {
        return openReader();
    }

    @Override // amazon.speech.io.DataStream
    public OutputStream createOutputStream() {
        return openWriter();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        throw new UnsupportedOperationException("In-process implementation, does not support sending over Binder.");
    }

    @Override // amazon.speech.io.DataStream
    public DataStream.Metadata getMetadata() {
        return this.mMetadata;
    }

    @Override // amazon.speech.io.DataStream
    public synchronized DataStreamReader openReader() {
        if (this.mNumPipes > 0) {
            if (this.mPendingDataStreamReaders.size() > 0) {
                return this.mPendingDataStreamReaders.remove(0);
            }
            throw new IllegalStateException("No more reserved pipes.");
        }
        return new InProcDataStreamReaderImpl();
    }

    @Override // amazon.speech.io.DataStream
    public DataStreamWriter openWriter() {
        if (!this.mWriterAdded) {
            this.mWriterAdded = true;
            this.mWriterClosed = false;
            return new DataStreamWriterImpl();
        }
        throw new IllegalStateException("Only one Writer is supported.");
    }

    @Override // amazon.speech.io.DataStream
    public void recycle() {
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        throw new UnsupportedOperationException("In-process implementation, does not support sending over Binder.");
    }

    InProcDataStream(int i, ReentrantLock reentrantLock, Condition condition, Condition condition2, ByteArrayQueue byteArrayQueue, DataStream.Metadata metadata) {
        init(i, reentrantLock, condition, condition2, byteArrayQueue, metadata);
    }
}
