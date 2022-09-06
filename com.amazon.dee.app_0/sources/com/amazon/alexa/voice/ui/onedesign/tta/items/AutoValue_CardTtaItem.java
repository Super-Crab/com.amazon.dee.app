package com.amazon.alexa.voice.ui.onedesign.tta.items;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
final class AutoValue_CardTtaItem extends CardTtaItem {
    private final String cardType;
    private final String itemDescription;
    private final String itemId;
    private final CharSequence itemText;
    private final String linkUrl;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder extends CardTtaItem.Builder {
        private String cardType;
        private String itemDescription;
        private String itemId;
        private CharSequence itemText;
        private String linkUrl;

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem.Builder
        public CardTtaItem build() {
            String str = "";
            if (this.itemText == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemText");
            }
            if (this.itemDescription == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemDescription");
            }
            if (this.itemId == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemId");
            }
            if (this.linkUrl == null) {
                str = GeneratedOutlineSupport1.outline72(str, " linkUrl");
            }
            if (this.cardType == null) {
                str = GeneratedOutlineSupport1.outline72(str, " cardType");
            }
            if (str.isEmpty()) {
                return new AutoValue_CardTtaItem(this.itemText, this.itemDescription, this.itemId, this.linkUrl, this.cardType);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem.Builder
        public CardTtaItem.Builder cardType(String str) {
            if (str != null) {
                this.cardType = str;
                return this;
            }
            throw new NullPointerException("Null cardType");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem.Builder
        public CardTtaItem.Builder itemDescription(String str) {
            if (str != null) {
                this.itemDescription = str;
                return this;
            }
            throw new NullPointerException("Null itemDescription");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem.Builder
        public CardTtaItem.Builder itemId(String str) {
            if (str != null) {
                this.itemId = str;
                return this;
            }
            throw new NullPointerException("Null itemId");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem.Builder
        public CardTtaItem.Builder itemText(CharSequence charSequence) {
            if (charSequence != null) {
                this.itemText = charSequence;
                return this;
            }
            throw new NullPointerException("Null itemText");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem.Builder
        public CardTtaItem.Builder linkUrl(String str) {
            if (str != null) {
                this.linkUrl = str;
                return this;
            }
            throw new NullPointerException("Null linkUrl");
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem
    public String getCardType() {
        return this.cardType;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem
    public String getItemDescription() {
        return this.itemDescription;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem
    public String getItemId() {
        return this.itemId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem
    public CharSequence getItemText() {
        return this.itemText;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.CardTtaItem
    public String getLinkUrl() {
        return this.linkUrl;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CardTtaItem{itemText=");
        outline107.append((Object) this.itemText);
        outline107.append(", itemDescription=");
        outline107.append(this.itemDescription);
        outline107.append(", itemId=");
        outline107.append(this.itemId);
        outline107.append(", linkUrl=");
        outline107.append(this.linkUrl);
        outline107.append(", cardType=");
        return GeneratedOutlineSupport1.outline91(outline107, this.cardType, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_CardTtaItem(CharSequence charSequence, String str, String str2, String str3, String str4) {
        this.itemText = charSequence;
        this.itemDescription = str;
        this.itemId = str2;
        this.linkUrl = str3;
        this.cardType = str4;
    }
}
