package com.amazon.communication;

import com.dp.framework.StreamCodec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public abstract class ProtocolHandlerBase implements ProtocolHandler {
    protected static final byte[] EMPTY_FOOTER = new byte[0];
    private final byte[] mFooter;
    protected final StreamCodec mStreamCodec;

    public ProtocolHandlerBase(byte[] bArr, StreamCodec streamCodec) {
        this.mFooter = bArr;
        this.mStreamCodec = streamCodec;
    }

    public boolean confirmFooter(InputStream inputStream) {
        for (int i = 0; i < this.mFooter.length; i++) {
            try {
                if (((byte) inputStream.read()) != this.mFooter[i]) {
                    return false;
                }
            } catch (IOException unused) {
                return false;
            }
        }
        return true;
    }

    public void encodeFooter(OutputStream outputStream) throws ProtocolException {
        try {
            outputStream.write(this.mFooter);
        } catch (IOException e) {
            throw new ProtocolException(e);
        }
    }

    public int getAvailableDataSize(ByteBuffer[] byteBufferArr) {
        int i = 0;
        for (ByteBuffer byteBuffer : byteBufferArr) {
            i += byteBuffer.remaining();
        }
        return i;
    }
}
