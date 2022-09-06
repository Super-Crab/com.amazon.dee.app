package com.amazon.alexa.voice.ui.onedesign.tta.items;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
final class AutoValue_PillResultTtaItem extends PillResultTtaItem {
    private final boolean fromSimba;
    private final String itemId;
    private final String itemRoute;
    private final CharSequence itemText;
    private final int itemType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static final class Builder extends PillResultTtaItem.Builder {
        private Boolean fromSimba;
        private String itemId;
        private String itemRoute;
        private CharSequence itemText;
        private Integer itemType;

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem.Builder
        public PillResultTtaItem build() {
            String str = "";
            if (this.itemText == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemText");
            }
            if (this.itemId == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemId");
            }
            if (this.itemType == null) {
                str = GeneratedOutlineSupport1.outline72(str, " itemType");
            }
            if (this.fromSimba == null) {
                str = GeneratedOutlineSupport1.outline72(str, " fromSimba");
            }
            if (str.isEmpty()) {
                return new AutoValue_PillResultTtaItem(this.itemText, this.itemId, this.itemType.intValue(), this.itemRoute, this.fromSimba.booleanValue());
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem.Builder
        public PillResultTtaItem.Builder fromSimba(boolean z) {
            this.fromSimba = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem.Builder
        public PillResultTtaItem.Builder itemId(String str) {
            if (str != null) {
                this.itemId = str;
                return this;
            }
            throw new NullPointerException("Null itemId");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem.Builder
        public PillResultTtaItem.Builder itemRoute(@Nullable String str) {
            this.itemRoute = str;
            return this;
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem.Builder
        public PillResultTtaItem.Builder itemText(CharSequence charSequence) {
            if (charSequence != null) {
                this.itemText = charSequence;
                return this;
            }
            throw new NullPointerException("Null itemText");
        }

        @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem.Builder
        public PillResultTtaItem.Builder itemType(int i) {
            this.itemType = Integer.valueOf(i);
            return this;
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem
    public String getItemId() {
        return this.itemId;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem
    @Nullable
    public String getItemRoute() {
        return this.itemRoute;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem
    public CharSequence getItemText() {
        return this.itemText;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem
    public int getItemType() {
        return this.itemType;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.items.PillResultTtaItem
    public boolean isFromSimba() {
        return this.fromSimba;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PillResultTtaItem{itemText=");
        outline107.append((Object) this.itemText);
        outline107.append(", itemId=");
        outline107.append(this.itemId);
        outline107.append(", itemType=");
        outline107.append(this.itemType);
        outline107.append(", itemRoute=");
        outline107.append(this.itemRoute);
        outline107.append(", fromSimba=");
        return GeneratedOutlineSupport1.outline97(outline107, this.fromSimba, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_PillResultTtaItem(CharSequence charSequence, String str, int i, @Nullable String str2, boolean z) {
        this.itemText = charSequence;
        this.itemId = str;
        this.itemType = i;
        this.itemRoute = str2;
        this.fromSimba = z;
    }
}
