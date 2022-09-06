package com.amazon.alexa.voice.ui.onedesign.tta.items;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.onedesign.tta.items.UserInputTtaItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
final class AutoValue_UserInputTtaItem extends UserInputTtaItem {
    private final CharSequence itemText;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder extends UserInputTtaItem.Builder {
        private CharSequence itemText;

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.UserInputTtaItem.Builder
        public UserInputTtaItem build() {
            String str = "";
            if (this.itemText == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemText");
            }
            if (str.isEmpty()) {
                return new AutoValue_UserInputTtaItem(this.itemText);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.UserInputTtaItem.Builder
        public UserInputTtaItem.Builder itemText(CharSequence charSequence) {
            if (charSequence != null) {
                this.itemText = charSequence;
                return this;
            }
            throw new NullPointerException("Null itemText");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UserInputTtaItem)) {
            return false;
        }
        return this.itemText.equals(((UserInputTtaItem) obj).getItemText());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.UserInputTtaItem
    public CharSequence getItemText() {
        return this.itemText;
    }

    public int hashCode() {
        return this.itemText.hashCode() ^ 1000003;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UserInputTtaItem{itemText=");
        outline107.append((Object) this.itemText);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    private AutoValue_UserInputTtaItem(CharSequence charSequence) {
        this.itemText = charSequence;
    }
}
