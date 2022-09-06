package com.amazon.alexa.accessory.notifications;

import com.amazon.alexa.accessory.notifications.LocalNotification;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
final class AutoValue_LocalNotification extends LocalNotification {
    private final String deepLink;
    private final String text;
    private final String title;

    /* loaded from: classes.dex */
    static final class Builder extends LocalNotification.Builder {
        private String deepLink;
        private String text;
        private String title;

        @Override // com.amazon.alexa.accessory.notifications.LocalNotification.Builder
        public LocalNotification build() {
            String str = "";
            if (this.title == null) {
                str = GeneratedOutlineSupport1.outline72(str, " title");
            }
            if (this.text == null) {
                str = GeneratedOutlineSupport1.outline72(str, " text");
            }
            if (this.deepLink == null) {
                str = GeneratedOutlineSupport1.outline72(str, " deepLink");
            }
            if (str.isEmpty()) {
                return new AutoValue_LocalNotification(this.title, this.text, this.deepLink);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.accessory.notifications.LocalNotification.Builder
        public LocalNotification.Builder setDeepLink(String str) {
            if (str != null) {
                this.deepLink = str;
                return this;
            }
            throw new NullPointerException("Null deepLink");
        }

        @Override // com.amazon.alexa.accessory.notifications.LocalNotification.Builder
        public LocalNotification.Builder setText(String str) {
            if (str != null) {
                this.text = str;
                return this;
            }
            throw new NullPointerException("Null text");
        }

        @Override // com.amazon.alexa.accessory.notifications.LocalNotification.Builder
        public LocalNotification.Builder setTitle(String str) {
            if (str != null) {
                this.title = str;
                return this;
            }
            throw new NullPointerException("Null title");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocalNotification)) {
            return false;
        }
        LocalNotification localNotification = (LocalNotification) obj;
        return this.title.equals(localNotification.getTitle()) && this.text.equals(localNotification.getText()) && this.deepLink.equals(localNotification.getDeepLink());
    }

    @Override // com.amazon.alexa.accessory.notifications.LocalNotification
    public String getDeepLink() {
        return this.deepLink;
    }

    @Override // com.amazon.alexa.accessory.notifications.LocalNotification
    public String getText() {
        return this.text;
    }

    @Override // com.amazon.alexa.accessory.notifications.LocalNotification
    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return ((((this.title.hashCode() ^ 1000003) * 1000003) ^ this.text.hashCode()) * 1000003) ^ this.deepLink.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocalNotification{title=");
        outline107.append(this.title);
        outline107.append(", text=");
        outline107.append(this.text);
        outline107.append(", deepLink=");
        return GeneratedOutlineSupport1.outline91(outline107, this.deepLink, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_LocalNotification(String str, String str2, String str3) {
        this.title = str;
        this.text = str2;
        this.deepLink = str3;
    }
}
