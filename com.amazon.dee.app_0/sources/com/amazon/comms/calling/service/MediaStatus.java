package com.amazon.comms.calling.service;
/* loaded from: classes11.dex */
public class MediaStatus {
    private final boolean localAudioCapable;
    private final boolean localAudioEnabled;
    private final boolean localRealTimeTextEnabled;
    private final boolean localVideoCapable;
    private final boolean localVideoEnabled;
    private final boolean realTimeTextRequested;
    private final boolean remoteAudioCapable;
    private final boolean remoteAudioEnabled;
    private final boolean remoteAudioRequested;
    private final boolean remoteRealTimeTextEnabled;
    private final boolean remoteVideoCapable;
    private final boolean remoteVideoEnabled;
    private final boolean remoteVideoRequested;

    public MediaStatus(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13) {
        this.localAudioCapable = z;
        this.localVideoCapable = z2;
        this.remoteAudioCapable = z3;
        this.remoteVideoCapable = z4;
        this.localAudioEnabled = z5;
        this.localVideoEnabled = z6;
        this.remoteAudioEnabled = z7;
        this.remoteVideoEnabled = z8;
        this.remoteAudioRequested = z9;
        this.remoteVideoRequested = z10;
        this.localRealTimeTextEnabled = z11;
        this.remoteRealTimeTextEnabled = z12;
        this.realTimeTextRequested = z13;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof MediaStatus;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaStatus)) {
            return false;
        }
        MediaStatus mediaStatus = (MediaStatus) obj;
        return mediaStatus.canEqual(this) && isLocalAudioCapable() == mediaStatus.isLocalAudioCapable() && isLocalVideoCapable() == mediaStatus.isLocalVideoCapable() && isRemoteAudioCapable() == mediaStatus.isRemoteAudioCapable() && isRemoteVideoCapable() == mediaStatus.isRemoteVideoCapable() && isLocalAudioEnabled() == mediaStatus.isLocalAudioEnabled() && isLocalVideoEnabled() == mediaStatus.isLocalVideoEnabled() && isRemoteAudioEnabled() == mediaStatus.isRemoteAudioEnabled() && isRemoteVideoEnabled() == mediaStatus.isRemoteVideoEnabled() && isRemoteAudioRequested() == mediaStatus.isRemoteAudioRequested() && isRemoteVideoRequested() == mediaStatus.isRemoteVideoRequested() && isLocalRealTimeTextEnabled() == mediaStatus.isLocalRealTimeTextEnabled() && isRemoteRealTimeTextEnabled() == mediaStatus.isRemoteRealTimeTextEnabled() && isRealTimeTextRequested() == mediaStatus.isRealTimeTextRequested();
    }

    public int hashCode() {
        int i = 79;
        int i2 = ((((((((((((((((((((((((isLocalAudioCapable() ? 79 : 97) + 59) * 59) + (isLocalVideoCapable() ? 79 : 97)) * 59) + (isRemoteAudioCapable() ? 79 : 97)) * 59) + (isRemoteVideoCapable() ? 79 : 97)) * 59) + (isLocalAudioEnabled() ? 79 : 97)) * 59) + (isLocalVideoEnabled() ? 79 : 97)) * 59) + (isRemoteAudioEnabled() ? 79 : 97)) * 59) + (isRemoteVideoEnabled() ? 79 : 97)) * 59) + (isRemoteAudioRequested() ? 79 : 97)) * 59) + (isRemoteVideoRequested() ? 79 : 97)) * 59) + (isLocalRealTimeTextEnabled() ? 79 : 97)) * 59) + (isRemoteRealTimeTextEnabled() ? 79 : 97)) * 59;
        if (!isRealTimeTextRequested()) {
            i = 97;
        }
        return i2 + i;
    }

    public boolean isLocalAudioCapable() {
        return this.localAudioCapable;
    }

    public boolean isLocalAudioEnabled() {
        return this.localAudioEnabled;
    }

    public boolean isLocalRealTimeTextEnabled() {
        return this.localRealTimeTextEnabled;
    }

    public boolean isLocalVideoCapable() {
        return this.localVideoCapable;
    }

    public boolean isLocalVideoEnabled() {
        return this.localVideoEnabled;
    }

    public boolean isRealTimeTextRequested() {
        return this.realTimeTextRequested;
    }

    public boolean isRemoteAudioCapable() {
        return this.remoteAudioCapable;
    }

    public boolean isRemoteAudioEnabled() {
        return this.remoteAudioEnabled;
    }

    public boolean isRemoteAudioRequested() {
        return this.remoteAudioRequested;
    }

    public boolean isRemoteRealTimeTextEnabled() {
        return this.remoteRealTimeTextEnabled;
    }

    public boolean isRemoteVideoCapable() {
        return this.remoteVideoCapable;
    }

    public boolean isRemoteVideoEnabled() {
        return this.remoteVideoEnabled;
    }

    public boolean isRemoteVideoRequested() {
        return this.remoteVideoRequested;
    }

    public String toString() {
        return String.format("Capabilities: {local:[%s, %s], remote:[%s, %s]}, State: {local:[%s, %s], remote:[%s, %s]}, Requested: {remote:[%s, %s]}, RealTimeText Enabled: {local: [%s], remote: [%s]}, RealTimeText Requested: {[%s]}", Boolean.valueOf(this.localAudioCapable), Boolean.valueOf(this.localVideoCapable), Boolean.valueOf(this.remoteAudioCapable), Boolean.valueOf(this.remoteVideoCapable), Boolean.valueOf(this.localAudioEnabled), Boolean.valueOf(this.localVideoEnabled), Boolean.valueOf(this.remoteAudioEnabled), Boolean.valueOf(this.remoteVideoEnabled), Boolean.valueOf(this.remoteAudioRequested), Boolean.valueOf(this.remoteVideoRequested), Boolean.valueOf(this.localRealTimeTextEnabled), Boolean.valueOf(this.remoteRealTimeTextEnabled), Boolean.valueOf(this.realTimeTextRequested));
    }
}
