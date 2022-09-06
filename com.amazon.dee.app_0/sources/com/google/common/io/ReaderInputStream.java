package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
@GwtIncompatible
/* loaded from: classes3.dex */
final class ReaderInputStream extends InputStream {
    private ByteBuffer byteBuffer;
    private CharBuffer charBuffer;
    private boolean doneFlushing;
    private boolean draining;
    private final CharsetEncoder encoder;
    private boolean endOfInput;
    private final Reader reader;
    private final byte[] singleByte;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReaderInputStream(Reader reader, Charset charset, int i) {
        this(reader, charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), i);
    }

    private static int availableCapacity(Buffer buffer) {
        return buffer.capacity() - buffer.limit();
    }

    private int drain(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, this.byteBuffer.remaining());
        this.byteBuffer.get(bArr, i, min);
        return min;
    }

    private static CharBuffer grow(CharBuffer charBuffer) {
        CharBuffer wrap = CharBuffer.wrap(Arrays.copyOf(charBuffer.array(), charBuffer.capacity() * 2));
        Java8Compatibility.position(wrap, charBuffer.position());
        Java8Compatibility.limit(wrap, charBuffer.limit());
        return wrap;
    }

    private void readMoreChars() throws IOException {
        if (availableCapacity(this.charBuffer) == 0) {
            if (this.charBuffer.position() > 0) {
                Java8Compatibility.flip(this.charBuffer.compact());
            } else {
                this.charBuffer = grow(this.charBuffer);
            }
        }
        int limit = this.charBuffer.limit();
        int read = this.reader.read(this.charBuffer.array(), limit, availableCapacity(this.charBuffer));
        if (read == -1) {
            this.endOfInput = true;
        } else {
            Java8Compatibility.limit(this.charBuffer, limit + read);
        }
    }

    private void startDraining(boolean z) {
        Java8Compatibility.flip(this.byteBuffer);
        if (z && this.byteBuffer.remaining() == 0) {
            this.byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() * 2);
        } else {
            this.draining = true;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.reader.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (read(this.singleByte) == 1) {
            return UnsignedBytes.toInt(this.singleByte[0]);
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002a, code lost:
        if (r1 <= 0) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002d, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:?, code lost:
        return r1;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int read(byte[] r8, int r9, int r10) throws java.io.IOException {
        /*
            r7 = this;
            int r0 = r9 + r10
            int r1 = r8.length
            com.google.common.base.Preconditions.checkPositionIndexes(r9, r0, r1)
            r0 = 0
            if (r10 != 0) goto La
            return r0
        La:
            boolean r1 = r7.endOfInput
            r2 = r1
            r1 = r0
        Le:
            boolean r3 = r7.draining
            if (r3 == 0) goto L2f
            int r3 = r9 + r1
            int r4 = r10 - r1
            int r3 = r7.drain(r8, r3, r4)
            int r1 = r1 + r3
            if (r1 == r10) goto L2a
            boolean r3 = r7.doneFlushing
            if (r3 == 0) goto L22
            goto L2a
        L22:
            r7.draining = r0
            java.nio.ByteBuffer r3 = r7.byteBuffer
            com.google.common.io.Java8Compatibility.clear(r3)
            goto L2f
        L2a:
            if (r1 <= 0) goto L2d
            goto L2e
        L2d:
            r1 = -1
        L2e:
            return r1
        L2f:
            boolean r3 = r7.doneFlushing
            if (r3 == 0) goto L36
            java.nio.charset.CoderResult r3 = java.nio.charset.CoderResult.UNDERFLOW
            goto L4d
        L36:
            if (r2 == 0) goto L41
            java.nio.charset.CharsetEncoder r3 = r7.encoder
            java.nio.ByteBuffer r4 = r7.byteBuffer
            java.nio.charset.CoderResult r3 = r3.flush(r4)
            goto L4d
        L41:
            java.nio.charset.CharsetEncoder r3 = r7.encoder
            java.nio.CharBuffer r4 = r7.charBuffer
            java.nio.ByteBuffer r5 = r7.byteBuffer
            boolean r6 = r7.endOfInput
            java.nio.charset.CoderResult r3 = r3.encode(r4, r5, r6)
        L4d:
            boolean r4 = r3.isOverflow()
            r5 = 1
            if (r4 == 0) goto L58
            r7.startDraining(r5)
            goto Le
        L58:
            boolean r4 = r3.isUnderflow()
            if (r4 == 0) goto L70
            if (r2 == 0) goto L66
            r7.doneFlushing = r5
            r7.startDraining(r0)
            goto Le
        L66:
            boolean r3 = r7.endOfInput
            if (r3 == 0) goto L6c
            r2 = r5
            goto L2f
        L6c:
            r7.readMoreChars()
            goto L2f
        L70:
            boolean r4 = r3.isError()
            if (r4 == 0) goto L2f
            r3.throwException()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.ReaderInputStream.read(byte[], int, int):int");
    }

    ReaderInputStream(Reader reader, CharsetEncoder charsetEncoder, int i) {
        boolean z = true;
        this.singleByte = new byte[1];
        this.reader = (Reader) Preconditions.checkNotNull(reader);
        this.encoder = (CharsetEncoder) Preconditions.checkNotNull(charsetEncoder);
        Preconditions.checkArgument(i <= 0 ? false : z, "bufferSize must be positive: %s", i);
        charsetEncoder.reset();
        this.charBuffer = CharBuffer.allocate(i);
        Java8Compatibility.flip(this.charBuffer);
        this.byteBuffer = ByteBuffer.allocate(i);
    }
}
