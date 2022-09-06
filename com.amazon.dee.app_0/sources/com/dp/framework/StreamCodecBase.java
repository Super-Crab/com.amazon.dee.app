package com.dp.framework;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;
/* loaded from: classes2.dex */
public abstract class StreamCodecBase implements StreamCodec {
    protected static final byte FALSE_BYTE = 102;
    protected static final int MAX_ENCODED_LENGTH = 8192;
    protected static final byte TRUE_BYTE = 116;

    @Override // com.dp.framework.StreamCodec
    public String decodeAsciiString(InputStream inputStream, int i) throws CodecException {
        byte[] read = read(inputStream, i);
        readDelimiter(inputStream);
        try {
            return new String(read, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new CodecException(e);
        }
    }

    @Override // com.dp.framework.StreamCodec
    public boolean decodeBool(InputStream inputStream) throws CodecException {
        byte[] read = read(inputStream, 1);
        readDelimiter(inputStream);
        byte b = read[0];
        if (b == 116) {
            return true;
        }
        if (b != 102) {
            throw new CodecException(GeneratedOutlineSupport1.outline49("Unable to extract bool from byte: ", b));
        }
        return false;
    }

    @Override // com.dp.framework.StreamCodec
    public byte[] decodeByteArray(InputStream inputStream) throws CodecException {
        byte[] read = read(inputStream, decodeLength(inputStream));
        readDelimiter(inputStream);
        return read;
    }

    protected abstract int decodeLength(InputStream inputStream) throws CodecException;

    @Override // com.dp.framework.StreamCodec
    public String decodeString(InputStream inputStream) throws CodecException {
        try {
            return new String(decodeByteArray(inputStream), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new CodecException(e);
        }
    }

    @Override // com.dp.framework.StreamCodec
    public void encodeAsciiString(String str, OutputStream outputStream) throws CodecException {
        if (str != null) {
            try {
                byte[] bytes = str.getBytes("UTF-8");
                if (bytes.length == str.length()) {
                    write(bytes, outputStream);
                    writeDelimiter(outputStream);
                    return;
                }
                throw new IllegalArgumentException("String appears to not be ASCII");
            } catch (UnsupportedEncodingException e) {
                throw new CodecException(e);
            }
        }
        throw new IllegalArgumentException("String cannot be null");
    }

    @Override // com.dp.framework.StreamCodec
    public void encodeBool(boolean z, OutputStream outputStream) throws CodecException {
        if (z) {
            write(new byte[]{TRUE_BYTE}, outputStream);
        } else {
            write(new byte[]{FALSE_BYTE}, outputStream);
        }
        writeDelimiter(outputStream);
    }

    @Override // com.dp.framework.StreamCodec
    public void encodeByteArray(byte[] bArr, OutputStream outputStream) throws CodecException {
        if (bArr != null) {
            if (bArr.length <= 8192) {
                encodeLength(bArr.length, outputStream);
                write(bArr, outputStream);
                writeDelimiter(outputStream);
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("byte[] too long to encode: ");
            outline107.append(bArr.length);
            throw new CodecException(outline107.toString());
        }
        throw new IllegalArgumentException("byte[] cannot be null");
    }

    protected abstract void encodeLength(int i, OutputStream outputStream) throws CodecException;

    @Override // com.dp.framework.StreamCodec
    public void encodeString(String str, OutputStream outputStream) throws CodecException {
        if (str != null) {
            try {
                encodeByteArray(str.getBytes("UTF-8"), outputStream);
                return;
            } catch (UnsupportedEncodingException e) {
                throw new CodecException(e);
            }
        }
        throw new IllegalArgumentException("String cannot be null");
    }

    @Override // com.dp.framework.StreamCodec
    public int getSizeOfBool() {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] read(InputStream inputStream, int i) throws CodecException {
        if (i >= 0 && i <= 8192) {
            byte[] bArr = new byte[i];
            if (i != 0) {
                try {
                    int read = inputStream.read(bArr);
                    if (read != i) {
                        throw new CodecException("Unable to extract byte[] of length: " + i + ". Read: " + read);
                    }
                } catch (IOException e) {
                    throw new CodecException("Invalid message format: unexpected end of stream", e);
                } catch (BufferUnderflowException e2) {
                    throw new CodecException("Invalid message format: unexpected end of stream", e2);
                }
            }
            return bArr;
        }
        throw new CodecException(GeneratedOutlineSupport1.outline49("Invalid message format: incoming byte[] has invalid length: ", i));
    }

    protected abstract void readDelimiter(InputStream inputStream) throws CodecException;

    /* JADX INFO: Access modifiers changed from: protected */
    public void write(byte[] bArr, OutputStream outputStream) throws CodecException {
        if (bArr != null) {
            if (bArr.length <= 8192) {
                try {
                    outputStream.write(bArr);
                    return;
                } catch (IOException e) {
                    throw new CodecException(e);
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("byte[] too long to encode: ");
            outline107.append(bArr.length);
            throw new CodecException(outline107.toString());
        }
        throw new IllegalArgumentException("byte[] cannot be null");
    }

    protected abstract void writeDelimiter(OutputStream outputStream) throws CodecException;
}
