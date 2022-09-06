package com.amazon.alexa.comms.mediaInsights.service.transport;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class TransportResult {
    private int responseCode;

    /* loaded from: classes6.dex */
    public static class TransportResultBuilder {
        private int responseCode;

        TransportResultBuilder() {
        }

        public TransportResult build() {
            return new TransportResult(this.responseCode);
        }

        public TransportResultBuilder responseCode(int i) {
            this.responseCode = i;
            return this;
        }

        public String toString() {
            return GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("TransportResult.TransportResultBuilder(responseCode="), this.responseCode, ")");
        }
    }

    TransportResult(int i) {
        this.responseCode = i;
    }

    public static TransportResultBuilder builder() {
        return new TransportResultBuilder();
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TransportResult(responseCode=");
        outline107.append(getResponseCode());
        outline107.append(")");
        return outline107.toString();
    }
}
