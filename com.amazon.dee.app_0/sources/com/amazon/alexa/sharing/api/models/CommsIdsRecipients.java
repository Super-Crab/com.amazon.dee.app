package com.amazon.alexa.sharing.api.models;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects;
/* loaded from: classes10.dex */
public class CommsIdsRecipients implements Recipients {
    public static final String RECIPIENT_TYPE_COMMS_IDS = "recipientCommsIds";
    @SerializedName("commsIds")
    private List<String> commsIds;
    @SerializedName("type")
    private String type = RECIPIENT_TYPE_COMMS_IDS;

    public CommsIdsRecipients(@NonNull List<String> list) {
        this.commsIds = null;
        this.commsIds = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CommsIdsRecipients.class != obj.getClass()) {
            return false;
        }
        List<String> list = this.commsIds;
        List<String> list2 = ((CommsIdsRecipients) obj).commsIds;
        if (list != null) {
            return list.equals(list2);
        }
        return list2 == null;
    }

    @Override // com.amazon.alexa.sharing.api.models.Recipients
    public String getType() {
        return RECIPIENT_TYPE_COMMS_IDS;
    }

    public int hashCode() {
        return Objects.hash(this.commsIds);
    }
}
