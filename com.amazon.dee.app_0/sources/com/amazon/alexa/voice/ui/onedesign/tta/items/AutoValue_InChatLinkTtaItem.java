package com.amazon.alexa.voice.ui.onedesign.tta.items;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem;
import com.amazon.alexa.voice.ui.tta.search.ItemPosition;
import com.amazon.alexa.voice.ui.tta.search.ItemRoute;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
final class AutoValue_InChatLinkTtaItem extends InChatLinkTtaItem {
    private final String itemId;
    private final ItemPosition itemPosition;
    private final ItemRoute itemRoute;
    private final CharSequence primaryText;
    private final CharSequence secondText;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder extends InChatLinkTtaItem.Builder {
        private String itemId;
        private ItemPosition itemPosition;
        private ItemRoute itemRoute;
        private CharSequence primaryText;
        private CharSequence secondText;

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem.Builder
        public InChatLinkTtaItem build() {
            String str = "";
            if (this.itemId == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemId");
            }
            if (this.primaryText == null) {
                str = GeneratedOutlineSupport1.outline72(str, " primaryText");
            }
            if (this.itemRoute == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemRoute");
            }
            if (this.itemPosition == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemPosition");
            }
            if (str.isEmpty()) {
                return new AutoValue_InChatLinkTtaItem(this.itemId, this.primaryText, this.secondText, this.itemRoute, this.itemPosition);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem.Builder
        public InChatLinkTtaItem.Builder itemId(String str) {
            if (str != null) {
                this.itemId = str;
                return this;
            }
            throw new NullPointerException("Null itemId");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem.Builder
        public InChatLinkTtaItem.Builder itemPosition(ItemPosition itemPosition) {
            if (itemPosition != null) {
                this.itemPosition = itemPosition;
                return this;
            }
            throw new NullPointerException("Null itemPosition");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem.Builder
        public InChatLinkTtaItem.Builder itemRoute(ItemRoute itemRoute) {
            if (itemRoute != null) {
                this.itemRoute = itemRoute;
                return this;
            }
            throw new NullPointerException("Null itemRoute");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem.Builder
        public InChatLinkTtaItem.Builder primaryText(CharSequence charSequence) {
            if (charSequence != null) {
                this.primaryText = charSequence;
                return this;
            }
            throw new NullPointerException("Null primaryText");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem.Builder
        public InChatLinkTtaItem.Builder secondText(@Nullable CharSequence charSequence) {
            this.secondText = charSequence;
            return this;
        }
    }

    public boolean equals(Object obj) {
        CharSequence charSequence;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InChatLinkTtaItem)) {
            return false;
        }
        InChatLinkTtaItem inChatLinkTtaItem = (InChatLinkTtaItem) obj;
        return this.itemId.equals(inChatLinkTtaItem.getItemId()) && this.primaryText.equals(inChatLinkTtaItem.getPrimaryText()) && ((charSequence = this.secondText) != null ? charSequence.equals(inChatLinkTtaItem.getSecondText()) : inChatLinkTtaItem.getSecondText() == null) && this.itemRoute.equals(inChatLinkTtaItem.getItemRoute()) && this.itemPosition.equals(inChatLinkTtaItem.getItemPosition());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem
    public String getItemId() {
        return this.itemId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem
    public ItemPosition getItemPosition() {
        return this.itemPosition;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem
    public ItemRoute getItemRoute() {
        return this.itemRoute;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem
    public CharSequence getPrimaryText() {
        return this.primaryText;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.InChatLinkTtaItem
    @Nullable
    public CharSequence getSecondText() {
        return this.secondText;
    }

    public int hashCode() {
        int hashCode = (((this.itemId.hashCode() ^ 1000003) * 1000003) ^ this.primaryText.hashCode()) * 1000003;
        CharSequence charSequence = this.secondText;
        return ((((hashCode ^ (charSequence == null ? 0 : charSequence.hashCode())) * 1000003) ^ this.itemRoute.hashCode()) * 1000003) ^ this.itemPosition.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InChatLinkTtaItem{itemId=");
        outline107.append(this.itemId);
        outline107.append(", primaryText=");
        outline107.append((Object) this.primaryText);
        outline107.append(", secondText=");
        outline107.append((Object) this.secondText);
        outline107.append(", itemRoute=");
        outline107.append(this.itemRoute);
        outline107.append(", itemPosition=");
        outline107.append(this.itemPosition);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    private AutoValue_InChatLinkTtaItem(String str, CharSequence charSequence, @Nullable CharSequence charSequence2, ItemRoute itemRoute, ItemPosition itemPosition) {
        this.itemId = str;
        this.primaryText = charSequence;
        this.secondText = charSequence2;
        this.itemRoute = itemRoute;
        this.itemPosition = itemPosition;
    }
}
