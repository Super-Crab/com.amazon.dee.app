package org.apache.commons.io.input;

import java.io.DataInput;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.EndianUtils;
/* loaded from: classes4.dex */
public class SwappedDataInputStream extends ProxyInputStream implements DataInput {
    public SwappedDataInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.DataInput
    public boolean readBoolean() throws IOException, EOFException {
        return readByte() != 0;
    }

    @Override // java.io.DataInput
    public byte readByte() throws IOException, EOFException {
        return (byte) ((FilterInputStream) this).in.read();
    }

    @Override // java.io.DataInput
    public char readChar() throws IOException, EOFException {
        return (char) readShort();
    }

    @Override // java.io.DataInput
    public double readDouble() throws IOException, EOFException {
        return EndianUtils.readSwappedDouble(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public float readFloat() throws IOException, EOFException {
        return EndianUtils.readSwappedFloat(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr) throws IOException, EOFException {
        readFully(bArr, 0, bArr.length);
    }

    @Override // java.io.DataInput
    public int readInt() throws IOException, EOFException {
        return EndianUtils.readSwappedInteger(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public String readLine() throws IOException, EOFException {
        throw new UnsupportedOperationException("Operation not supported: readLine()");
    }

    @Override // java.io.DataInput
    public long readLong() throws IOException, EOFException {
        return EndianUtils.readSwappedLong(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public short readShort() throws IOException, EOFException {
        return EndianUtils.readSwappedShort(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public String readUTF() throws IOException, EOFException {
        throw new UnsupportedOperationException("Operation not supported: readUTF()");
    }

    @Override // java.io.DataInput
    public int readUnsignedByte() throws IOException, EOFException {
        return ((FilterInputStream) this).in.read();
    }

    @Override // java.io.DataInput
    public int readUnsignedShort() throws IOException, EOFException {
        return EndianUtils.readSwappedUnsignedShort(((FilterInputStream) this).in);
    }

    @Override // java.io.DataInput
    public int skipBytes(int i) throws IOException, EOFException {
        return (int) ((FilterInputStream) this).in.skip(i);
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr, int i, int i2) throws IOException, EOFException {
        int i3 = i2;
        while (i3 > 0) {
            int read = read(bArr, (i + i2) - i3, i3);
            if (-1 == read) {
                throw new EOFException();
            }
            i3 -= read;
        }
    }
}
