package com.amazonaws.services.cognitoidentity.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class MergeDeveloperIdentitiesRequest extends AmazonWebServiceRequest implements Serializable {
    private String destinationUserIdentifier;
    private String developerProviderName;
    private String identityPoolId;
    private String sourceUserIdentifier;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MergeDeveloperIdentitiesRequest)) {
            return false;
        }
        MergeDeveloperIdentitiesRequest mergeDeveloperIdentitiesRequest = (MergeDeveloperIdentitiesRequest) obj;
        if ((mergeDeveloperIdentitiesRequest.getSourceUserIdentifier() == null) ^ (getSourceUserIdentifier() == null)) {
            return false;
        }
        if (mergeDeveloperIdentitiesRequest.getSourceUserIdentifier() != null && !mergeDeveloperIdentitiesRequest.getSourceUserIdentifier().equals(getSourceUserIdentifier())) {
            return false;
        }
        if ((mergeDeveloperIdentitiesRequest.getDestinationUserIdentifier() == null) ^ (getDestinationUserIdentifier() == null)) {
            return false;
        }
        if (mergeDeveloperIdentitiesRequest.getDestinationUserIdentifier() != null && !mergeDeveloperIdentitiesRequest.getDestinationUserIdentifier().equals(getDestinationUserIdentifier())) {
            return false;
        }
        if ((mergeDeveloperIdentitiesRequest.getDeveloperProviderName() == null) ^ (getDeveloperProviderName() == null)) {
            return false;
        }
        if (mergeDeveloperIdentitiesRequest.getDeveloperProviderName() != null && !mergeDeveloperIdentitiesRequest.getDeveloperProviderName().equals(getDeveloperProviderName())) {
            return false;
        }
        if ((mergeDeveloperIdentitiesRequest.getIdentityPoolId() == null) ^ (getIdentityPoolId() == null)) {
            return false;
        }
        return mergeDeveloperIdentitiesRequest.getIdentityPoolId() == null || mergeDeveloperIdentitiesRequest.getIdentityPoolId().equals(getIdentityPoolId());
    }

    public String getDestinationUserIdentifier() {
        return this.destinationUserIdentifier;
    }

    public String getDeveloperProviderName() {
        return this.developerProviderName;
    }

    public String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public String getSourceUserIdentifier() {
        return this.sourceUserIdentifier;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getSourceUserIdentifier() == null ? 0 : getSourceUserIdentifier().hashCode()) + 31) * 31) + (getDestinationUserIdentifier() == null ? 0 : getDestinationUserIdentifier().hashCode())) * 31) + (getDeveloperProviderName() == null ? 0 : getDeveloperProviderName().hashCode())) * 31;
        if (getIdentityPoolId() != null) {
            i = getIdentityPoolId().hashCode();
        }
        return hashCode + i;
    }

    public void setDestinationUserIdentifier(String str) {
        this.destinationUserIdentifier = str;
    }

    public void setDeveloperProviderName(String str) {
        this.developerProviderName = str;
    }

    public void setIdentityPoolId(String str) {
        this.identityPoolId = str;
    }

    public void setSourceUserIdentifier(String str) {
        this.sourceUserIdentifier = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getSourceUserIdentifier() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("SourceUserIdentifier: ");
            outline1072.append(getSourceUserIdentifier());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getDestinationUserIdentifier() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("DestinationUserIdentifier: ");
            outline1073.append(getDestinationUserIdentifier());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDeveloperProviderName() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("DeveloperProviderName: ");
            outline1074.append(getDeveloperProviderName());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getIdentityPoolId() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("IdentityPoolId: ");
            outline1075.append(getIdentityPoolId());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public MergeDeveloperIdentitiesRequest withDestinationUserIdentifier(String str) {
        this.destinationUserIdentifier = str;
        return this;
    }

    public MergeDeveloperIdentitiesRequest withDeveloperProviderName(String str) {
        this.developerProviderName = str;
        return this;
    }

    public MergeDeveloperIdentitiesRequest withIdentityPoolId(String str) {
        this.identityPoolId = str;
        return this;
    }

    public MergeDeveloperIdentitiesRequest withSourceUserIdentifier(String str) {
        this.sourceUserIdentifier = str;
        return this;
    }
}
