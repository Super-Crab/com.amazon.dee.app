package com.amazon.matter.setuppayloadparser;

import chip.setuppayload.SetupPayload;
import chip.setuppayload.SetupPayloadParser;
import lombok.NonNull;
/* loaded from: classes9.dex */
public class QRSetupPayloadParser {
    private SetupPayloadParser setupPayloadParser;

    public QRSetupPayloadParser(SetupPayloadParser setupPayloadParser) {
        this.setupPayloadParser = setupPayloadParser;
    }

    public SetupPayload parseQRCode(@NonNull String str) throws SetupPayloadParser.UnrecognizedQrCodeException {
        if (str != null) {
            return this.setupPayloadParser.parseQrCode(str);
        }
        throw new NullPointerException("qrCode is marked non-null but is null");
    }
}
