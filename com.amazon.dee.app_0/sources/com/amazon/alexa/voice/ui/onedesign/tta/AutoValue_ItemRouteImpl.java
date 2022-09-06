package com.amazon.alexa.voice.ui.onedesign.tta;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.tta.search.ActionType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class AutoValue_ItemRouteImpl extends ItemRouteImpl {
    private final String routeLink;
    private final ActionType routeType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ItemRouteImpl(String str, ActionType actionType) {
        if (str != null) {
            this.routeLink = str;
            if (actionType != null) {
                this.routeType = actionType;
                return;
            }
            throw new NullPointerException("Null routeType");
        }
        throw new NullPointerException("Null routeLink");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ItemRouteImpl)) {
            return false;
        }
        ItemRouteImpl itemRouteImpl = (ItemRouteImpl) obj;
        return this.routeLink.equals(itemRouteImpl.getRouteLink()) && this.routeType.equals(itemRouteImpl.getRouteType());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.ItemRouteImpl, com.amazon.alexa.voice.ui.tta.search.ItemRoute
    public String getRouteLink() {
        return this.routeLink;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.ItemRouteImpl, com.amazon.alexa.voice.ui.tta.search.ItemRoute
    public ActionType getRouteType() {
        return this.routeType;
    }

    public int hashCode() {
        return ((this.routeLink.hashCode() ^ 1000003) * 1000003) ^ this.routeType.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ItemRouteImpl{routeLink=");
        outline107.append(this.routeLink);
        outline107.append(", routeType=");
        outline107.append(this.routeType);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
