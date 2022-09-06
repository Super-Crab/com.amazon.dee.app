package com.amazon.alexa.voice.ui.onedesign.tta.items;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem;
import com.amazon.alexa.voice.ui.tta.search.ItemPosition;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
final class AutoValue_InChatHintTtaItem extends InChatHintTtaItem {
    private final String itemId;
    private final ItemPosition itemPosition;
    private final CharSequence itemText;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder extends InChatHintTtaItem.Builder {
        private String itemId;
        private ItemPosition itemPosition;
        private CharSequence itemText;

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem.Builder
        public InChatHintTtaItem build() {
            String str = "";
            if (this.itemId == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemId");
            }
            if (this.itemText == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemText");
            }
            if (this.itemPosition == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemPosition");
            }
            if (str.isEmpty()) {
                return new AutoValue_InChatHintTtaItem(this.itemId, this.itemText, this.itemPosition);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem.Builder
        public InChatHintTtaItem.Builder itemId(String str) {
            if (str != null) {
                this.itemId = str;
                return this;
            }
            throw new NullPointerException("Null itemId");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem.Builder
        public InChatHintTtaItem.Builder itemPosition(ItemPosition itemPosition) {
            if (itemPosition != null) {
                this.itemPosition = itemPosition;
                return this;
            }
            throw new NullPointerException("Null itemPosition");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem.Builder
        public InChatHintTtaItem.Builder itemText(CharSequence charSequence) {
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
        if (!(obj instanceof InChatHintTtaItem)) {
            return false;
        }
        InChatHintTtaItem inChatHintTtaItem = (InChatHintTtaItem) obj;
        return this.itemId.equals(inChatHintTtaItem.getItemId()) && this.itemText.equals(inChatHintTtaItem.getItemText()) && this.itemPosition.equals(inChatHintTtaItem.getItemPosition());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem
    public String getItemId() {
        return this.itemId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem
    public ItemPosition getItemPosition() {
        return this.itemPosition;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatHintTtaItem
    public CharSequence getItemText() {
        return this.itemText;
    }

    public int hashCode() {
        return ((((this.itemId.hashCode() ^ 1000003) * 1000003) ^ this.itemText.hashCode()) * 1000003) ^ this.itemPosition.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InChatHintTtaItem{itemId=");
        outline107.append(this.itemId);
        outline107.append(", itemText=");
        outline107.append((Object) this.itemText);
        outline107.append(", itemPosition=");
        outline107.append(this.itemPosition);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    private AutoValue_InChatHintTtaItem(String str, CharSequence charSequence, ItemPosition itemPosition) {
        this.itemId = str;
        this.itemText = charSequence;
        this.itemPosition = itemPosition;
    }
}
