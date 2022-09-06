package com.amazon.comms.calling.service;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class AudioConfig {
    private int defaultOrOverridenAudioCaptureSampleRateHz;
    private int defaultOrOverridenAudioRenderSampleRateHz;
    private final FastRenderPath fastRenderPath;
    private final boolean isAudioCaptureSampleRateOverriden;
    private final boolean isAudioRenderSampleRateOverriden;
    private int renderTrackBufferSizeBytes;

    /* loaded from: classes11.dex */
    public enum FastRenderPath {
        NONE,
        AUDIOTRACK,
        OPENSLES
    }

    public AudioConfig(boolean z, int i, boolean z2, int i2, FastRenderPath fastRenderPath, int i3) {
        this.isAudioCaptureSampleRateOverriden = z;
        this.defaultOrOverridenAudioCaptureSampleRateHz = i;
        this.isAudioRenderSampleRateOverriden = z2;
        this.defaultOrOverridenAudioRenderSampleRateHz = i2;
        this.fastRenderPath = fastRenderPath;
        this.renderTrackBufferSizeBytes = i3;
    }

    public int getDefaultOrOverridenAudioCaptureSampleRateHz() {
        return this.defaultOrOverridenAudioCaptureSampleRateHz;
    }

    public int getDefaultOrOverridenAudioRenderSampleRateHz() {
        return this.defaultOrOverridenAudioRenderSampleRateHz;
    }

    public FastRenderPath getFastRenderPath() {
        return this.fastRenderPath;
    }

    public int getRenderTrackBufferSizeBytes() {
        return this.renderTrackBufferSizeBytes;
    }

    public boolean isAudioCaptureSampleRateOverriden() {
        return this.isAudioCaptureSampleRateOverriden;
    }

    public boolean isAudioRenderSampleRateOverriden() {
        return this.isAudioRenderSampleRateOverriden;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AudioConfig(isAudioCaptureSampleRateOverriden=");
        outline107.append(isAudioCaptureSampleRateOverriden());
        outline107.append(", defaultOrOverridenAudioCaptureSampleRateHz=");
        outline107.append(getDefaultOrOverridenAudioCaptureSampleRateHz());
        outline107.append(", isAudioRenderSampleRateOverriden=");
        outline107.append(isAudioRenderSampleRateOverriden());
        outline107.append(", defaultOrOverridenAudioRenderSampleRateHz=");
        outline107.append(getDefaultOrOverridenAudioRenderSampleRateHz());
        outline107.append(", fastRenderPath=");
        outline107.append(getFastRenderPath());
        outline107.append(", renderTrackBufferSizeBytes=");
        outline107.append(getRenderTrackBufferSizeBytes());
        outline107.append(")");
        return outline107.toString();
    }
}
