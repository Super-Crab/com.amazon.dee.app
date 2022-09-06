package com.amazon.alexa.voice.ui.onedesign.tta.items;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
final class AutoValue_SuggestionLinkTtaItem extends SuggestionLinkTtaItem {
    private final String actionData;
    private final String itemType;
    private final CharSequence suggestionDescription;
    private final String suggestionId;
    private final CharSequence suggestionTitle;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder extends SuggestionLinkTtaItem.Builder {
        private String actionData;
        private String itemType;
        private CharSequence suggestionDescription;
        private String suggestionId;
        private CharSequence suggestionTitle;

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem.Builder
        public SuggestionLinkTtaItem.Builder actionData(String str) {
            if (str != null) {
                this.actionData = str;
                return this;
            }
            throw new NullPointerException("Null actionData");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem.Builder
        public SuggestionLinkTtaItem build() {
            String str = "";
            if (this.suggestionTitle == null) {
                str = GeneratedOutlineSupport1.outline72(str, " suggestionTitle");
            }
            if (this.itemType == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemType");
            }
            if (this.actionData == null) {
                str = GeneratedOutlineSupport1.outline72(str, " actionData");
            }
            if (this.suggestionId == null) {
                str = GeneratedOutlineSupport1.outline72(str, " suggestionId");
            }
            if (str.isEmpty()) {
                return new AutoValue_SuggestionLinkTtaItem(this.suggestionTitle, this.suggestionDescription, this.itemType, this.actionData, this.suggestionId);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem.Builder
        public SuggestionLinkTtaItem.Builder itemType(String str) {
            if (str != null) {
                this.itemType = str;
                return this;
            }
            throw new NullPointerException("Null itemType");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem.Builder
        public SuggestionLinkTtaItem.Builder suggestionDescription(@Nullable CharSequence charSequence) {
            this.suggestionDescription = charSequence;
            return this;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem.Builder
        public SuggestionLinkTtaItem.Builder suggestionId(String str) {
            if (str != null) {
                this.suggestionId = str;
                return this;
            }
            throw new NullPointerException("Null suggestionId");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem.Builder
        public SuggestionLinkTtaItem.Builder suggestionTitle(CharSequence charSequence) {
            if (charSequence != null) {
                this.suggestionTitle = charSequence;
                return this;
            }
            throw new NullPointerException("Null suggestionTitle");
        }
    }

    public boolean equals(Object obj) {
        CharSequence charSequence;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SuggestionLinkTtaItem)) {
            return false;
        }
        SuggestionLinkTtaItem suggestionLinkTtaItem = (SuggestionLinkTtaItem) obj;
        return this.suggestionTitle.equals(suggestionLinkTtaItem.getSuggestionTitle()) && ((charSequence = this.suggestionDescription) != null ? charSequence.equals(suggestionLinkTtaItem.getSuggestionDescription()) : suggestionLinkTtaItem.getSuggestionDescription() == null) && this.itemType.equals(suggestionLinkTtaItem.getItemType()) && this.actionData.equals(suggestionLinkTtaItem.getActionData()) && this.suggestionId.equals(suggestionLinkTtaItem.getSuggestionId());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem
    public String getActionData() {
        return this.actionData;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem
    public String getItemType() {
        return this.itemType;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem
    @Nullable
    public CharSequence getSuggestionDescription() {
        return this.suggestionDescription;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem
    public String getSuggestionId() {
        return this.suggestionId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionLinkTtaItem
    public CharSequence getSuggestionTitle() {
        return this.suggestionTitle;
    }

    public int hashCode() {
        int hashCode = (this.suggestionTitle.hashCode() ^ 1000003) * 1000003;
        CharSequence charSequence = this.suggestionDescription;
        return ((((((hashCode ^ (charSequence == null ? 0 : charSequence.hashCode())) * 1000003) ^ this.itemType.hashCode()) * 1000003) ^ this.actionData.hashCode()) * 1000003) ^ this.suggestionId.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SuggestionLinkTtaItem{suggestionTitle=");
        outline107.append((Object) this.suggestionTitle);
        outline107.append(", suggestionDescription=");
        outline107.append((Object) this.suggestionDescription);
        outline107.append(", itemType=");
        outline107.append(this.itemType);
        outline107.append(", actionData=");
        outline107.append(this.actionData);
        outline107.append(", suggestionId=");
        return GeneratedOutlineSupport1.outline91(outline107, this.suggestionId, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_SuggestionLinkTtaItem(CharSequence charSequence, @Nullable CharSequence charSequence2, String str, String str2, String str3) {
        this.suggestionTitle = charSequence;
        this.suggestionDescription = charSequence2;
        this.itemType = str;
        this.actionData = str2;
        this.suggestionId = str3;
    }
}
