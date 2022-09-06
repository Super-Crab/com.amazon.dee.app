package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListIndicesResult implements Serializable {
    private List<String> indexNames;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListIndicesResult)) {
            return false;
        }
        ListIndicesResult listIndicesResult = (ListIndicesResult) obj;
        if ((listIndicesResult.getIndexNames() == null) ^ (getIndexNames() == null)) {
            return false;
        }
        if (listIndicesResult.getIndexNames() != null && !listIndicesResult.getIndexNames().equals(getIndexNames())) {
            return false;
        }
        if ((listIndicesResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listIndicesResult.getNextToken() == null || listIndicesResult.getNextToken().equals(getNextToken());
    }

    public List<String> getIndexNames() {
        return this.indexNames;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getIndexNames() == null ? 0 : getIndexNames().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setIndexNames(Collection<String> collection) {
        if (collection == null) {
            this.indexNames = null;
        } else {
            this.indexNames = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIndexNames() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("indexNames: ");
            outline1072.append(getIndexNames());
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

    public ListIndicesResult withIndexNames(String... strArr) {
        if (getIndexNames() == null) {
            this.indexNames = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.indexNames.add(str);
        }
        return this;
    }

    public ListIndicesResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListIndicesResult withIndexNames(Collection<String> collection) {
        setIndexNames(collection);
        return this;
    }
}
