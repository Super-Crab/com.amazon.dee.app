package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListTagsForResourceRequest extends AmazonWebServiceRequest implements Serializable {
    private String nextToken;
    private String resourceArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTagsForResourceRequest)) {
            return false;
        }
        ListTagsForResourceRequest listTagsForResourceRequest = (ListTagsForResourceRequest) obj;
        if ((listTagsForResourceRequest.getResourceArn() == null) ^ (getResourceArn() == null)) {
            return false;
        }
        if (listTagsForResourceRequest.getResourceArn() != null && !listTagsForResourceRequest.getResourceArn().equals(getResourceArn())) {
            return false;
        }
        if ((listTagsForResourceRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listTagsForResourceRequest.getNextToken() == null || listTagsForResourceRequest.getNextToken().equals(getNextToken());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getResourceArn() {
        return this.resourceArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getResourceArn() == null ? 0 : getResourceArn().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setResourceArn(String str) {
        this.resourceArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getResourceArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("resourceArn: ");
            outline1072.append(getResourceArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListTagsForResourceRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListTagsForResourceRequest withResourceArn(String str) {
        this.resourceArn = str;
        return this;
    }
}
