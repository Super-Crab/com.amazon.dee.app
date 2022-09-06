package com.dp.framework;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes2.dex */
public class HexStreamCodec extends StreamCodecBase {
    private static final byte FIELD_DELIMITER = 32;
    private static final int INT_LENGTH = 10;
    private static final int LONG_LENGTH = 18;

    private long decodeHex(InputStream inputStream, int i) throws CodecException {
        try {
            if (inputStream.available() >= 1) {
                if (inputStream.read() != 48 || inputStream.read() != 120) {
                    throw new CodecException("Value not expressed in hexadecimal form");
                }
                long j = 0;
                int i2 = i;
                boolean z = true;
                while (true) {
                    int read = inputStream.read();
                    if (read < 0) {
                        break;
                    }
                    byte b = (byte) read;
                    if (32 != b) {
                        z = false;
                        long j2 = j << 4;
                        int digit = Character.digit(b, 16);
                        if (digit == -1) {
                            throw new CodecException("Unexpected character in value: " + ((int) b));
                        }
                        j = j2 + digit;
                        i2--;
                        if (i2 < 0) {
                            throw new CodecException("Value length longer than expected");
                        }
                    } else if (z) {
                        throw new CodecException("Unable to extract value from an empty parameter");
                    }
                }
                if (i2 == 0) {
                    return j;
                }
                throw new CodecException("Value length (" + (i - i2) + ") shorter than expected (" + i + ")");
            }
            throw new CodecException("End of stream reached");
        } catch (IOException e) {
            throw new CodecException("Invalid message format: unexpected end of stream", e);
        }
    }

    @Override // com.dp.framework.StreamCodec
    public int decodeInt(InputStream inputStream) throws CodecException {
        return (int) decodeHex(inputStream, 8);
    }

    @Override // com.dp.framework.StreamCodecBase
    protected int decodeLength(InputStream inputStream) throws CodecException {
        return decodeInt(inputStream);
    }

    @Override // com.dp.framework.StreamCodec
    public long decodeLong(InputStream inputStream) throws CodecException {
        return decodeHex(inputStream, 16);
    }

    @Override // com.dp.framework.StreamCodec
    public void encodeInt(int i, OutputStream outputStream) throws CodecException {
        write(String.format("0x%08x", Integer.valueOf(i)).getBytes(), outputStream);
        writeDelimiter(outputStream);
    }

    @Override // com.dp.framework.StreamCodecBase
    protected void encodeLength(int i, OutputStream outputStream) throws CodecException {
        encodeInt(i, outputStream);
    }

    @Override // com.dp.framework.StreamCodec
    public void encodeLong(long j, OutputStream outputStream) throws CodecException {
        write(String.format("0x%016x", Long.valueOf(j)).getBytes(), outputStream);
        writeDelimiter(outputStream);
    }

    public byte getDelimiter() {
        return (byte) 32;
    }

    @Override // com.dp.framework.StreamCodec
    public String getName() {
        return "H";
    }

    @Override // com.dp.framework.StreamCodec
    public int getSizeOfDelimiter() {
        return 1;
    }

    @Override // com.dp.framework.StreamCodec
    @Deprecated
    public int getSizeOfEncodedMaxInteger() {
        return INT_LENGTH;
    }

    @Override // com.dp.framework.StreamCodec
    public int getSizeOfInt() {
        return INT_LENGTH;
    }

    @Override // com.dp.framework.StreamCodec
    public int getSizeOfLengthEncoding() {
        return INT_LENGTH;
    }

    @Override // com.dp.framework.StreamCodec
    public int getSizeOfLong() {
        return LONG_LENGTH;
    }

    @Override // com.dp.framework.StreamCodecBase
    protected void readDelimiter(InputStream inputStream) throws CodecException {
        byte b = read(inputStream, 1)[0];
        if (b == 32) {
            return;
        }
        throw new CodecException(GeneratedOutlineSupport1.outline52("Incorrect delimiter.  Expected '32' but saw '", b, "' instead."));
    }

    @Override // com.dp.framework.StreamCodecBase
    protected void writeDelimiter(OutputStream outputStream) throws CodecException {
        try {
            outputStream.write(32);
        } catch (IOException e) {
            throw new CodecException(e);
        }
    }
}
