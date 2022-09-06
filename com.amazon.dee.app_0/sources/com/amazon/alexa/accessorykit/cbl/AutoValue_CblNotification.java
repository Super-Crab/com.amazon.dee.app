package com.amazon.alexa.accessorykit.cbl;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessorykit.cbl.CblNotification;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
final class AutoValue_CblNotification extends CblNotification {
    private final Accessory accessory;
    private final String text;
    private final String title;

    /* loaded from: classes6.dex */
    static final class Builder extends CblNotification.Builder {
        private Accessory accessory;
        private String text;
        private String title;

        @Override // com.amazon.alexa.accessorykit.cbl.CblNotification.Builder
        public CblNotification build() {
            String str = "";
            if (this.title == null) {
                str = GeneratedOutlineSupport1.outline72(str, " title");
            }
            if (this.text == null) {
                str = GeneratedOutlineSupport1.outline72(str, " text");
            }
            if (this.accessory == null) {
                str = GeneratedOutlineSupport1.outline72(str, " accessory");
            }
            if (str.isEmpty()) {
                return new AutoValue_CblNotification(this.title, this.text, this.accessory);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.accessorykit.cbl.CblNotification.Builder
        public CblNotification.Builder setAccessory(Accessory accessory) {
            if (accessory != null) {
                this.accessory = accessory;
                return this;
            }
            throw new NullPointerException("Null accessory");
        }

        @Override // com.amazon.alexa.accessorykit.cbl.CblNotification.Builder
        public CblNotification.Builder setText(String str) {
            if (str != null) {
                this.text = str;
                return this;
            }
            throw new NullPointerException("Null text");
        }

        @Override // com.amazon.alexa.accessorykit.cbl.CblNotification.Builder
        public CblNotification.Builder setTitle(String str) {
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
        if (!(obj instanceof CblNotification)) {
            return false;
        }
        CblNotification cblNotification = (CblNotification) obj;
        return this.title.equals(cblNotification.getTitle()) && this.text.equals(cblNotification.getText()) && this.accessory.equals(cblNotification.getAccessory());
    }

    @Override // com.amazon.alexa.accessorykit.cbl.CblNotification
    public Accessory getAccessory() {
        return this.accessory;
    }

    @Override // com.amazon.alexa.accessorykit.cbl.CblNotification
    public String getText() {
        return this.text;
    }

    @Override // com.amazon.alexa.accessorykit.cbl.CblNotification
    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return ((((this.title.hashCode() ^ 1000003) * 1000003) ^ this.text.hashCode()) * 1000003) ^ this.accessory.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CblNotification{title=");
        outline107.append(this.title);
        outline107.append(", text=");
        outline107.append(this.text);
        outline107.append(", accessory=");
        outline107.append(this.accessory);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    private AutoValue_CblNotification(String str, String str2, Accessory accessory) {
        this.title = str;
        this.text = str2;
        this.accessory = accessory;
    }
}
