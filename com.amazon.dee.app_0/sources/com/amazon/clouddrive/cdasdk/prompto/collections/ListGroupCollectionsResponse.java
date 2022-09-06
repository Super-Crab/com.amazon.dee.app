package com.amazon.clouddrive.cdasdk.prompto.collections;

import com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class ListGroupCollectionsResponse extends PromptoServicePaginatedResponse {
    @JsonProperty("collections")
    private List<GroupCollectionResponse> collections;

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof ListGroupCollectionsResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListGroupCollectionsResponse)) {
            return false;
        }
        ListGroupCollectionsResponse listGroupCollectionsResponse = (ListGroupCollectionsResponse) obj;
        if (!listGroupCollectionsResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        List<GroupCollectionResponse> collections = getCollections();
        List<GroupCollectionResponse> collections2 = listGroupCollectionsResponse.getCollections();
        return collections != null ? collections.equals(collections2) : collections2 == null;
    }

    public List<GroupCollectionResponse> getCollections() {
        return this.collections;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        List<GroupCollectionResponse> collections = getCollections();
        return (hashCode * 59) + (collections == null ? 43 : collections.hashCode());
    }

    @JsonProperty("collections")
    public void setCollections(List<GroupCollectionResponse> list) {
        this.collections = list;
    }

    @Override // com.amazon.clouddrive.cdasdk.prompto.common.PromptoServicePaginatedResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListGroupCollectionsResponse(collections=");
        outline107.append(getCollections());
        outline107.append(")");
        return outline107.toString();
    }
}
