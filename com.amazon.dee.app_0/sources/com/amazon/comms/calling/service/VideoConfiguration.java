package com.amazon.comms.calling.service;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class VideoConfiguration {
    boolean camera1APIPreferred;
    boolean camera2APIForced;
    boolean captureToTexture;
    boolean ignoreCameraEvictionError;
    boolean overrideRemoteVideoMaxBitrate;
    boolean provideCallingServiceHalParameter;
    boolean simulateFirstFrameReceived;
    boolean updateCameraHalFramerateAllowed;
    int videoFps;
    int videoHeight;
    int videoMaxBitrate;
    int videoStartBitrate;
    int videoWidth;

    /* loaded from: classes11.dex */
    public static class VideoConfigurationBuilder {
        private boolean camera1APIPreferred;
        private boolean camera2APIForced;
        private boolean captureToTexture;
        private boolean ignoreCameraEvictionError;
        private boolean overrideRemoteVideoMaxBitrate;
        private boolean provideCallingServiceHalParameter;
        private boolean simulateFirstFrameReceived;
        private boolean updateCameraHalFramerateAllowed;
        private int videoFps;
        private int videoHeight;
        private int videoMaxBitrate;
        private int videoStartBitrate;
        private int videoWidth;

        VideoConfigurationBuilder() {
        }

        public VideoConfiguration build() {
            return new VideoConfiguration(this.videoHeight, this.videoWidth, this.videoFps, this.captureToTexture, this.camera1APIPreferred, this.camera2APIForced, this.updateCameraHalFramerateAllowed, this.provideCallingServiceHalParameter, this.videoMaxBitrate, this.simulateFirstFrameReceived, this.ignoreCameraEvictionError, this.videoStartBitrate, this.overrideRemoteVideoMaxBitrate);
        }

        public VideoConfigurationBuilder camera1APIPreferred(boolean z) {
            this.camera1APIPreferred = z;
            return this;
        }

        public VideoConfigurationBuilder camera2APIForced(boolean z) {
            this.camera2APIForced = z;
            return this;
        }

        public VideoConfigurationBuilder captureToTexture(boolean z) {
            this.captureToTexture = z;
            return this;
        }

        public VideoConfigurationBuilder ignoreCameraEvictionError(boolean z) {
            this.ignoreCameraEvictionError = z;
            return this;
        }

        public VideoConfigurationBuilder overrideRemoteVideoMaxBitrate(boolean z) {
            this.overrideRemoteVideoMaxBitrate = z;
            return this;
        }

        public VideoConfigurationBuilder provideCallingServiceHalParameter(boolean z) {
            this.provideCallingServiceHalParameter = z;
            return this;
        }

        public VideoConfigurationBuilder simulateFirstFrameReceived(boolean z) {
            this.simulateFirstFrameReceived = z;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VideoConfiguration.VideoConfigurationBuilder(videoHeight=");
            outline107.append(this.videoHeight);
            outline107.append(", videoWidth=");
            outline107.append(this.videoWidth);
            outline107.append(", videoFps=");
            outline107.append(this.videoFps);
            outline107.append(", captureToTexture=");
            outline107.append(this.captureToTexture);
            outline107.append(", camera1APIPreferred=");
            outline107.append(this.camera1APIPreferred);
            outline107.append(", camera2APIForced=");
            outline107.append(this.camera2APIForced);
            outline107.append(", updateCameraHalFramerateAllowed=");
            outline107.append(this.updateCameraHalFramerateAllowed);
            outline107.append(", provideCallingServiceHalParameter=");
            outline107.append(this.provideCallingServiceHalParameter);
            outline107.append(", videoMaxBitrate=");
            outline107.append(this.videoMaxBitrate);
            outline107.append(", simulateFirstFrameReceived=");
            outline107.append(this.simulateFirstFrameReceived);
            outline107.append(", ignoreCameraEvictionError=");
            outline107.append(this.ignoreCameraEvictionError);
            outline107.append(", videoStartBitrate=");
            outline107.append(this.videoStartBitrate);
            outline107.append(", overrideRemoteVideoMaxBitrate=");
            return GeneratedOutlineSupport1.outline97(outline107, this.overrideRemoteVideoMaxBitrate, ")");
        }

        public VideoConfigurationBuilder updateCameraHalFramerateAllowed(boolean z) {
            this.updateCameraHalFramerateAllowed = z;
            return this;
        }

        public VideoConfigurationBuilder videoFps(int i) {
            this.videoFps = i;
            return this;
        }

        public VideoConfigurationBuilder videoHeight(int i) {
            this.videoHeight = i;
            return this;
        }

        public VideoConfigurationBuilder videoMaxBitrate(int i) {
            this.videoMaxBitrate = i;
            return this;
        }

        public VideoConfigurationBuilder videoStartBitrate(int i) {
            this.videoStartBitrate = i;
            return this;
        }

        public VideoConfigurationBuilder videoWidth(int i) {
            this.videoWidth = i;
            return this;
        }
    }

    VideoConfiguration(int i, int i2, int i3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i4, boolean z6, boolean z7, int i5, boolean z8) {
        this.videoHeight = i;
        this.videoWidth = i2;
        this.videoFps = i3;
        this.captureToTexture = z;
        this.camera1APIPreferred = z2;
        this.camera2APIForced = z3;
        this.updateCameraHalFramerateAllowed = z4;
        this.provideCallingServiceHalParameter = z5;
        this.videoMaxBitrate = i4;
        this.simulateFirstFrameReceived = z6;
        this.ignoreCameraEvictionError = z7;
        this.videoStartBitrate = i5;
        this.overrideRemoteVideoMaxBitrate = z8;
    }

    public static VideoConfigurationBuilder builder() {
        return new VideoConfigurationBuilder();
    }

    public int getVideoFps() {
        return this.videoFps;
    }

    public int getVideoHeight() {
        return this.videoHeight;
    }

    public int getVideoMaxBitrate() {
        return this.videoMaxBitrate;
    }

    public int getVideoStartBitrate() {
        return this.videoStartBitrate;
    }

    public int getVideoWidth() {
        return this.videoWidth;
    }

    public boolean isCamera1APIPreferred() {
        return this.camera1APIPreferred;
    }

    public boolean isCamera2APIForced() {
        return this.camera2APIForced;
    }

    public boolean isCaptureToTexture() {
        return this.captureToTexture;
    }

    public boolean isIgnoreCameraEvictionError() {
        return this.ignoreCameraEvictionError;
    }

    public boolean isOverrideRemoteVideoMaxBitrate() {
        return this.overrideRemoteVideoMaxBitrate;
    }

    public boolean isProvideCallingServiceHalParameter() {
        return this.provideCallingServiceHalParameter;
    }

    public boolean isSimulateFirstFrameReceived() {
        return this.simulateFirstFrameReceived;
    }

    public boolean isUpdateCameraHalFramerateAllowed() {
        return this.updateCameraHalFramerateAllowed;
    }
}
