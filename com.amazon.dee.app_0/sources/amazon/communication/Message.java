package amazon.communication;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public interface Message {
    @FireOsSdk
    void appendPayload(ByteBuffer byteBuffer);

    @FireOsSdk
    Message extractPayload();

    @FireOsSdk
    InputStream getPayload();

    @FireOsSdk
    int getPayloadSize();

    @FireOsSdk
    void prependPayload(ByteBuffer byteBuffer);
}
