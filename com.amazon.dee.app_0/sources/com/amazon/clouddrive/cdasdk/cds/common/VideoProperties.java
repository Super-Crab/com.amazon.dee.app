package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class VideoProperties {
    @JsonProperty("audioBitrate")
    private Double audioBitrate;
    @JsonProperty("audioChannelLayout")
    private String audioChannelLayout;
    @JsonProperty("audioChannels")
    private Integer audioChannels;
    @JsonProperty("audioCodec")
    private String audioCodec;
    @JsonProperty("audioSampleRate")
    private Double audioSampleRate;
    @JsonProperty("bitrate")
    private Double bitrate;
    @JsonProperty("creationDate")
    private String creationDate;
    @JsonProperty("duration")
    private Double duration;
    @JsonProperty("encoder")
    private String encoder;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("location")
    private String location;
    @JsonProperty("make")
    private String make;
    @JsonProperty("model")
    private String model;
    @JsonProperty("rotate")
    private Integer rotate;
    @JsonProperty("title")
    private String title;
    @JsonProperty("videoBitrate")
    private Double videoBitrate;
    @JsonProperty("videoCodec")
    private String videoCodec;
    @JsonProperty("videoFrameRate")
    private Double videoFrameRate;
    @JsonProperty("width")
    private Integer width;

    protected boolean canEqual(Object obj) {
        return obj instanceof VideoProperties;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VideoProperties)) {
            return false;
        }
        VideoProperties videoProperties = (VideoProperties) obj;
        if (!videoProperties.canEqual(this)) {
            return false;
        }
        Integer height = getHeight();
        Integer height2 = videoProperties.getHeight();
        if (height != null ? !height.equals(height2) : height2 != null) {
            return false;
        }
        Integer width = getWidth();
        Integer width2 = videoProperties.getWidth();
        if (width != null ? !width.equals(width2) : width2 != null) {
            return false;
        }
        String creationDate = getCreationDate();
        String creationDate2 = videoProperties.getCreationDate();
        if (creationDate != null ? !creationDate.equals(creationDate2) : creationDate2 != null) {
            return false;
        }
        Double duration = getDuration();
        Double duration2 = videoProperties.getDuration();
        if (duration != null ? !duration.equals(duration2) : duration2 != null) {
            return false;
        }
        String videoCodec = getVideoCodec();
        String videoCodec2 = videoProperties.getVideoCodec();
        if (videoCodec != null ? !videoCodec.equals(videoCodec2) : videoCodec2 != null) {
            return false;
        }
        Double videoBitrate = getVideoBitrate();
        Double videoBitrate2 = videoProperties.getVideoBitrate();
        if (videoBitrate != null ? !videoBitrate.equals(videoBitrate2) : videoBitrate2 != null) {
            return false;
        }
        Double videoFrameRate = getVideoFrameRate();
        Double videoFrameRate2 = videoProperties.getVideoFrameRate();
        if (videoFrameRate != null ? !videoFrameRate.equals(videoFrameRate2) : videoFrameRate2 != null) {
            return false;
        }
        String audioCodec = getAudioCodec();
        String audioCodec2 = videoProperties.getAudioCodec();
        if (audioCodec != null ? !audioCodec.equals(audioCodec2) : audioCodec2 != null) {
            return false;
        }
        Double audioBitrate = getAudioBitrate();
        Double audioBitrate2 = videoProperties.getAudioBitrate();
        if (audioBitrate != null ? !audioBitrate.equals(audioBitrate2) : audioBitrate2 != null) {
            return false;
        }
        Double audioSampleRate = getAudioSampleRate();
        Double audioSampleRate2 = videoProperties.getAudioSampleRate();
        if (audioSampleRate != null ? !audioSampleRate.equals(audioSampleRate2) : audioSampleRate2 != null) {
            return false;
        }
        Integer audioChannels = getAudioChannels();
        Integer audioChannels2 = videoProperties.getAudioChannels();
        if (audioChannels != null ? !audioChannels.equals(audioChannels2) : audioChannels2 != null) {
            return false;
        }
        String audioChannelLayout = getAudioChannelLayout();
        String audioChannelLayout2 = videoProperties.getAudioChannelLayout();
        if (audioChannelLayout != null ? !audioChannelLayout.equals(audioChannelLayout2) : audioChannelLayout2 != null) {
            return false;
        }
        Double bitrate = getBitrate();
        Double bitrate2 = videoProperties.getBitrate();
        if (bitrate != null ? !bitrate.equals(bitrate2) : bitrate2 != null) {
            return false;
        }
        Integer rotate = getRotate();
        Integer rotate2 = videoProperties.getRotate();
        if (rotate != null ? !rotate.equals(rotate2) : rotate2 != null) {
            return false;
        }
        String location = getLocation();
        String location2 = videoProperties.getLocation();
        if (location != null ? !location.equals(location2) : location2 != null) {
            return false;
        }
        String make = getMake();
        String make2 = videoProperties.getMake();
        if (make != null ? !make.equals(make2) : make2 != null) {
            return false;
        }
        String model = getModel();
        String model2 = videoProperties.getModel();
        if (model != null ? !model.equals(model2) : model2 != null) {
            return false;
        }
        String encoder = getEncoder();
        String encoder2 = videoProperties.getEncoder();
        if (encoder != null ? !encoder.equals(encoder2) : encoder2 != null) {
            return false;
        }
        String title = getTitle();
        String title2 = videoProperties.getTitle();
        return title != null ? title.equals(title2) : title2 == null;
    }

    public Double getAudioBitrate() {
        return this.audioBitrate;
    }

    public String getAudioChannelLayout() {
        return this.audioChannelLayout;
    }

    public Integer getAudioChannels() {
        return this.audioChannels;
    }

    public String getAudioCodec() {
        return this.audioCodec;
    }

    public Double getAudioSampleRate() {
        return this.audioSampleRate;
    }

    public Double getBitrate() {
        return this.bitrate;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

    public Double getDuration() {
        return this.duration;
    }

    public String getEncoder() {
        return this.encoder;
    }

    public Integer getHeight() {
        return this.height;
    }

    public String getLocation() {
        return this.location;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public Integer getRotate() {
        return this.rotate;
    }

    public String getTitle() {
        return this.title;
    }

    public Double getVideoBitrate() {
        return this.videoBitrate;
    }

    public String getVideoCodec() {
        return this.videoCodec;
    }

    public Double getVideoFrameRate() {
        return this.videoFrameRate;
    }

    public Integer getWidth() {
        return this.width;
    }

    public int hashCode() {
        Integer height = getHeight();
        int i = 43;
        int hashCode = height == null ? 43 : height.hashCode();
        Integer width = getWidth();
        int hashCode2 = ((hashCode + 59) * 59) + (width == null ? 43 : width.hashCode());
        String creationDate = getCreationDate();
        int hashCode3 = (hashCode2 * 59) + (creationDate == null ? 43 : creationDate.hashCode());
        Double duration = getDuration();
        int hashCode4 = (hashCode3 * 59) + (duration == null ? 43 : duration.hashCode());
        String videoCodec = getVideoCodec();
        int hashCode5 = (hashCode4 * 59) + (videoCodec == null ? 43 : videoCodec.hashCode());
        Double videoBitrate = getVideoBitrate();
        int hashCode6 = (hashCode5 * 59) + (videoBitrate == null ? 43 : videoBitrate.hashCode());
        Double videoFrameRate = getVideoFrameRate();
        int hashCode7 = (hashCode6 * 59) + (videoFrameRate == null ? 43 : videoFrameRate.hashCode());
        String audioCodec = getAudioCodec();
        int hashCode8 = (hashCode7 * 59) + (audioCodec == null ? 43 : audioCodec.hashCode());
        Double audioBitrate = getAudioBitrate();
        int hashCode9 = (hashCode8 * 59) + (audioBitrate == null ? 43 : audioBitrate.hashCode());
        Double audioSampleRate = getAudioSampleRate();
        int hashCode10 = (hashCode9 * 59) + (audioSampleRate == null ? 43 : audioSampleRate.hashCode());
        Integer audioChannels = getAudioChannels();
        int hashCode11 = (hashCode10 * 59) + (audioChannels == null ? 43 : audioChannels.hashCode());
        String audioChannelLayout = getAudioChannelLayout();
        int hashCode12 = (hashCode11 * 59) + (audioChannelLayout == null ? 43 : audioChannelLayout.hashCode());
        Double bitrate = getBitrate();
        int hashCode13 = (hashCode12 * 59) + (bitrate == null ? 43 : bitrate.hashCode());
        Integer rotate = getRotate();
        int hashCode14 = (hashCode13 * 59) + (rotate == null ? 43 : rotate.hashCode());
        String location = getLocation();
        int hashCode15 = (hashCode14 * 59) + (location == null ? 43 : location.hashCode());
        String make = getMake();
        int hashCode16 = (hashCode15 * 59) + (make == null ? 43 : make.hashCode());
        String model = getModel();
        int hashCode17 = (hashCode16 * 59) + (model == null ? 43 : model.hashCode());
        String encoder = getEncoder();
        int hashCode18 = (hashCode17 * 59) + (encoder == null ? 43 : encoder.hashCode());
        String title = getTitle();
        int i2 = hashCode18 * 59;
        if (title != null) {
            i = title.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("audioBitrate")
    public void setAudioBitrate(Double d) {
        this.audioBitrate = d;
    }

    @JsonProperty("audioChannelLayout")
    public void setAudioChannelLayout(String str) {
        this.audioChannelLayout = str;
    }

    @JsonProperty("audioChannels")
    public void setAudioChannels(Integer num) {
        this.audioChannels = num;
    }

    @JsonProperty("audioCodec")
    public void setAudioCodec(String str) {
        this.audioCodec = str;
    }

    @JsonProperty("audioSampleRate")
    public void setAudioSampleRate(Double d) {
        this.audioSampleRate = d;
    }

    @JsonProperty("bitrate")
    public void setBitrate(Double d) {
        this.bitrate = d;
    }

    @JsonProperty("creationDate")
    public void setCreationDate(String str) {
        this.creationDate = str;
    }

    @JsonProperty("duration")
    public void setDuration(Double d) {
        this.duration = d;
    }

    @JsonProperty("encoder")
    public void setEncoder(String str) {
        this.encoder = str;
    }

    @JsonProperty("height")
    public void setHeight(Integer num) {
        this.height = num;
    }

    @JsonProperty("location")
    public void setLocation(String str) {
        this.location = str;
    }

    @JsonProperty("make")
    public void setMake(String str) {
        this.make = str;
    }

    @JsonProperty("model")
    public void setModel(String str) {
        this.model = str;
    }

    @JsonProperty("rotate")
    public void setRotate(Integer num) {
        this.rotate = num;
    }

    @JsonProperty("title")
    public void setTitle(String str) {
        this.title = str;
    }

    @JsonProperty("videoBitrate")
    public void setVideoBitrate(Double d) {
        this.videoBitrate = d;
    }

    @JsonProperty("videoCodec")
    public void setVideoCodec(String str) {
        this.videoCodec = str;
    }

    @JsonProperty("videoFrameRate")
    public void setVideoFrameRate(Double d) {
        this.videoFrameRate = d;
    }

    @JsonProperty("width")
    public void setWidth(Integer num) {
        this.width = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VideoProperties(height=");
        outline107.append(getHeight());
        outline107.append(", width=");
        outline107.append(getWidth());
        outline107.append(", creationDate=");
        outline107.append(getCreationDate());
        outline107.append(", duration=");
        outline107.append(getDuration());
        outline107.append(", videoCodec=");
        outline107.append(getVideoCodec());
        outline107.append(", videoBitrate=");
        outline107.append(getVideoBitrate());
        outline107.append(", videoFrameRate=");
        outline107.append(getVideoFrameRate());
        outline107.append(", audioCodec=");
        outline107.append(getAudioCodec());
        outline107.append(", audioBitrate=");
        outline107.append(getAudioBitrate());
        outline107.append(", audioSampleRate=");
        outline107.append(getAudioSampleRate());
        outline107.append(", audioChannels=");
        outline107.append(getAudioChannels());
        outline107.append(", audioChannelLayout=");
        outline107.append(getAudioChannelLayout());
        outline107.append(", bitrate=");
        outline107.append(getBitrate());
        outline107.append(", rotate=");
        outline107.append(getRotate());
        outline107.append(", location=");
        outline107.append(getLocation());
        outline107.append(", make=");
        outline107.append(getMake());
        outline107.append(", model=");
        outline107.append(getModel());
        outline107.append(", encoder=");
        outline107.append(getEncoder());
        outline107.append(", title=");
        outline107.append(getTitle());
        outline107.append(")");
        return outline107.toString();
    }
}
