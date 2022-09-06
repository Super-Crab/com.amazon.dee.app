package com.amazon.alexa.sharing.repo.models.acms.payload;

import com.amazon.alexa.sharing.api.models.Payload;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public class MediaMessagePayload implements MessagePayload {
    public static final String IMAGE_TYPE = "IMAGE";
    public static final String TYPE = "message/media";
    public static final String VIDEO_TYPE = "VIDEO";
    @SerializedName(Payload.SERIALIZED_NAME_ATTACHMENTS)
    private Attachment[] attachments;

    /* loaded from: classes10.dex */
    public static class Attachment {
        @SerializedName("type")
        private String type;

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }
    }

    /* loaded from: classes10.dex */
    public enum MediaNotificationType {
        EMPTY_PAYLOAD,
        UNKNOWN_PAYLOAD,
        SINGLE_IMAGE,
        SINGLE_VIDEO,
        MULTIPLE_IMAGES,
        MULTIPLE_VIDEOS,
        IMAGES_AND_VIDEOS
    }

    private MediaNotificationType determineMediaNotificationType() {
        Attachment[] attachments = getAttachments();
        if (attachments.length == 0) {
            return MediaNotificationType.EMPTY_PAYLOAD;
        }
        String type = attachments[0].getType();
        if (attachments.length == 1) {
            return getSingularMediaNotificationType(type);
        }
        return getMultipleMediaNotificationType(type);
    }

    private MediaNotificationType getMultipleMediaNotificationType(String str) {
        if (includesImagesAndVideo().booleanValue()) {
            return MediaNotificationType.IMAGES_AND_VIDEOS;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != 69775675) {
            if (hashCode == 81665115 && str.equals("VIDEO")) {
                c = 1;
            }
        } else if (str.equals("IMAGE")) {
            c = 0;
        }
        if (c == 0) {
            return MediaNotificationType.MULTIPLE_IMAGES;
        }
        if (c != 1) {
            return MediaNotificationType.UNKNOWN_PAYLOAD;
        }
        return MediaNotificationType.MULTIPLE_VIDEOS;
    }

    private MediaNotificationType getSingularMediaNotificationType(String str) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != 69775675) {
            if (hashCode == 81665115 && str.equals("VIDEO")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("IMAGE")) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1) {
                return MediaNotificationType.UNKNOWN_PAYLOAD;
            }
            return MediaNotificationType.SINGLE_VIDEO;
        }
        return MediaNotificationType.SINGLE_IMAGE;
    }

    private Boolean includesImagesAndVideo() {
        Attachment[] attachments = getAttachments();
        for (Attachment attachment : attachments) {
            if (!attachment.type.equalsIgnoreCase(attachments[0].type)) {
                return true;
            }
        }
        return false;
    }

    public Attachment[] getAttachments() {
        return this.attachments;
    }

    @Override // com.amazon.alexa.sharing.repo.models.acms.payload.MessagePayload
    public String getType() {
        return "message/media";
    }
}
