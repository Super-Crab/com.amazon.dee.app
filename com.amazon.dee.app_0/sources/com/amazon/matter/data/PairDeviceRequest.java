package com.amazon.matter.data;

import chip.setuppayload.SetupPayload;
import lombok.Generated;
/* loaded from: classes9.dex */
public class PairDeviceRequest {
    private long fabricId;
    private long nodeId;
    private SetupPayload setupPayload;

    @Generated
    public PairDeviceRequest(long j, long j2, SetupPayload setupPayload) {
        this.fabricId = j;
        this.nodeId = j2;
        this.setupPayload = setupPayload;
    }

    @Generated
    public long getFabricId() {
        return this.fabricId;
    }

    @Generated
    public long getNodeId() {
        return this.nodeId;
    }

    @Generated
    public SetupPayload getSetupPayload() {
        return this.setupPayload;
    }
}
