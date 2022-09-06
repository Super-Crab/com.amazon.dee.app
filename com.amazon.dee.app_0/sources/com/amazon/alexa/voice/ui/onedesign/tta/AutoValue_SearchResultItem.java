package com.amazon.alexa.voice.ui.onedesign.tta;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.tta.search.ItemRoute;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class AutoValue_SearchResultItem extends SearchResultItem {
    private final String id;
    private final ItemRoute itemRoute;
    private final String itemText;
    private final int type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SearchResultItem(String str, int i, String str2, @Nullable ItemRoute itemRoute) {
        if (str != null) {
            this.id = str;
            this.type = i;
            if (str2 != null) {
                this.itemText = str2;
                this.itemRoute = itemRoute;
                return;
            }
            throw new NullPointerException("Null itemText");
        }
        throw new NullPointerException("Null id");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SearchResultItem)) {
            return false;
        }
        SearchResultItem searchResultItem = (SearchResultItem) obj;
        if (this.id.equals(searchResultItem.getId()) && this.type == searchResultItem.getType() && this.itemText.equals(searchResultItem.getItemText())) {
            ItemRoute itemRoute = this.itemRoute;
            if (itemRoute == null) {
                if (searchResultItem.getItemRoute() == null) {
                    return true;
                }
            } else if (itemRoute.equals(searchResultItem.getItemRoute())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.SearchResultItem, com.amazon.alexa.voice.ui.tta.search.ResultItem
    public String getId() {
        return this.id;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.SearchResultItem
    @Nullable
    public ItemRoute getItemRoute() {
        return this.itemRoute;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.SearchResultItem
    public String getItemText() {
        return this.itemText;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.SearchResultItem, com.amazon.alexa.voice.ui.tta.search.ResultItem
    public int getType() {
        return this.type;
    }

    public int hashCode() {
        int hashCode = (((((this.id.hashCode() ^ 1000003) * 1000003) ^ this.type) * 1000003) ^ this.itemText.hashCode()) * 1000003;
        ItemRoute itemRoute = this.itemRoute;
        return hashCode ^ (itemRoute == null ? 0 : itemRoute.hashCode());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchResultItem{id=");
        outline107.append(this.id);
        outline107.append(", type=");
        outline107.append(this.type);
        outline107.append(", itemText=");
        outline107.append(this.itemText);
        outline107.append(", itemRoute=");
        outline107.append(this.itemRoute);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
