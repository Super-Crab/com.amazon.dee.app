package com.amazon.deecomms.messaging.model.payload;

import android.content.Context;
import com.amazon.alexa.sharing.api.models.Payload;
import com.amazon.deecomms.R;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class MediaMessagePayload implements MessagePayload {
    public static final String IMAGE_TYPE = "IMAGE";
    public static final String TYPE = "message/media";
    public static final String VIDEO_TYPE = "VIDEO";
    @JsonProperty(Payload.SERIALIZED_NAME_ATTACHMENTS)
    private Attachment[] attachments;

    /* renamed from: com.amazon.deecomms.messaging.model.payload.MediaMessagePayload$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$messaging$model$payload$MediaMessagePayload$MediaNotificationType = new int[MediaNotificationType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$payload$MediaMessagePayload$MediaNotificationType[MediaNotificationType.SINGLE_IMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$payload$MediaMessagePayload$MediaNotificationType[MediaNotificationType.MULTIPLE_IMAGES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$payload$MediaMessagePayload$MediaNotificationType[MediaNotificationType.SINGLE_VIDEO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$payload$MediaMessagePayload$MediaNotificationType[MediaNotificationType.MULTIPLE_VIDEOS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$payload$MediaMessagePayload$MediaNotificationType[MediaNotificationType.IMAGES_AND_VIDEOS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$messaging$model$payload$MediaMessagePayload$MediaNotificationType[MediaNotificationType.EMPTY_PAYLOAD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class Attachment {
        @JsonProperty("type")
        private String type;

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }
    }

    /* loaded from: classes12.dex */
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

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getNotificationText(Context context) {
        return getSummaryText(context);
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getSummaryText(Context context) {
        int ordinal = determineMediaNotificationType().ordinal();
        if (ordinal != 0) {
            if (ordinal == 2) {
                return context.getString(R.string.notification_text_new_image_message);
            }
            if (ordinal == 3) {
                return context.getString(R.string.notification_text_new_video_message);
            }
            if (ordinal == 4) {
                return String.format(context.getString(R.string.notification_text_multiple_image_messages), Integer.valueOf(getAttachments().length));
            }
            if (ordinal == 5) {
                return String.format(context.getString(R.string.notification_text_multiple_video_messages), Integer.valueOf(getAttachments().length));
            }
            return ordinal != 6 ? "" : String.format(context.getString(R.string.notification_text_multiple_videos_and_images_message), Integer.valueOf(getAttachments().length));
        }
        return "";
    }

    @Override // com.amazon.deecomms.messaging.model.payload.MessagePayload
    public String getType() {
        return "message/media";
    }
}
