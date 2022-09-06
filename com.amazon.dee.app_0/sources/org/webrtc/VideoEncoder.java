package org.webrtc;

import org.webrtc.EncodedImage;
/* loaded from: classes5.dex */
public interface VideoEncoder {

    /* loaded from: classes5.dex */
    public static class BitrateAllocation {
        public final int[][] bitratesBbs;

        public BitrateAllocation(int[][] iArr) {
            this.bitratesBbs = iArr;
        }

        public int getSum() {
            int[][] iArr = this.bitratesBbs;
            int length = iArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2;
                for (int i4 : iArr[i]) {
                    i3 += i4;
                }
                i++;
                i2 = i3;
            }
            return i2;
        }
    }

    /* loaded from: classes5.dex */
    public interface Callback {
        void onEncodedFrame(EncodedImage encodedImage, CodecSpecificInfo codecSpecificInfo);
    }

    /* loaded from: classes5.dex */
    public static class CodecSpecificInfo {
    }

    /* loaded from: classes5.dex */
    public static class CodecSpecificInfoH264 extends CodecSpecificInfo {
    }

    /* loaded from: classes5.dex */
    public static class CodecSpecificInfoVP8 extends CodecSpecificInfo {
    }

    /* loaded from: classes5.dex */
    public static class CodecSpecificInfoVP9 extends CodecSpecificInfo {
    }

    /* loaded from: classes5.dex */
    public static class EncodeInfo {
        public final EncodedImage.FrameType[] frameTypes;

        public EncodeInfo(EncodedImage.FrameType[] frameTypeArr) {
            this.frameTypes = frameTypeArr;
        }
    }

    /* loaded from: classes5.dex */
    public static class Settings {
        public final boolean automaticResizeOn;
        public final int height;
        public final int maxFramerate;
        public final int numberOfCores;
        public final int startBitrate;
        public final int width;

        public Settings(int i, int i2, int i3, int i4, int i5, boolean z) {
            this.numberOfCores = i;
            this.width = i2;
            this.height = i3;
            this.startBitrate = i4;
            this.maxFramerate = i5;
            this.automaticResizeOn = z;
        }
    }

    VideoCodecStatus encode(VideoFrame videoFrame, EncodeInfo encodeInfo);

    String getImplementationName();

    ScalingSettings getScalingSettings();

    VideoCodecStatus initEncode(Settings settings, Callback callback);

    VideoCodecStatus release();

    VideoCodecStatus setChannelParameters(short s, long j);

    VideoCodecStatus setRateAllocation(BitrateAllocation bitrateAllocation, int i);

    /* loaded from: classes5.dex */
    public static class ScalingSettings {
        public final Integer high;
        public final Integer low;
        public final boolean on;

        public ScalingSettings(boolean z) {
            this.on = z;
            this.low = null;
            this.high = null;
        }

        public ScalingSettings(boolean z, int i, int i2) {
            this.on = z;
            this.low = Integer.valueOf(i);
            this.high = Integer.valueOf(i2);
        }
    }
}
