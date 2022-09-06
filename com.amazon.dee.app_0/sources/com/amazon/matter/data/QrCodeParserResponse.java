package com.amazon.matter.data;

import chip.setuppayload.SetupPayload;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
@SuppressFBWarnings({"URF_UNREAD_FIELD"})
/* loaded from: classes9.dex */
public class QrCodeParserResponse {
    private SetupPayload setupPayload;

    @Generated
    public QrCodeParserResponse(SetupPayload setupPayload) {
        this.setupPayload = setupPayload;
    }
}
