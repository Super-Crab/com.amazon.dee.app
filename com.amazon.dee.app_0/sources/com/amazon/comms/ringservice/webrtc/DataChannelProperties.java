package com.amazon.comms.ringservice.webrtc;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class DataChannelProperties {
    private final int id;
    private final String label;
    private final String protocol;

    /* loaded from: classes12.dex */
    public static class DataChannelPropertiesBuilder {
        private int id;
        private String label;
        private String protocol;

        DataChannelPropertiesBuilder() {
        }

        public DataChannelProperties build() {
            return new DataChannelProperties(this.id, this.label, this.protocol);
        }

        public DataChannelPropertiesBuilder id(int i) {
            this.id = i;
            return this;
        }

        public DataChannelPropertiesBuilder label(String str) {
            this.label = str;
            return this;
        }

        public DataChannelPropertiesBuilder protocol(String str) {
            this.protocol = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DataChannelProperties.DataChannelPropertiesBuilder(id=");
            outline107.append(this.id);
            outline107.append(", label=");
            outline107.append(this.label);
            outline107.append(", protocol=");
            return GeneratedOutlineSupport1.outline91(outline107, this.protocol, ")");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataChannelProperties(int i, String str, String str2) {
        this.id = i;
        this.label = str;
        this.protocol = str2;
    }

    public static DataChannelPropertiesBuilder builder() {
        return new DataChannelPropertiesBuilder();
    }

    public int getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public String getProtocol() {
        return this.protocol;
    }
}
