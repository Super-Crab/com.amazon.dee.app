package kotlin.reflect.jvm.internal.impl.protobuf;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes4.dex */
public abstract class ByteString implements Iterable<Byte> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final ByteString EMPTY = new LiteralByteString(new byte[0]);

    /* loaded from: classes4.dex */
    public interface ByteIterator extends Iterator<Byte> {
        byte nextByte();
    }

    private static ByteString balancedConcat(Iterator<ByteString> it2, int i) {
        if (i == 1) {
            return it2.next();
        }
        int i2 = i >>> 1;
        return balancedConcat(it2, i2).concat(balancedConcat(it2, i - i2));
    }

    public static ByteString copyFrom(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new LiteralByteString(bArr2);
    }

    public static ByteString copyFromUtf8(String str) {
        try {
            return new LiteralByteString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public static Output newOutput() {
        return new Output(128);
    }

    public ByteString concat(ByteString byteString) {
        int size = size();
        int size2 = byteString.size();
        if (size + size2 < 2147483647L) {
            return RopeByteString.concatenate(this, byteString);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline28(53, "ByteString would be too long: ", size, "+", size2));
    }

    public void copyTo(byte[] bArr, int i, int i2, int i3) {
        if (i >= 0) {
            if (i2 < 0) {
                throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline27(30, "Target offset < 0: ", i2));
            }
            if (i3 >= 0) {
                int i4 = i + i3;
                if (i4 <= size()) {
                    int i5 = i2 + i3;
                    if (i5 > bArr.length) {
                        throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline27(34, "Target end offset < 0: ", i5));
                    }
                    if (i3 <= 0) {
                        return;
                    }
                    copyToInternal(bArr, i, i2, i3);
                    return;
                }
                throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline27(34, "Source end offset < 0: ", i4));
            }
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline27(23, "Length < 0: ", i3));
        }
        throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline27(30, "Source offset < 0: ", i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void copyToInternal(byte[] bArr, int i, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int getTreeDepth();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isBalanced();

    public boolean isEmpty() {
        return size() == 0;
    }

    public abstract boolean isValidUtf8();

    @Override // java.lang.Iterable
    public abstract Iterator<Byte> iterator();

    public abstract CodedInputStream newCodedInput();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int partialHash(int i, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int partialIsValidUtf8(int i, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int peekCachedHashCode();

    public abstract int size();

    public byte[] toByteArray() {
        int size = size();
        if (size == 0) {
            return Internal.EMPTY_BYTE_ARRAY;
        }
        byte[] bArr = new byte[size];
        copyToInternal(bArr, 0, 0, size);
        return bArr;
    }

    public String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }

    public abstract String toString(String str) throws UnsupportedEncodingException;

    public String toStringUtf8() {
        try {
            return toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeTo(OutputStream outputStream, int i, int i2) throws IOException {
        if (i >= 0) {
            if (i2 >= 0) {
                int i3 = i + i2;
                if (i3 > size()) {
                    throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline27(39, "Source end offset exceeded: ", i3));
                }
                if (i2 <= 0) {
                    return;
                }
                writeToInternal(outputStream, i, i2);
                return;
            }
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline27(23, "Length < 0: ", i2));
        }
        throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline27(30, "Source offset < 0: ", i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void writeToInternal(OutputStream outputStream, int i, int i2) throws IOException;

    /* loaded from: classes4.dex */
    public static final class Output extends OutputStream {
        private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
        private byte[] buffer;
        private int bufferPos;
        private final ArrayList<ByteString> flushedBuffers;
        private int flushedBuffersTotalBytes;
        private final int initialCapacity;

        Output(int i) {
            if (i >= 0) {
                this.initialCapacity = i;
                this.flushedBuffers = new ArrayList<>();
                this.buffer = new byte[i];
                return;
            }
            throw new IllegalArgumentException("Buffer size < 0");
        }

        private byte[] copyArray(byte[] bArr, int i) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i));
            return bArr2;
        }

        private void flushFullBuffer(int i) {
            this.flushedBuffers.add(new LiteralByteString(this.buffer));
            this.flushedBuffersTotalBytes += this.buffer.length;
            this.buffer = new byte[Math.max(this.initialCapacity, Math.max(i, this.flushedBuffersTotalBytes >>> 1))];
            this.bufferPos = 0;
        }

        private void flushLastBuffer() {
            int i = this.bufferPos;
            byte[] bArr = this.buffer;
            if (i >= bArr.length) {
                this.flushedBuffers.add(new LiteralByteString(bArr));
                this.buffer = EMPTY_BYTE_ARRAY;
            } else if (i > 0) {
                this.flushedBuffers.add(new LiteralByteString(copyArray(bArr, i)));
            }
            this.flushedBuffersTotalBytes += this.bufferPos;
            this.bufferPos = 0;
        }

        public synchronized int size() {
            return this.flushedBuffersTotalBytes + this.bufferPos;
        }

        public synchronized ByteString toByteString() {
            flushLastBuffer();
            return ByteString.copyFrom(this.flushedBuffers);
        }

        public String toString() {
            return String.format("<ByteString.Output@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
        }

        @Override // java.io.OutputStream
        public synchronized void write(int i) {
            if (this.bufferPos == this.buffer.length) {
                flushFullBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i2 = this.bufferPos;
            this.bufferPos = i2 + 1;
            bArr[i2] = (byte) i;
        }

        @Override // java.io.OutputStream
        public synchronized void write(byte[] bArr, int i, int i2) {
            if (i2 <= this.buffer.length - this.bufferPos) {
                System.arraycopy(bArr, i, this.buffer, this.bufferPos, i2);
                this.bufferPos += i2;
            } else {
                int length = this.buffer.length - this.bufferPos;
                System.arraycopy(bArr, i, this.buffer, this.bufferPos, length);
                int i3 = i2 - length;
                flushFullBuffer(i3);
                System.arraycopy(bArr, i + length, this.buffer, 0, i3);
                this.bufferPos = i3;
            }
        }
    }

    public static ByteString copyFrom(byte[] bArr) {
        return copyFrom(bArr, 0, bArr.length);
    }

    public static ByteString copyFrom(Iterable<ByteString> iterable) {
        Collection collection;
        if (!(iterable instanceof Collection)) {
            collection = new ArrayList();
            for (ByteString byteString : iterable) {
                collection.add(byteString);
            }
        } else {
            collection = (Collection) iterable;
        }
        if (collection.isEmpty()) {
            return EMPTY;
        }
        return balancedConcat(collection.iterator(), collection.size());
    }
}
