package com.amazon.alexa.voice.ui.onedesign.tta.items;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionHintTtaItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
final class AutoValue_SuggestionHintTtaItem extends SuggestionHintTtaItem {
    private final String itemType;
    private final String suggestionId;
    private final CharSequence suggestionTitle;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder extends SuggestionHintTtaItem.Builder {
        private String itemType;
        private String suggestionId;
        private CharSequence suggestionTitle;

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionHintTtaItem.Builder
        public SuggestionHintTtaItem build() {
            String str = "";
            if (this.suggestionTitle == null) {
                str = GeneratedOutlineSupport1.outline72(str, " suggestionTitle");
            }
            if (this.itemType == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemType");
            }
            if (this.suggestionId == null) {
                str = GeneratedOutlineSupport1.outline72(str, " suggestionId");
            }
            if (str.isEmpty()) {
                return new AutoValue_SuggestionHintTtaItem(this.suggestionTitle, this.itemType, this.suggestionId);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionHintTtaItem.Builder
        public SuggestionHintTtaItem.Builder itemType(String str) {
            if (str != null) {
                this.itemType = str;
                return this;
            }
            throw new NullPointerException("Null itemType");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionHintTtaItem.Builder
        public SuggestionHintTtaItem.Builder suggestionId(String str) {
            if (str != null) {
                this.suggestionId = str;
                return this;
            }
            throw new NullPointerException("Null suggestionId");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionHintTtaItem.Builder
        public SuggestionHintTtaItem.Builder suggestionTitle(CharSequence charSequence) {
            if (charSequence != null) {
                this.suggestionTitle = charSequence;
                return this;
            }
            throw new NullPointerException("Null suggestionTitle");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SuggestionHintTtaItem)) {
            return false;
        }
        SuggestionHintTtaItem suggestionHintTtaItem = (SuggestionHintTtaItem) obj;
        return this.suggestionTitle.equals(suggestionHintTtaItem.getSuggestionTitle()) && this.itemType.equals(suggestionHintTtaItem.getItemType()) && this.suggestionId.equals(suggestionHintTtaItem.getSuggestionId());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionHintTtaItem
    public String getItemType() {
        return this.itemType;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionHintTtaItem
    public String getSuggestionId() {
        return this.suggestionId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.SuggestionHintTtaItem
    public CharSequence getSuggestionTitle() {
        return this.suggestionTitle;
    }

    public int hashCode() {
        return ((((this.suggestionTitle.hashCode() ^ 1000003) * 1000003) ^ this.itemType.hashCode()) * 1000003) ^ this.suggestionId.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SuggestionHintTtaItem{suggestionTitle=");
        outline107.append((Object) this.suggestionTitle);
        outline107.append(", itemType=");
        outline107.append(this.itemType);
        outline107.append(", suggestionId=");
        return GeneratedOutlineSupport1.outline91(outline107, this.suggestionId, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_SuggestionHintTtaItem(CharSequence charSequence, String str, String str2) {
        this.suggestionTitle = charSequence;
        this.itemType = str;
        this.suggestionId = str2;
    }
}
