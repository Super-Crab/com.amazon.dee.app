package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DeleteIdentitiesRequest extends AmazonWebServiceRequest implements Serializable {
    private List<String> identityIdsToDelete;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteIdentitiesRequest)) {
            return false;
        }
        DeleteIdentitiesRequest deleteIdentitiesRequest = (DeleteIdentitiesRequest) obj;
        if ((deleteIdentitiesRequest.getIdentityIdsToDelete() == null) ^ (getIdentityIdsToDelete() == null)) {
            return false;
        }
        return deleteIdentitiesRequest.getIdentityIdsToDelete() == null || deleteIdentitiesRequest.getIdentityIdsToDelete().equals(getIdentityIdsToDelete());
    }

    public List<String> getIdentityIdsToDelete() {
        return this.identityIdsToDelete;
    }

    public int hashCode() {
        return 31 + (getIdentityIdsToDelete() == null ? 0 : getIdentityIdsToDelete().hashCode());
    }

    public void setIdentityIdsToDelete(Collection<String> collection) {
        if (collection == null) {
            this.identityIdsToDelete = null;
        } else {
            this.identityIdsToDelete = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIdentityIdsToDelete() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("IdentityIdsToDelete: ");
            outline1072.append(getIdentityIdsToDelete());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteIdentitiesRequest withIdentityIdsToDelete(String... strArr) {
        if (getIdentityIdsToDelete() == null) {
            this.identityIdsToDelete = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.identityIdsToDelete.add(str);
        }
        return this;
    }

    public DeleteIdentitiesRequest withIdentityIdsToDelete(Collection<String> collection) {
        setIdentityIdsToDelete(collection);
        return this;
    }
}
