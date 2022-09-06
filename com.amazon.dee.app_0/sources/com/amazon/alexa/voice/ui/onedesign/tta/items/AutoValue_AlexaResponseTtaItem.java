package com.amazon.alexa.voice.ui.onedesign.tta.items;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.onedesign.tta.items.AlexaResponseTtaItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
final class AutoValue_AlexaResponseTtaItem extends AlexaResponseTtaItem {
    private final String itemId;
    private final CharSequence itemText;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder extends AlexaResponseTtaItem.Builder {
        private String itemId;
        private CharSequence itemText;

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.AlexaResponseTtaItem.Builder
        public AlexaResponseTtaItem build() {
            String str = "";
            if (this.itemText == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemText");
            }
            if (this.itemId == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemId");
            }
            if (str.isEmpty()) {
                return new AutoValue_AlexaResponseTtaItem(this.itemText, this.itemId);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.AlexaResponseTtaItem.Builder
        public AlexaResponseTtaItem.Builder itemId(String str) {
            if (str != null) {
                this.itemId = str;
                return this;
            }
            throw new NullPointerException("Null itemId");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.AlexaResponseTtaItem.Builder
        public AlexaResponseTtaItem.Builder itemText(CharSequence charSequence) {
            if (charSequence != null) {
                this.itemText = charSequence;
                return this;
            }
            throw new NullPointerException("Null itemText");
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.AlexaResponseTtaItem
    public String getItemId() {
        return this.itemId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.AlexaResponseTtaItem
    public CharSequence getItemText() {
        return this.itemText;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaResponseTtaItem{itemText=");
        outline107.append((Object) this.itemText);
        outline107.append(", itemId=");
        return GeneratedOutlineSupport1.outline91(outline107, this.itemId, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_AlexaResponseTtaItem(CharSequence charSequence, String str) {
        this.itemText = charSequence;
        this.itemId = str;
    }
}
