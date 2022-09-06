package com.amazon.clouddrive.cdasdk.dps.collections;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class ListCollectionsResponse {
    @JsonProperty("collections")
    private List<CollectionsResponse> collections;
    @JsonProperty("nextToken")
    private String nextToken;

    protected boolean canEqual(Object obj) {
        return obj instanceof ListCollectionsResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListCollectionsResponse)) {
            return false;
        }
        ListCollectionsResponse listCollectionsResponse = (ListCollectionsResponse) obj;
        if (!listCollectionsResponse.canEqual(this)) {
            return false;
        }
        List<CollectionsResponse> collections = getCollections();
        List<CollectionsResponse> collections2 = listCollectionsResponse.getCollections();
        if (collections != null ? !collections.equals(collections2) : collections2 != null) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = listCollectionsResponse.getNextToken();
        return nextToken != null ? nextToken.equals(nextToken2) : nextToken2 == null;
    }

    public List<CollectionsResponse> getCollections() {
        return this.collections;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        List<CollectionsResponse> collections = getCollections();
        int i = 43;
        int hashCode = collections == null ? 43 : collections.hashCode();
        String nextToken = getNextToken();
        int i2 = (hashCode + 59) * 59;
        if (nextToken != null) {
            i = nextToken.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("collections")
    public void setCollections(List<CollectionsResponse> list) {
        this.collections = list;
    }

    @JsonProperty("nextToken")
    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListCollectionsResponse(collections=");
        outline107.append(getCollections());
        outline107.append(", nextToken=");
        outline107.append(getNextToken());
        outline107.append(")");
        return outline107.toString();
    }
}
