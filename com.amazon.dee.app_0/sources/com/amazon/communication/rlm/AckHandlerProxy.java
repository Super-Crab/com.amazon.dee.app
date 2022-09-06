package com.amazon.communication.rlm;

import amazon.communication.rlm.AckHandler;
import android.os.RemoteException;
import com.amazon.communication.rlm.IAckHandler;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class AckHandlerProxy extends IAckHandler.Stub {
    private static final DPLogger log = new DPLogger("TComm.AckHandlerProxy");
    private final AckHandler mAckHandler;

    public AckHandlerProxy(AckHandler ackHandler) {
        this.mAckHandler = ackHandler;
    }

    @Override // com.amazon.communication.rlm.IAckHandler
    public void onAck(int i) throws RemoteException {
        log.info("onAck", "Received an ACK", "messadeID", Integer.valueOf(i));
        try {
            this.mAckHandler.onAck(i);
        } catch (RuntimeException e) {
            log.warn("onAck", "Exception occurred", e);
            throw e;
        }
    }

    @Override // com.amazon.communication.rlm.IAckHandler
    public void onNack(int i, int i2, String str) throws RemoteException {
        log.info("onNack", "Received a NACK", "messadeId", Integer.valueOf(i), "errorCode", Integer.valueOf(i2), "description", str);
        try {
            this.mAckHandler.onNack(i, i2, str);
        } catch (RuntimeException e) {
            log.warn("onNack", "Exception occurred", e);
            throw e;
        }
    }

    @Override // com.amazon.communication.rlm.IAckHandler
    public void onPack(int i, int i2, String str) throws RemoteException {
        log.info("onPack", "Received a PACK", "messadeId", Integer.valueOf(i), "packCode", Integer.valueOf(i2), "description", str);
        try {
            this.mAckHandler.onPack(i, i2, str);
        } catch (RuntimeException e) {
            log.warn("onPack", "Exception occurred", e);
            throw e;
        }
    }
}
