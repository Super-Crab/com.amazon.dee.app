package com.amazon.comms.calling.service;

import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import java.util.Objects;
/* loaded from: classes11.dex */
public class VideoConstraints implements Comparable<VideoConstraints> {
    private int videoFps;
    private int videoHeight;
    private int videoWidth;

    public VideoConstraints(int i, int i2, int i3) {
        this.videoWidth = i;
        this.videoHeight = i2;
        this.videoFps = i3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VideoConstraints)) {
            return false;
        }
        VideoConstraints videoConstraints = (VideoConstraints) obj;
        return this.videoWidth == videoConstraints.getVideoWidth() && this.videoHeight == videoConstraints.getVideoHeight() && this.videoFps == videoConstraints.getVideoFps();
    }

    public int getVideoFps() {
        return this.videoFps;
    }

    public int getVideoHeight() {
        return this.videoHeight;
    }

    public int getVideoWidth() {
        return this.videoWidth;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.videoHeight), Integer.valueOf(this.videoWidth), Integer.valueOf(this.videoFps));
    }

    public String toString() {
        return this.videoWidth + ReactProperties.HereMapMarker.X + this.videoHeight + "@" + this.videoFps;
    }

    @Override // java.lang.Comparable
    public int compareTo(VideoConstraints videoConstraints) {
        if (videoConstraints != null) {
            if (videoConstraints == this) {
                return 0;
            }
            int i = this.videoHeight * this.videoWidth * this.videoFps;
            int videoHeight = videoConstraints.getVideoHeight();
            return i - (videoConstraints.getVideoFps() * (videoConstraints.getVideoWidth() * videoHeight));
        }
        throw new NullPointerException("compareTo requires a valid object");
    }
}
