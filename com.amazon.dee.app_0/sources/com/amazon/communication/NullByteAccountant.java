package com.amazon.communication;

import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
public class NullByteAccountant implements BandwidthToolByteAccountant {
    private static final DPLogger log = new DPLogger("TComm.NullByteAccountant");

    @Override // com.amazon.communication.BandwidthToolByteAccountant
    public boolean accountBytesReceived(int i, long j) {
        log.verbose("accountBytesReceived", "Noop - not accounting request's bytes for caller", "bytesToAdd", Long.valueOf(j), "callerUid", Integer.valueOf(i), "metricEvent");
        return true;
    }

    @Override // com.amazon.communication.BandwidthToolByteAccountant
    public boolean accountBytesTransmitted(int i, long j) {
        log.verbose("accountBytesTransmitted", "Noop - not accounting request's bytes for caller", "bytesToAdd", Long.valueOf(j), "callerUid", Integer.valueOf(i), "metricEvent");
        return true;
    }
}
