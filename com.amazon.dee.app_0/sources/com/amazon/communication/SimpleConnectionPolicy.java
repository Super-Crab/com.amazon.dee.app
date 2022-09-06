package com.amazon.communication;

import amazon.communication.connection.ConnectionPolicy;
import amazon.communication.connection.Priority;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
/* loaded from: classes12.dex */
public class SimpleConnectionPolicy implements ConnectionPolicy {
    protected boolean mIsRoamingAllowed = true;
    protected boolean mIsShortLived = true;
    protected boolean mIsLowLatencyNecessary = false;
    protected boolean mIsRequestResponseOnly = false;
    protected Priority mPriority = Priority.PRIORITY_NORMAL;
    protected boolean mIsInstanceLocked = false;
    protected boolean mIsClearText = false;
    protected boolean mIsWiFiNecessary = false;
    protected boolean mIsAnonymousCredentialsAllowed = false;
    protected ConnectionPolicy.CompressionOption mCompressionOption = ConnectionPolicy.CompressionOption.ALLOWED;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SimpleConnectionPolicy simpleConnectionPolicy = (SimpleConnectionPolicy) obj;
        if (this.mIsRoamingAllowed == simpleConnectionPolicy.mIsRoamingAllowed && this.mIsShortLived == simpleConnectionPolicy.mIsShortLived && this.mIsLowLatencyNecessary == simpleConnectionPolicy.mIsLowLatencyNecessary && this.mIsRequestResponseOnly == simpleConnectionPolicy.mIsRequestResponseOnly && this.mIsInstanceLocked == simpleConnectionPolicy.mIsInstanceLocked && this.mIsClearText == simpleConnectionPolicy.mIsClearText && this.mIsWiFiNecessary == simpleConnectionPolicy.mIsWiFiNecessary && this.mIsAnonymousCredentialsAllowed == simpleConnectionPolicy.mIsAnonymousCredentialsAllowed && this.mPriority == simpleConnectionPolicy.mPriority) {
            if (this.mCompressionOption == null && simpleConnectionPolicy.mCompressionOption == null) {
                return true;
            }
            ConnectionPolicy.CompressionOption compressionOption = this.mCompressionOption;
            if (compressionOption != null && compressionOption.equals(simpleConnectionPolicy.mCompressionOption)) {
                return true;
            }
        }
        return false;
    }

    @Override // amazon.communication.connection.ConnectionPolicy
    public ConnectionPolicy.CompressionOption getCompressionOption() {
        return this.mCompressionOption;
    }

    @Override // amazon.communication.connection.ConnectionPolicy
    public Priority getPriority() {
        return this.mPriority;
    }

    public int hashCode() {
        int hashCode = this.mCompressionOption.hashCode();
        return this.mPriority.hashCode() + ((((((((hashCode + ((((((((((JfifUtil.MARKER_EOI + (this.mIsRoamingAllowed ? 1 : 0)) * 31) + (this.mIsShortLived ? 1 : 0)) * 31) + (this.mIsLowLatencyNecessary ? 1 : 0)) * 31) + (this.mIsRequestResponseOnly ? 1 : 0)) * 31) + (this.mIsInstanceLocked ? 1 : 0)) * 31)) * 31) + (this.mIsClearText ? 1 : 0)) * 31) + (this.mIsWiFiNecessary ? 1 : 0)) * 31) + (this.mIsAnonymousCredentialsAllowed ? 1 : 0)) * 31);
    }

    @Override // amazon.communication.connection.ConnectionPolicy
    public boolean isAnonymousCredentialsAllowed() {
        return this.mIsAnonymousCredentialsAllowed;
    }

    @Override // amazon.communication.connection.ConnectionPolicy
    public boolean isClearText() {
        return this.mIsClearText;
    }

    public boolean isInstanceLocked() {
        return this.mIsInstanceLocked;
    }

    @Override // amazon.communication.connection.ConnectionPolicy
    public boolean isLowLatencyNecessary() {
        return this.mIsLowLatencyNecessary;
    }

    @Override // amazon.communication.connection.ConnectionPolicy
    public boolean isRequestResponseOnly() {
        return this.mIsRequestResponseOnly;
    }

    @Override // amazon.communication.connection.ConnectionPolicy
    public boolean isRoamingAllowed() {
        return this.mIsRoamingAllowed;
    }

    @Override // amazon.communication.connection.ConnectionPolicy
    public boolean isShortLived() {
        return this.mIsShortLived;
    }

    @Override // amazon.communication.connection.ConnectionPolicy
    public boolean isWiFiNecessary() {
        return this.mIsWiFiNecessary;
    }

    public void setCompressionOption(ConnectionPolicy.CompressionOption compressionOption) {
        this.mCompressionOption = compressionOption;
    }

    public void setIsAnonymousCredentialsAllowed(boolean z) {
        this.mIsAnonymousCredentialsAllowed = z;
    }

    public void setIsClearText(boolean z) {
        this.mIsClearText = z;
    }

    public void setIsInstanceLocked(boolean z) {
        this.mIsInstanceLocked = z;
    }

    public void setIsLowLatencyNecessary(boolean z) {
        this.mIsLowLatencyNecessary = z;
    }

    public void setIsRequestResponseOnly(boolean z) {
        this.mIsRequestResponseOnly = z;
    }

    public void setIsRoamingAllowed(boolean z) {
        this.mIsRoamingAllowed = z;
    }

    public void setIsShortLived(boolean z) {
        this.mIsShortLived = z;
    }

    public void setIsWiFiNecessary(boolean z) {
        this.mIsWiFiNecessary = z;
    }

    public void setPriority(Priority priority) {
        this.mPriority = priority;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{ Roaming: ");
        sb.append(this.mIsRoamingAllowed);
        sb.append(", ShortLived: ");
        sb.append(this.mIsShortLived);
        sb.append(", LowLatency: ");
        sb.append(this.mIsLowLatencyNecessary);
        sb.append(", RequestResponseOnly: ");
        sb.append(this.mIsRequestResponseOnly);
        sb.append(", Priority: ");
        sb.append(this.mPriority);
        sb.append(", CompressionOption: ");
        sb.append(this.mCompressionOption);
        sb.append(", IsClearText: ");
        sb.append(this.mIsClearText);
        sb.append(", IsWiFiNecessary: ");
        sb.append(this.mIsWiFiNecessary);
        sb.append(", IsAnonymousCredentialsAllowed: ");
        return GeneratedOutlineSupport1.outline97(sb, this.mIsAnonymousCredentialsAllowed, " }");
    }
}
