package com.amazon.matter.setuppayloadparser;

import chip.setuppayload.SetupPayload;
import chip.setuppayload.SetupPayloadParser;
import lombok.NonNull;
/* loaded from: classes9.dex */
public class ManualSetupPayloadParser {
    private SetupPayloadParser setupPayloadParser;

    public ManualSetupPayloadParser(SetupPayloadParser setupPayloadParser) {
        this.setupPayloadParser = setupPayloadParser;
    }

    public SetupPayload parseManualCode(@NonNull String str) throws SetupPayloadParser.InvalidEntryCodeFormatException {
        if (str != null) {
            return this.setupPayloadParser.parseManualEntryCode(str);
        }
        throw new NullPointerException("manualCode is marked non-null but is null");
    }
}
