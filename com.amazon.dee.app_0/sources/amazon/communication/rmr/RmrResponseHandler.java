package amazon.communication.rmr;

import amazon.communication.Message;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
@Deprecated
/* loaded from: classes.dex */
public interface RmrResponseHandler {
    @FireOsSdk
    @Deprecated
    void onError(RmrRequest rmrRequest, RmrResponseException rmrResponseException);

    @FireOsSdk
    @Deprecated
    void onFinish(RmrRequest rmrRequest);

    @FireOsSdk
    @Deprecated
    void onResponse(RmrRequest rmrRequest, Message message);

    @FireOsSdk
    @Deprecated
    void onStart(RmrRequest rmrRequest);
}
