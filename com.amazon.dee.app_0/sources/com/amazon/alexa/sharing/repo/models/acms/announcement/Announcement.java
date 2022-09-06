package com.amazon.alexa.sharing.repo.models.acms.announcement;

import androidx.annotation.Nullable;
import com.amazon.alexa.sharing.repo.models.acms.announcement.enums.AnnouncementType;
import com.amazon.alexa.sharing.repo.models.acms.announcement.enums.SoundEffect;
/* loaded from: classes10.dex */
public final class Announcement {
    private String expiryDate;
    private String id;
    @Nullable
    private String localUri;
    @Nullable
    private String mediaId;
    private String messageText;
    private String originDate;
    @Nullable
    private SoundEffect[] soundEffects;
    private String sourceName;
    private String speakerName;
    private AnnouncementType type;

    /* loaded from: classes10.dex */
    public static class Builder {
        private String expiryDate;
        private String id;
        @Nullable
        private String localUri;
        @Nullable
        private String mediaId;
        private String messageText;
        private String originDate;
        @Nullable
        private SoundEffect[] soundEffects;
        private String sourceName;
        private String speakerName;
        private AnnouncementType type;

        public Announcement build() {
            return new Announcement(this);
        }

        public Builder setExpiryDate(String str) {
            this.expiryDate = str;
            return this;
        }

        public Builder setID(String str) {
            this.id = str;
            return this;
        }

        public Builder setLocalUri(String str) {
            this.localUri = str;
            return this;
        }

        public Builder setMediaId(String str) {
            this.mediaId = str;
            return this;
        }

        public Builder setMessageText(String str) {
            this.messageText = str;
            return this;
        }

        public Builder setOriginDate(String str) {
            this.originDate = str;
            return this;
        }

        public Builder setSoundEffects(SoundEffect[] soundEffectArr) {
            this.soundEffects = soundEffectArr;
            return this;
        }

        public Builder setSourceName(String str) {
            this.sourceName = str;
            return this;
        }

        public Builder setSpeakerName(String str) {
            this.speakerName = str;
            return this;
        }

        public Builder setType(AnnouncementType announcementType) {
            this.type = announcementType;
            return this;
        }
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public String getId() {
        return this.id;
    }

    public String getLocalUri() {
        return this.localUri;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public String getOriginDate() {
        return this.originDate;
    }

    public SoundEffect[] getSoundEffects() {
        return this.soundEffects;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public String getSpeakerName() {
        return this.speakerName;
    }

    public AnnouncementType getType() {
        return this.type;
    }

    private Announcement(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.soundEffects = builder.soundEffects;
        this.messageText = builder.messageText;
        this.expiryDate = builder.expiryDate;
        this.originDate = builder.originDate;
        this.speakerName = builder.speakerName;
        this.sourceName = builder.sourceName;
        this.localUri = builder.localUri;
        this.mediaId = builder.mediaId;
    }
}
