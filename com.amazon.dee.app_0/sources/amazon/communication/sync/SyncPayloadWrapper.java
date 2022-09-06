package amazon.communication.sync;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public class SyncPayloadWrapper {
    @FireOsSdk
    public static final Integer NO_LISTENER_ERROR = 1;
    private Integer errorCode;
    private String messageId;
    private ByteBuffer payload;

    @FireOsSdk
    public Integer getErrorCode() {
        return this.errorCode;
    }

    @FireOsSdk
    public String getMessageId() {
        return this.messageId;
    }

    @FireOsSdk
    public ByteBuffer getPayload() {
        return this.payload;
    }

    @FireOsSdk
    public void setErrorCode(Integer num) {
        this.errorCode = num;
    }

    @FireOsSdk
    public void setMessageId(String str) {
        this.messageId = str;
    }

    @FireOsSdk
    public void setPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
    }
}
