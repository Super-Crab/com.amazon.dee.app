package amazon.communication.rmr;

import com.amazon.dp.logger.DPLogger;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
@Deprecated
/* loaded from: classes.dex */
public class FullRmrTimeoutResponseHandler extends PeriodicRmrResponseHandlerBase {
    private static final DPLogger log = new DPLogger("TComm.FullRmrTimeoutResponseHandler");

    @FireOsSdk
    @Deprecated
    public FullRmrTimeoutResponseHandler(RmrResponseHandler rmrResponseHandler, int i) {
        super(rmrResponseHandler, i);
        throw new UnsupportedOperationException();
    }

    @Override // amazon.communication.rmr.PeriodicRmrResponseHandlerBase
    @FireOsSdk
    @Deprecated
    public final synchronized void onPeriodicNotification(RmrRequest rmrRequest) {
        throw new UnsupportedOperationException();
    }
}
