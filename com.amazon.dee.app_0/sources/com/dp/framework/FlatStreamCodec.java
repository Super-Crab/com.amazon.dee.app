package com.dp.framework;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.utils.FailFast;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public class FlatStreamCodec extends StreamCodecBase {
    private static final int INT_LENGTH = 4;
    private static final int LONG_LENGTH = 8;
    private static final int SHORT_LENGTH = 2;

    public FlatStreamCodec() {
        FailFast.expectTrue(true);
    }

    private short decodeShort(InputStream inputStream) throws CodecException {
        return readIntoByteBuffer(inputStream, 2).getShort();
    }

    private void encodeShort(short s, OutputStream outputStream) throws CodecException {
        write(ByteBuffer.allocate(2).putShort(s).array(), outputStream);
    }

    private ByteBuffer readIntoByteBuffer(InputStream inputStream, int i) throws CodecException {
        return ByteBuffer.wrap(read(inputStream, i));
    }

    @Override // com.dp.framework.StreamCodec
    public int decodeInt(InputStream inputStream) throws CodecException {
        return readIntoByteBuffer(inputStream, 4).getInt();
    }

    @Override // com.dp.framework.StreamCodecBase
    protected int decodeLength(InputStream inputStream) throws CodecException {
        return decodeShort(inputStream);
    }

    @Override // com.dp.framework.StreamCodec
    public long decodeLong(InputStream inputStream) throws CodecException {
        return readIntoByteBuffer(inputStream, 8).getLong();
    }

    @Override // com.dp.framework.StreamCodec
    public void encodeInt(int i, OutputStream outputStream) throws CodecException {
        write(ByteBuffer.allocate(4).putInt(i).array(), outputStream);
    }

    @Override // com.dp.framework.StreamCodecBase
    protected void encodeLength(int i, OutputStream outputStream) throws CodecException {
        if (i <= 8192) {
            encodeShort((short) i, outputStream);
            return;
        }
        throw new CodecException(GeneratedOutlineSupport1.outline49("Invalid length to encode: ", i));
    }

    @Override // com.dp.framework.StreamCodec
    public void encodeLong(long j, OutputStream outputStream) throws CodecException {
        write(ByteBuffer.allocate(8).putLong(j).array(), outputStream);
    }

    @Override // com.dp.framework.StreamCodec
    public String getName() {
        return "F";
    }

    @Override // com.dp.framework.StreamCodec
    public int getSizeOfDelimiter() {
        return 0;
    }

    @Override // com.dp.framework.StreamCodec
    @Deprecated
    public int getSizeOfEncodedMaxInteger() {
        return 4;
    }

    @Override // com.dp.framework.StreamCodec
    public int getSizeOfInt() {
        return 4;
    }

    @Override // com.dp.framework.StreamCodec
    public int getSizeOfLengthEncoding() {
        return 2;
    }

    @Override // com.dp.framework.StreamCodec
    public int getSizeOfLong() {
        return 8;
    }

    @Override // com.dp.framework.StreamCodecBase
    protected void readDelimiter(InputStream inputStream) throws CodecException {
    }

    @Override // com.dp.framework.StreamCodecBase
    protected void writeDelimiter(OutputStream outputStream) throws CodecException {
    }
}
