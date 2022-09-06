package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListAuthorizersResult implements Serializable {
    private List<AuthorizerSummary> authorizers;
    private String nextMarker;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListAuthorizersResult)) {
            return false;
        }
        ListAuthorizersResult listAuthorizersResult = (ListAuthorizersResult) obj;
        if ((listAuthorizersResult.getAuthorizers() == null) ^ (getAuthorizers() == null)) {
            return false;
        }
        if (listAuthorizersResult.getAuthorizers() != null && !listAuthorizersResult.getAuthorizers().equals(getAuthorizers())) {
            return false;
        }
        if ((listAuthorizersResult.getNextMarker() == null) ^ (getNextMarker() == null)) {
            return false;
        }
        return listAuthorizersResult.getNextMarker() == null || listAuthorizersResult.getNextMarker().equals(getNextMarker());
    }

    public List<AuthorizerSummary> getAuthorizers() {
        return this.authorizers;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getAuthorizers() == null ? 0 : getAuthorizers().hashCode()) + 31) * 31;
        if (getNextMarker() != null) {
            i = getNextMarker().hashCode();
        }
        return hashCode + i;
    }

    public void setAuthorizers(Collection<AuthorizerSummary> collection) {
        if (collection == null) {
            this.authorizers = null;
        } else {
            this.authorizers = new ArrayList(collection);
        }
    }

    public void setNextMarker(String str) {
        this.nextMarker = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getAuthorizers() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("authorizers: ");
            outline1072.append(getAuthorizers());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextMarker() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextMarker: ");
            outline1073.append(getNextMarker());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListAuthorizersResult withAuthorizers(AuthorizerSummary... authorizerSummaryArr) {
        if (getAuthorizers() == null) {
            this.authorizers = new ArrayList(authorizerSummaryArr.length);
        }
        for (AuthorizerSummary authorizerSummary : authorizerSummaryArr) {
            this.authorizers.add(authorizerSummary);
        }
        return this;
    }

    public ListAuthorizersResult withNextMarker(String str) {
        this.nextMarker = str;
        return this;
    }

    public ListAuthorizersResult withAuthorizers(Collection<AuthorizerSummary> collection) {
        setAuthorizers(collection);
        return this;
    }
}
