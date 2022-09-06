package com.amazon.clouddrive.cdasdk.cds.source;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class ListSourcesResponse extends CloudDriveResponse {
    @JsonProperty("sources")
    private List<SourceInfo> sources;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    protected boolean canEqual(Object obj) {
        return obj instanceof ListSourcesResponse;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListSourcesResponse)) {
            return false;
        }
        ListSourcesResponse listSourcesResponse = (ListSourcesResponse) obj;
        if (!listSourcesResponse.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        List<SourceInfo> sources = getSources();
        List<SourceInfo> sources2 = listSourcesResponse.getSources();
        return sources != null ? sources.equals(sources2) : sources2 == null;
    }

    public List<SourceInfo> getSources() {
        return this.sources;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public int hashCode() {
        int hashCode = super.hashCode();
        List<SourceInfo> sources = getSources();
        return (hashCode * 59) + (sources == null ? 43 : sources.hashCode());
    }

    @JsonProperty("sources")
    public void setSources(List<SourceInfo> list) {
        this.sources = list;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveResponse
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListSourcesResponse(sources=");
        outline107.append(getSources());
        outline107.append(")");
        return outline107.toString();
    }
}
