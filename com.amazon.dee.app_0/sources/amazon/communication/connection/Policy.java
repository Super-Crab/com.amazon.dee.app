package amazon.communication.connection;

import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
/* loaded from: classes.dex */
public final class Policy {
    private final CompressionOption mCompressionOption;
    private final boolean mIsAnonymousCredentialsAllowed;
    private final boolean mIsClearText;
    private final boolean mIsDedicated;
    private final boolean mIsLowLatencyNecessary;
    private final boolean mIsRequestResponseOnly;
    private final boolean mIsWiFiNecessary;
    private final KeepAlive mKeepAlive;
    private final Priority mPriority;
    private final Purpose mPurpose;
    private final boolean mReconnectOnFailure;
    @FireOsSdk
    public static final Policy ONE_SHOT = new Builder().setIsRequestResponseOnly(true).setIsClearText(true).build();
    @FireOsSdk
    public static final Policy SHARED_BIDI = new Builder().setIsLowLatencyNecessary(true).build();
    @FireOsSdk
    public static final Policy FAST_BIDI = new Builder().setIsLowLatencyNecessary(true).build();

    /* loaded from: classes.dex */
    public static final class Builder {
        private CompressionOption mCompressionOption = CompressionOption.ALLOWED;
        private Priority mPriority = Priority.PRIORITY_NORMAL;
        private boolean mIsLowLatencyNecessary = false;
        private boolean mIsRequestResponseOnly = false;
        private boolean mIsClearText = false;
        private boolean mIsWiFiNecessary = false;
        private boolean mIsAnonymousCredentialsAllowed = false;
        private boolean mIsDedicated = false;
        private Purpose mPurpose = null;
        private boolean mReconnectOnFailure = false;
        private KeepAlive mKeepAlive = KeepAlive.NONE;
        private boolean mBuilt = false;

        private void enforceSingleBuild() {
            if (!this.mBuilt) {
                return;
            }
            throw new IllegalStateException("Each builder can only be used once. Create a new builder");
        }

        @FireOsSdk
        public Policy build() {
            enforceSingleBuild();
            this.mBuilt = true;
            if (this.mIsDedicated && Purpose.REGULAR.equals(this.mPurpose)) {
                throw new IllegalArgumentException("purpose must be set for dedicated connection");
            }
            return new Policy(this.mCompressionOption, this.mPriority, this.mIsLowLatencyNecessary, this.mIsRequestResponseOnly, this.mIsClearText, this.mIsWiFiNecessary, this.mIsAnonymousCredentialsAllowed, this.mIsDedicated, this.mPurpose, this.mReconnectOnFailure, this.mKeepAlive);
        }

        @FireOsSdk
        public Policy fromConnectionPolicy(ConnectionPolicy connectionPolicy) {
            if (connectionPolicy != null) {
                enforceSingleBuild();
                setCompressionOption(CompressionOption.values()[connectionPolicy.getCompressionOption().ordinal()]);
                setPriority(connectionPolicy.getPriority());
                setIsLowLatencyNecessary(connectionPolicy.isLowLatencyNecessary());
                setIsRequestResponseOnly(connectionPolicy.isRequestResponseOnly());
                setIsClearText(connectionPolicy.isClearText());
                setIsWiFiNecessary(connectionPolicy.isWiFiNecessary());
                setIsAnonymousCredentialsAllowed(connectionPolicy.isAnonymousCredentialsAllowed());
                return build();
            }
            throw new IllegalArgumentException("compatibility must not be null");
        }

        @FireOsSdk
        public Builder setCompressionOption(CompressionOption compressionOption) {
            enforceSingleBuild();
            this.mCompressionOption = compressionOption;
            return this;
        }

        @FireOsSdk
        @Deprecated
        public Builder setDirectedId(String str) {
            throw new UnsupportedOperationException("Method is not supported");
        }

        @FireOsSdk
        public Builder setIsAnonymousCredentialsAllowed(boolean z) {
            enforceSingleBuild();
            this.mIsAnonymousCredentialsAllowed = z;
            return this;
        }

        @FireOsSdk
        public Builder setIsClearText(boolean z) {
            enforceSingleBuild();
            this.mIsClearText = z;
            return this;
        }

        @FireOsSdk
        public Builder setIsDedicated(boolean z) {
            enforceSingleBuild();
            this.mIsDedicated = z;
            return this;
        }

        @FireOsSdk
        public Builder setIsLowLatencyNecessary(boolean z) {
            enforceSingleBuild();
            this.mIsLowLatencyNecessary = z;
            return this;
        }

        @FireOsSdk
        public Builder setIsRequestResponseOnly(boolean z) {
            enforceSingleBuild();
            this.mIsRequestResponseOnly = z;
            return this;
        }

        @FireOsSdk
        public Builder setIsWiFiNecessary(boolean z) {
            enforceSingleBuild();
            this.mIsWiFiNecessary = z;
            return this;
        }

        @FireOsSdk
        public Builder setKeepAlive(KeepAlive keepAlive) {
            enforceSingleBuild();
            this.mKeepAlive = keepAlive;
            return this;
        }

        @FireOsSdk
        public Builder setPriority(Priority priority) {
            enforceSingleBuild();
            this.mPriority = priority;
            return this;
        }

        @FireOsSdk
        public Builder setPurpose(Purpose purpose) {
            enforceSingleBuild();
            this.mPurpose = purpose;
            return this;
        }

        @FireOsSdk
        public Builder setReconnectOnFailure(boolean z) {
            enforceSingleBuild();
            this.mReconnectOnFailure = z;
            return this;
        }
    }

    @FireOsSdk
    public boolean equals(Object obj) {
        Purpose purpose;
        Purpose purpose2;
        if (this == obj) {
            return true;
        }
        if (obj == null || Policy.class != obj.getClass()) {
            return false;
        }
        Policy policy = (Policy) obj;
        return this.mCompressionOption == policy.mCompressionOption && this.mPriority == policy.mPriority && this.mIsLowLatencyNecessary == policy.mIsLowLatencyNecessary && this.mIsRequestResponseOnly == policy.mIsRequestResponseOnly && this.mIsClearText == policy.mIsClearText && this.mIsWiFiNecessary == policy.mIsWiFiNecessary && this.mIsAnonymousCredentialsAllowed == policy.mIsAnonymousCredentialsAllowed && this.mIsDedicated == policy.mIsDedicated && ((purpose = this.mPurpose) == (purpose2 = policy.mPurpose) || (purpose != null && purpose.equals(purpose2))) && this.mReconnectOnFailure == policy.mReconnectOnFailure && this.mKeepAlive == policy.mKeepAlive;
    }

    @FireOsSdk
    public CompressionOption getCompressionOption() {
        return this.mCompressionOption;
    }

    @FireOsSdk
    @Deprecated
    public String getDirectedId() {
        throw new UnsupportedOperationException("Method is not supported");
    }

    @FireOsSdk
    public KeepAlive getKeepAlive() {
        return this.mKeepAlive;
    }

    @FireOsSdk
    public Priority getPriority() {
        return this.mPriority;
    }

    @FireOsSdk
    public Purpose getPurpose() {
        return this.mPurpose;
    }

    @FireOsSdk
    public boolean getReconnectOnFailure() {
        return this.mReconnectOnFailure;
    }

    @FireOsSdk
    public int hashCode() {
        int hashCode = this.mPriority.hashCode();
        int hashCode2 = this.mPurpose.hashCode();
        return this.mKeepAlive.hashCode() + ((((hashCode2 + ((((((((((((((hashCode + ((this.mCompressionOption.hashCode() + JfifUtil.MARKER_EOI) * 31)) * 31) + (this.mIsLowLatencyNecessary ? 1 : 0)) * 31) + (this.mIsRequestResponseOnly ? 1 : 0)) * 31) + (this.mIsClearText ? 1 : 0)) * 31) + (this.mIsWiFiNecessary ? 1 : 0)) * 31) + (this.mIsAnonymousCredentialsAllowed ? 1 : 0)) * 31) + (this.mIsDedicated ? 1 : 0)) * 31)) * 31) + (this.mReconnectOnFailure ? 1 : 0)) * 31);
    }

    @FireOsSdk
    public boolean isAnonymousCredentialsAllowed() {
        return this.mIsAnonymousCredentialsAllowed;
    }

    @FireOsSdk
    public boolean isClearText() {
        return this.mIsClearText;
    }

    @FireOsSdk
    public boolean isDedicated() {
        return this.mIsDedicated;
    }

    @FireOsSdk
    public boolean isLowLatencyNecessary() {
        return this.mIsLowLatencyNecessary;
    }

    @FireOsSdk
    public boolean isRequestResponseOnly() {
        return this.mIsRequestResponseOnly;
    }

    @FireOsSdk
    public boolean isWiFiNecessary() {
        return this.mIsWiFiNecessary;
    }

    @FireOsSdk
    public String toString() {
        StringBuilder outline112 = GeneratedOutlineSupport1.outline112("{ ", ", CompressionOption: ");
        outline112.append(this.mCompressionOption);
        outline112.append(", Priority: ");
        outline112.append(this.mPriority);
        outline112.append(", LowLatency: ");
        outline112.append(this.mIsLowLatencyNecessary);
        outline112.append(", RequestResponseOnly: ");
        outline112.append(this.mIsRequestResponseOnly);
        outline112.append(", IsClearText: ");
        outline112.append(this.mIsClearText);
        outline112.append(", IsWiFiNecessary: ");
        outline112.append(this.mIsWiFiNecessary);
        outline112.append(", IsAnonymousCredentialsAllowed: ");
        outline112.append(this.mIsAnonymousCredentialsAllowed);
        outline112.append(", mIsDedicated: ");
        outline112.append(this.mIsDedicated);
        outline112.append(", mPurpose: ");
        outline112.append(this.mPurpose);
        outline112.append(", mReconnectOnFailure: ");
        outline112.append(this.mReconnectOnFailure);
        outline112.append(", mKeepAlive: ");
        outline112.append(this.mKeepAlive);
        outline112.append(" }");
        return outline112.toString();
    }

    private Policy(CompressionOption compressionOption, Priority priority, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, Purpose purpose, boolean z7, KeepAlive keepAlive) {
        this.mCompressionOption = compressionOption;
        this.mPriority = priority;
        this.mIsLowLatencyNecessary = z;
        this.mIsRequestResponseOnly = z2;
        this.mIsClearText = z3;
        this.mIsWiFiNecessary = z4;
        this.mIsAnonymousCredentialsAllowed = z5;
        this.mIsDedicated = z6;
        this.mPurpose = purpose;
        this.mReconnectOnFailure = z7;
        this.mKeepAlive = keepAlive;
    }
}
