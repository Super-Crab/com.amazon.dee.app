package amazon.communication;

import com.amazon.communication.ByteBufferChainMessageImpl;
import com.amazon.communication.InputStreamMessageImpl;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public final class MessageFactory {
    private MessageFactory() {
    }

    @FireOsSdk
    public static Message createMessage(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            return new ByteBufferChainMessageImpl(byteBuffer);
        }
        throw new IllegalArgumentException("ByteBuffer should not be null");
    }

    @FireOsSdk
    public static Message createMessage(ByteBuffer[] byteBufferArr) {
        if (byteBufferArr != null && byteBufferArr.length != 0) {
            return new ByteBufferChainMessageImpl(byteBufferArr);
        }
        throw new IllegalArgumentException("ByteBuffer should not be null");
    }

    @FireOsSdk
    public static Message createMessage(InputStream inputStream) {
        if (inputStream != null) {
            return new InputStreamMessageImpl(inputStream);
        }
        throw new IllegalArgumentException("InputStream should not be null");
    }
}
