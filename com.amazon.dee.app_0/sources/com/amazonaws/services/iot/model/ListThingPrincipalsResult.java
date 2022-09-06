package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListThingPrincipalsResult implements Serializable {
    private List<String> principals;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListThingPrincipalsResult)) {
            return false;
        }
        ListThingPrincipalsResult listThingPrincipalsResult = (ListThingPrincipalsResult) obj;
        if ((listThingPrincipalsResult.getPrincipals() == null) ^ (getPrincipals() == null)) {
            return false;
        }
        return listThingPrincipalsResult.getPrincipals() == null || listThingPrincipalsResult.getPrincipals().equals(getPrincipals());
    }

    public List<String> getPrincipals() {
        return this.principals;
    }

    public int hashCode() {
        return 31 + (getPrincipals() == null ? 0 : getPrincipals().hashCode());
    }

    public void setPrincipals(Collection<String> collection) {
        if (collection == null) {
            this.principals = null;
        } else {
            this.principals = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPrincipals() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("principals: ");
            outline1072.append(getPrincipals());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListThingPrincipalsResult withPrincipals(String... strArr) {
        if (getPrincipals() == null) {
            this.principals = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.principals.add(str);
        }
        return this;
    }

    public ListThingPrincipalsResult withPrincipals(Collection<String> collection) {
        setPrincipals(collection);
        return this;
    }
}
