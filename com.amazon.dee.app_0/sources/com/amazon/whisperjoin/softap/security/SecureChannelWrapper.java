package com.amazon.whisperjoin.softap.security;

import com.amazon.whispercloak.SecureChannel;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class SecureChannelWrapper {
    private int scheme;
    private SecureChannel secureChannel;

    /* loaded from: classes13.dex */
    public static class SecureChannelWrapperBuilder {
        private int scheme;
        private SecureChannel secureChannel;

        SecureChannelWrapperBuilder() {
        }

        public SecureChannelWrapper build() {
            return new SecureChannelWrapper(this.secureChannel, this.scheme);
        }

        public SecureChannelWrapperBuilder scheme(int i) {
            this.scheme = i;
            return this;
        }

        public SecureChannelWrapperBuilder secureChannel(SecureChannel secureChannel) {
            this.secureChannel = secureChannel;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SecureChannelWrapper.SecureChannelWrapperBuilder(secureChannel=");
            outline107.append(this.secureChannel);
            outline107.append(", scheme=");
            return GeneratedOutlineSupport1.outline86(outline107, this.scheme, ")");
        }
    }

    SecureChannelWrapper(SecureChannel secureChannel, int i) {
        this.secureChannel = secureChannel;
        this.scheme = i;
    }

    public static SecureChannelWrapperBuilder builder() {
        return new SecureChannelWrapperBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof SecureChannelWrapper;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SecureChannelWrapper)) {
            return false;
        }
        SecureChannelWrapper secureChannelWrapper = (SecureChannelWrapper) obj;
        if (!secureChannelWrapper.canEqual(this)) {
            return false;
        }
        SecureChannel secureChannel = getSecureChannel();
        SecureChannel secureChannel2 = secureChannelWrapper.getSecureChannel();
        if (secureChannel != null ? !secureChannel.equals(secureChannel2) : secureChannel2 != null) {
            return false;
        }
        return getScheme() == secureChannelWrapper.getScheme();
    }

    public int getScheme() {
        return this.scheme;
    }

    public SecureChannel getSecureChannel() {
        return this.secureChannel;
    }

    public int hashCode() {
        SecureChannel secureChannel = getSecureChannel();
        return getScheme() + (((secureChannel == null ? 43 : secureChannel.hashCode()) + 59) * 59);
    }

    public void setScheme(int i) {
        this.scheme = i;
    }

    public void setSecureChannel(SecureChannel secureChannel) {
        this.secureChannel = secureChannel;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SecureChannelWrapper(secureChannel=");
        outline107.append(getSecureChannel());
        outline107.append(", scheme=");
        outline107.append(getScheme());
        outline107.append(")");
        return outline107.toString();
    }
}
