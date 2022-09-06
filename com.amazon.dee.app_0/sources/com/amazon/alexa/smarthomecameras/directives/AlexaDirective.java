package com.amazon.alexa.smarthomecameras.directives;

import java.util.Objects;
/* loaded from: classes10.dex */
public class AlexaDirective {
    private final AlexaHeader header;
    private final AlexaPayload payload;

    public AlexaDirective(AlexaHeader alexaHeader, AlexaPayload alexaPayload) {
        this.header = alexaHeader;
        this.payload = alexaPayload;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AlexaDirective)) {
            return false;
        }
        AlexaDirective alexaDirective = (AlexaDirective) obj;
        return Objects.equals(this.header, alexaDirective.header) && Objects.equals(this.payload, alexaDirective.payload);
    }

    public AlexaHeader getHeader() {
        return this.header;
    }

    public String getName() {
        return this.header.getName();
    }

    public String getNamespace() {
        return this.header.getNamespace();
    }

    public AlexaPayload getPayload() {
        return this.payload;
    }
}
