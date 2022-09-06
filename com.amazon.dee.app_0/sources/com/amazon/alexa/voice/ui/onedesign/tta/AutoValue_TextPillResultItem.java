package com.amazon.alexa.voice.ui.onedesign.tta;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class AutoValue_TextPillResultItem extends TextPillResultItem {
    private final boolean fromSimba;
    private final String id;
    private final String itemRoute;
    private final String itemText;
    private final int type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TextPillResultItem(boolean z, String str, @Nullable String str2, int i, String str3) {
        this.fromSimba = z;
        if (str != null) {
            this.itemText = str;
            this.itemRoute = str2;
            this.type = i;
            if (str3 != null) {
                this.id = str3;
                return;
            }
            throw new NullPointerException("Null id");
        }
        throw new NullPointerException("Null itemText");
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextPillResultItem)) {
            return false;
        }
        TextPillResultItem textPillResultItem = (TextPillResultItem) obj;
        return this.fromSimba == textPillResultItem.isFromSimba() && this.itemText.equals(textPillResultItem.getItemText()) && ((str = this.itemRoute) != null ? str.equals(textPillResultItem.getItemRoute()) : textPillResultItem.getItemRoute() == null) && this.type == textPillResultItem.getType() && this.id.equals(textPillResultItem.getId());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TextPillResultItem, com.amazon.alexa.voice.ui.tta.search.ResultItem
    public String getId() {
        return this.id;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TextPillResultItem, com.amazon.alexa.voice.ui.tta.search.PillResultItem
    @Nullable
    public String getItemRoute() {
        return this.itemRoute;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TextPillResultItem, com.amazon.alexa.voice.ui.tta.search.PillResultItem
    public String getItemText() {
        return this.itemText;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TextPillResultItem, com.amazon.alexa.voice.ui.tta.search.ResultItem
    public int getType() {
        return this.type;
    }

    public int hashCode() {
        int hashCode = ((((this.fromSimba ? 1231 : 1237) ^ 1000003) * 1000003) ^ this.itemText.hashCode()) * 1000003;
        String str = this.itemRoute;
        return ((((hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.type) * 1000003) ^ this.id.hashCode();
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.PillResultItem
    public boolean isFromSimba() {
        return this.fromSimba;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TextPillResultItem{fromSimba=");
        outline107.append(this.fromSimba);
        outline107.append(", itemText=");
        outline107.append(this.itemText);
        outline107.append(", itemRoute=");
        outline107.append(this.itemRoute);
        outline107.append(", type=");
        outline107.append(this.type);
        outline107.append(", id=");
        return GeneratedOutlineSupport1.outline91(outline107, this.id, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
