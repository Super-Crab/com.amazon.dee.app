package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class VideoProperties implements Comparable<VideoProperties> {
    private Double audioBitrate;
    private String audioChannelLayout;
    private Integer audioChannels;
    private String audioCodec;
    private Double audioSampleRate;
    private Double bitrate;
    private String creationDate;
    private Double duration;
    private String encoder;
    private Integer height;
    private String location;
    private String make;
    private String model;
    private Integer rotate;
    private String title;
    private Double videoBitrate;
    private String videoCodec;
    private Double videoFrameRate;
    private Integer width;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof VideoProperties) && compareTo((VideoProperties) obj) == 0;
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
        int i = 0;
        int hashCode = (getTitle() == null ? 0 : getTitle().hashCode()) + 1 + (getBitrate() == null ? 0 : getBitrate().hashCode()) + (getVideoFrameRate() == null ? 0 : getVideoFrameRate().hashCode()) + (getCreationDate() == null ? 0 : getCreationDate().hashCode()) + (getModel() == null ? 0 : getModel().hashCode()) + (getVideoBitrate() == null ? 0 : getVideoBitrate().hashCode()) + (getAudioCodec() == null ? 0 : getAudioCodec().hashCode()) + (getRotate() == null ? 0 : getRotate().hashCode()) + (getDuration() == null ? 0 : getDuration().hashCode()) + (getEncoder() == null ? 0 : getEncoder().hashCode()) + (getLocation() == null ? 0 : getLocation().hashCode()) + (getAudioBitrate() == null ? 0 : getAudioBitrate().hashCode()) + (getAudioSampleRate() == null ? 0 : getAudioSampleRate().hashCode()) + (getMake() == null ? 0 : getMake().hashCode()) + (getVideoCodec() == null ? 0 : getVideoCodec().hashCode()) + (getHeight() == null ? 0 : getHeight().hashCode()) + (getAudioChannels() == null ? 0 : getAudioChannels().hashCode()) + (getWidth() == null ? 0 : getWidth().hashCode());
        if (getAudioChannelLayout() != null) {
            i = getAudioChannelLayout().hashCode();
        }
        return hashCode + i;
    }

    public void setAudioBitrate(Double d) {
        this.audioBitrate = d;
    }

    public void setAudioChannelLayout(String str) {
        this.audioChannelLayout = str;
    }

    public void setAudioChannels(Integer num) {
        this.audioChannels = num;
    }

    public void setAudioCodec(String str) {
        this.audioCodec = str;
    }

    public void setAudioSampleRate(Double d) {
        this.audioSampleRate = d;
    }

    public void setBitrate(Double d) {
        this.bitrate = d;
    }

    public void setCreationDate(String str) {
        this.creationDate = str;
    }

    public void setDuration(Double d) {
        this.duration = d;
    }

    public void setEncoder(String str) {
        this.encoder = str;
    }

    public void setHeight(Integer num) {
        this.height = num;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setMake(String str) {
        this.make = str;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setRotate(Integer num) {
        this.rotate = num;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setVideoBitrate(Double d) {
        this.videoBitrate = d;
    }

    public void setVideoCodec(String str) {
        this.videoCodec = str;
    }

    public void setVideoFrameRate(Double d) {
        this.videoFrameRate = d;
    }

    public void setWidth(Integer num) {
        this.width = num;
    }

    @Override // java.lang.Comparable
    public int compareTo(VideoProperties videoProperties) {
        if (videoProperties == null) {
            return -1;
        }
        if (videoProperties == this) {
            return 0;
        }
        String title = getTitle();
        String title2 = videoProperties.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int compareTo = title.compareTo(title2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        Double bitrate = getBitrate();
        Double bitrate2 = videoProperties.getBitrate();
        if (bitrate != bitrate2) {
            if (bitrate == null) {
                return -1;
            }
            if (bitrate2 == null) {
                return 1;
            }
            int compareTo2 = bitrate.compareTo(bitrate2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        Double videoFrameRate = getVideoFrameRate();
        Double videoFrameRate2 = videoProperties.getVideoFrameRate();
        if (videoFrameRate != videoFrameRate2) {
            if (videoFrameRate == null) {
                return -1;
            }
            if (videoFrameRate2 == null) {
                return 1;
            }
            int compareTo3 = videoFrameRate.compareTo(videoFrameRate2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        String creationDate = getCreationDate();
        String creationDate2 = videoProperties.getCreationDate();
        if (creationDate != creationDate2) {
            if (creationDate == null) {
                return -1;
            }
            if (creationDate2 == null) {
                return 1;
            }
            int compareTo4 = creationDate.compareTo(creationDate2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        String model = getModel();
        String model2 = videoProperties.getModel();
        if (model != model2) {
            if (model == null) {
                return -1;
            }
            if (model2 == null) {
                return 1;
            }
            int compareTo5 = model.compareTo(model2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        Double videoBitrate = getVideoBitrate();
        Double videoBitrate2 = videoProperties.getVideoBitrate();
        if (videoBitrate != videoBitrate2) {
            if (videoBitrate == null) {
                return -1;
            }
            if (videoBitrate2 == null) {
                return 1;
            }
            int compareTo6 = videoBitrate.compareTo(videoBitrate2);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        String audioCodec = getAudioCodec();
        String audioCodec2 = videoProperties.getAudioCodec();
        if (audioCodec != audioCodec2) {
            if (audioCodec == null) {
                return -1;
            }
            if (audioCodec2 == null) {
                return 1;
            }
            int compareTo7 = audioCodec.compareTo(audioCodec2);
            if (compareTo7 != 0) {
                return compareTo7;
            }
        }
        Integer rotate = getRotate();
        Integer rotate2 = videoProperties.getRotate();
        if (rotate != rotate2) {
            if (rotate == null) {
                return -1;
            }
            if (rotate2 == null) {
                return 1;
            }
            int compareTo8 = rotate.compareTo(rotate2);
            if (compareTo8 != 0) {
                return compareTo8;
            }
        }
        Double duration = getDuration();
        Double duration2 = videoProperties.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int compareTo9 = duration.compareTo(duration2);
            if (compareTo9 != 0) {
                return compareTo9;
            }
        }
        String encoder = getEncoder();
        String encoder2 = videoProperties.getEncoder();
        if (encoder != encoder2) {
            if (encoder == null) {
                return -1;
            }
            if (encoder2 == null) {
                return 1;
            }
            int compareTo10 = encoder.compareTo(encoder2);
            if (compareTo10 != 0) {
                return compareTo10;
            }
        }
        String location = getLocation();
        String location2 = videoProperties.getLocation();
        if (location != location2) {
            if (location == null) {
                return -1;
            }
            if (location2 == null) {
                return 1;
            }
            int compareTo11 = location.compareTo(location2);
            if (compareTo11 != 0) {
                return compareTo11;
            }
        }
        Double audioBitrate = getAudioBitrate();
        Double audioBitrate2 = videoProperties.getAudioBitrate();
        if (audioBitrate != audioBitrate2) {
            if (audioBitrate == null) {
                return -1;
            }
            if (audioBitrate2 == null) {
                return 1;
            }
            int compareTo12 = audioBitrate.compareTo(audioBitrate2);
            if (compareTo12 != 0) {
                return compareTo12;
            }
        }
        Double audioSampleRate = getAudioSampleRate();
        Double audioSampleRate2 = videoProperties.getAudioSampleRate();
        if (audioSampleRate != audioSampleRate2) {
            if (audioSampleRate == null) {
                return -1;
            }
            if (audioSampleRate2 == null) {
                return 1;
            }
            int compareTo13 = audioSampleRate.compareTo(audioSampleRate2);
            if (compareTo13 != 0) {
                return compareTo13;
            }
        }
        String make = getMake();
        String make2 = videoProperties.getMake();
        if (make != make2) {
            if (make == null) {
                return -1;
            }
            if (make2 == null) {
                return 1;
            }
            int compareTo14 = make.compareTo(make2);
            if (compareTo14 != 0) {
                return compareTo14;
            }
        }
        String videoCodec = getVideoCodec();
        String videoCodec2 = videoProperties.getVideoCodec();
        if (videoCodec != videoCodec2) {
            if (videoCodec == null) {
                return -1;
            }
            if (videoCodec2 == null) {
                return 1;
            }
            int compareTo15 = videoCodec.compareTo(videoCodec2);
            if (compareTo15 != 0) {
                return compareTo15;
            }
        }
        Integer height = getHeight();
        Integer height2 = videoProperties.getHeight();
        if (height != height2) {
            if (height == null) {
                return -1;
            }
            if (height2 == null) {
                return 1;
            }
            int compareTo16 = height.compareTo(height2);
            if (compareTo16 != 0) {
                return compareTo16;
            }
        }
        Integer audioChannels = getAudioChannels();
        Integer audioChannels2 = videoProperties.getAudioChannels();
        if (audioChannels != audioChannels2) {
            if (audioChannels == null) {
                return -1;
            }
            if (audioChannels2 == null) {
                return 1;
            }
            int compareTo17 = audioChannels.compareTo(audioChannels2);
            if (compareTo17 != 0) {
                return compareTo17;
            }
        }
        Integer width = getWidth();
        Integer width2 = videoProperties.getWidth();
        if (width != width2) {
            if (width == null) {
                return -1;
            }
            if (width2 == null) {
                return 1;
            }
            int compareTo18 = width.compareTo(width2);
            if (compareTo18 != 0) {
                return compareTo18;
            }
        }
        String audioChannelLayout = getAudioChannelLayout();
        String audioChannelLayout2 = videoProperties.getAudioChannelLayout();
        if (audioChannelLayout != audioChannelLayout2) {
            if (audioChannelLayout == null) {
                return -1;
            }
            if (audioChannelLayout2 == null) {
                return 1;
            }
            int compareTo19 = audioChannelLayout.compareTo(audioChannelLayout2);
            if (compareTo19 != 0) {
                return compareTo19;
            }
        }
        return 0;
    }
}
