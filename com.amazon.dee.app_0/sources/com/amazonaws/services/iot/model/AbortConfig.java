package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class AbortConfig implements Serializable {
    private List<AbortCriteria> criteriaList;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AbortConfig)) {
            return false;
        }
        AbortConfig abortConfig = (AbortConfig) obj;
        if ((abortConfig.getCriteriaList() == null) ^ (getCriteriaList() == null)) {
            return false;
        }
        return abortConfig.getCriteriaList() == null || abortConfig.getCriteriaList().equals(getCriteriaList());
    }

    public List<AbortCriteria> getCriteriaList() {
        return this.criteriaList;
    }

    public int hashCode() {
        return 31 + (getCriteriaList() == null ? 0 : getCriteriaList().hashCode());
    }

    public void setCriteriaList(Collection<AbortCriteria> collection) {
        if (collection == null) {
            this.criteriaList = null;
        } else {
            this.criteriaList = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getCriteriaList() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("criteriaList: ");
            outline1072.append(getCriteriaList());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AbortConfig withCriteriaList(AbortCriteria... abortCriteriaArr) {
        if (getCriteriaList() == null) {
            this.criteriaList = new ArrayList(abortCriteriaArr.length);
        }
        for (AbortCriteria abortCriteria : abortCriteriaArr) {
            this.criteriaList.add(abortCriteria);
        }
        return this;
    }

    public AbortConfig withCriteriaList(Collection<AbortCriteria> collection) {
        setCriteriaList(collection);
        return this;
    }
}
