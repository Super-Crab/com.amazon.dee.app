package com.amazon.deecomms.calling.datachannel;

import com.google.gson.JsonElement;
/* loaded from: classes12.dex */
public class CommsDataChannelEvent {
    private CommsDataChannelHeader header;
    private JsonElement payload;

    /* loaded from: classes12.dex */
    public static final class Builder {
        private CommsDataChannelHeader header;
        private JsonElement payload;

        public CommsDataChannelEvent build() {
            return new CommsDataChannelEvent(this.header, this.payload);
        }

        public Builder withHeader(CommsDataChannelHeader commsDataChannelHeader) {
            this.header = commsDataChannelHeader;
            return this;
        }

        public Builder withPayload(JsonElement jsonElement) {
            this.payload = jsonElement;
            return this;
        }
    }

    protected CommsDataChannelEvent(CommsDataChannelHeader commsDataChannelHeader, JsonElement jsonElement) {
        this.header = commsDataChannelHeader;
        this.payload = jsonElement;
    }

    public static Builder builder() {
        return new Builder();
    }

    public CommsDataChannelHeader getHeader() {
        return this.header;
    }

    public JsonElement getPayload() {
        return this.payload;
    }
}
