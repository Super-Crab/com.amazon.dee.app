package com.amazon.tarazed.core.webrtc;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: VideoParameters.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J;\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u001b"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/VideoParameters;", "", "isHardwareVideoEncodingEnabled", "", "maxVideoDimension", "", "minVideoDimensions", "maxVideoBitrateKbps", "videoFramerate", "(ZIIII)V", "()Z", "getMaxVideoBitrateKbps", "()I", "getMaxVideoDimension", "getMinVideoDimensions", "getVideoFramerate", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class VideoParameters {
    private final boolean isHardwareVideoEncodingEnabled;
    private final int maxVideoBitrateKbps;
    private final int maxVideoDimension;
    private final int minVideoDimensions;
    private final int videoFramerate;

    public VideoParameters(boolean z, int i, int i2, int i3, int i4) {
        this.isHardwareVideoEncodingEnabled = z;
        this.maxVideoDimension = i;
        this.minVideoDimensions = i2;
        this.maxVideoBitrateKbps = i3;
        this.videoFramerate = i4;
    }

    public static /* synthetic */ VideoParameters copy$default(VideoParameters videoParameters, boolean z, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            z = videoParameters.isHardwareVideoEncodingEnabled;
        }
        if ((i5 & 2) != 0) {
            i = videoParameters.maxVideoDimension;
        }
        int i6 = i;
        if ((i5 & 4) != 0) {
            i2 = videoParameters.minVideoDimensions;
        }
        int i7 = i2;
        if ((i5 & 8) != 0) {
            i3 = videoParameters.maxVideoBitrateKbps;
        }
        int i8 = i3;
        if ((i5 & 16) != 0) {
            i4 = videoParameters.videoFramerate;
        }
        return videoParameters.copy(z, i6, i7, i8, i4);
    }

    public final boolean component1() {
        return this.isHardwareVideoEncodingEnabled;
    }

    public final int component2() {
        return this.maxVideoDimension;
    }

    public final int component3() {
        return this.minVideoDimensions;
    }

    public final int component4() {
        return this.maxVideoBitrateKbps;
    }

    public final int component5() {
        return this.videoFramerate;
    }

    @NotNull
    public final VideoParameters copy(boolean z, int i, int i2, int i3, int i4) {
        return new VideoParameters(z, i, i2, i3, i4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof VideoParameters)) {
                return false;
            }
            VideoParameters videoParameters = (VideoParameters) obj;
            return this.isHardwareVideoEncodingEnabled == videoParameters.isHardwareVideoEncodingEnabled && this.maxVideoDimension == videoParameters.maxVideoDimension && this.minVideoDimensions == videoParameters.minVideoDimensions && this.maxVideoBitrateKbps == videoParameters.maxVideoBitrateKbps && this.videoFramerate == videoParameters.videoFramerate;
        }
        return true;
    }

    public final int getMaxVideoBitrateKbps() {
        return this.maxVideoBitrateKbps;
    }

    public final int getMaxVideoDimension() {
        return this.maxVideoDimension;
    }

    public final int getMinVideoDimensions() {
        return this.minVideoDimensions;
    }

    public final int getVideoFramerate() {
        return this.videoFramerate;
    }

    public int hashCode() {
        boolean z = this.isHardwareVideoEncodingEnabled;
        if (z) {
            z = true;
        }
        int i = z ? 1 : 0;
        int i2 = z ? 1 : 0;
        int hashCode = Integer.hashCode(this.maxVideoDimension);
        int hashCode2 = Integer.hashCode(this.minVideoDimensions);
        int hashCode3 = Integer.hashCode(this.maxVideoBitrateKbps);
        return Integer.hashCode(this.videoFramerate) + ((hashCode3 + ((hashCode2 + ((hashCode + (i * 31)) * 31)) * 31)) * 31);
    }

    public final boolean isHardwareVideoEncodingEnabled() {
        return this.isHardwareVideoEncodingEnabled;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VideoParameters(isHardwareVideoEncodingEnabled=");
        outline107.append(this.isHardwareVideoEncodingEnabled);
        outline107.append(", maxVideoDimension=");
        outline107.append(this.maxVideoDimension);
        outline107.append(", minVideoDimensions=");
        outline107.append(this.minVideoDimensions);
        outline107.append(", maxVideoBitrateKbps=");
        outline107.append(this.maxVideoBitrateKbps);
        outline107.append(", videoFramerate=");
        return GeneratedOutlineSupport1.outline86(outline107, this.videoFramerate, ")");
    }
}
