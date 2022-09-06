package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class BillingGroupMetadata implements Serializable {
    private Date creationDate;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BillingGroupMetadata)) {
            return false;
        }
        BillingGroupMetadata billingGroupMetadata = (BillingGroupMetadata) obj;
        if ((billingGroupMetadata.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        return billingGroupMetadata.getCreationDate() == null || billingGroupMetadata.getCreationDate().equals(getCreationDate());
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public int hashCode() {
        return 31 + (getCreationDate() == null ? 0 : getCreationDate().hashCode());
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCreationDate() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1072.append(getCreationDate());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public BillingGroupMetadata withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }
}
