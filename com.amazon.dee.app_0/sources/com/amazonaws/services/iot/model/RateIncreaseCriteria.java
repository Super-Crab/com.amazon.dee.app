package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class RateIncreaseCriteria implements Serializable {
    private Integer numberOfNotifiedThings;
    private Integer numberOfSucceededThings;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof RateIncreaseCriteria)) {
            return false;
        }
        RateIncreaseCriteria rateIncreaseCriteria = (RateIncreaseCriteria) obj;
        if ((rateIncreaseCriteria.getNumberOfNotifiedThings() == null) ^ (getNumberOfNotifiedThings() == null)) {
            return false;
        }
        if (rateIncreaseCriteria.getNumberOfNotifiedThings() != null && !rateIncreaseCriteria.getNumberOfNotifiedThings().equals(getNumberOfNotifiedThings())) {
            return false;
        }
        if ((rateIncreaseCriteria.getNumberOfSucceededThings() == null) ^ (getNumberOfSucceededThings() == null)) {
            return false;
        }
        return rateIncreaseCriteria.getNumberOfSucceededThings() == null || rateIncreaseCriteria.getNumberOfSucceededThings().equals(getNumberOfSucceededThings());
    }

    public Integer getNumberOfNotifiedThings() {
        return this.numberOfNotifiedThings;
    }

    public Integer getNumberOfSucceededThings() {
        return this.numberOfSucceededThings;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getNumberOfNotifiedThings() == null ? 0 : getNumberOfNotifiedThings().hashCode()) + 31) * 31;
        if (getNumberOfSucceededThings() != null) {
            i = getNumberOfSucceededThings().hashCode();
        }
        return hashCode + i;
    }

    public void setNumberOfNotifiedThings(Integer num) {
        this.numberOfNotifiedThings = num;
    }

    public void setNumberOfSucceededThings(Integer num) {
        this.numberOfSucceededThings = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getNumberOfNotifiedThings() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("numberOfNotifiedThings: ");
            outline1072.append(getNumberOfNotifiedThings());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNumberOfSucceededThings() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("numberOfSucceededThings: ");
            outline1073.append(getNumberOfSucceededThings());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public RateIncreaseCriteria withNumberOfNotifiedThings(Integer num) {
        this.numberOfNotifiedThings = num;
        return this;
    }

    public RateIncreaseCriteria withNumberOfSucceededThings(Integer num) {
        this.numberOfSucceededThings = num;
        return this;
    }
}
