package com.amazon.comms.calling.service;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class DataChannelConfiguration {
    private final String label;
    private final int maxRetransmits;
    private final boolean ordered;
    private final String protocol;

    /* loaded from: classes11.dex */
    public static class DataChannelConfigurationBuilder {
        private String label;
        private int maxRetransmits;
        private boolean maxRetransmits$set;
        private boolean ordered;
        private String protocol;

        DataChannelConfigurationBuilder() {
        }

        public DataChannelConfiguration build() {
            return new DataChannelConfiguration(this.label, this.ordered, this.protocol, this.maxRetransmits$set ? this.maxRetransmits : DataChannelConfiguration.$default$maxRetransmits());
        }

        public DataChannelConfigurationBuilder label(String str) {
            this.label = str;
            return this;
        }

        public DataChannelConfigurationBuilder maxRetransmits(int i) {
            this.maxRetransmits = i;
            this.maxRetransmits$set = true;
            return this;
        }

        public DataChannelConfigurationBuilder ordered(boolean z) {
            this.ordered = z;
            return this;
        }

        public DataChannelConfigurationBuilder protocol(String str) {
            this.protocol = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DataChannelConfiguration.DataChannelConfigurationBuilder(label=");
            outline107.append(this.label);
            outline107.append(", ordered=");
            outline107.append(this.ordered);
            outline107.append(", protocol=");
            outline107.append(this.protocol);
            outline107.append(", maxRetransmits=");
            return GeneratedOutlineSupport1.outline86(outline107, this.maxRetransmits, ")");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int $default$maxRetransmits() {
        return -1;
    }

    DataChannelConfiguration(String str, boolean z, String str2, int i) {
        this.label = str;
        this.ordered = z;
        this.protocol = str2;
        this.maxRetransmits = i;
    }

    public static DataChannelConfigurationBuilder builder() {
        return new DataChannelConfigurationBuilder();
    }

    public String getLabel() {
        return this.label;
    }

    public int getMaxRetransmits() {
        return this.maxRetransmits;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public boolean isOrdered() {
        return this.ordered;
    }
}
