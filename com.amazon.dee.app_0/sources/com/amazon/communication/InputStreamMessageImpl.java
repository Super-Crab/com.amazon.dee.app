package com.amazon.communication;

import amazon.communication.Message;
import com.amazon.dp.logger.DPFormattedMessage;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes12.dex */
public class InputStreamMessageImpl implements Message {
    private static final DPLogger log = new DPLogger("TComm.InputStreamMessageImpl");
    private final InputStream mInputStream;
    private boolean mIsGetPayloadInvoked;
    private final List<ByteBuffer> mPrependedData;

    public InputStreamMessageImpl(InputStream inputStream) {
        this.mPrependedData = new ArrayList();
        this.mInputStream = inputStream;
        this.mIsGetPayloadInvoked = false;
    }

    @Override // amazon.communication.Message
    public void appendPayload(ByteBuffer byteBuffer) {
        throw new UnsupportedOperationException("We don't support append a payload to InputStream based message.");
    }

    @Override // amazon.communication.Message
    public Message extractPayload() {
        return new InputStreamMessageImpl(this.mPrependedData, this.mInputStream);
    }

    @Override // amazon.communication.Message
    public InputStream getPayload() {
        if (!this.mIsGetPayloadInvoked) {
            this.mIsGetPayloadInvoked = true;
            return new PayloadInputStream();
        }
        throw new UnsupportedOperationException("getPayload can only be called once for InputStream based message.");
    }

    @Override // amazon.communication.Message
    public int getPayloadSize() {
        return -1;
    }

    @Override // amazon.communication.Message
    public void prependPayload(ByteBuffer byteBuffer) {
        if (!this.mIsGetPayloadInvoked) {
            this.mPrependedData.add(0, byteBuffer);
            return;
        }
        throw new UnsupportedOperationException("Can't prepend more payload after getPayload is called.");
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InputStreamMessageImpl [PrependedData=");
        outline107.append(this.mPrependedData);
        outline107.append("] [InputStream=");
        outline107.append(this.mInputStream);
        outline107.append("]");
        return outline107.toString();
    }

    public InputStreamMessageImpl(List<ByteBuffer> list, InputStream inputStream) {
        this.mPrependedData = new ArrayList(list);
        this.mInputStream = inputStream;
        this.mIsGetPayloadInvoked = false;
    }

    /* loaded from: classes12.dex */
    private class PayloadInputStream extends InputStream implements BetterInputStream {
        private final ByteBuffer[] mPrependedDataArray;
        private int mPrependedReadIndex = 0;
        private boolean mEndOfStream = false;

        PayloadInputStream() {
            this.mPrependedDataArray = (ByteBuffer[]) InputStreamMessageImpl.this.mPrependedData.toArray(new ByteBuffer[InputStreamMessageImpl.this.mPrependedData.size()]);
        }

        private int readFromPrepends(byte[] bArr, int i, int i2) {
            int i3 = i;
            int i4 = 0;
            while (i4 < i2) {
                int i5 = this.mPrependedReadIndex;
                ByteBuffer[] byteBufferArr = this.mPrependedDataArray;
                if (i5 >= byteBufferArr.length) {
                    break;
                }
                ByteBuffer byteBuffer = byteBufferArr[i5];
                if (byteBuffer.hasRemaining()) {
                    int i6 = i2 - i4;
                    if (i6 > byteBuffer.remaining()) {
                        i6 = byteBuffer.remaining();
                    }
                    byteBuffer.get(bArr, i3, i6);
                    i3 += i6;
                    i4 += i6;
                } else {
                    this.mPrependedReadIndex++;
                }
            }
            InputStreamMessageImpl.log.verbose("readFromPrepends", "final", "maxBytesToRead", Integer.valueOf(i2), "currentOffset", Integer.valueOf(i3), "bytesRead", Integer.valueOf(i4));
            return i4;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            if (!this.mEndOfStream) {
                int i = this.mPrependedReadIndex;
                int i2 = 0;
                while (true) {
                    ByteBuffer[] byteBufferArr = this.mPrependedDataArray;
                    if (i >= byteBufferArr.length) {
                        break;
                    }
                    i2 += byteBufferArr[i].remaining();
                    i++;
                }
                int available = InputStreamMessageImpl.this.mInputStream.available() + i2;
                if (!(InputStreamMessageImpl.this.mInputStream instanceof BetterInputStream) && available == 0) {
                    return 1;
                }
                return available;
            }
            return 1;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            InputStreamMessageImpl.this.mInputStream.close();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            int i;
            if (!this.mEndOfStream) {
                boolean z = true;
                if (this.mPrependedReadIndex >= this.mPrependedDataArray.length) {
                    i = InputStreamMessageImpl.this.mInputStream.read();
                } else {
                    while (true) {
                        int i2 = this.mPrependedReadIndex;
                        ByteBuffer[] byteBufferArr = this.mPrependedDataArray;
                        if (i2 >= byteBufferArr.length || byteBufferArr[i2].hasRemaining()) {
                            break;
                        }
                        this.mPrependedReadIndex++;
                    }
                    int i3 = this.mPrependedReadIndex;
                    ByteBuffer[] byteBufferArr2 = this.mPrependedDataArray;
                    i = i3 < byteBufferArr2.length ? byteBufferArr2[i3].get() & 255 : InputStreamMessageImpl.this.mInputStream.read();
                }
                if (i != -1) {
                    z = false;
                }
                this.mEndOfStream = z;
            } else {
                i = -1;
            }
            InputStreamMessageImpl.log.verbose("read", "single-byte read; too many invocations may indicate inefficiency, and that bulk read operations should be used", new Object[0]);
            return i;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (bArr != null) {
                boolean z = true;
                if (i < 0 || i >= bArr.length || i2 < 0 || i + i2 > bArr.length) {
                    throw new IndexOutOfBoundsException(DPFormattedMessage.toDPFormat("read", "array index out of bounds", "array.length", Integer.valueOf(bArr.length), "offset", Integer.valueOf(i), "length", Integer.valueOf(i2)));
                }
                if (i2 == 0) {
                    return 0;
                }
                if (this.mEndOfStream) {
                    return -1;
                }
                InputStreamMessageImpl.log.verbose("read", "reading with offset", "array.length", Integer.valueOf(bArr.length), "length", Integer.valueOf(i2), "offset", Integer.valueOf(i));
                int readFromPrepends = readFromPrepends(bArr, i, i2);
                InputStreamMessageImpl.log.verbose("read", "finished reading with offset", "bytesRead", Integer.valueOf(readFromPrepends));
                if (readFromPrepends < i2) {
                    int read = InputStreamMessageImpl.this.mInputStream.read(bArr, i + readFromPrepends, i2 - readFromPrepends);
                    if (read != -1) {
                        z = false;
                    }
                    this.mEndOfStream = z;
                    if (read > 0) {
                        readFromPrepends += read;
                    }
                }
                if (readFromPrepends != 0) {
                    return readFromPrepends;
                }
                return -1;
            }
            throw new NullPointerException("Array cannot be null");
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }
    }
}
