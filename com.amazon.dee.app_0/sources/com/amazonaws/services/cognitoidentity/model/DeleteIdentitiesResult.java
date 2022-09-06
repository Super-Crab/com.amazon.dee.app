package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class DeleteIdentitiesResult implements Serializable {
    private List<UnprocessedIdentityId> unprocessedIdentityIds;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteIdentitiesResult)) {
            return false;
        }
        DeleteIdentitiesResult deleteIdentitiesResult = (DeleteIdentitiesResult) obj;
        if ((deleteIdentitiesResult.getUnprocessedIdentityIds() == null) ^ (getUnprocessedIdentityIds() == null)) {
            return false;
        }
        return deleteIdentitiesResult.getUnprocessedIdentityIds() == null || deleteIdentitiesResult.getUnprocessedIdentityIds().equals(getUnprocessedIdentityIds());
    }

    public List<UnprocessedIdentityId> getUnprocessedIdentityIds() {
        return this.unprocessedIdentityIds;
    }

    public int hashCode() {
        return 31 + (getUnprocessedIdentityIds() == null ? 0 : getUnprocessedIdentityIds().hashCode());
    }

    public void setUnprocessedIdentityIds(Collection<UnprocessedIdentityId> collection) {
        if (collection == null) {
            this.unprocessedIdentityIds = null;
        } else {
            this.unprocessedIdentityIds = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getUnprocessedIdentityIds() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("UnprocessedIdentityIds: ");
            outline1072.append(getUnprocessedIdentityIds());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DeleteIdentitiesResult withUnprocessedIdentityIds(UnprocessedIdentityId... unprocessedIdentityIdArr) {
        if (getUnprocessedIdentityIds() == null) {
            this.unprocessedIdentityIds = new ArrayList(unprocessedIdentityIdArr.length);
        }
        for (UnprocessedIdentityId unprocessedIdentityId : unprocessedIdentityIdArr) {
            this.unprocessedIdentityIds.add(unprocessedIdentityId);
        }
        return this;
    }

    public DeleteIdentitiesResult withUnprocessedIdentityIds(Collection<UnprocessedIdentityId> collection) {
        setUnprocessedIdentityIds(collection);
        return this;
    }
}
